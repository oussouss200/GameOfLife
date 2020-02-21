package com.game.gameoflifeTest;

import com.game.gameoflife.File;
import com.game.gameoflife.Simulation;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class SimulationTest {

    private Simulation testSimulation;

    @Before
    public void init(){
        File file = new File("inPutTextFile.txt");
        ArrayList<String> fileArrayList = file.extractLines();
        int height = file.extractHeight(fileArrayList);
        int width = file.extractWidth(fileArrayList);
        testSimulation = new Simulation(width,height);

        /* ---------- Revive cells ------------*/

        for (int y = 2; y < fileArrayList.size(); y++) {
            for (int x = 0; x < width; x++) {
                if(fileArrayList.get(y).trim().charAt(x) == '*'){
                    testSimulation.setAlive(x,y-2);
                }
            }
        }
    }

    @Test
    @Parameters({"0,0,0",
                 "1,0,0",
                 "2,0,0",
                 "3,0,1",
                 "4,0,1",
                 "5,0,1",
                 "6,0,0",
                 "7,0,0",

                 "0,1,0",
                 "1,1,0",
                 "2,1,1",
                 "3,1,3",
                 "4,1,2",
                 "5,1,2",
                 "6,1,0",
                 "7,1,0",

                 "0,2,0",
                 "1,2,0",
                 "2,2,1",
                 "3,2,2",
                 "4,2,2",
                 "5,2,2",
                 "6,2,0",
                 "7,2,0",

                 "0,3,0",
                 "1,3,0",
                 "2,3,1",
                 "3,3,2",
                 "4,3,2",
                 "5,3,1",
                 "6,3,0",
                 "7,3,0",
    })
    public void testCountAliveNeighbours(int x, int y, int expected){
        int actual = testSimulation.countAliveNeighbours(x,y);
        assertEquals(expected,actual);
    }

    @Test
    public void testSetAlive() {
        testSimulation.setAlive(0, 0);
        assertEquals(1, testSimulation.getState(0, 0));
    }
    @Test
    public void testSetDead() {
        testSimulation.setDead(0, 0);
        assertEquals(0, testSimulation.getState(0, 0));
    }

    @Test
    public void testSetSate(){
        testSimulation.setState(0,0,1);
        int actual = testSimulation.getState(0,0);
        assertEquals(1,actual);
    }

    @Test
    @Parameters({"0,0,0",
            "1,0,0",
            "2,0,0",
            "3,0,0",
            "4,0,0",
            "5,0,0",
            "6,0,0",
            "7,0,0",

            "0,1,0",
            "1,1,0",
            "2,1,0",
            "3,1,0",
            "4,1,1",
            "5,1,0",
            "6,1,0",
            "7,1,0",

            "0,2,0",
            "1,2,0",
            "2,2,0",
            "3,2,1",
            "4,2,1",
            "5,2,0",
            "6,2,0",
            "7,2,0",

            "0,3,0",
            "1,3,0",
            "2,3,0",
            "3,3,0",
            "4,3,0",
            "5,3,0",
            "6,3,0",
            "7,3,0",
    })
    public void testGetState(int x, int y, int expected){
            int actual = testSimulation.getState(x,y);
            assertEquals("wrong cell value ",expected,actual);
    }

    @Test
    @Parameters({"1,0,0",
                 "1,0,1",
                 "1,1,2",
                 "1,1,3",
                 "1,0,4",
                 "1,0,5",
                 "1,0,6",
                 "1,0,7",
                 "1,0,8",

                 "0,0,0",
                 "0,0,1",
                 "0,0,2",
                 "0,1,3",
                 "0,0,4",
                 "0,0,5",
                 "0,0,6",
                 "0,0,7",
                 "0,0,8",

    })
    public void testStepOn(int initial, int expected, int nbrNeighbours){
        int [][] nextBoard = testSimulation.step();
        for (int y = 0; y < testSimulation.getHeight(); y++) {
            for (int x = 0; x < testSimulation.getWidth(); x++) {
                if((testSimulation.countAliveNeighbours(x,y) == nbrNeighbours) && (initial == testSimulation.getState(x,y))){
                    assertEquals(expected, nextBoard[x][y]);
                }
            }
        }
    }
}
