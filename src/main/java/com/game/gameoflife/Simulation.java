package com.game.gameoflife;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Simulation {
    int width;
    int height;
    int[][] board;

    public Simulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getBoard() {
        return board;
    }

    public void printBoard(){
        for (int y = 0; y < height; y++) {
            String line = "";
            for (int x = 0; x < width; x++) {
                if (this.board[x][y] == 0){
                    line += ".";
                }else {
                    line += "*";
                }
            }
            line += "";
            System.out.println(line);
        }
        System.out.println("\n");
    }
    public void setState(int x, int y,int state){
        this.board[x][y] = state;
    }

    public void setAlive(int x, int y){
        this.board[x][y] = 1;
    }
    public void setDead(int x, int y){
        this.board[x][y] = 0;
    }

    public int countAliveNeighbours(int x, int y) {
        int count = 0;

        count += getState(x - 1, y - 1);
        count += getState(x, y - 1);
        count += getState(x + 1, y - 1);

        count += getState(x - 1, y);
        count += getState(x + 1, y);

        count += getState(x - 1, y + 1);
        count += getState(x, y + 1);
        count += getState(x + 1, y + 1);

        return count;
    }
    public int getState(int x, int y) {
        if (x < 0 || x >= width) {
            return 0;
        }

        if (y < 0 || y >= height) {
            return 0;
        }

        return this.board[x][y];
    }

    public int[][] step() {
        int[][] newBoard = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int aliveNeighbours = countAliveNeighbours(x, y);

                if (getState(x, y) == 1) {
                    if (aliveNeighbours < 2) {
                        newBoard[x][y] = 0;
                    } else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                        newBoard[x][y] = 1;
                    } else if (aliveNeighbours > 3) {
                        newBoard[x][y] = 0;
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        newBoard[x][y] = 1;
                    }
                }

            }
        }

        //this.board = newBoard;
        return newBoard;
    }

    public static void main(String[] args)  {
        File file = new File("inPutTextFile.txt");
        ArrayList<String> fileArrayList = file.extractLines();
        int height = file.extractHeight(fileArrayList);
        int width = file.extractWidth(fileArrayList);
        Simulation simulation = new Simulation(width,height);

        /* ---------- Revive cells ------------*/

        for (int y = 2; y < fileArrayList.size(); y++) {
            for (int x = 0; x < width; x++) {
                if(fileArrayList.get(y).trim().charAt(x) == '*'){
                    simulation.setAlive(x,y-2);
                }
            }
        }

        /* ---------- Simulation ------------*/

        int nombreSimulation = 3;
        System.out.println("Génération " + Integer.toString(1) + ":");
        System.out.println( height + " " + width);
        simulation.printBoard();
        for (int i = 2; i <= nombreSimulation; i++) {
            int [][] stepOnBoard = simulation.step();
            simulation.board = stepOnBoard;
            System.out.println("Génération " + Integer.toString(i) + ":");
            System.out.println( height + " " + width);
            simulation.printBoard();
        }

    }
}
