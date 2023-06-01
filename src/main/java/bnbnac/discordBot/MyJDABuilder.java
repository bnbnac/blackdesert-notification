package bnbnac.discordBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.JDABuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
        setToken();
        builder = JDABuilder.createDefault(token);

        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setActivity(Activity.watching("검은사막 패치노트"));
    }

    private void setToken() {
        String token = null;
        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream("src/main/resources/token")) {
            properties.load(input);
            token = properties.getProperty("token");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("toke={YOUR_TOKEN} is not found in `src/main/resources/token`");
        }

        this.token = token;
    }
}
