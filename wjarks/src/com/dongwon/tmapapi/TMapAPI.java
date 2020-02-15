package com.dongwon.tmapapi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.dongwon.tmapapi.exceptions.NoAPIKeyException;

public class TMapAPI {
	
	private static String API_KEY = null;
	private static String baseUrl = "https://apis.openapi.sk.com/tmap/";
	public static Logger logger = Logger.getLogger("DongwonTMapAPI");

	public static void setAPIKey(String key) {
		API_KEY = key;
	}
	
	// ----------------------------------------------------------
	//                 REST Post 요청 
	// ----------------------------------------------------------
	private static Document apiPostCall(String uri, String content) throws NoAPIKeyException {
		if(API_KEY == null) {
			throw new NoAPIKeyException();
		}
        Document doc = null;
        String url = uri+"&"+content;
        url = url.replace(" ", "%20");
        TMapAPI.logger.log(Level.INFO, "RequestPOST : "+url);
        try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("appKey", API_KEY);

			if (conn.getResponseCode() == 400) {
				TMapAPI.logger.log(Level.WARNING, "잘못된 데이터 요청 Response:400");
				return null;
			}
			DocumentBuilder dom = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			
			doc = dom.parse(new InputSource(isr));
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return doc;
    }
	
	// ----------------------------------------------------------
	//                 REST GET 요청  
	// ----------------------------------------------------------
	private static Document apiGetCall(String uri, String content) throws NoAPIKeyException {
		if(API_KEY == null) {
			throw new NoAPIKeyException();
		}
        Document doc = null;
        String url = uri+"&"+content;
        TMapAPI.logger.log(Level.INFO, "RequestGET : "+url);
        try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("appKey", API_KEY);

			if (conn.getResponseCode() == 400) {
				TMapAPI.logger.log(Level.WARNING, "잘못된 데이터 요청 Response:400");
				return null;
			}
			DocumentBuilder dom = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			
			doc = dom.parse(new InputSource(isr));
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return doc;
    }
	
	// ----------------------------------------------------------
	//                  거리, 시간, 요금 데이터 
	// ----------------------------------------------------------
	public static PathData getPathData(Delivery from, Delivery to) throws NoAPIKeyException {
        TMapAPI.logger.log(Level.INFO, "Call getPathData");
		if(API_KEY == null) {
			throw new NoAPIKeyException();
		}
		StringBuilder uri = new StringBuilder();
		uri.append(baseUrl);
		uri.append("routes?version=1");

		if(from.getX() < 10 || from.getY() < 10 || to.getX() < 10 || to.getY() < 10) {
			System.out.println("error "+from.getCode()+" -> "+to.getY());
			return null;
		}
				
		StringBuilder content = new StringBuilder();
		content.append("totalValue=2&reqCoordType=WGS84GEO&resCoordType=WGS84GEO&format=xml");
		content.append("&startX=").append(from.getX());
		content.append("&startY=").append(from.getY());
		content.append("&endX=").append(to.getX());
		content.append("&endY=").append(to.getY());
				
		Document doc = apiPostCall(uri.toString(), content.toString());
				
		PathData pd = null;
				
		if(doc != null) {
			pd = new PathData();
			NodeList list = doc.getElementsByTagName("tmap:totalDistance");
			if (list.getLength() == 1) {
				pd.setDistance(Long.parseLong(list.item(0).getFirstChild().getNodeValue()));
			}
			
			list = doc.getElementsByTagName("tmap:totalTime");
			if (list.getLength() == 1) {
				pd.setTime(Long.parseLong(list.item(0).getFirstChild().getNodeValue()));
			}
			
			list = doc.getElementsByTagName("tmap:totalFare");
			if (list.getLength() == 1) {
				pd.setFare(Long.parseLong(list.item(0).getFirstChild().getNodeValue()));
			}
		}
		return pd;
	}

	// ----------------------------------------------------------
	//                  배송지 주소 지오코딩 
	// ----------------------------------------------------------

/* 	newRoadAddr = arrResult.city_do + ' ' + arrResult.gu_gun + ' '; 
			
			if(arrResult.eup_myun == '' && (lastLegal=="읍"||lastLegal=="면")){//읍면
				newRoadAddr +=  arrResult.legalDong;	
			}else{
				newRoadAddr +=  arrResult.eup_myun;
			}
			newRoadAddr += ' ' +arrResult.roadName + ' ' + arrResult.buildingIndex;
			
			// 새주소 법정동& 건물명 체크
			if(arrResult.legalDong != '' && (lastLegal!="읍"&&lastLegal!="면")){//법정동과 읍면이 같은 경우
				
				if(arrResult.buildingName != ''){//빌딩명 존재하는 경우
					newRoadAddr +=  (' (' + arrResult.legalDong + ', ' +arrResult.buildingName +') ');
				}else{
					newRoadAddr += (' (' + arrResult.legalDong + ')');
				}
			}else if(arrResult.buildingName != ''){//빌딩명만 존재하는 경우
				newRoadAddr +=  (' (' + arrResult.buildingName +') ');
			}
			
			// 구주소
			jibunAddr = arrResult.city_do + ' ' + arrResult.gu_gun + ' ' + arrResult.legalDong + ' '+ arrResult.ri + ' ' + arrResult.bunji;
			//구주소 빌딩명 존재
			if(arrResult.buildingName != ''){//빌딩명만 존재하는 경우
				jibunAddr +=  (' '+arrResult.buildingName);
			}
			 */
	public static double[] converyAddressStringToWGS84GEO(String address) throws NoAPIKeyException {
        TMapAPI.logger.log(Level.INFO, "Call converyAddressStringToWGS84GEO");
		if(API_KEY == null) {
			throw new NoAPIKeyException();
		}
		if (address == null) return null;
		if (address.length() <= 2) return null;
 
        address = address.replaceAll("&", "&amp;");
		StringBuilder uri = new StringBuilder();
		uri.append(baseUrl);
		uri.append("geo/fullAddrGeo?&version=1&format=xml");
				
		StringBuilder content = new StringBuilder();
		content.append("addressFlag=F00"); 
		content.append("&coordType=WGS84GEO");
		try {
			content.append("&fullAddr=").append(URLEncoder.encode(address, "UTF-8"));
		} catch (UnsupportedEncodingException e) {}
			catch(NullPointerException e) {
				TMapAPI.logger.log(Level.WARNING, "주소가 입력되지 않음");
				return null;
			}
		Document doc = apiGetCall(uri.toString(), content.toString());
				
		if(doc != null) {
			double wgs84geo[] = {0, 0};

			NodeList list = doc.getElementsByTagName("newLon");
			
			if(list.getLength() > 0) {
				wgs84geo[0] = Double.parseDouble(list.item(0).getFirstChild().getNodeValue());
				list = doc.getElementsByTagName("newLat");
				wgs84geo[1] = Double.parseDouble(list.item(0).getFirstChild().getNodeValue());
			}
			else {
				list = doc.getElementsByTagName("lon");
				wgs84geo[0] = Double.parseDouble(list.item(0).getFirstChild().getNodeValue());
				list = doc.getElementsByTagName("lat");
				wgs84geo[1] = Double.parseDouble(list.item(0).getFirstChild().getNodeValue());
			}
			return wgs84geo;
		}
		else {
			TMapAPI.logger.info("doc NULL 임");
		}
		return null;
	}
}
