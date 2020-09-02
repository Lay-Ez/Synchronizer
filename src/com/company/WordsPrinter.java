package com.company;

import java.util.List;

public class WordsPrinter implements Runnable {

    private final List<String> words;
    private final int step;
    private final Chrono chrono;

    public WordsPrinter(List<String> words, int step, Chrono chrono) {
        this.words = words;
        this.step = step;
        this.chrono = chrono;
    }

    @Override
    public void run() {
        int index = 0;
        while (true) {
            synchronized (chrono.lock) {
                try {
                    chrono.lock.wait();
                    if (chrono.getPeriodsPassed() % step == 0) {
                        if (index < words.size()) {
                            System.out.println(words.get(index));
                            index++;
                        } else {
                            break;
                        }
                    }
                } catch (InterruptedException ignored) {}
            }
        }
    }
}