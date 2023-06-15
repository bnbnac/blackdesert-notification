import bnbnac.discordBot.PatchNote;
import bnbnac.discordBot.PatchNotesStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComparatorTest {
    static Set<PatchNote> prev;
    static Set<PatchNote> cur;
    PatchNotesStorage patchNotesStorage;

    @BeforeAll
    public static void fake() {
        prev = new HashSet<>();
        prev.add(new PatchNote("1", "1", "1"));
        prev.add(new PatchNote("2", "2", "2"));
        prev.add(new PatchNote("3", "3", "3"));

        cur = new HashSet<>(prev);
        cur.add(new PatchNote("new", "new", "new"));
    }

    @BeforeEach
    void init() {
        patchNotesStorage = new PatchNotesStorage();
    }

    @Test
    void pushFirstPatchThenNewsIsEmpty() {
        patchNotesStorage.pushPatchNotes(prev);
        List<String> news = patchNotesStorage.getNews();

        Assertions.assertTrue(news.isEmpty());
    }

    @Test
    void pushDifferentPatchAndGetNews() {
        patchNotesStorage.pushPatchNotes(prev);
        patchNotesStorage.pushPatchNotes(cur);
        List<String> news = patchNotesStorage.getNews();

        Assertions.assertEquals(news.get(0), "new");
    }
}
