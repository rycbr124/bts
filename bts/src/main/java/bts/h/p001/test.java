package bts.h.p001;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test {
		
	public static void main(String[] args) {
        // Jsoup를 이용해서 네이버 스포츠 크롤링
        String url = "https://www.goodchoice.kr/product/home/1";
        Document doc = null;
         
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        // 주요 뉴스로 나오는 태그를 찾아서 가져오도록 한다.
        Elements element = doc.select("div.list_wrap");
         
        // 1. 헤더 부분의 제목을 가져온다.
        String title = element.select("li").text().substring(0, 4);
 
        System.out.println("============================================================");
        System.out.println(title);
        System.out.println("============================================================");
         
        for(Element el : element.select("li")) {    // 하위 뉴스 기사들을 for문 돌면서 출력
            System.out.println(el.text());
        }
         
        System.out.println("============================================================");
    }
 
}
