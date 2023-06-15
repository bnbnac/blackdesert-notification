package bnbnac.discordBot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static bnbnac.discordBot.PatchNotesUtils.parseElementToPatchNote;

public class MyCrawler implements Crawler {
    private boolean connected;
    private PatchNotesStorage patchNotesStorage;

    public MyCrawler(PatchNotesStorage patchNotesStorage) {
        this.patchNotesStorage = patchNotesStorage;
        connected = true;
    }

    @Override
    public boolean crawl() {
        String url = "https://www.kr.playblackdesert.com/ko-kr/News/Notice?boardType=2";
        boolean needToSendFailMessage = false;

        try {
            Document doc = Jsoup.connect(url).get();
            connected = true;

            Elements patchNoteElements = doc.select("strong.title");

            Set<PatchNote> curPatchNotes = new HashSet<>();
            for (Element patchNoteElement : patchNoteElements) {
                curPatchNotes.add(parseElementToPatchNote(patchNoteElement));
            }

            patchNotesStorage.pushPatchNotes(curPatchNotes);
        } catch (IOException e) {
            e.printStackTrace();
            if (connected) {
                needToSendFailMessage = true;
            }
            connected = false;
        }

        return needToSendFailMessage;
    }
}
