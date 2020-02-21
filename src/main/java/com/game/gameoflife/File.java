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

    public ArrayList<String> extractLines(){
        ArrayList<String> fileArrayList = new ArrayList<>();
        try {
            fileArrayList = this.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileArrayList;
    }
    public int extractHeight(ArrayList<String> fileArrayList){
        String[] heightWidth = fileArrayList.get(1).split(" ");
        int height = Integer.parseInt(heightWidth[0].trim());
        return height;
    }

    public int extractWidth(ArrayList<String> fileArrayList){
        String[] heightWidth = fileArrayList.get(1).split(" ");
        int width = Integer.parseInt(heightWidth[1].trim());
        return width;
    }


}
