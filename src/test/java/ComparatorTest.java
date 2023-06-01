import bnbnac.discordBot.Controller;
import bnbnac.discordBot.Crawler;
import bnbnac.discordBot.PatchNote;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComparatorTest {
    class FakeCrawler implements Crawler {
        int i = 0;
        List<Set<PatchNote>> list = new ArrayList<>();

        public FakeCrawler() {
            Set<PatchNote> prev = new HashSet<>();
            prev.add(new PatchNote("1", "1", "1"));
            prev.add(new PatchNote("2", "2", "2"));
            prev.add(new PatchNote("3", "3", "3"));
            list.add(prev);

            Set<PatchNote> cur = new HashSet<>();
            cur.add(new PatchNote("1", "1", "1"));
            cur.add(new PatchNote("2", "2", "2"));
            cur.add(new PatchNote("3", "3", "3"));
            cur.add(new PatchNote("new", "new", "new"));
            list.add(cur);
        }

        public Set<PatchNote> crawl() {
            return list.get(i++);
        }
    }

    @Test
    void getNewPatchNotesTest() throws InterruptedException {
        Controller controller = new Controller();
        controller.crawler = new FakeCrawler();
        controller.run();
        controller.run();
    }
}
