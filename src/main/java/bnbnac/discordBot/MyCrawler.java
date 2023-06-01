package bnbnac.discordBot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MyCrawler {

    // give parameter to store date
    // 다 가져올 필요는 없다 먼저 비교한다
    public static void crawl() {
        // try catch?
        try {
            String url = "https://www.kr.playblackdesert.com/ko-kr/News/Notice?boardType=2";
            Document doc = Jsoup.connect(url).get();

            Elements childElements = doc.select("strong.title");

            for (Element child : childElements) {
                Element date = child.select("span.date").first();
                String dateString = date.text();

                Element a = child.parent().parent().selectFirst("a");
                String link = a.attr("href");

                Element lineClamp = child.select("span.line_clamp").first();
                String title = lineClamp.text();
            }

        } catch (IOException e) {
            // 디코방에 announce. 리패치 성공하면 다시 announce. 실패하면 그대로.
            // <em class="thum_cate cate_update">[업데이트]&nbsp;</em>
            e.printStackTrace();
        }
    }
}
