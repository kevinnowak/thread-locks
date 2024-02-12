package com.gitlab.kevinnowak;

import java.util.concurrent.locks.Lock;

class Student  extends Thread {

    private final SharedCode code;
    private final int readingTime;
    private final Lock codeAccess;

    Student(SharedCode code, int readingTime, Lock codeAccess) {
        this.code = code;
        this.readingTime = readingTime;
        this.codeAccess = codeAccess;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            codeAccess.lock();
            try {
                code.presentTo(this);
            } finally {
                codeAccess.unlock();
            }
        }
    }

    void read(String line) {
        try {
            LiveCodingSession.TIME_UNIT.sleep(readingTime);
        } catch (InterruptedException e) {
            shutdown();
        }
    }

    void shutdown() {
        System.err.println("Interrupted. Shutting down.");
        Thread.currentThread().interrupt();
    }
}
