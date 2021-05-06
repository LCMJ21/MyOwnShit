import pygame
import Print

BACKGROUND = (75, 75, 75)


def desenha_cores(screen, cores,img):
    for coords, image, at in cores:
        screen.blit(image, coords)
        if at:
            screen.blit(img, coords)


def deteta_rato(lista, botoes, altura, largura):
    # Deteta rato
    mouse = pygame.mouse.get_pos()
    click = pygame.mouse.get_pressed()

    cc = 0
    for (x, y), cor, at in lista:
        if x <= mouse[0] <= x + largura:
            if y <= mouse[1] <= y + altura:
                lista[cc][2] = True
                if click[0]:
                    botoes[cc] = True

        cc += 1


def inicio(screen, botoes, base, alternativo, number, img, switch_white):
    # Coordenadas iniciais
    altura = 75
    largura = 150
    gap = 50
    x = 60
    y = 100

    # Imagens
    if switch_white:
        back = pygame.image.load('Images/text/back_b.png')
    else:
        back = pygame.image.load('Images/text/back_w.png')

    # Coordenadas calculadas
    y1 = Print.calcula_inicio(1, y, altura, gap)
    y2 = Print.calcula_inicio(2, y, altura, gap)
    y3 = Print.calcula_inicio(3, y, altura, gap)

    x1 = Print.calcula_inicio(1, x, largura, gap)
    x2 = Print.calcula_inicio(2, x, largura, gap)
    x3 = Print.calcula_inicio(3, x, largura, gap)

    # Botao Leave
    mouse = pygame.mouse.get_pos()
    click = pygame.mouse.get_pressed()
    lista = [(x, y3), base, back]
    if lista[0][0] <= mouse[0] <= lista[0][0] + 200:
        if lista[0][1] <= mouse[1] <= lista[0][1] + 100:
            lista[1] = alternativo
            if click[0]:
                botoes[10] = True

    black = pygame.image.load('Images/colours/small/small_black.png')
    blue = pygame.image.load('Images/colours/small/small_blue.png')
    green = pygame.image.load('Images/colours/small/small_green.png')
    light_blue = pygame.image.load('Images/colours/small/small_light_blue.png')
    orange = pygame.image.load('Images/colours/small/small_orange.png')
    pink = pygame.image.load('Images/colours/small/small_pink.png')
    purple = pygame.image.load('Images/colours/small/small_purple.png')
    red = pygame.image.load('Images/colours/small/small_red.png')
    white = pygame.image.load('Images/colours/small/small_white.png')
    yellow = pygame.image.load('Images/colours/small/small_yellow.png')

    cores = [[(x, y + 25), black, False],
             [(x1, y + 25), blue, False],
             [(x2, y + 25), green, False],
             [(x3, y + 25), light_blue, False],
             [(x, y1 + 25), orange, False],
             [(x1, y1 + 25), pink, False],
             [(x2, y1 + 25), purple, False],
             [(x3, y1 + 25), red, False],
             [(x1, y2 + 25), white, False],
             [(x2, y2 + 25), yellow, False]]
    cores[number][2] = True

    deteta_rato(cores, botoes, altura, largura)

    desenha_cores(screen, cores, img)
    screen.blit(lista[1], lista[0])
    screen.blit(lista[2], lista[0])


def ativa(botoes, ecra, tela_cor, number):
    cc = 0
    while cc < 10:
        if botoes[cc]:
            number = cc
            botoes[cc] = False
        cc += 1

    if botoes[10]:
        ecra = 6
        botoes[10] = False

    return ecra, tela_cor, number


def i_select_cores(screen, botoes, ecra, base, alternativo, tela_cor, first, second, third, fourth, switch_white):
    if tela_cor == 0:
        if switch_white:
            img = pygame.image.load('Images/text/small/first_b.png')
        else:
            img = pygame.image.load('Images/text/small/first_w.png')
        inicio(screen, botoes, base, alternativo, first, img, switch_white)
        ecra, tela_cor, first = ativa(botoes, ecra, tela_cor, first)

    elif tela_cor == 1:
        if switch_white:
            img = pygame.image.load('Images/text/small/second_b.png')
        else:
            img = pygame.image.load('Images/text/small/second_w.png')
        inicio(screen, botoes, base, alternativo, second, img, switch_white)
        ecra, tela_cor, second = ativa(botoes, ecra, tela_cor, second)

    elif tela_cor == 2:
        if switch_white:
            img = pygame.image.load('Images/text/small/third_b.png')
        else:
            img = pygame.image.load('Images/text/small/third_w.png')
        inicio(screen, botoes, base, alternativo, third, img, switch_white)
        ecra, tela_cor, third = ativa(botoes, ecra, tela_cor, third)

    elif tela_cor == 3:
        if switch_white:
            img = pygame.image.load('Images/text/small/fourth_b.png')
        else:
            img = pygame.image.load('Images/text/small/fourth_w.png')
        inicio(screen, botoes, base, alternativo, fourth, img, switch_white)
        ecra, tela_cor, fourth = ativa(botoes, ecra, tela_cor, fourth)

    return ecra, tela_cor, first, second, third, fourth
