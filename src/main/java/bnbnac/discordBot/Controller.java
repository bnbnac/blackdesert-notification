package bnbnac.discordBot;

import net.dv8tion.jda.api.JDA;

import java.util.List;
import java.util.Set;

public class Controller {
    public Crawler crawler;
    JDA myJDA;
    PatchNotesStorage patchNotesStorage;

    public Controller() {
        crawler = new MyCrawler();
        myJDA = new MyJDABuilder().build();
        patchNotesStorage = new PatchNotesStorage();
    }

    public void run() {
        Set<PatchNote> curPatchNotes = crawler.crawl();
        List<String> news = patchNotesStorage.getNews(curPatchNotes);

        if (news.isEmpty()) {
            return;
        }

        for (String n : news) {
            for (PatchNote curPatchNote : curPatchNotes) {
                if (n.equals(curPatchNote.title)) {
                    // should parse and
                    // bot.announceToDiscord
                    System.out.println(curPatchNote.title);
                }
            }
        }
    }
}
