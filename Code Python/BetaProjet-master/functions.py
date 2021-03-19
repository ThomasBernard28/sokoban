import pygame
from Tile import Tile


def setup_window(WIDTH=1920, HEIGHT=1080, sizeX=40, sizeY=40, marginX=0, marginY=0):
    """
       1280 x 720 = 32 x 18
       1920 x 1080 = 48 x 26
       """
    # win setup
    window = pygame.display.set_mode((WIDTH, HEIGHT))
    pygame.display.set_caption("Sokoban")

    Tile.sizeX = sizeX
    Tile.sizeY = sizeY
    Tile.marginX = marginX
    Tile.marginY = marginY

    # load images
    images = dict()
    if WIDTH == 1280:
        images["bg"] = pygame.image.load("images/bg_1280.png")
    if WIDTH == 1920:
        images["bg"] = pygame.image.load("images/bg_1920.png")
    images["player"] = pygame.image.load("images/player.png")
    images["box"] = pygame.image.load("images/box.png")
    images["box_flagged"] = pygame.image.load("images/box_flagged.png")
    images["flag"] = pygame.image.load("images/flag.png")
    images["wall"] = pygame.image.load("images/wall.png")

    return window, images


def graphics_construction(window, grid, images):
    """
    for size and margin, put those in Tile class as class arguments
    """
    #size = pygame.display.get_window_size()
    window.blit(images['bg'], (0, 0))
    for line in grid:
        for tile in line:
            if tile.isWall():
                window.blit(images["wall"], tile.get_pixelco())
            elif tile.isFlag():
                window.blit(images["flag"], tile.get_pixelco())
            elif tile.isBox():
                window.blit(images["box"], tile.get_pixelco())
            elif tile.isFlaggedBox():
                window.blit(images["box_flagged"], tile.get_pixelco())
            elif tile.isPlayer() or tile.isFlaggedPlayer():
                window.blit(images["player"], tile.get_pixelco())


# pattern functions
def rotation(angle, matrix):
    """
    Take a pattern and return its rotation respecting the input angle
    Rotation is clockwise
    :param angle: integer either 90, 270 or 180
    :param matrix: Pattern that is either 3x3 or 2x2
    :return: a matrix with the rotation applied
    """
    assert angle in (0, 90, 180, 270, 360)
    # recursive base, no rotation left
    if angle == 0:
        return matrix
    # we find the transpose matrix to make a 90 degree rotation
    res = list()
    for i in range(len(matrix)):
        res.append([])
        for j in range(len(matrix)):
            res[i].append(matrix[j][i])
    for i in range(len(matrix)):
        res[i][0], res[i][-1] = res[i][-1], res[i][0]
    return rotation(angle - 90, res)


def assemble(pattern):
    """
    Take a pattern and return a pattern with tile object instead of str
    :param pattern: matrix 3x3 or 2x2 with str
    :return: matrix 2x2 or 3x3 with tile object
    """
    res = list()
    i = 0
    for i in range(len(pattern)):
        res.append([])
        for j in range(len(pattern[0])):
            if pattern[i][j] == "w":
                res[i].append(Tile(j, i, "w"))
            elif pattern[i][j] == "e":
                res[i].append(Tile(j, i, "e"))
            else:
                # in case of problem
                raise ValueError("Big trouble", pattern[i][j])
    return res


if __name__ == "__main__":
    # assembled = assemble(PatternGenerator.empty)
    # print("assembled:\n", assembled)
    test = [["a", "b", "c"], ["d", "e", "f"], ["g", "h", "i"]]
    print("original:")
    for line in test:
        print(line)

    print("\nrotated 90:")
    for line in rotation(90, test):
        print(line)
    print("gda\nheb\nifc")

    print("\nrotated 180:")
    for line in rotation(180, test):
        print(line)

    print("\nrotated 270:")
    for line in rotation(270, test):
        print(line)
