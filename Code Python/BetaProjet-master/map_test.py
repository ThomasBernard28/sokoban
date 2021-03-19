from main import *


def reparation_square(grid):
    """
    Put outside walls in a square shaped grid
    :param grid: matrix with tile objects
    :return: none
    """
    for i in range(len(grid)):
        for j in range(len(grid[0])):
            if not isinstance(grid[i][j], Tile):
                grid[i][j] = Tile(j, i, 'w')

    return grid


row = 17
col = 17
patterns = ["empty", "square", "corner_2", "center_line", "side_line", "double_line"]
weight = None

pygame.init()
window, images = setup_window(WIDTH=1280, HEIGHT=720)
# initial setup
generator = Generator(row, col)
generator.base_maker()
generator.theOneAndOnly(patterns, weight=weight)
grid = generator.grid
graphics_construction(window, grid, images)


playing = True
while playing:

    pygame.display.update()

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            mainloop = False
            pygame.quit()

    keys = pygame.key.get_pressed()
    for key in keys:
        if keys[pygame.K_SPACE]:
            generator = Generator(row, col)
            generator.base_maker()
            generator.theOneAndOnly(patterns, weight=weight)
            grid = generator.grid
            graphics_construction(window, grid, images)
            pygame.display.update()
            pygame.time.delay(100)
            break
