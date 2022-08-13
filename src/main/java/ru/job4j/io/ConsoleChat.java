package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean running = true;
        boolean writing = true;
        String input;
        String reply;
        Random random = new Random();
        List<String> conversation = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (running) {
            input = in.nextLine();
            conversation.add(input);
            switch (input) {
                case STOP -> running = false;
                case OUT -> writing = false;
                case CONTINUE -> writing = true;
                default -> {
                    if (writing) {
                        reply = readPhrases().get(random.nextInt(readPhrases().size()));
                        System.out.println(reply);
                        conversation.add(reply);
                    }
                }
            }
        }
        saveLog(conversation);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            phrases = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/log.txt", "./data/phrases.txt");
        cc.run();
    }
}