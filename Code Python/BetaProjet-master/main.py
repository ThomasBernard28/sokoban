from functions import *
from map_generator import Generator

if __name__ == '__main__':
    row = 15
    col = 30
    generator = Generator(row, col)
    player = generator.base_maker()
    grid = generator.grid

    window, images = setup_window()
    graphics_construction(window, grid, images)
    print(player)

    pygame.init()
    fpsClock = pygame.time.Clock()
    FPS = 60

    playing = True
    while playing:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                mainloop = False
                pygame.quit()

        keys = pygame.key.get_pressed()
        for key in keys:
            if keys[pygame.K_z]:
                if grid[player[1]][player[0]].checkMovePlayer(grid, 0, -1):
                    grid[player[1]][player[0]].MovePlayer(grid, 0, -1)
                    graphics_construction(window, grid, images)
                    player[1] -= 1
                    pygame.time.delay(130)
                    break

            if keys[pygame.K_q]:
                if grid[player[1]][player[0]].checkMovePlayer(grid, -1, 0):
                    grid[player[1]][player[0]].MovePlayer(grid, -1, 0)
                    graphics_construction(window, grid, images)
                    player[0] -= 1
                    pygame.time.delay(130)
                    break

            if keys[pygame.K_s]:
                if grid[player[1]][player[0]].checkMovePlayer(grid, 0, 1):
                    grid[player[1]][player[0]].MovePlayer(grid, 0, 1)
                    graphics_construction(window, grid, images)
                    player[1] += 1
                    pygame.time.delay(130)
                    break

            if keys[pygame.K_d]:
                if grid[player[1]][player[0]].checkMovePlayer(grid, 1, 0):
                    grid[player[1]][player[0]].MovePlayer(grid, 1, 0)
                    graphics_construction(window, grid, images)
                    player[0] += 1
                    pygame.time.delay(130)
                    break

            if keys[pygame.K_SPACE]:
                generator = Generator(row, col)
                player = generator.base_maker()
                grid = generator.grid
                graphics_construction(window, grid, images)
                break

        pygame.display.update()
        fpsClock.tick(FPS)
