package bnbnac.discordBot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private ScheduledExecutorService executor;
    private Controller controller;

    public Main() {
        executor = Executors.newScheduledThreadPool(1);
        controller = new Controller();
    }

    public void start() {
        executor.scheduleAtFixedRate(this::run, 0, 5, TimeUnit.MINUTES);
    }

    public void run() {
        this.controller.run();
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
}