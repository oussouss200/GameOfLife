Game of life
=================================
Presentation:

This project is about calculating the next generation of Conway’s game of life.
We start with a two dimensional grid of cells, where each cell is either alive or dead. 
In this version of the problem, the grid is finite, and no life can exist off the edges. 
When calculating the next generation of the grid, follow these rules:

   1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
   2. Any live cell with more than three live neighbours dies, as if by overcrowding.
   3. Any live cell with two or three live neighbours lives on to the next generation.
   4. Any dead cell with exactly three live neighbours becomes a live cell.
   
The input starting position is a text file called "inPutTextFile.txt" that looks like this:
   
   Generation 1:
   4 8
   ........                                  4 : is the height
   ....*...                                  8 : is the width
   ...**...                                  . : is a dead cell
   ........                                  * : is a alice cell
   
Then we get Generation 2 (in the console) which looks like :

   Generation 2:
   4 8
   ........
   ...**...
   ...**...
   ........

Then,  Generation 3,4, ..

To choose the number of generation wanted to get, there is a variable to set in the main function called "nombreSimulation"    

And to modify the input board you have to modify the file called "inPutTextFile.txt" existing in the project file.

Requirements:

To run this implementation of the game of life you need:

Java 1.8
Maven 2+

Installation:
 *Download
You can download the GameOfLife project directly from Github using the following command: git clone https://github.com/oussouss200/GameOfLife.git

 *Launch
- Import the project into an IDE (ex: intellij idea) 
- Go to the class Simulation in the package src/main/java/com.game.gameoflife
- Run 'Simulation.main()' 
- About the test if you can run method by method in the class SimulationTest or you can run all one shot by runnig 'SimulationTest'


