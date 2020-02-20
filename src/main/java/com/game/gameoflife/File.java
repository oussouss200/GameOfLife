package com.game.gameoflife;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class File {

    String path;

    public File(String path) {
        this.path = path;
    }

    public ArrayList<String> readFile() throws IOException {
        ArrayList<String> result = new ArrayList<>();

        FileReader file = new FileReader(path);
        StringBuffer sb = new StringBuffer();
        while (file.ready()) {
            char c = (char) file.read();
            if (c == '\n') {
                result.add(sb.toString());
                sb = new StringBuffer();
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            result.add(sb.toString());
        }
        return result;
    }

}
