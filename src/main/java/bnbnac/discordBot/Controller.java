package bnbnac.discordBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

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

        MessageChannel channel = getChannel();
        if (channel == null) {
            System.out.println("channel not found");
            return;
        }
        channel.sendMessage("## " + "channel found!");
        if (news.isEmpty()) {
            return;
        }

        for (String n : news) {
            for (PatchNote curPatchNote : curPatchNotes) {
                if (n.equals(curPatchNote.title)) {
                    String message = "";

                    // need stream
                    message += "updated at " + curPatchNote.date + "\n";
                    message += "# " + curPatchNote.title + "\n";
                    message += curPatchNote.link;

                    channel.sendMessage(message);
                }
            }
        }
    }

    // need optimize 매번 이짓을 하고 있음
    public MessageChannel getChannel() {
        String channelId = SecretReader.read(SecretReader.CHANNEL_ID);

        return myJDA.getTextChannelById(channelId);
    }
}
