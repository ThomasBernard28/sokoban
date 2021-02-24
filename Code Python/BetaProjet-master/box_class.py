class Wall:
    def __init__(self, x, y):
        self.x = x
        self.y = y


class Flag:
    def __init__(self, x, y):
        self.x = x
        self.y = y


class Box:
    def __init__(self, x, y, arrived=False):
        self.x = x
        self.y = y
        self.isArrived = arrived

    def check_move(self, grid, direction):

        if isinstance(grid[self.y + direction[1]][self.x + direction[0]], Wall):
            return False
        if isinstance(grid[self.y + direction[1]][self.x + direction[0]], Box):
            return False
        return True

    def move(self, grid, direction):

        if isinstance(grid[self.y + direction[1]][self.x + direction[0]], Flag) and self.isArrived:
            grid[self.y + direction[1]][self.x + direction[0]] = self
            grid[self.y][self.x] = Flag(self.x, self.y)

        elif not isinstance(grid[self.y + direction[1]][self.x + direction[0]], Flag) and not self.isArrived:
            grid[self.y + direction[1]][self.x + direction[0]], grid[self.y][self.x] = self, None

        elif not isinstance(grid[self.y + direction[1]][self.x + direction[0]], Flag) and self.isArrived:
            self.isArrived = False
            grid[self.y + direction[1]][self.x + direction[0]] = self
            grid[self.y][self.x] = Flag(self.x, self.y)

        else:
            self.isArrived = True
            grid[self.y + direction[1]][self.x + direction[0]], grid[self.y][self.x] = self, None

        self.x += direction[0]
        self.y += direction[1]
        print("box is now at ({},{})".format(self.x, self.y))
