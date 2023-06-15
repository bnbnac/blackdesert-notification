package bnbnac.discordBot;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PatchNotesUtils {
    public static List<String> getNewPatchNotes(Set<String> prev, Set<String> cur) {
        List<String> news = new ArrayList<>();

        for (String c : cur) {
            if (!prev.contains(c)) {
                news.add(c);
            }
        }

        return news;
    }

    public static PatchNote parseElementToPatchNote(Element patchNoteElement) {
        String date = patchNoteElement.select("span.date").first().text();
        String link = patchNoteElement.parent().parent().selectFirst("a").attr("href");
        String title = patchNoteElement.select("span.line_clamp").first().text();

        return new PatchNote(date, link, title);
    }

    public static List<String> parseNews(List<String> news, Set<PatchNote> storedPatchNotes) {
        List<String> messages = new ArrayList<>();
        HashMap<String, PatchNote> map = getTitleMap(storedPatchNotes);

        for (String n : news) {
            PatchNote storedPatchNote = map.get(n);

            String message = "updated at " + storedPatchNote.date + "\n" +
                    "# " + storedPatchNote.title + "\n" +
                    storedPatchNote.link;

            messages.add(message);
        }

        return messages;
    }

    // 추상화?
    public static HashMap<String, PatchNote> getTitleMap(Set<PatchNote> set) {
        HashMap<String, PatchNote> map = new HashMap<>();

        for (PatchNote s : set) {
            map.put(s.title, s);
        }

        return map;
    }
}