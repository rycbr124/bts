package bts.h.p001;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class test {
	
	private static String URL = "https://www.hotelscombined.co.kr/Hotels/Search?";
	
	public static void main(String[] args) throws IOException {
		
		String KEY_WORD = "place";
		System.out.println("URL :: " + URL + getParameter(KEY_WORD, 2));
		//1. DOcument를 가져온다
		Document doc = Jsoup.connect(URL + getParameter(KEY_WORD, 2)).get();
		
		//2.목록을 가져온다.
		//System.out.println("" + doc.toString());
		Elements elements = doc.select(".hc-searchresultitem .hc-searchresultitem__hotelsummary a p");
	
		//3.목록(배열)에서 정보를 가져온다.
		int idx = 0;
		for(Element element : elements) {
			
			System.out.println(++idx +" : " + element.toString());
			System.out.println("==============================================================\n\n");
		}
	}

	private static String getParameter(String KEY_WORD, int PAGE) {
		return "destination=" + KEY_WORD + ""
				+ "&adults_1: 2"
				+ "&affiliateid= 400"
				+ "&mapState= expanded%3D0"
//				+ "&originalReferer= https%253a%252f%252fwww.hotelscombined.co.kr%252fHotels%252fSearch%253fdestination%253dplace%25253ASeoul%2526checkin%253d2019-12-01%2526checkout%253d2019-12-02%2526Rooms%253d1%2526adults_1%253d2%2526languageCode%253dKO%2526currencyCode%253dKRW%2526cmp2blocked%253dtrue"
				+ "&pageIndex" + PAGE + ""
				+ "&pageSize=15"
				+ "&platform= DesktopWeb"
				+ "radius= 0km"
				+ "Rooms= 1"
				+ "scroll= 315"
				+ "searchid= -200000605449366"
				+ "showSoldOut= false"
				+ "sort= Popularity-desc"
				+ "visitid= 54c2fe3de07d462db01b72e91ec49591"
				+ "visitorid= 239097509961481e8b75129969872bf9";
				
	}

}
