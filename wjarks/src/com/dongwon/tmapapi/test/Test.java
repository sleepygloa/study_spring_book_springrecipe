package com.dongwon.tmapapi.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dongwon.tmapapi.TMapAPI;
import com.dongwon.tmapapi.db.DBWork;
import com.dongwon.tmapapi.db.QueryResult;
import com.dongwon.tmapapi.exceptions.NoAPIKeyException;
import com.dongwon.tmapapi.db.DBModule;

public class Test {
	// https://apis.openapi.sk.com/tmap/geo/reversegeocoding?version=1&lat=37.116755&lon=126.914341&addressType=A00&appKey=eda0e9dc-d44e-4bd9-b9a9-2591f79bab40
	public static void main(String[] args) throws NoAPIKeyException, SQLException {
		TMapAPI.setAPIKey("l7xx07e0102ff8f141c3af6ce8fe82beacd7");
		// d5fd32c0-b930-409e-ab94-eb697cf70b85
		// aaf4aa99-d6d1-4da4-8c7d-20059559a87a

		// SELECT FIX_ROUTE_ID, COUNT(*) FROM TMSMTFIXROUTE_D3 GROUP BY FIX_ROUTE_ID

		DBModule adm = DBModule.builder().host("172.20.13.81:9991").id("DWNGL_TMS").pw("DWNGL_TMS").db("DWNGL_ADM")
				.sid("NLMSDEV1").build();
		DBModule tms = DBModule.builder().host("172.20.13.81:9991").id("DWNGL_TMS").pw("DWNGL_TMS").db("DWNGL_TMS")
				.sid("NLMSDEV1").build();
		// DBModule lms = DBModule.builder().host("172.20.13.83:9991").id("PJ_HELO").pw("DWNGL_TMS").db("PJ_HELO")
		// 		.sid("NLMSDEV2").build();



		// DBWork.generatePTPDataForCenter(adm, tms, "2600DC", true);
			// DBWork.doGeocoding(adm, false, " AND DELIVERY_ID IS NOT NULL AND CLIENT_CD='1247' AND NVL(ADDR_X, 0) <= 0 AND LENGTH(NVL(ADDR_BASIC, '')) > 2"); // 140684
		// DBWork.generatePTPDataForRoute(adm, tms, "2100-126", "2100DC", true);
		DBWork.generatePTPDataForCenter(adm, tms, "2600DC", true, " FIX_ROUTE_ID >= '2600-027'");  // 
			
			// ZIP_CD 없는거는 나중에 한번에 오류테이블에 넣음 !

			// String sql = "SELECT a.*, m1.FIX_ROUTE_NM FROM (SELECT FIX_ROUTE_ID, COUNT(*) cnt FROM TMSMTFIXROUTE_D3 WHERE CENTER_CD>='2100DC' AND CENTER_CD<='2600DC' GROUP BY FIX_ROUTE_ID) a LEFT JOIN TMSMTFIXROUTE_M1 m1 ON m1.FIX_ROUTE_ID=a.FIX_ROUTE_ID WHERE m1.FIX_ROUTE_NM NOT LIKE '%종료%'AND m1.FIX_ROUTE_NM NOT LIKE '배송차량' ORDER BY a.cnt DESC";
			// final QueryResult res = tms.executeQuery(sql);
			// final ResultSet rs = res.getResultSet();

			// int amountRequest = 0;
			// if (rs != null) {
			// 	while (rs.next()) {
			// 		String route = rs.getString(1);
			// 		int count = rs.getInt(2);
			// 		int req = count*(count-1)/2;
			// 		TMapAPI.logger.info(route+": "+count+" -> "+req);
			// 		amountRequest += count*(count-1)/2;
			// 	}
			// }

			// TMapAPI.logger.info("Total Request : "+amountRequest); // 82,241,585
	}
	
}

