package com.bin.otkrivashkin.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogManager {
    private static final String TEXT_FILE_PATH = "C:\\Users\\Adam\\Desktop\\GitHub\\Hotel-booking\\back-end\\src\\main\\resources\\logs\\log.txt";

    public static final String IN_CONSOLE = "console";
    public static final String IN_FILE = "file";
    public static final String IN_CONSOLE_AND_FILE = "console, file";
    private String level = IN_FILE;


    private String className;
    private static LogManager logManager;

    public static LogManager getLogger(Class inClass) {
        logManager = new LogManager();
        logManager.setClassName(inClass.getName());
        return logManager;
    }

    public void info(String massage) {
        String log = LocalDateTime.now() + " INFO " + className + " - " + massage;
        if (level.equals(IN_CONSOLE) || level.equals(IN_CONSOLE_AND_FILE)) {
            System.out.println(log);
        } else if (level.equals(IN_FILE) || level.equals(IN_CONSOLE_AND_FILE)) {
            write(log);
        }
    }

    public void error(String massage) {
        String log = LocalDateTime.now() + " ERROR " + className + " - " + massage;
        if (level.equals(IN_CONSOLE) || level.equals(IN_CONSOLE_AND_FILE)) {
            System.err.println(log);
        } else if (level.equals(IN_FILE) || level.equals(IN_CONSOLE_AND_FILE)) {
            write(log);
        }
    }

    private void write(String massage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(TEXT_FILE_PATH), false))) {
            writer.append(massage);
            writer.newLine();
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void setClassName(String className) {
        this.className = className;
    }

    public void setLevel(String level) {
        switch (level) {
            case IN_CONSOLE_AND_FILE:
                logManager.level = IN_CONSOLE_AND_FILE;
                break;
            case IN_CONSOLE:
                logManager.level = IN_CONSOLE;
                break;
            default:
                logManager.level = IN_FILE;
                break;
        }
    }
}
