import pygame
import Print

WHITHE = (255, 255, 255)
GREY = (75, 75, 75)


def atualiza_dificuldade(dificuldade, botoes_dificuldade, ecra):
    # Ativa os botoes
    if botoes_dificuldade[0]:
        dificuldade = 20
        botoes_dificuldade[0] = False

    if botoes_dificuldade[1]:
        dificuldade = 35
        botoes_dificuldade[1] = False

    if botoes_dificuldade[2]:
        dificuldade = 50
        botoes_dificuldade[2] = False

    if botoes_dificuldade[3]:
        if dificuldade > 10:
            dificuldade -= 1
        botoes_dificuldade[3] = False

    if botoes_dificuldade[4]:
        if dificuldade < 70:
            dificuldade += 1
        botoes_dificuldade[4] = False

    if botoes_dificuldade[5]:
        ecra = 2
        botoes_dificuldade[5] = False

    return dificuldade, ecra


def cria_listas(switch_white, x, y, largura, altura, gap, base):
    # Imagens
    if switch_white:
        easy = pygame.image.load('Images/text/easy_b.png')
        medium = pygame.image.load('Images/text/medium_b.png')
        hard = pygame.image.load('Images/text/hard_b.png')
        leave = pygame.image.load('Images/text/leave_b.png')
        right = pygame.image.load('Images/text/right_b.png')
        left = pygame.image.load('Images/text/left_b.png')
    else:
        easy = pygame.image.load('Images/text/easy_w.png')
        medium = pygame.image.load('Images/text/medium_w.png')
        hard = pygame.image.load('Images/text/hard_w.png')
        leave = pygame.image.load('Images/text/leave_w.png')
        right = pygame.image.load('Images/text/right_w.png')
        left = pygame.image.load('Images/text/left_w.png')

    # Coordenadas calculadas
    y1 = Print.calcula_inicio(1, y, altura, gap)
    y2 = Print.calcula_inicio(2, y, altura, gap)

    x1 = Print.calcula_inicio(1, x, largura, gap)
    x2 = Print.calcula_inicio(2, x, largura, gap)

    lista = [[(x, y), base, easy],
             [(x1, y), base, medium],
             [(x2, y), base, hard],
             [(x, y1), base, left],
             [(x2, y1), base, right],
             [(x1, y2), base, leave]]

    return lista


def imprime_texto(screen, switch_white, dificuldade, x, y, largura, altura, base, gap):
    if switch_white:
        colour = GREY
    else:
        colour = WHITHE

    y1 = Print.calcula_inicio(1, y, altura, gap)
    x1 = Print.calcula_inicio(1, x, largura, gap)

    # Adiciona o numero
    my_font = pygame.font.SysFont("monospace", 65)
    text = str(dificuldade)
    label = my_font.render(text, 1, colour)
    centro = label.get_rect(center=(x1 + (largura / 2), y1 + (altura / 2)))

    screen.blit(base, (x1, y1))
    screen.blit(label, centro)


def imprime_dificuldade(screen, dificuldade, botoes, base, alternativo, switch_white):
    # Coordenadas iniciais
    x = 100
    y = 100
    largura = 200
    altura = 100
    gap = 50

    lista = cria_listas(switch_white, x, y, largura, altura, gap, base)

    Print.deteta_rato(lista, botoes, alternativo, altura, largura)

    dificuldade, ecra = atualiza_dificuldade(dificuldade, botoes, 3)

    Print.desenha_botoes(screen, lista)
    imprime_texto(screen, switch_white, dificuldade, x, y, largura, altura, base, gap)
    Print.desenha_botoes(screen, lista)

    return dificuldade, ecra
