class Tile:
    sizeX = 0
    sizeY = 0
    marginX = 0
    marginY = 0

    def __init__(self, x, y, content='e'):
        self.x = x
        self.y = y
        self.movable = 'e'
        self.immovable = 'e'
        if content in ('w', 'f'):
            self.immovable = content
        else:
            self.movable = content

    def __repr__(self):
        res = self.immovable + self.movable
        return res.replace('e', ' ')

    def isWall(self):
        return self.immovable == 'w'

    def isFlag(self):
        return self.immovable == 'f' and self.movable == 'e'

    def isBox(self):
        return self.movable == 'b' and self.immovable == 'e'

    def isFlaggedBox(self):
        return self.immovable == 'f' and self.movable == 'b'

    def isPlayer(self):
        return self.immovable == 'e' and self.movable == 'p'

    def isFlaggedPlayer(self):
        return self.immovable == 'f' and self.movable == 'p'

    def isEmpty(self):
        return self.immovable == 'e' and self.movable == 'e'

    def setMovable(self, movable):
        if movable not in ('e', 'b', 'p'):
            raise ValueError('Bad type of movable')
        self.movable = movable

    def setImmovable(self, immovable):
        if immovable not in ('f', 'w', 'e'):
            raise ValueError('Bad type of immovable', type(immovable))
        self.immovable = immovable

    def clearMovable(self):
        self.movable = 'e'

    def checkMoveBox(self, grid, directionX, directionY):
        return not grid[directionY + self.y][directionX + self.x].isWall() and \
               not grid[directionY + self.y][directionX + self.x].isBox() and \
               not grid[directionY + self.y][directionX + self.x].isFlaggedBox()

    def MoveBox(self, grid, directionX, directionY):
        grid[self.y + directionY][self.x + directionX].setMovable('b')
        self.clearMovable()
        print("box now at({},{})".format(self.x + directionX, self.y + directionY))

    def checkMovePlayer(self, grid, directionX, directionY):
        print('entering')
        if grid[directionY + self.y][self.x + directionX].isFlaggedBox() \
                or grid[directionY + self.y][self.x + directionX].isBox():

            return grid[directionY + self.y][self.x + directionX].checkMoveBox(grid, directionX, directionY)

        return not grid[directionY + self.y][self.x + directionX].isWall()

    def MovePlayer(self, grid, directionX, directionY):
        if grid[directionY + self.y][self.x + directionX].isFlaggedBox() \
                or grid[directionY + self.y][self.x + directionX].isBox():

            grid[directionY + self.y][self.x + directionX].MoveBox(grid, directionX, directionY)

        grid[directionY + self.y][self.x + directionX].setMovable('p')
        self.clearMovable()
        print("player now at ({},{})".format(self.x + directionX, self.y + directionY))

    def get_pixelco(self):
        """
        sizeX and Y = tile size
        """
        return self.x * Tile.sizeX + Tile.marginX, self.y * Tile.sizeY + Tile.marginY
