package bnbnac.discordBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
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

        String channelId = SecretReader.read(SecretReader.CHANNEL_ID);
        MessageChannel channel = myJDA.getTextChannelById(channelId);
                if (channel != null) {
            channel.sendMessage("direct channel").queue();
        } else {
            System.out.println("not found");
        }

        if (news.isEmpty()) {
            return;
        }

        for (String n : news) {
            for (PatchNote curPatchNote : curPatchNotes) {
                if (n.equals(curPatchNote.title)) {
                    // should parse and
                    // bot.announceToDiscord

                    //<span class="date">2023.05.31</span> // date
                    //<span class="line_clamp">5월 31일(수) 최신 버전 업데이트 안내 (최종 수정 : 2023-05-31 20:43)</span> // title
                    //<a href=
                    // "https://www.kr.playblackdesert.com/News/Notice/Detail?groupContentNo=10426&amp;countryType=ko-kr"
                    //link
//1113603635591520270
                    //channel.sendMessage()

                    System.out.println(curPatchNote.title);
                }
            }
        }
    }
}
