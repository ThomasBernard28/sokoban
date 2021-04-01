package be.ac.umons.Sokoban.MapGeneration;

public class PatternGenerator {

    enum xProfile{

        EMPTY (new char[][]{
                {'e','e','e'},
                {'e','e','e'},
                {'e','e','e'}
        }),

        CENTER_POINT(new char[][]{
                {'e', 'e', 'e'},
                {'e', 'w', 'e'},
                {'e', 'e', 'e'}
        }),

        CROSS(new char[][]{
                {'e', 'w', 'e'},
                {'w', 'w', 'w'},
                {'e', 'w', 'e'}
        });

        private final char[][] pattern;

        xProfile(char[][] pattern){
            this.pattern = pattern;
        }

        public char[][] getPattern() {
            return pattern;
        }

    }

    enum iProfile{

        CENTER_LINE(new char[][]{
                {'e', 'w', 'e'},
                {'e', 'w', 'e'},
                {'e', 'w', 'e'},
        }),

        DOUBLE_LINE(new char[][]{
                {'w', 'w', 'w'},
                {'e', 'e', 'e'},
                {'w', 'w', 'w'}
        });

        private final char[][] pattern;

        iProfile(char[][] pattern){
            this.pattern = pattern;
        }
        public char[][] getPattern() {
            return pattern;
        }
    }

    enum eProfile{

        SQUARE(new char[][]{
                {'w','w','e'},
                {'w','w','e'},
                {'e','e','e'}
        }),

        CORNER_2(new char[][]{
                {'w', 'w', 'e'},
                {'e', 'e', 'e'},
                {'e', 'e', 'e'}
        }),

        TOP_POINT(new char[][]{
                {'e', 'w', 'e'},
                {'e', 'e', 'e'},
                {'e', 'e', 'e'}
        }),

        CORNER_POINT(new char[][]{
                {'w', 'e', 'e'},
                {'e', 'e', 'e'},
                {'e', 'e', 'e'}
        }),

        SIDE_LINE(new char[][]{
                {'w', 'e', 'e'},
                {'w', 'e', 'e'},
                {'w', 'e', 'e'}
        }),

        CORNER_3(new char[][]{
                {'w', 'w', 'e'},
                {'w', 'e', 'e'},
                {'e', 'e', 'e'}
        }),

        ANTI_CORNER(new char[][]{
                {'e', 'w', 'e'},
                {'w', 'w', 'e'},
                {'e', 'e', 'e'}
        }),

        DOUBLE_SIDE_POINT(new char[][]{
                {'w', 'e', 'e'},
                {'e', 'e', 'e'},
                {'w', 'e', 'e'}
        });

        private final char[][] pattern;

        eProfile(char[][] pattern){
            this.pattern = pattern;
        }
        public char[][] getPattern(){
            return pattern;
        }
    }
}
