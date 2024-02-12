package com.gitlab.kevinnowak;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

class Teacher extends Thread {

    private final SharedCode code;
    private final int writingTime;
    private final Lock codeAccess;

    Teacher(SharedCode code, int writingTime, Lock codeAccess) {
        this.code = code;
        this.writingTime = writingTime;
        this.codeAccess = codeAccess;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            codeAccess.lock();
            try {
                String line = "Line " + (code.length()) + 1;
                code.addLine(line);
                System.out.println("[" + timestamp() + "]" + line);
                LiveCodingSession.TIME_UNIT.sleep(writingTime);
            } catch (InterruptedException e) {
                shutdown();
            } finally {
                codeAccess.unlock();
            }
        }
    }

    private long timestamp() {
        return LiveCodingSession.TIME_UNIT.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    void shutdown() {
        System.err.println("Interrupted. Shutting down.");
        Thread.currentThread().interrupt();
    }
}
