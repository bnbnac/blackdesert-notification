package bnbnac.discordBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.util.List;

import static bnbnac.discordBot.PatchNotesUtils.parseNews;

public class Controller {
    public PatchNotesStorage patchNotesStorage;
    public Crawler crawler;
    JDA myJDA;
    MessageChannel channel;

    public Controller() {
        patchNotesStorage = new PatchNotesStorage();
        crawler = new MyCrawler(patchNotesStorage);
        myJDA = new MyJDABuilder().build();
        channel = null;
    }

    public void run() {
        if (channel == null) {
            channel = getChannel();
        }

        boolean needToSendFailMessage = crawler.crawl();
        if (needToSendFailMessage) {
            channel.sendMessage("홈페이지 연결에 실패했습니다.");
            return;
        }

        List<String> news = patchNotesStorage.getNews();
        if (news.isEmpty()) {
            return;
        }
        List<String> messages = parseNews(news, patchNotesStorage.getStoredPatchNotes());

        for (String message : messages) {
            channel.sendMessage(message);
        }
    }

    public MessageChannel getChannel() {
        String channelId = SecretReader.read(SecretReader.CHANNEL_ID);
        return myJDA.getTextChannelById(channelId);
    }
}
