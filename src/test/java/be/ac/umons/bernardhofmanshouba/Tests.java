package be.ac.umons.bernardhofmanshouba;

import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;
import be.ac.umons.bernardhofmanshouba.Save.Load;
import be.ac.umons.bernardhofmanshouba.Save.Path;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class Tests {

    private Grid gridTest;

    private String setUp(String movFile) {
        Grid currentGrid;
        try{
            currentGrid = Load.loadFile(Path.UNIT_TEST_IN, "unitTest.xsb");
        }catch (FileNotFoundException e){
            throw new IllegalStateException("File is missing");
        }

        Assert.assertNotNull(currentGrid);

        gridTest = currentGrid;
        try {
            return TestApply.loadMov(Path.UNIT_TEST_IN, movFile);
        } catch( FileNotFoundException e) {
            Assert.fail();
            return "";
        }
    }

    /**
     * test1 method check if player can move through walls
     */
    @Test
    public void test1(){
        String listMove = setUp("test1.mov");
        TestApply.applyMov(gridTest, listMove);

        Assert.assertEquals("@", gridTest.getGridAt(1,5).toString());
        Assert.assertEquals("#", gridTest.getGridAt(0,5).toString());
    }
    /**
     * test2 method check if player can move to empty tile
     */
    @Test
    public void test2(){
        String listMove = setUp("test2.mov");
        TestApply.applyMov(gridTest, listMove);

        Assert.assertEquals("@", gridTest.getGridAt(1,4).toString());
    }

    /**
     * test3 check if box can move to empty tile
     */
    @Test
    public void test3(){
        String listMove = setUp("test3.mov");
        TestApply.applyMov(gridTest, listMove);

        Assert.assertEquals("$", gridTest.getGridAt(2,1).toString());
    }

    /**
     * test4 check if box collide with a wall
     */
    @Test
    public void test4(){
        String listMove = setUp("test4.mov");
        TestApply.applyMov(gridTest, listMove);

        Assert.assertEquals("$", gridTest.getGridAt(2,1).toString());
        Assert.assertEquals("#", gridTest.getGridAt(2,0).toString());
    }

    /**
     * test5 check if box goes to flag
     */
    @Test
    public void test5(){
        String listMove = setUp("test5.mov");
        TestApply.applyMov(gridTest, listMove);

        Assert.assertEquals("*", gridTest.getGridAt(1,1).toString());
        Assert.assertEquals("#", gridTest.getGridAt(0,1).toString());
    }


}
