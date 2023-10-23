package com.bootcamp.multithreading.services;

import com.bootcamp.multithreading.frames.MainFrame;

import javax.swing.*;

public class App15SwingWorker {
    public static void run() {
        SwingUtilities.invokeLater(() -> {
            new MainFrame("SwingWorker Demo");
        });
    }
}
