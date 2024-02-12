package com.gitlab.kevinnowak;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class LiveCodingSession {

    static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;
    public static final int WRITING_TIME = 10;
    public static final int MAX_READING_TIME = 10;
    public static final int NUM_OF_STUDENTS = 10;

    public static void main(String[] args) {
        SharedCode code = new SharedCode();
        ReadWriteLock codeAccess = new ReentrantReadWriteLock();
        Teacher teacher = new Teacher(code, WRITING_TIME, codeAccess.writeLock());
        Student[] students = new Student[NUM_OF_STUDENTS];
        Random random = new Random();

        for (int i = 0; i < NUM_OF_STUDENTS; i++) {
            students[i] = new Student(code, random.nextInt(MAX_READING_TIME), codeAccess.readLock());
        }

        teacher.start();

        for (Student student : students) {
            student.start();
        }
    }
}
