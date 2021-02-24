import pygame
from box_class import *
from player_class import Player


def setup_window(WIDTH=1280, HEIGHT=720):
    """
    1280//80 = 16
    720 // 80 = 9
    1920 // 80 = 24
    1080 // 80 = 13
    """
    # window setup
    window = pygame.display.set_mode((WIDTH, HEIGHT))
    pygame.display.set_caption("Sokoban")

    # loading images
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


def setup(map_plan):
    grid = list()
    player_co = None
    for i in map_plan:
        grid.append([])

    for i in range(len(map_plan)):
        j = 0
        for case in map_plan[i]:
            if case == "w":
                grid[i].append(Wall(j, i))
            elif case == "b":
                grid[i].append(Box(j, i))
            elif case == "f":
                grid[i].append(Flag(j, i))
            elif case == "p":
                grid[i].append(Player(j, i))
                player_co = [j, i]
            elif case == "o":
                grid[i].append(None)
            else:
                raise ValueError("Bug in map plan")
            j += 1

    return grid, player_co


def graphics_construction(window, grid, images, row, column):
    size = pygame.display.get_window_size()
    window.blit(images["bg"], (0, 0))
    for line in grid:
        for case in line:
            if isinstance(case, Wall):
                window.blit(images["wall"], get_pixelco(case, row, column, size[0], size[1]))
            elif isinstance(case, Box):
                if case.isArrived:
                    window.blit(images["box"], get_pixelco(case, row, column, size[0], size[1]))
                else:
                    window.blit(images["box_flagged"], get_pixelco(case, row, column, size[0], size[1]))
            elif isinstance(case, Player):
                window.blit(images["player"], get_pixelco(case, row, column, size[0], size[1]))
            elif isinstance(case, Flag):
                window.blit(images["flag"], get_pixelco(case, row, column, size[0], size[1]))

    pygame.display.update()


def get_pixelco(entity, row, column, width, height, size=40):
    return entity.x * size, entity.y * size
    # return tuple([(width // column) * entity.x, (height // row) * entity.y])
