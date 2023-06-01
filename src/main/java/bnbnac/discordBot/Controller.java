package bnbnac.discordBot;

import net.dv8tion.jda.api.JDA;

public class Controller {
    MyCrawler myCrawler;
    JDA myJDA;
    PatchNotesStorage patchNotesStorage;

    public Controller() {
        myCrawler = new MyCrawler();
        myJDA = new MyJDABuilder().build();
        patchNotesStorage = new PatchNotesStorage();
    }

    public void run() {

        if (patchNotesStorage.updated()) {

        }
    }


}
