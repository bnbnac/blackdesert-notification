package bnbnac.discordBot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SecretReader {
    public static String TOKEN = "token";
    public static String CHANNEL_ID = "channel";

    public static String read(String property) {
        String value = null;

        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream("src/main/resources/secret")) {
            properties.load(input);
            value = properties.getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(property + "={Something} is not found in `src/main/resources/secret`");
        }

        return value;
    }
}
