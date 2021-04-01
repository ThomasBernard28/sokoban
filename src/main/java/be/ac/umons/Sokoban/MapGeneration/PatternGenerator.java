package be.ac.umons.Sokoban.MapGeneration;

import java.util.Random;

public class PatternGenerator {

    private static char[][] rotation(char[][] pattern, int angle){
        /*
        * This method rotate recursively a pattern. The goal behind the rotation
        * is to find a possible pathway as we assemble the patterns together. If
        * there is no pathway between two patterns we'll try to rotation the second one.
        * If after all rotation there is no pathway we take a new pattern.
        */
        if(angle == 0){
            return pattern;
        }
        else{
            char[][] rotatedPattern = new char[3][3];
            for (int row =0; row < pattern.length; row ++){
                for (int col =0; col < 3; col ++){
                    rotatedPattern[row][col] = pattern[col][row];
                }
            }
            for (int row = 0; row < pattern.length; row++){
                char temp = rotatedPattern[row][0];
                rotatedPattern[row][0] = rotatedPattern[row][2];
                rotatedPattern[row][2] = temp;
            }
            return rotation(rotatedPattern, angle-90);
        }
    }

    enum xProfile{ //pattern that will not change after rotation like 'X'

        EMPTY (new char[][]{
                {'e','e','e'},
                {'e','e','e'},
                {'e','e','e'}
        },1),

        CENTER_POINT(new char[][]{
                {'e', 'e', 'e'},
                {'e', 'w', 'e'},
                {'e', 'e', 'e'}
        },1),

        CROSS(new char[][]{
                {'e', 'w', 'e'},
                {'w', 'w', 'w'},
                {'e', 'w', 'e'}
        },1);

        private final char[][] pattern;

        xProfile(char[][] pattern, double weight){
            this.pattern = pattern;
        }

        public char[][] getPattern() {
            return pattern;
        }

    }

    enum iProfile{ // pattern that only change when rotated of 90 or 270 degrees

        CENTER_LINE(new char[][]{
                {'e', 'w', 'e'},
                {'e', 'w', 'e'},
                {'e', 'w', 'e'},
        },1),

        DOUBLE_LINE(new char[][]{
                {'w', 'w', 'w'},
                {'e', 'e', 'e'},
                {'w', 'w', 'w'}
        },1);

        private final char[][] pattern;

        iProfile(char[][] pattern, double weight){
            this.pattern = pattern;
        }
        public char[][] getPattern() {
            Random rand = new Random();
            int i = rand.nextInt(2);
            int angle = i*90;
            return rotation(pattern, angle);
        }
    }

    enum eProfile { // Pattern that change at every rotation

        SQUARE(new char[][]{
                {'w', 'w', 'e'},
                {'w', 'w', 'e'},
                {'e', 'e', 'e'}
        },1),

        CORNER_2(new char[][]{
                {'w', 'w', 'e'},
                {'e', 'e', 'e'},
                {'e', 'e', 'e'}
        },1),

        TOP_POINT(new char[][]{
                {'e', 'w', 'e'},
                {'e', 'e', 'e'},
                {'e', 'e', 'e'}
        },1),

        CORNER_POINT(new char[][]{
                {'w', 'e', 'e'},
                {'e', 'e', 'e'},
                {'e', 'e', 'e'}
        },1),

        SIDE_LINE(new char[][]{
                {'w', 'e', 'e'},
                {'w', 'e', 'e'},
                {'w', 'e', 'e'}
        },1),

        CORNER_3(new char[][]{
                {'w', 'w', 'e'},
                {'w', 'e', 'e'},
                {'e', 'e', 'e'}
        },1),

        ANTI_CORNER(new char[][]{
                {'e', 'w', 'e'},
                {'w', 'w', 'e'},
                {'e', 'e', 'e'}
        },1),

        DOUBLE_SIDE_POINT(new char[][]{
                {'w', 'e', 'e'},
                {'e', 'e', 'e'},
                {'w', 'e', 'e'}
        },1);

        private final char[][] pattern;

        eProfile(char[][] pattern, double weight) {
            this.pattern = pattern;
        }

        public char[][] getPattern() {
            Random rand = new Random();
            int i = rand.nextInt(4);
            int angle = i * 90;
            return rotation(pattern, angle);
        }
    }
}
