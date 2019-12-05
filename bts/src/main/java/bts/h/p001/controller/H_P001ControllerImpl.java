package bts.h.p001.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bts.h.p001.vo.H_P001VO;


@Controller("h_p001")
@RequestMapping(value = "/resve")
public class H_P001ControllerImpl implements H_P001Controller {
	
	
	@Override
	@RequestMapping(value = "/Info")
	public ModelAndView pageInit(  HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView("/h/p001/d001");
		
		
		List listPro1 = new ArrayList<>();
		try {
			//야놀자
//			 Document doc = Jsoup.connect("https://www.yanolja.com/search?keyword=서울").get();
//			 Elements listName = doc.select("div.fv9oaxp fgk86s8 > div.f8jq42y place-image");
//			 System.out.println(doc);
//			 for(Element e1 : listName) {
//				 Elements listName2 = e1.select("div > a");
			//호텔스컴바인
//			String url="https://www.hotelscombined.co.kr/Hotels/Search?destination=place%3ASeoul&checkin=2019-12-24&checkout=2019-12-25&Rooms=1&adults_1=2&languageCode=KO&currencyCode=KRW&RequestID:10d93a10-1575-11ea-8c33-55257f586863";
//			Document doc = Jsoup.connect(url).get();
//			 Elements listName = doc.select("div.hc_r_3");
//			 System.out.println(doc);
//			 System.out.println(listName);
//			 for(Element e1 : listName) {
//				 Elements listName2 = e1.select("div.hc-searchresultitem__hotelsummary > a");
			//부킹닷컴
			 Document doc = Jsoup.connect("https://www.booking.com/searchresults.ko.html?aid=376440&label=bookings-naam-W6Tfg48v4XhOlis8ZCyMrAS267777897763%3Apl%3Ata%3Ap1%3Ap22%2C567%2C000%3Aac%3Aap1t1%3Aneg%3Afi%3Atikwd-314746182377%3Alp1009871%3Ali%3Adec%3Adm&sid=93653f37c4cfc2e7dfe938a9b5f531ef&sb=1&src=index&src_elem=sb&error_url=https%3A%2F%2Fwww.booking.com%2Findex.ko.html%3Faid%3D376440%3Blabel%3Dbookings-naam-W6Tfg48v4XhOlis8ZCyMrAS267777897763%253Apl%253Ata%253Ap1%253Ap22%252C567%252C000%253Aac%253Aap1t1%253Aneg%253Afi%253Atikwd-314746182377%253Alp1009871%253Ali%253Adec%253Adm%3Bsid%3D93653f37c4cfc2e7dfe938a9b5f531ef%3Bsb_price_type%3Dtotal%3Bsrpvid%3D2b7f2cb7090a008f%26%3B&ss=%EC%84%9C%EC%9A%B8&is_ski_area=0&ssne=%EC%84%9C%EC%9A%B8&ssne_untouched=%EC%84%9C%EC%9A%B8&dest_id=-716583&dest_type=city&checkin_year=2019&checkin_month=12&checkin_monthday=24&checkout_year=2019&checkout_month=12&checkout_monthday=25&group_adults=1&group_children=0&no_rooms=1&b_h4u_keep_filters=&from_sf=1").get();
			 Elements listName = doc.select("div#ajaxsrwrap");
			 System.out.println(listName);
			 for(Element e1 : listName) {
				 Elements listName2 = e1.select("div > a");
			
			 }
				listPro1.add(listName);
				mav.addObject("listPro1",listPro1);
				System.out.println("listProooooooooooooooooooooooooooooo:" + listPro1);
				
//			 Elements list = doc.select("div.item-list > div.fg4uun text-place");
//			 for(Element e : list) {
//				 Elements sList2 = e.select("div.f8jq42y place-image > div._2TtUOE > url");
//				 Elements sList3 = e.select("div > h1");
//				 Elements sList4 = e.select("div > i > span");
//				 Elements sList6 = e.select("div.ico_goods > em.best > span.png24");
//				 System.out.printf("\t%s\t%s\t%s\t%s\n",sList2.attr("src"),sList3.attr("title"),sList4.text(),sList6.html());
//				 
//				 
//				 
//				 H_P001VO h_p001vo = new H_P001VO();
//				 h_p001vo.setImg(sList2.attr("src"));
//				 h_p001vo.setTitle(sList3.attr("title"));
//				 h_p001vo.setPrice(sList4.text());
//				 mav.addObject("lisPro1",listPro1.add(h_p001vo));
//			 }
			 System.out.println(listPro1.size());
		} catch(Exception e) {
			e.printStackTrace();
		}

		

		return mav;
	}
}
