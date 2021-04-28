package be.ac.umons.bernardhofmanshouba;

import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class Tests {

    public static int[] playerPosition = new int[] {1, 5};

    /**
     * test1 method check if player can move through walls
     */
    @Test
    public void test1(){
        Grid currentGrid = null;
        try{
            currentGrid = TestApply.applyMov("unitTest.xsb", "test1.mov");
        }catch (IOException e){
            e.printStackTrace();
        }
        Assert.assertArrayEquals(playerPosition, new int[] {currentGrid.getPlayerX(), currentGrid.getPlayerY()});

    }
    /**
     * test2 method check if player can move to empty tile
     */
    @Test
    public  void test2(){
        Grid currentGrid = null;
        try{
            currentGrid = TestApply.applyMov("unitTest.xsb", "test2.mov");
        }catch (IOException e){
            e.printStackTrace();
        }
        Assert.assertEquals(playerPosition[1], currentGrid.getPlayerY()+1);
    }

    /**
     * test3 check if box can move to empty tile
     */
    @Test
    public  void test3(){
        Grid currentGrid = null;
        try{
            currentGrid = TestApply.applyMov("unitTest.xsb", "test3.mov");
        }catch (IOException e){
            e.printStackTrace();
        }

        Assert.assertEquals("$", currentGrid.getGridAt(2,1).toString());
    }


}
