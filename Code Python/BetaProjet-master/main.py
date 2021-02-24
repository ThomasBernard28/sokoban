from functions import *


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    row = 7
    column = 7
    map_plan = ["wwwwwww", "woofoow", "woopoow", "wooboow", "wooooow", "wooooow", "wwwwwww"]
    grid, player = setup(map_plan)
    window, images = setup_window()
    pygame.init()
    graphics_construction(window, grid, images, row, column)

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
                if grid[player[1]][player[0]].check_move(grid, (0, -1)):
                    grid[player[1]][player[0]].move(grid, (0, -1))
                    graphics_construction(window, grid, images, row, column)
                    player[1] -= 1
                    pygame.time.delay(100)
                    break

            if keys[pygame.K_q]:
                if grid[player[1]][player[0]].check_move(grid, (-1, 0)):
                    grid[player[1]][player[0]].move(grid, (-1, 0))
                    graphics_construction(window, grid, images, row, column)
                    player[0] -= 1
                    pygame.time.delay(100)
                    break

            if keys[pygame.K_s]:
                if grid[player[1]][player[0]].check_move(grid, (0, 1)):
                    grid[player[1]][player[0]].move(grid, (0, 1))
                    graphics_construction(window, grid, images, row, column)
                    player[1] += 1
                    pygame.time.delay(100)
                    break

            if keys[pygame.K_d]:
                if grid[player[1]][player[0]].check_move(grid, (1, 0)):
                    grid[player[1]][player[0]].move(grid, (1, 0))
                    graphics_construction(window, grid, images, row, column)
                    player[0] += 1
                    pygame.time.delay(100)
                    break

            if keys[pygame.K_SPACE]:
                grid, player = setup(map_plan)
                graphics_construction(window, grid, images, row, column)
                break

        pygame.display.update()
        fpsClock.tick(FPS)
