import pygame
import Logic

WHITHE = (255, 255, 255)
GREY = (75, 75, 75)


def print_imgs(screen, switch_white):
    if switch_white:
        logo = pygame.image.load('Images/logo_black.png')
        title = pygame.image.load('Images/Title_black.png')
    else:
        logo = pygame.image.load('Images/logo_white.png')
        title = pygame.image.load('Images/Title_white.png')

    screen.blit(title, (0, 0))
    screen.blit(logo, (90, 90))


def print_fundo(screen, switch_white):
    if switch_white:
        screen.fill(WHITHE)
        return GREY
    else:
        screen.fill(GREY)
        return WHITHE




def correto(jogo):
    if Logic.jogo_acabou(jogo):
        lista = ["Victory",
                 "easy peasy lemon squeezy",
                 "Easy win"]
        return Logic.chose_one(lista)
    else:
        lista = ["Defeat",
                 "Noob ...",
                 "Wtf, are you on drugs ?",
                 "Wtf, you dumb ?"]
        return Logic.chose_one(lista)


def deteta_rato(lista, botoes, alternativo, altura, largura):
    # Deteta rato
    mouse = pygame.mouse.get_pos()
    click = pygame.mouse.get_pressed()

    cc = 0
    for (x, y), image, tex in lista:
        if x <= mouse[0] <= x + largura:
            if y <= mouse[1] <= y + altura:
                lista[cc][1] = alternativo
                if click[0]:
                    botoes[cc] = True

        cc += 1


def desenha_botoes(screen, lista):
    for coords, image, text in lista:
        screen.blit(image, coords)
        screen.blit(text, coords)


def calcula_inicio(cc, y, altura, gap):
    return y + (cc * altura) + (cc * gap)


def carrega_imgs(cor):

    # Cores
    # 0 - black
    # 1 - blue
    # 2 - green
    # 3 - light_blue
    # 4 - orange
    # 5 - pink
    # 6 - purple
    # 7 - red
    # 8 - white
    # 9 - yellow

    if cor == 0:
        base = pygame.image.load('Images/colours/normal/black.png')
        small = pygame.image.load('Images/colours/small/small_black.png')
        mini = pygame.image.load('Images/colours/mini/mini_black.png')
    elif cor == 1:
        base = pygame.image.load('Images/colours/normal/blue.png')
        small = pygame.image.load('Images/colours/small/small_blue.png')
        mini = pygame.image.load('Images/colours/mini/mini_blue.png')
    elif cor == 2:
        base = pygame.image.load('Images/colours/normal/green.png')
        small = pygame.image.load('Images/colours/small/small_green.png')
        mini = pygame.image.load('Images/colours/mini/mini_green.png')
    elif cor == 3:
        base = pygame.image.load('Images/colours/normal/light_blue.png')
        small = pygame.image.load('Images/colours/small/small_light_blue.png')
        mini = pygame.image.load('Images/colours/mini/mini_light_blue.png')
    elif cor == 4:
        base = pygame.image.load('Images/colours/normal/orange.png')
        small = pygame.image.load('Images/colours/small/small_orange.png')
        mini = pygame.image.load('Images/colours/mini/mini_orange.png')
    elif cor == 5:
        base = pygame.image.load('Images/colours/normal/pink.png')
        small = pygame.image.load('Images/colours/small/small_pink.png')
        mini = pygame.image.load('Images/colours/mini/mini_pink.png')
    elif cor == 6:
        base = pygame.image.load('Images/colours/normal/purple.png')
        small = pygame.image.load('Images/colours/small/small_purple.png')
        mini = pygame.image.load('Images/colours/mini/mini_purple.png')
    elif cor == 7:
        base = pygame.image.load('Images/colours/normal/red.png')
        small = pygame.image.load('Images/colours/small/small_red.png')
        mini = pygame.image.load('Images/colours/mini/mini_red.png')
    elif cor == 8:
        base = pygame.image.load('Images/colours/normal/white.png')
        small = pygame.image.load('Images/colours/small/small_white.png')
        mini = pygame.image.load('Images/colours/mini/mini_white.png')
    elif cor == 9:
        base = pygame.image.load('Images/colours/normal/yellow.png')
        small = pygame.image.load('Images/colours/small/small_yellow.png')
        mini = pygame.image.load('Images/colours/mini/mini_yellow.png')

    return base, small, mini


def carrega_cores(first, second, third, fourth):

    base, small_base, mini_base = carrega_imgs(first)
    alternativo, small_alternativo, mini_alternativo = carrega_imgs(second)
    thirdimg, small_third, mini_third = carrega_imgs(third)
    fourthimg, small_fourth, mini_fourth = carrega_imgs(fourth)

    return base, alternativo,small_base, small_alternativo, mini_base, mini_alternativo, small_third,  mini_third, small_fourth, mini_fourth








