import pygame
import Print

TAMANHO = 880

BIG_UPDATE = 110
SMALL_UPDATE = 70
X_INICIAL = 85
Y_INICIAL = 25

# Cores
GREEN = (0, 255, 0)
RED = (255, 0, 0)
BLACK = (0, 0, 0)
PURPLE = (255, 0, 255)
YELOW = (255, 255, 0)
YELOW2 = (155, 255, 0)
BLUE = (0, 0, 255)
WHITHE = (255, 255, 255)


def deteta_rato_spec(lista, botoes, alternativo, altura, largura):
    # Deteta rato
    mouse = pygame.mouse.get_pos()
    click = pygame.mouse.get_pressed()

    cc = 0
    for (x, y), image, tex, index in lista:
        if x <= mouse[0] <= x + largura:
            if y <= mouse[1] <= y + altura:
                lista[cc][1] = alternativo
                if click[0]:
                    botoes[index] = True

        cc += 1


def atualiza_vars(inicial, coord):
    # Encorda as coordenadas no ecra em que tem que pintar a coord
    if coord >= 6:
        inicial += (coord - 2) * SMALL_UPDATE + 2 * BIG_UPDATE
    elif coord >= 3:
        inicial += (coord - 1) * SMALL_UPDATE + BIG_UPDATE
    else:
        inicial += coord * SMALL_UPDATE
    return inicial


def desenha_botoes(screen, lista):
    for coords, image, text, index in lista:
        screen.blit(image, coords)
        screen.blit(text, coords)


def calcula_inicio(cc, y, altura, gap):
    return y + (cc * altura) + (cc * gap)


def botoes_jogo(screen, botoes, selecionados, wrong, helps, fix, base, alternativo, switch_white):
    # Imagens
    if switch_white:
        helpm = pygame.image.load('Images/text/small/help_b.png')
        fixm = pygame.image.load('Images/text/small/fix_b.png')
        wrongm = pygame.image.load('Images/text/small/wrong_b.png')
        finishm = pygame.image.load('Images/text/small/finish_b.png')
    else:
        helpm = pygame.image.load('Images/text/small/help_w.png')
        fixm = pygame.image.load('Images/text/small/fix_w.png')
        wrongm = pygame.image.load('Images/text/small/wrong_w.png')
        finishm = pygame.image.load('Images/text/small/finish_w.png')

    # Coordenadas iniciais
    largura = 150
    altura = 75
    gap = 20
    y = 770
    x = 110

    # Coordenadas calculadas
    x1 = Print.calcula_inicio(1, x, largura, gap)
    x2 = Print.calcula_inicio(2, x, largura, gap)
    x3 = Print.calcula_inicio(3, x, largura, gap)

    lista = [[(x3, y), base, finishm, 3]]

    if wrong:
        lista.append([(x, y), base, wrongm, 0])
    if fix:
        lista.append([(x1, y), base, fixm, 1])
    if selecionados and helps:
        lista.append([(x2, y), base, helpm, 2])

    deteta_rato_spec(lista, botoes, alternativo, altura, largura)
    desenha_botoes(screen, lista)


def imprime_alt_erd(lista, screen, img):
    # Imprimes os quadrados das celulas que são para ser alteradas
    for linha, coluna in lista:
        imp_x = atualiza_vars(X_INICIAL, coluna)
        imp_y = atualiza_vars(Y_INICIAL, linha)
        screen.blit(img, (imp_x, imp_y))


def imprime_jog(linha, coluna, colour, screen):
    # Imprime a localização do jogador
    imp_x = atualiza_vars(X_INICIAL, coluna)
    imp_y = atualiza_vars(Y_INICIAL, linha)

    screen.blit(colour, (imp_x, imp_y))


def imprime_mat(mat, screen, cor_letras):
    # Imprime a matriz
    x = X_INICIAL
    y = Y_INICIAL
    linha = 0
    colum = 0
    my_font = pygame.font.SysFont("monospace", 65)

    for line in mat:
        for elem in line:
            if not elem == -1:
                text = str(elem)
                label = my_font.render(text, 1, cor_letras)
                centro = label.get_rect(center=(x + (70 / 2), y + (70 / 2)))
                screen.blit(label, centro)

            if colum == 2:
                x += BIG_UPDATE
                colum = 0
            else:
                x += SMALL_UPDATE
                colum += 1

        if linha == 2:
            y += BIG_UPDATE
            linha = 0
            x = X_INICIAL
        else:
            y += SMALL_UPDATE
            linha += 1
            x = X_INICIAL
