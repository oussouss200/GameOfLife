package com.game.gameoflife;

public class Simulation {
    int width;
    int height;
    int[][] board;

    public Simulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public void printBoard(){
        System.out.println("---");
        for (int y = 0; y < height; y++) {
            String line = "|";
            for (int x = 0; x < width; x++) {
                if (this.board[x][y] == 0){
                    line += ".";
                }else {
                    line += "*";
                }
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("---\n");
    }

    public void setAlive(int x, int y){
        this.board[x][y] = 1;
    }
    public void setDead(int x, int y){
        this.board[x][y] = 0;
    }

    public int countAliveNeighbours(int x,int y){
        int count = 0;
        count += this.board[x-1][y-1];
        count += this.board[x][y-1];
        count += this.board[x+1][y-1];

        count += this.board[x-1][y];
        count += this.board[x+1][y];

        count += this.board[x-1][y+1];
        count += this.board[x][y+1];
        count += this.board[x+1][y+1];

        return  count;
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation( 8,5);

        simulation.setAlive(2,2);
        simulation.setAlive(3,2);
        simulation.setAlive(4,2);

        simulation.printBoard();

        System.out.println(simulation.countAliveNeighbours(3,2));
    }
}
