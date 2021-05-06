import pygame
import Print
import Results

WHITHE = (255, 255, 255)
BACKGROUND = (75, 75, 75)

def format_tempo(tempo):
    minutos, seguntos = divmod(tempo, 60)
    horas, minutos = divmod(minutos, 24)
    text = str(int(horas)) + ':' + str(int(minutos)) + ":" + str(int(seguntos)) + "s"
    return text


def print_tempos(screen, best_results, cor_letras):
    y = 90
    x = 125
    x1 = 220
    x2 = 525
    altura = 60
    my_font = pygame.font.SysFont("monospace", 60)
    cc = 1
    for tempo, skill in best_results:
        label = my_font.render(str(cc), 1, cor_letras)
        screen.blit(label, (x, y))
        label = my_font.render(format_tempo(tempo), 1, cor_letras)
        screen.blit(label, (x1, y))
        label = my_font.render(str(skill), 1, cor_letras)
        screen.blit(label, (x2, y))
        y += altura
        cc += 1







def resultados(screen, botoes, ecra, base, alternativo, switch_white, best_results, cor_letras):
    # Coordenadas iniciais botoes
    x = 215
    y = 750
    largura = 200
    altura = 100
    gap = 50

    # Imagens
    if switch_white:
        clean = pygame.image.load('Images/text/clean_b.png')
        back = pygame.image.load('Images/text/back_b.png')
        img = pygame.image.load('Images/best_games_b.png')
    else:
        clean = pygame.image.load('Images/text/clean_w.png')
        back = pygame.image.load('Images/text/back_w.png')
        img = pygame.image.load('Images/best_games_w.png')

    screen.blit(img, (0, 0))

    # Coordenadas calculadas
    x1 = Print.calcula_inicio(1, x, largura, gap)

    lista = [[(x, y), base, clean],
             [(x1, y), base, back]]

    Print.deteta_rato(lista, botoes, alternativo, altura, largura)

    if botoes[0]:
        best_results = []
        Results.gravar_resultados(best_results)
        botoes[0] = False

    if botoes[1]:
        ecra = 1
        botoes[1] = False

    print_tempos(screen, best_results, cor_letras)
    Print.desenha_botoes(screen, lista)

    return ecra, best_results
