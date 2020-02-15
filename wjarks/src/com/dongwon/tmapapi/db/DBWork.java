package com.dongwon.tmapapi.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.dongwon.tmapapi.Delivery;
import com.dongwon.tmapapi.PathData;
import com.dongwon.tmapapi.TMapAPI;
import com.dongwon.tmapapi.exceptions.NoAPIKeyException;

public class DBWork {

	// --------------------------------------------------------------------------
	// 							테이블명
	// --------------------------------------------------------------------------
	public static String TABLE_DELIVERY = "DWNGL_ADM.ADMCMCLIENTDELIVERY_M1";
	public static String TABLE_TMAP = "DWNGL_TMS.TMSMSTTMAP_M0";
	public static String TABLE_ROUTE = "DWNGL_TMS.TMSMTFIXROUTE_D3";


	// --------------------------------------------------------------------------
	// 							배송처 목록 지오코딩
	// --------------------------------------------------------------------------
	public static void doGeocoding(DBModule adm, boolean all) throws NoAPIKeyException { doGeocoding(adm, all, ""); }
	public static void doGeocoding(final DBModule adm, boolean all, String condition) throws NoAPIKeyException{
		String sql = null;
		TMapAPI.logger.log(Level.INFO, "Call doGeocoding.");
		if(all)
			if(condition.length()>0)
				sql = ("SELECT CLIENT_CD, DELIVERY_ID, ADDR_BASIC FROM "+TABLE_DELIVERY+" WHERE "+condition + " ORDER BY DELIVERY_ID ASC");
			else
				sql = ("SELECT CLIENT_CD, DELIVERY_ID, ADDR_BASIC FROM "+TABLE_DELIVERY + " ORDER BY DELIVERY_ID ASC");
		else
			sql = ("SELECT CLIENT_CD, DELIVERY_ID, ADDR_BASIC FROM "+TABLE_DELIVERY+" WHERE (ADDR_X is null OR ADDR_X<=\'0\' OR ADDR_Y is null OR ADDR_Y<=\'0\') "+condition + " ORDER BY DELIVERY_ID ASC");
		final QueryResult res = adm.executeQuery(sql);
		final ResultSet rs = res.getResultSet();
		TMapAPI.logger.log(Level.INFO, "DB Query : "+sql);
		
		
		if(rs != null) {
			new Thread() {
				@Override
				public void run() {
					String sql = "";
					try {
						while(rs.next()) {
							String client = rs.getString(1);
							String code = rs.getString(2);
							String addr = rs.getString(3);
							double xy[] = TMapAPI.converyAddressStringToWGS84GEO(addr);
							TMapAPI.logger.info("------------- "+code);
							if(xy==null) {
								sql = String.format("UPDATE "+TABLE_DELIVERY+" SET ADDR_X=\'-1\', ADDR_Y=\'-1\' WHERE CLIENT_CD='%s' AND DELIVERY_ID=\'%s\'", client, code);
								adm.executeUpdate(sql);
								sql = String.format("MERGE INTO DWNGL_TMS.TMAP_ERROR S USING DUAL ON (S.CLIENT_CD = '%s' AND S.DELIVERY_ID = '%s')"+
								" WHEN NOT MATCHED THEN INSERT (S.CLIENT_CD, S.DELIVERY_ID, S.ADDR_BASIC) VALUES ('%s', '%s', '%s')", client, code, client, code, addr);
								adm.executeUpdate(sql);
								TMapAPI.logger.log(Level.WARNING, "잘못된 주소 형식 : "+addr);
								if (addr != null && addr.length() > 2)
									Thread.sleep(20);
								else 
									Thread.sleep(10);
								continue;
							}
							sql = String.format("UPDATE "+TABLE_DELIVERY+" SET ADDR_X=\'%f\', ADDR_Y=\'%f\' WHERE CLIENT_CD='%s' AND DELIVERY_ID=\'%s\'", xy[0], xy[1], client, code);
							adm.executeUpdate(sql);
							TMapAPI.logger.log(Level.INFO, "DB Updates : "+sql);
							Thread.sleep(20);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoAPIKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					TMapAPI.logger.log(Level.INFO, "Geocoding done.");
					res.close();
				};
			}.start();
			
		}
	}



	public static void doGeocodingByRoute(final DBModule adm, String fixRouteId) throws NoAPIKeyException{
		String sql = null;
		TMapAPI.logger.log(Level.INFO, "Call doGeocoding.");
		sql = String.format("SELECT DELIVERY_ID, ARR_SEQ, ADDR_BASIC, ADDR_X, ADDR_Y FROM ("
			+ "SELECT r.DELIVERY_ID, r.ARR_SEQ, d.DELIVERY_ID, d.ADDR_BASIC, d.ADDR_X, dd.ADDR_Y "
			+ "FROM "+TABLE_ROUTE+" r JOIN "+TABLE_DELIVERY+" d ON d.DELIVERY_ID = r.DELIVERY_ID WHERE r.FIX_ROUTE_ID = '%s') dd"
					+ "WHERE ADDR_BASIC is NOT null AND (ADDR_X is null OR ADDR_Y is null OR ADDR_X='0' OR ADDR_Y='0')", fixRouteId);

		final QueryResult res = adm.executeQuery(sql);
		final ResultSet rs = res.getResultSet();
		TMapAPI.logger.log(Level.INFO, "DB Query : "+sql);

		if(rs != null) {
			new Thread() {
				@Override
				public void run() {
					String sql = "";
					try {
						while(rs.next()) {
							String code = rs.getString(1);
							//int seq = rs.getInt(2);
							String addr = rs.getString(3);

							double xy[] = TMapAPI.converyAddressStringToWGS84GEO(addr);

							if(xy==null) {
								if(addr != null) {
									TMapAPI.logger.log(Level.WARNING, "잘못된 주소 형식 : "+addr);
									Thread.sleep(20);
								}
								continue;
							}
							sql = String.format("UPDATE "+TABLE_DELIVERY+" SET ADDR_X=\'%f\', ADDR_Y=\'%f\' WHERE DELIVERY_ID=\'%s\'", xy[0], xy[1], code);
							adm.executeUpdate(sql);
							TMapAPI.logger.log(Level.INFO, "DB Updates : "+sql);
							Thread.sleep(20);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoAPIKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					TMapAPI.logger.log(Level.INFO, "Geocoding done.");
					res.close();
				};
			}.start();
			
		}
	}

	public static void doGeocodingByRouteLike(final DBModule adm, String fixRouteId) throws NoAPIKeyException{
		String sql = null;
		TMapAPI.logger.log(Level.INFO, "Call doGeocoding.");
		sql = String.format("SELECT DELIVERY_ID, ARR_SEQ, ADDR_BASIC, FIX_ROUTE_ID FROM ("
			+ "SELECT r.DELIVERY_ID, r.ARR_SEQ, d.DELIVERY_ID, d.ADDR_BASIC, d.ADDR_X, d.ADDR_Y, r.FIX_ROUTE_ID "
			+ "FROM "+TABLE_ROUTE+" r JOIN "+TABLE_DELIVERY+" d ON d.DELIVERY_ID = r.DELIVERY_ID WHERE r.FIX_ROUTE_ID LIKE '%s') "
					+ "WHERE ADDR_BASIC is NOT null AND(ADDR_X is null OR ADDR_Y is null OR ADDR_X='0' OR ADDR_Y='0') ORDER BY FIX_ROUTE_ID", fixRouteId);

		final QueryResult res = adm.executeQuery(sql);
		final ResultSet rs = res.getResultSet();
		TMapAPI.logger.log(Level.INFO, "DB Query : "+sql);

		if(rs != null) {
			new Thread() {
				@Override
				public void run() {
					String sql = "";
					try {
						while(rs.next()) {
							String code = rs.getString(1);
							//int seq = rs.getInt(2);
							String addr = rs.getString(3);

							double xy[] = TMapAPI.converyAddressStringToWGS84GEO(addr);

							if(xy==null) {
								if(addr != null) {
									TMapAPI.logger.log(Level.WARNING, "잘못된 주소 형식 : "+addr);
									Thread.sleep(20);
								}
								continue;
							}
							sql = String.format("UPDATE "+TABLE_DELIVERY+" SET ADDR_X=\'%f\', ADDR_Y=\'%f\' WHERE DELIVERY_ID=\'%s\'", xy[0], xy[1], code);
							adm.executeUpdate(sql);
							TMapAPI.logger.log(Level.INFO, "DB Updates : "+sql);
							Thread.sleep(20);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoAPIKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					TMapAPI.logger.log(Level.INFO, "Geocoding done.");
					res.close();
				};
			}.start();
			
		}
	}

	public static void tmapTest(final DBModule tms) throws NoAPIKeyException{
		String sql = null;
		TMapAPI.logger.log(Level.INFO, "Call doGeocoding.");
		sql = String.format("SELECT TMAPTEST_ID, TMAPTEST_ADDR, TMAPTEST_RESULT FROM TMAPADDRTEST t ORDER BY TMAPTEST_ID");

		final QueryResult res = tms.executeQuery(sql);
		final ResultSet rs = res.getResultSet();
		TMapAPI.logger.log(Level.INFO, "DB Query : "+sql);

		if(rs != null) {
			new Thread() {
				@Override
				public void run() {
					String sql = "";
					try {
						while(rs.next()) {
							int id = rs.getInt(1);
							String addr = rs.getString(2);
							String result = rs.getString(3);

							double xy[] = TMapAPI.converyAddressStringToWGS84GEO(addr);

							if(xy==null) {
								sql = String.format("UPDATE TMAPADDRTEST SET TMAPTEST_RESULT='N' WHERE TMAPTEST_ID=\'%d\'", id);
								tms.executeUpdate(sql);
							}
							else {
								sql = String.format("UPDATE TMAPADDRTEST SET TMAPTEST_RESULT='Y' WHERE TMAPTEST_ID=\'%d\'", id);
								tms.executeUpdate(sql);
							}
							TMapAPI.logger.log(Level.INFO, "DB Updates : "+sql);
							Thread.sleep(20);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoAPIKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					TMapAPI.logger.log(Level.INFO, "Geocoding done.");
					res.close();
				};
			}.start();
			
		}
	}
	// --------------------------------------------------------------------------
	// 							노선 경유지간 점간거리 계산
	// --------------------------------------------------------------------------

	public static void generatePTPDataForRoute(DBModule adm, DBModule tms, final String routeId, final String centerr, boolean skipIfExists) throws NoAPIKeyException { generatePTPDataForRoute(adm, tms, routeId, centerr, skipIfExists, ""); }
	public static void generatePTPDataForRoute(DBModule adm, final DBModule tms, final String routeId, final String centerr, final boolean skipIfExists, String condition) throws NoAPIKeyException {
		String sql = null;
		TMapAPI.logger.log(Level.INFO, "Call generatePTPDataForRoute.");
		//sql = String.format("SELECT DELIVERY_ID, ADDR_BASIC, ADDR_X, ADDR_Y FROM "+TABLE_DELIVERY+" WHERE FIX_ROUTE_ID=\'%s\'", routeId);

		if(condition.length()>0)
			sql = String.format("SELECT DELIVERY_ID, ARR_SEQ, ADDR_BASIC, ADDR_X, ADDR_Y, CLIENT_CD FROM ("
			+ "SELECT r.DELIVERY_ID, r.ARR_SEQ, d.ADDR_BASIC, d.ADDR_X, d.ADDR_Y, d.CLIENT_CD "
			+ "FROM "+TABLE_ROUTE+" r JOIN "+TABLE_DELIVERY+" d ON d.DELIVERY_ID = r.DELIVERY_ID WHERE r.FIX_ROUTE_ID = '%s') "
					+ "WHERE ADDR_X is not null AND ADDR_Y is not null AND ADDR_X > 10.0 AND ADDR_Y > 10.0 AND CLIENT_CD='1247' AND %s", routeId, condition);


		else
			sql = String.format("SELECT DELIVERY_ID, ARR_SEQ, ADDR_BASIC, ADDR_X, ADDR_Y, CLIENT_CD FROM ("
			+ "SELECT r.DELIVERY_ID, r.ARR_SEQ, d.ADDR_BASIC, d.ADDR_X, d.ADDR_Y, d.CLIENT_CD "
			+ "FROM "+TABLE_ROUTE+" r JOIN "+TABLE_DELIVERY+" d ON d.DELIVERY_ID = r.DELIVERY_ID WHERE r.FIX_ROUTE_ID = '%s') "
					+ "WHERE ADDR_X is not null AND ADDR_Y is not null AND ADDR_X > 10.0 AND ADDR_Y > 10.0 AND CLIENT_CD='1247'", routeId);
	final QueryResult res = adm.executeQuery(sql);
		final ResultSet rs = res.getResultSet();
		TMapAPI.logger.log(Level.INFO, "DB Query : "+sql);
		
		final List<Delivery> dlist = new ArrayList<>();
		
		if(rs != null) {
			try {
				while(rs.next()) {
					String code = rs.getString(1);
					//int seq = rs.getInt(2);
					String addr = rs.getString(3);
					double x = rs.getDouble(4);
					double y = rs.getDouble(5);
					
					if(x == 0 || y == 0) {
						TMapAPI.logger.log(Level.WARNING, "failed to generate PTPData : null coordinates found. doGeocoding first.");
						continue;
					}
					
					dlist.add(new Delivery(code, addr, x, y));
					
					TMapAPI.logger.log(Level.INFO, "added "+code+" to list : ");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		res.close();
		
		
		new Thread() {
			@Override
			public void run() {
				String sql = "";
				try {
					for(int i=0;i<dlist.size()-1;i++) {
						for(int j=i+1;j<dlist.size();j++) {
							Delivery start = dlist.get(i);
							Delivery end = dlist.get(j);

							Delivery center = null;
							sql = String.format("SELECT ctm0.CENTER_CD, ctm0.ADDR_X, ctm0.ADDR_Y "+
							"FROM DWNGL_ADM.ADMCMCLIENTDELIVERY_M1 cdm1 "+
							"JOIN DWNGL_ADM.ADMCMCENTER_M0 ctm0 "+
							"ON ctm0.CENTER_CD='"+centerr+"' "+
							"AND cdm1.CLIENT_CD='1247' "+
							"WHERE cdm1.DELIVERY_ID='%s'", start.getCode());

							QueryResult res = tms.executeQuery(sql);
							ResultSet rs = res.getResultSet();
							if(rs.next()) {
								center = new Delivery(rs.getString(1), "", rs.getDouble(2), rs.getDouble(3));
							}
							else {
								TMapAPI.logger.log(Level.INFO, "센터가 지정되지 않음 "+start.getCode());
								res.close();
								continue;
							}
							res.close();
							
							sql = String.format("SELECT TMAP_CD FROM "+TABLE_TMAP+" "
									+ "WHERE (DELIVERY_START_ID=\'%s\' AND DELIVERY_END_ID=\'%s') "
									+ "OR (DELIVERY_START_ID=\'%s\' AND DELIVERY_END_ID=\'%s')", start.getCode(), end.getCode(), end.getCode(), start.getCode());
							
							res = tms.executeQuery(sql);
							rs = res.getResultSet();
							TMapAPI.logger.log(Level.INFO, sql);
							if(rs.next()) { // exists
								if(skipIfExists) {
									TMapAPI.logger.log(Level.INFO, "skip ptp "+start.getCode()+" to "+end.getCode());
									res.close();
									continue;
								}

								PathData pd = TMapAPI.getPathData(start, end);
								long distance = pd.getDistance();
								long time = pd.getTime();
								TMapAPI.logger.log(Level.INFO, "get distance from "+start.getCode()+" to "+end.getCode()+" : "+distance);
								
								sql = String.format("UPDATE "+TABLE_TMAP+" "
										+ "SET DISTANCE=\'%d\', TIME=\'%d\' "
										+ "WHERE (DELIVERY_START_ID=\'%s\' AND DELIVERY_END_ID=\'%s') "
										+ "OR (DELIVERY_START_ID=\'%s\' AND DELIVERY_END_ID=\'%s')", distance, time, start.getCode(), end.getCode(), end.getCode(), start.getCode());
								
							} else {
								PathData pd = TMapAPI.getPathData(start, end);
								long distance = pd.getDistance();
								long time = pd.getTime();
								long center_distance = -1;
								long delivery_distance = -1;

								sql = String.format("SELECT DELIVERY_START_ID, DELIVERY_END_ID, CENTER_DISTANCE, DELIVERY_DISTANCE "
													+"FROM "+TABLE_TMAP+" "
													+"WHERE DELIVERY_START_ID='%s' OR DELIVERY_END_ID='%s'", start.getCode(), start.getCode());
								QueryResult qr = tms.executeQuery(sql);
								if(qr.getResultSet().next()){
									ResultSet ress = qr.getResultSet();
									if (start.getCode().equals(ress.getString(1))){
										center_distance = ress.getLong(3);
									}
									else if (start.getCode().equals(ress.getString(2))){
										center_distance = ress.getLong(4);
									}

									if (end.getCode().equals(ress.getString(1))){
										delivery_distance = ress.getLong(3);
									}
									else if (end.getCode().equals(ress.getString(2))){
										delivery_distance = ress.getLong(4);
									}
								}
								qr.close();

								if (center_distance == -1){
									Thread.sleep(20);
									PathData path = TMapAPI.getPathData(center, start);
									if(path == null) continue;
									center_distance = path.getDistance();
									TMapAPI.logger.log(Level.INFO, "calculate center distance "+start.getCode()+" to "+center.getCode()+" : "+center_distance);
								}
								if (delivery_distance == -1){
									Thread.sleep(20);
									PathData path = TMapAPI.getPathData(center, end);
									if(path == null) continue;
									delivery_distance = path.getDistance();
									TMapAPI.logger.log(Level.INFO, "calculate delivery distance "+center.getCode()+" to "+end.getCode()+" : "+delivery_distance);
								}

								TMapAPI.logger.log(Level.INFO, "get distance from "+start.getCode()+" to "+end.getCode()+" : "+distance);
								
								sql = String.format(
									"INSERT INTO "+TABLE_TMAP+" "
										+ "(TMAP_CD, FIX_ROUTE_ID, CENTER_CD, DELIVERY_START_ID, DELIVERY_END_ID, DISTANCE, CENTER_DISTANCE, DELIVERY_DISTANCE, TIME) "
										+ "VALUES(TMAP_CD_SEQ.NEXTVAL, \'%s\', \'%s\', \'%s\', \'%s\', \'%d\', \'%d\', \'%d\', \'%d\')"
										, routeId, center.getCode(), end.getCode(), start.getCode(), distance, center_distance, delivery_distance, time);
								tms.executeUpdate(sql);
								sql = String.format(
									"INSERT INTO "+TABLE_TMAP+" "
									+ "(TMAP_CD, FIX_ROUTE_ID, CENTER_CD, DELIVERY_START_ID, DELIVERY_END_ID, DISTANCE, CENTER_DISTANCE, DELIVERY_DISTANCE, TIME) "
									+ "VALUES(TMAP_CD_SEQ.NEXTVAL, \'%s\', \'%s\', \'%s\', \'%s\', \'%d\', \'%d\', \'%d\', \'%d\')"
									, routeId, center.getCode(), start.getCode(), end.getCode(), distance, delivery_distance, center_distance, time);
							}
							
							res.close();
							
							tms.executeUpdate(sql);
							TMapAPI.logger.log(Level.INFO, "DB Updates : "+sql);
							Thread.sleep(20);
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoAPIKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TMapAPI.logger.log(Level.INFO, "generate PTPData done.");
			};
		}.start();
	}

	// --------------------------------------------------------------------------
	// 							노선 경유지간 점간거리 계산
	// --------------------------------------------------------------------------

	public static void generatePTPDataForCenter(DBModule adm, DBModule tms, final String centerCd, boolean skipIfExists) throws NoAPIKeyException { generatePTPDataForCenter(adm, tms, centerCd, skipIfExists, ""); }
	public static void generatePTPDataForCenter(final DBModule adm, final DBModule tms, final String centerCd, final boolean skipIfExists, String condition) throws NoAPIKeyException {
		String sql = null;
		TMapAPI.logger.log(Level.INFO, "Call generatePTPDataForCenter.");
		//sql = String.format("SELECT DELIVERY_ID, ADDR_BASIC, ADDR_X, ADDR_Y FROM "+TABLE_DELIVERY+" WHERE FIX_ROUTE_ID=\'%s\'", routeId);

		if(condition.length()>0)
			sql = String.format("SELECT CENTER_CD, FIX_ROUTE_ID, FIX_ROUTE_NM "
				+ "FROM DWNGL_TMS.TMSMTFIXROUTE_M1 "
				+ "WHERE USE_YN='Y' AND NOT REGEXP_LIKE(FIX_ROUTE_NM, '테스트|임시|test|배차이동|미사용|용차사용|이동거래|종료|예비|이고|없는|배송차량') AND CENTER_CD='%s' AND "+condition+" ORDER BY FIX_ROUTE_ID ASC", centerCd);
		else
			sql = String.format("SELECT CENTER_CD, FIX_ROUTE_ID, FIX_ROUTE_NM "
			+ "FROM DWNGL_TMS.TMSMTFIXROUTE_M1 "
			+ "WHERE USE_YN='Y' AND NOT REGEXP_LIKE(FIX_ROUTE_NM, '테스트|임시|test|배차이동|미사용|용차사용|이동거래|종료|예비|이고|없는|배송차량') AND CENTER_CD='%s' ORDER BY FIX_ROUTE_ID ASC", centerCd);
		
		final QueryResult res = adm.executeQuery(sql);
		final ResultSet rs = res.getResultSet();
		TMapAPI.logger.log(Level.INFO, "DB Query : "+sql);
		
		final List<String> routes = new ArrayList<>();
		
		if(rs != null) {
			try {
				while(rs.next()) {
					String routeId = rs.getString(2);
					String routeNm = rs.getString(3);
					
					routes.add(routeId);

					TMapAPI.logger.log(Level.INFO, "added "+routeNm+"("+routeId+") to list");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		res.close();
		
		
		new Thread() {
			@Override
			public void run() {
				
				String sql = "";
				try {
					for (String routeId : routes) {
						sql = String.format("SELECT DELIVERY_ID, ARR_SEQ, ADDR_BASIC, ADDR_X, ADDR_Y, CLIENT_CD FROM ("
						+ "SELECT r.DELIVERY_ID, r.ARR_SEQ, d.ADDR_BASIC, d.ADDR_X, d.ADDR_Y, d.CLIENT_CD "
						+ "FROM "+TABLE_ROUTE+" r JOIN "+TABLE_DELIVERY+" d ON d.DELIVERY_ID = r.DELIVERY_ID WHERE r.FIX_ROUTE_ID = '%s' AND d.DEAL_DIV='1') "
								+ "WHERE ADDR_X is not null AND ADDR_Y is not null AND ADDR_X > 10.0 AND ADDR_Y > 10.0 AND CLIENT_CD='1247' ", routeId);
						final QueryResult res = adm.executeQuery(sql);
						final ResultSet rs = res.getResultSet();
						TMapAPI.logger.log(Level.INFO, "DB Query : "+sql);
						
						List<Delivery> dlist = new ArrayList<>();
						
						if(rs != null) {
							try {
								while(rs.next()) {
									String code = rs.getString(1);
									//int seq = rs.getInt(2);
									String addr = rs.getString(3);
									double x = rs.getDouble(4);
									double y = rs.getDouble(5);
									
									if(x == 0 || y == 0) {
										TMapAPI.logger.log(Level.WARNING, "failed to generate PTPData : null coordinates found. doGeocoding first.");
										continue;
									}
									
									dlist.add(new Delivery(code, addr, x, y));
									
									TMapAPI.logger.log(Level.INFO, "added "+code+" to list : ");
								}
								int k = 0;
								for(int i=0;i<dlist.size()-1;i++) {
									for(int j=i+1;j<dlist.size();j++) {
										Delivery start = dlist.get(i);
										Delivery end = dlist.get(j);
										System.out.println("-----------------------------------------------------------------");
										System.out.println("노     선 : "+routeId+", 배송처 수 : "+dlist.size());
										System.out.println("      i  : "+i+"   ,   j : "+j);
										System.out.println("점간 데이터 : "+(k)+" / "+(dlist.size()*(dlist.size()-1)/2));
										System.out.println("-----------------------------------------------------------------");
										k++;
										Delivery center = null;

										sql = String.format("SELECT ctm0.CENTER_CD, ctm0.ADDR_X, ctm0.ADDR_Y "+
										"FROM DWNGL_ADM.ADMCMCLIENTDELIVERY_M1 cdm1 "+
										"JOIN DWNGL_ADM.ADMCMCENTER_M0 ctm0 "+
										"ON ctm0.CENTER_CD='"+centerCd+"' "+
										"AND cdm1.CLIENT_CD='1247' "+
										"WHERE cdm1.DELIVERY_ID='%s'", start.getCode());
			
										QueryResult ress = tms.executeQuery(sql);
										ResultSet rss = ress.getResultSet();
										if(rss.next()) {
											center = new Delivery(rss.getString(1), "", rss.getDouble(2), rss.getDouble(3));
										}
										else {
											TMapAPI.logger.log(Level.INFO, "센터가 지정되지 않음 "+start.getCode());
											continue;
										}
										ress.close();
										
										sql = String.format("SELECT TMAP_CD FROM "+TABLE_TMAP+" "
												+ "WHERE (DELIVERY_START_ID=\'%s\' AND DELIVERY_END_ID=\'%s') "
												+ "OR (DELIVERY_START_ID=\'%s\' AND DELIVERY_END_ID=\'%s')", start.getCode(), end.getCode(), end.getCode(), start.getCode());
										
										ress = tms.executeQuery(sql);
										rss = ress.getResultSet();
										TMapAPI.logger.log(Level.INFO, sql);
										if(rss.next()) { // exists
											if(skipIfExists) {
												TMapAPI.logger.log(Level.INFO, "skip ptp "+start.getCode()+" to "+end.getCode());
												ress.close();
												continue;
											}
			
											PathData pd = TMapAPI.getPathData(start, end);
											long distance = pd.getDistance();
											long time = pd.getTime();
											TMapAPI.logger.log(Level.INFO, "get distance from "+start.getCode()+" to "+end.getCode()+" : "+distance);
											
											sql = String.format("UPDATE "+TABLE_TMAP+" "
													+ "SET DISTANCE=\'%d\', TIME=\'%d\' "
													+ "WHERE (DELIVERY_START_ID=\'%s\' AND DELIVERY_END_ID=\'%s') "
													+ "OR (DELIVERY_START_ID=\'%s\' AND DELIVERY_END_ID=\'%s')", distance, time, start.getCode(), end.getCode(), end.getCode(), start.getCode());
											
										} else {
											PathData pd = TMapAPI.getPathData(start, end);
											long distance = pd.getDistance();
											long time = pd.getTime();
											long center_distance = -1;
											long delivery_distance = -1;
			
											sql = String.format("SELECT DELIVERY_START_ID, DELIVERY_END_ID, CENTER_DISTANCE, DELIVERY_DISTANCE "
																+"FROM "+TABLE_TMAP+" "
																+"WHERE DELIVERY_START_ID='%s' OR DELIVERY_END_ID='%s'", start.getCode(), end.getCode());
											QueryResult qr = tms.executeQuery(sql);
											if(qr.getResultSet().next()){
												ResultSet resss = qr.getResultSet();
												if (start.getCode().equals(resss.getString(1))){
													center_distance = resss.getLong(3);
												}
												else if (start.getCode().equals(resss.getString(2))){
													center_distance = resss.getLong(4);
												}
			
												if (end.getCode().equals(resss.getString(1))){
													delivery_distance = resss.getLong(3);
												}
												else if (end.getCode().equals(resss.getString(2))){
													delivery_distance = resss.getLong(4);
												}
											}
											qr.close();
			
											if (center_distance == -1){
												Thread.sleep(20);
												PathData path = TMapAPI.getPathData(center, start);
												if(path == null) continue;
												center_distance = path.getDistance();
												TMapAPI.logger.log(Level.INFO, "calculate center distance "+start.getCode()+" to "+center.getCode()+" : "+center_distance);
											}
											if (delivery_distance == -1){
												Thread.sleep(20);
												PathData path = TMapAPI.getPathData(center, end);
												if(path == null) continue;
												delivery_distance = path.getDistance();
												TMapAPI.logger.log(Level.INFO, "calculate delivery distance "+center.getCode()+" to "+end.getCode()+" : "+delivery_distance);
											}
			
											TMapAPI.logger.log(Level.INFO, "get distance from "+start.getCode()+" to "+end.getCode()+" : "+distance);
											
											sql = String.format(
												"INSERT INTO "+TABLE_TMAP+" "
													+ "(TMAP_CD, FIX_ROUTE_ID, CENTER_CD, DELIVERY_START_ID, DELIVERY_END_ID, DISTANCE, CENTER_DISTANCE, DELIVERY_DISTANCE, TIME) "
													+ "VALUES(TMAP_CD_SEQ.NEXTVAL, \'%s\', \'%s\', \'%s\', \'%s\', \'%d\', \'%d\', \'%d\', \'%d\')"
													, routeId, centerCd, end.getCode(), start.getCode(), distance, center_distance, delivery_distance, time);
											tms.executeUpdate(sql);
											sql = String.format(
												"INSERT INTO "+TABLE_TMAP+" "
												+ "(TMAP_CD, FIX_ROUTE_ID, CENTER_CD, DELIVERY_START_ID, DELIVERY_END_ID, DISTANCE, CENTER_DISTANCE, DELIVERY_DISTANCE, TIME) "
												+ "VALUES(TMAP_CD_SEQ.NEXTVAL, \'%s\', \'%s\', \'%s\', \'%s\', \'%d\', \'%d\', \'%d\', \'%d\')"
												, routeId, centerCd, start.getCode(), end.getCode(), distance, delivery_distance, center_distance, time);
										}
										
										ress.close();
										
										tms.executeUpdate(sql);
										TMapAPI.logger.log(Level.INFO, "DB Updates : "+sql);
										
										Thread.sleep(20);
									}

								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
						res.close();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoAPIKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TMapAPI.logger.log(Level.INFO, "generate PTPData done.");
			};
		}.start();
	}
}
