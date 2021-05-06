import pygame
import Print


def desenha_cores(screen, cores):
    # Desenha corres para referencia
    for coords, image in cores:
        screen.blit(image, coords)


def create_lista(x, y, gap, altura, largura, switch_white, base, small_base, small_alternativo, small_third,
                 small_fourth, alternativo):
    # Cria a lista de imagens para imprimir

    # Imagens
    if switch_white:
        first = pygame.image.load('Images/text/first_b.png')
        second = pygame.image.load('Images/text/second_b.png')
        third = pygame.image.load('Images/text/third_b.png')
        fourth = pygame.image.load('Images/text/fourth_b.png')
        white = pygame.image.load('Images/text/white_b.png')
        black = pygame.image.load('Images/text/dark_b.png')
        back = pygame.image.load('Images/text/back_w.png')
    else:
        first = pygame.image.load('Images/text/first_w.png')
        second = pygame.image.load('Images/text/second_w.png')
        third = pygame.image.load('Images/text/third_w.png')
        fourth = pygame.image.load('Images/text/fourth_w.png')
        white = pygame.image.load('Images/text/white_w.png')
        black = pygame.image.load('Images/text/dark_w.png')
        back = pygame.image.load('Images/text/back_w.png')

    # Coordenadas calculadas
    y1 = Print.calcula_inicio(1, y, altura, gap)
    y2 = Print.calcula_inicio(2, y, altura, gap)
    y3 = Print.calcula_inicio(3, y, altura, gap)
    y4 = Print.calcula_inicio(4, y, altura, gap)
    x1 = Print.calcula_inicio(1, x, largura, gap)
    x2 = Print.calcula_inicio(2, x, largura, gap)

    lista = [[(x, y), base, first],
             [(x, y1), base, second],
             [(x, y2), base, third],
             [(x, y3), base, fourth],
             [(x, y4), base, white],
             [(x1, y4), base, black],
             [(x2, y4), base, back]]

    if switch_white:
        lista[4][1] = alternativo
    else:
        lista[5][1] = alternativo

    cores = [[(x1, y + 25), small_base],
             [(x1, y1 + 25), small_alternativo],
             [(x1, y2 + 25), small_third],
             [(x1, y3 + 25), small_fourth]]

    return lista, cores


def inicio(screen, botoes, base, alternativo, small_base, small_alternativo, small_third, small_fourth, switch_white):
    # Imprime o ecra inicial

    # Coordenadas iniciais
    altura = 100
    largura = 200
    gap = 50
    x = 60
    y = 100

    lista, cores = create_lista(x, y, gap, altura, largura, switch_white, base, small_base, small_alternativo,
                                small_third, small_fourth, alternativo)

    Print.deteta_rato(lista, botoes, alternativo, altura, largura)

    desenha_cores(screen, cores)
    Print.desenha_botoes(screen, lista)


def ativa(botoes, ecra, tela_cor, switch_white):
    # Ativa os botoes quando acionados

    if botoes[0]:
        ecra = 7
        tela_cor = 0
        botoes[0] = False

    if botoes[1]:
        ecra = 7
        tela_cor = 1
        botoes[1] = False

    if botoes[2]:
        ecra = 7
        tela_cor = 2
        botoes[2] = False

    if botoes[3]:
        ecra = 7
        tela_cor = 3
        botoes[3] = False

    if botoes[4]:
        switch_white = True
        botoes[4] = False

    if botoes[5]:
        switch_white = False
        botoes[5] = False

    if botoes[6]:
        ecra = 1
        botoes[6] = False

    return ecra, tela_cor, switch_white


def i_cores(screen, botoes, ecra, base, alternativo, tela_cor, small_base, small_alternativo, small_third, small_fourth,
            switch_white):
    # Faz o menu das corres
    inicio(screen, botoes, base, alternativo, small_base, small_alternativo, small_third, small_fourth, switch_white)
    ecra, tela_cor, switch_white = ativa(botoes, ecra, tela_cor, switch_white)
    return ecra, tela_cor, switch_white
