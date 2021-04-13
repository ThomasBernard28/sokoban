package be.ac.umons.Sokoban.MapGeneration;

import java.util.Arrays;
import java.util.Random;

enum PatternType {
    X,
    I,
    E
}

public class PatternGenerator {
    private final Pattern[] patterns;
    private final double[] weights;

    public PatternGenerator(){
        patterns = new Pattern[] {
                Pattern.EMPTY,
                Pattern.CENTER_LINE,
                Pattern.SQUARE,
                Pattern.CORNER_2,
                Pattern.SIDE_LINE,
                Pattern.DOUBLE_LINE
        };
        weights = new double[]{
                1,
                1,
                1,
                1,
                1,
                1
        };
    }

    public char[][] getRandomPattern(){
        double rnd = (new Random()).nextDouble();

        double sumOfWeights = 0;
        for(double weight : weights){
            sumOfWeights += weight;
        }

        sumOfWeights *= rnd;

        for(int i = 0; i < patterns.length; i++) {
            sumOfWeights -= weights[i];
            if(sumOfWeights <= 0){
                return patterns[i].getPattern();
            }
        }
        return new char[][] {{'x'}, {'D'}};
    }

    public enum Pattern{
        EMPTY (new char[][]{
                {'e','e','e'},
                {'e','e','e'},
                {'e','e','e'}
        },PatternType.X),

        CENTER_POINT(new char[][]{
            {'e', 'e', 'e'},
            {'e', 'w', 'e'},
            {'e', 'e', 'e'}
        },PatternType.X),

        CROSS(new char[][]{
            {'e', 'w', 'e'},
            {'w', 'w', 'w'},
            {'e', 'w', 'e'}
        },PatternType.X),

        CENTER_LINE(new char[][]{
                {'e', 'w', 'e'},
                {'e', 'w', 'e'},
                {'e', 'w', 'e'},
        },PatternType.I),

        DOUBLE_LINE(new char[][]{
                {'w', 'w', 'w'},
                {'e', 'e', 'e'},
                {'w', 'w', 'w'}
        },PatternType.I),

        SQUARE(new char[][]{
                {'w', 'w', 'e'},
                {'w', 'w', 'e'},
                {'e', 'e', 'e'}
        },PatternType.E),

        CORNER_2(new char[][]{
                {'w', 'w', 'e'},
                {'e', 'e', 'e'},
                {'e', 'e', 'e'}
        },PatternType.E),

        TOP_POINT(new char[][]{
                {'e', 'w', 'e'},
                {'e', 'e', 'e'},
                {'e', 'e', 'e'}
        },PatternType.E),

        CORNER_POINT(new char[][]{
                {'w', 'e', 'e'},
                {'e', 'e', 'e'},
                {'e', 'e', 'e'}
        },PatternType.E),

        SIDE_LINE(new char[][]{
                {'w', 'e', 'e'},
                {'w', 'e', 'e'},
                {'w', 'e', 'e'}
        },PatternType.E),

        CORNER_3(new char[][]{
                {'w', 'w', 'e'},
                {'w', 'e', 'e'},
                {'e', 'e', 'e'}
        },PatternType.E),

        ANTI_CORNER(new char[][]{
                {'e', 'w', 'e'},
                {'w', 'w', 'e'},
                {'e', 'e', 'e'}
        },PatternType.E),

        DOUBLE_SIDE_POINT(new char[][]{
                {'w', 'e', 'e'},
                {'e', 'e', 'e'},
                {'w', 'e', 'e'}
        },PatternType.E);


        private final char[][] pattern;
        private final PatternType patternType;

        Pattern(char[][] pattern, PatternType type){
            this.pattern = pattern;
            this.patternType = type;
        }

        public char[][] getPattern() {
            final int i;
            switch (patternType){
                case E:
                    i = (new Random()).nextInt(4);
                    return rotation(pattern, i * 90);
                case I:
                    i = (new Random()).nextInt(2);
                    return rotation(pattern, i * 90);
                case X:
                    return pattern;
                default:
                    throw new IllegalStateException("Unexpected enum value");
            }
        }
    }

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

    public static void main(String[] args) {
        for (Pattern pattern : Pattern.values()) {
            System.out.println(Arrays.deepToString(pattern.getPattern()));
        }
    }

    public static char[][] getEmpty(){
        return Pattern.EMPTY.getPattern();
    }
}
