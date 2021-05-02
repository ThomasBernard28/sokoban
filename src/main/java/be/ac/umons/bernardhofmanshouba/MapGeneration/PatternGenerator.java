package be.ac.umons.bernardhofmanshouba.MapGeneration;

import java.util.Random;

/*
patterns = new Pattern[] {
                Pattern.EMPTY,
                Pattern.CENTER_LINE,
                Pattern.SQUARE,
                Pattern.CORNER_2,
                Pattern.SIDE_LINE,
                Pattern.DOUBLE_LINE
        };
 */

/**
 * Class used to generate random pattern among a selection of pattern
 * each pattern has a probability attached to it
 */
public class PatternGenerator {
    private final Pattern[] patterns;
    private final double[] weights;

    public PatternGenerator(){
        patterns = new Pattern[] {
                Pattern.CENTER_LINE,
                Pattern.SQUARE,
                Pattern.SIDE_LINE,
                Pattern.CORNER_3,
                Pattern.EMPTY
        };
        weights = new double[]{
                1,
                0.2,
                1,
                0.2,
                0.1
        };
    }

    /**
     * @return a random pattern chosen among the selected
     */
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
        throw new IllegalStateException("Not supposed to be here");
    }

    /**
     * Contains patterns that are used in the generation of the walls
     */
    private enum Pattern{
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


        final char[][] pattern;
        final PatternType patternType;

        /**
         * A pattern contains a pattern and its type which indicates its symmetry
         * @param pattern char[][] array
         * @param type symmetry of the pattern
         */
        Pattern(char[][] pattern, PatternType type){
            this.pattern = pattern;
            this.patternType = type;
        }

        /**
         * @return return the pattern randomly rotated
         */
        char[][] getPattern() {
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

    /**
     * Used to indicate the symmetry of a pattern
     * the symmetry is the same as the letter used
     */
    private enum PatternType {
        X,
        I,
        E
    }

    /**
     * Rotates a pattern clockwise
     * @param pattern pattern to be rotated
     * @param angle angle of rotation
     * @return rotated pattern
     */
    protected static char[][] rotation(char[][] pattern, int angle){
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

    /**
     * @return the empty pattern
     */
    protected static char[][] getEmpty(){
        return Pattern.EMPTY.getPattern();
    }
}
