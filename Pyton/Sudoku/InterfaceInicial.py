import pygame
import Print
import files
import Results


def cria_lista(switch_white, x, y, altura, largura, gap, base):
    # Imagens
    if switch_white:
        start = pygame.image.load('Images/text/start_b.png')
        options = pygame.image.load('Images/text/options_b.png')
        colours = pygame.image.load('Images/text/colours_b.png')
        gravar = pygame.image.load('Images/text/save_b.png')
        best = pygame.image.load('Images/text/best_b.png')
        leave = pygame.image.load('Images/text/leave_b.png')
    else:
        start = pygame.image.load('Images/text/start_w.png')
        options = pygame.image.load('Images/text/options_w.png')
        colours = pygame.image.load('Images/text/colours_w.png')
        gravar = pygame.image.load('Images/text/save_w.png')
        best = pygame.image.load('Images/text/best_w.png')
        leave = pygame.image.load('Images/text/leave_w.png')

    # Coordenadas calculadas
    x1 = Print.calcula_inicio(1, x, largura, gap)
    x2 = Print.calcula_inicio(2, x, largura, gap)
    y1 = Print.calcula_inicio(1, y, altura, gap)
    y2 = Print.calcula_inicio(2, y, altura, gap)

    lista = [[(x, y), base, start],
             [(x, y1), base, options],
             [(x1, y1), base, colours],
             [(x, y2), base, gravar],
             [(x1, y2), base, best],
             [(x2, y2), base, leave]]

    return lista


def inicio(screen, botoes, base, alternativo, switch_white):
    # Coordenadas iniciais
    altura = 100
    largura = 200
    gap = 50
    x = 60
    y = 390

    lista = cria_lista(switch_white, x, y, altura, largura, gap, base)

    Print.deteta_rato(lista, botoes, alternativo, altura, largura)

    Print.print_imgs(screen, switch_white)
    Print.desenha_botoes(screen, lista)


def ativa(botoes, ecra, game_over, first, second, third, fourth, switch_white, dificuldade, wrong, helps, fix):

    if botoes[0]:
        ecra = 4
        botoes[0] = False

    if botoes[1]:
        ecra = 2
        botoes[1] = False

    if botoes[2]:
        ecra = 6
        botoes[2] = False

    if botoes[3]:
        defenicoes = files.gera_def(first, second, third, fourth, switch_white, dificuldade, wrong, helps, fix)
        files.gravar_def(defenicoes)
        botoes[3] = False

    if botoes[4]:
        ecra = 8
        botoes[4] = False

    if botoes[5]:
        game_over = True
        botoes[5] = False

    return ecra, game_over


def strart(screen, botoes, ecra, base, alternativo, game_over, first, second, third, fourth, switch_white, dificuldade, wrong, helps, fix):
    inicio(screen, botoes, base, alternativo, switch_white)
    ecra, game_over = ativa(botoes, ecra, game_over, first, second, third, fourth, switch_white, dificuldade, wrong, helps, fix)
    return ecra, game_over
