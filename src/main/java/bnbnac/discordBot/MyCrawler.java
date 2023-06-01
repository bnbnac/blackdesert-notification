package bnbnac.discordBot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class MyCrawler {
    public Set<PatchNote> crawl() {
        Set<PatchNote> curPatchNotes = new HashSet<>();
        boolean state;

        try {
            String url = "https://www.kr.playblackdesert.com/ko-kr/News/Notice?boardType=2";
            Document doc = Jsoup.connect(url).get();
            Elements patchNoteElements = doc.select("strong.title");

            for (Element patchNoteElement : patchNoteElements) {
                buildCurPatchNotes(patchNoteElement, curPatchNotes);
            }
        } catch (IOException e) {
            // 디코방에 announce. 리패치 성공하면 다시 announce. 실패하면 pass. state
            e.printStackTrace();
        }

        return curPatchNotes;
    }

    public void buildCurPatchNotes(Element patchNoteElement, Set<PatchNote> curPatchNotes) {
        String date = patchNoteElement.select("span.date").first().text();
        String link = patchNoteElement.parent().parent().selectFirst("a").attr("href");
        String title = patchNoteElement.select("span.line_clamp").first().text();

        LocalDate rawToday = LocalDate.now();
        String today = rawToday.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        String yesterday = rawToday.minusDays(1).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        if (today.equals(date) || yesterday.equals(date)) {
            curPatchNotes.add(new PatchNote(date, link, title));
        }
    }
}
