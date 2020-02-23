package com.game.gameoflife;

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

// function for drawing our board

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

    // function returns the state (alive or dead) of a cell

    public void setState(int x, int y,int state){
        this.board[x][y] = state;
    }

    // function to set a cell alive

    public void setAlive(int x, int y){
        this.board[x][y] = 1;
    }

    // function to set a cell dead

    public void setDead(int x, int y){
        this.board[x][y] = 0;
    }

    // function to count then number of neighbours (alive cells) of a cell

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

    // fucntion to get the next state of all the board

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

        //read the file
        File file = new File("inPutTextFile.txt");

        //get informations from file
        ArrayList<String> fileArrayList = file.extractLines();
        int height = file.extractHeight(fileArrayList);
        int width = file.extractWidth(fileArrayList);

        //create a Simulation object
        Simulation simulation = new Simulation(width,height);

        //Revive cells
        for (int y = 2; y < fileArrayList.size(); y++) {
            for (int x = 0; x < width; x++) {
                if(fileArrayList.get(y).trim().charAt(x) == '*'){
                    simulation.setAlive(x,y-2);
                }
            }
        }

        //Simulation
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
