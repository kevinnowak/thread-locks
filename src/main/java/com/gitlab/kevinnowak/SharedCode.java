package com.gitlab.kevinnowak;

import java.util.ArrayList;
import java.util.List;

class SharedCode {

    private final List<String> lines = new ArrayList<>();

    void presentTo(Student student) {
        for (String line : lines) {
            student.read(line);
        }
    }

    void addLine(String line) {
        lines.add(line);
    }

    int length() {
        return lines.size();
    }
}
