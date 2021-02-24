from box_class import *


class Player:
    def __init__(self, x, y, isArrived=False):
        self.x = x
        self.y = y
        self.isArrived = isArrived

    def check_move(self, grid, direction):
        if isinstance(grid[self.y + direction[1]][self.x + direction[0]], Box):
            if grid[self.y + direction[1]][self.x + direction[0]].check_move(grid, direction):
                return True

            return False

        elif isinstance(grid[self.y + direction[1]][self.x + direction[0]], Wall):
            return False
        return True

    def move(self, grid, direction):
        # moving the box
        if isinstance(grid[self.y + direction[1]][self.x + direction[0]], Box):
            grid[self.y + direction[1]][self.x + direction[0]].move(grid, direction)

        # moving the player
        if isinstance(grid[self.y + direction[1]][self.x + direction[0]], Flag) and self.isArrived:
            grid[self.y + direction[1]][self.x + direction[0]] = self
            grid[self.y][self.x] = Flag(self.x, self.y)

        elif isinstance(grid[self.y + direction[1]][self.x + direction[0]], Flag) and not self.isArrived:

            self.isArrived = True
            grid[self.y + direction[1]][self.x + direction[0]], grid[self.y][self.x] = self, None

        elif not isinstance(grid[self.y + direction[1]][self.x + direction[0]], Flag) and self.isArrived:
            self.isArrived = False
            grid[self.y + direction[1]][self.x + direction[0]] = self
            grid[self.y][self.x] = Flag(self.x, self.y)

        else:
            grid[self.y + direction[1]][self.x + direction[0]], grid[self.y][self.x] = self, None

        self.x += direction[0]
        self.y += direction[1]
        print("player is now at ({},{})".format(self.x, self.y))
