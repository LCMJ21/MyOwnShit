import pygame
import Print
import Results

WHITHE = (255, 255, 255)
BACKGROUND = (75, 75, 75)


def print_string(screen, string,altura, cor_letras):
    my_font = pygame.font.SysFont("monospace", 40)
    for y, text in string:
        label = my_font.render(text, 1, cor_letras)
        centro = label.get_rect(center=(440, y + (altura / 2)))
        screen.blit(label, centro)


def format_tempo(tempo):
    minutos, seguntos = divmod(tempo, 60)
    horas, minutos = divmod(minutos, 24)
    text = "Finhished in " + str(int(horas)) + ':' + str(int(minutos)) + ":" + str(int(seguntos)) + "s"
    return text


def final(screen, botoes, ecra, result, tempo, game_over, base, alternativo, switch_white, cor_letras):
    # Coordenadas iniciais
    x = 340
    y = 100
    largura = 200
    altura = 100
    gap = 50

    # Imagens
    if switch_white:
        continuem = pygame.image.load('Images/text/continue_b.png')
        leave = pygame.image.load('Images/text/leave_b.png')
    else:
        continuem = pygame.image.load('Images/text/continue_w.png')
        leave = pygame.image.load('Images/text/leave_w.png')

    # Coordenadas calculadas
    y1 = Print.calcula_inicio(1, y, altura, gap)
    y2 = Print.calcula_inicio(2, y, altura, gap)
    y3 = Print.calcula_inicio(3, y, altura, gap)

    string = [[y, result], [y1, format_tempo(tempo)]]

    lista = [[(x, y2), base, continuem],
             [(x, y3), base, leave]]

    Print.deteta_rato(lista, botoes, alternativo, altura, largura)

    if botoes[0]:
        ecra = 1
        botoes[0] = False

    if botoes[1]:
        game_over = True
        botoes[1] = False

    print_string(screen, string, altura, cor_letras)
    Print.desenha_botoes(screen, lista)

    return ecra, game_over
