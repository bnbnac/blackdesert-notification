package bnbnac.discordBot;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PatchNotesComparator {
    public static List<String> getNewPatchNotes(Set<String> prev, Set<String> cur) {
        List<String> news = new ArrayList<>();

        for (String c : cur) {
            if (!prev.contains(c)) {
                news.add(c);
            }
        }

        return news;
    }
}