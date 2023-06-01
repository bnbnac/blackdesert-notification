package bnbnac.discordBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.JDABuilder;

// exception 잡기
public class MyJDABuilder {
    private JDABuilder builder;
    private String token;

    public MyJDABuilder() {
        setBuilder();
    }

    public JDA build() {
        return builder.build();
    }

    private void setBuilder() {
        this.token = SecretReader.read(SecretReader.TOKEN);

        builder = JDABuilder.createDefault(token);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.setActivity(Activity.watching("검은사막 패치노트"));
    }
}
