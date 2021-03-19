from Tile import Tile
from Pattern_generator import PatternGenerator
"""
necessary intel:
/ nb of rows and col
"""


class Generator:
    UP = (0, -1)
    DOWN = (0, 1)
    LEFT = (- 1, 0)
    RIGHT = (1, 0)
    directions = [UP, DOWN, LEFT, RIGHT]

    def __init__(self, row, col, method=None):
        self.row = row
        self.col = col
        self.grid = list()
        self.walkable = list()
        for i in range(row):
            self.grid.append([])
            self.walkable.append([])
            for j in range(col):
                self.grid[i].append(Tile(j, i))
                self.walkable[i].append(True)

    def set_col(self, col, content, movable=False):
        if movable:
            for line in self.grid:
                line[col].setMovable(content)
        else:
            for line in self.grid:
                line[col].setImmovable(content)

    def set_row(self, row, content, movable=False):
        if movable:
            for tile in self.grid[row]:
                tile.setMovable(content)
        else:
            for tile in self.grid[row]:
                tile.setImmovable(content)

    def base_maker(self, playable_set=False):
        self.set_row(0, 'w')
        self.set_row(self.row - 1, 'w')
        self.set_col(0, 'w')
        self.set_col(self.col - 1, 'w')
        if playable_set:
            self.grid[self.row // 2][self.col//2].setMovable('p')
            self.grid[self.row // 3][self.col // 2].setImmovable('f')
            self.grid[self.row // 3][self.col // 3].setMovable('b')
            return list((self.col // 2, self.row // 2))

    def list_integration(self, setX, setY, matrix):
        """
        Copy a matrix inside the grid being generated (no index error controls)
        :param setX: start position in x
        :param setY: start position in y
        :param matrix: matrix to be copied
        :return: nothing
        """
        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                assert matrix[i][j] in ("w", "e")
                self.grid[setY + i][setX + j].setImmovable(matrix[i][j])

    def pattern_implementation(self, patterns, weight=None):
        """
        insert random patterns this change the grid argument
        :param patterns: list of patterns included among the selection
        :param weight: weight of the patterns by default the weights are equal
        :return: nothing
        """
        pattern_giver = PatternGenerator(patterns, weight)
        for i in range(self.row // 3):
            for j in range(self.col // 3):
                self.list_integration(j * 3 + 1, i * 3 + 1, pattern_giver.return_semi_random())

    def theOneAndOnly(self, patterns, weight=None):
        """
        same as pattern_implementation but with pathfinding algo to check if the map is solvable
        must place the outside walls before
        """
        pattern_giver = PatternGenerator(patterns, weight)
        self.reset_walkable()
        for i in range(self.row // 3):
            for j in range(self.col // 3):
                # player is gonna be on the top left so the place must be empty to prevent collision with a wall
                if i == j == 0:
                    self.list_integration(1, 1, pattern_giver.return_empty())
                else:
                    self.list_integration(j * 3 + 1, i * 3 + 1, pattern_giver.return_semi_random())
                    # update the walkable to the walls that have been placed
                    self.reset_walkable()
                    self.pathfinder(1, 1)
                    if not self.solvable():
                        # when the map is not solvable we replace the tile that has been placed with an empty one
                        self.list_integration(j * 3 + 1, i * 3 + 1, pattern_giver.return_empty())
                        print("map corrected")

    def pathfinder(self, startX, startY):
        """
        find every walkable tile and return false if some can't be reached
        https://fr.wikipedia.org/wiki/Algorithme_de_parcours_en_profondeur
        :param startX: start pos in X for the character
        :param startY: start pos in Y for the character
        :return: false if some tiles are out of reach true otherwise
        """
        self.walkable[startY][startX] = False
        for neighbour in self.neighbour_tiles((startX, startY)):
            if self.walkable[startY + neighbour[1]][startX + neighbour[0]]:
                self.pathfinder(startX + neighbour[0], startY + neighbour[1])

    def neighbour_tiles(self, pos):
        """
        Return a list of direction with adjacent tiles that aren't explored yet
        :param pos:2-tuple with int indicating x and y pos
        :return:[(-1, 0), (1,0)] for instance
        """
        res = list()
        for direction in self.directions:
            if self.in_index_range(pos, direction) and self.walkable[pos[1] + direction[1]][pos[0] + direction[0]]:
                res.append(tuple(direction))
        return res

    def reset_walkable(self):
        """
        Reconstruct self.walkable to be True if empty and False if wall
        :return: none
        """
        for i in range(self.row):
            for j in range(self.col):
                if self.grid[i][j].isWall():
                    self.walkable[i][j] = False
                else:
                    self.walkable[i][j] = True

    def in_index_range(self, pos, direction):
        return (0 <= pos[0] + direction[0] < self. col) and (0 <= pos[1] + direction[1] < self.col)

    def solvable(self):
        """
        Explore the walkable arg if only one tile is True then it's not solvable
        :return:
        """
        for row in self.walkable:
            for case in row:
                if case:
                    return False
        return True


if __name__ == "__main__":
    my_generator = Generator(8, 8)
    # pattern_provider = PatternGenerator(["empty", "double_line", "corner"])
    # my_generator.base_maker()
    # my_generator.list_integration(0, 0, pattern_provider.cross)
    # my_generator.pattern_implementation(["empty", "double_line", "cross"])

    # my_generator.list_integration(0, 0, PatternGenerator.cross)
    # my_generator.reset_walkable()
    # my_generator.pathfinder(0, 0)
    for line in my_generator.grid:print(line)
    for line in my_generator.walkable:print(line)
