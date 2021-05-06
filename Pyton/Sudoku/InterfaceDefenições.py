import pygame
import Print

# Cores
WHITHE = (255, 255, 255)
GREY = (75, 75, 75)


def inicia_cor(botoes_def, atv, n_atv):
    # Define as corres dos botoes de yes and no
    cor = []
    cc = 1
    while cc < 4:
        if botoes_def[cc]:
            cor.append((atv, n_atv))
        else:
            cor.append((n_atv, atv))
        cc += 1

    return cor[0], cor[1], cor[2]


def deteta_rato_def(lista, botoes, alternativo, altura, largura):
    # Deteta rato
    mouse = pygame.mouse.get_pos()
    click = pygame.mouse.get_pressed()

    cc = 0
    for (x, y), image, tex in lista:
        if x <= mouse[0] <= x + largura:
            if y <= mouse[1] <= y + altura:
                lista[cc][1] = alternativo
                if click[0] and cc < 3:
                    botoes[cc + 1] = True
                elif click[0]:
                    botoes[cc - 2] = False
        cc += 1


def deteta_rato_def2(lista, botoes, alternativo, altura, largura):
    # Deteta rato
    mouse = pygame.mouse.get_pos()
    click = pygame.mouse.get_pressed()

    cc = 0
    for (x, y), image, tex in lista:
        if x <= mouse[0] <= x + largura:
            if y <= mouse[1] <= y + altura:
                lista[cc][1] = alternativo
                if click[0]:
                    if cc == 1:
                        botoes[4] = True
                    else:
                        botoes[0] = True

        cc += 1


def cria_listas(y, x, altura, largura, gap, switch_white, botoes, small_alternativo, small_base, base, soma_peq):
    # Coordenadas calculadas
    y1 = Print.calcula_inicio(1, y, altura, gap)
    y2 = Print.calcula_inicio(2, y, altura, gap)
    y3 = Print.calcula_inicio(3, y, altura, gap)
    y4 = Print.calcula_inicio(4, y, altura, gap)

    x1 = Print.calcula_inicio(1, x, largura, gap)
    x2 = Print.calcula_inicio(2, x, largura, gap)

    # Imagens
    if switch_white:
        skill = pygame.image.load('Images/text/skill_b.png')
        back = pygame.image.load('Images/text/back_b.png')
        yes = pygame.image.load('Images/text/small/yes_b.png')
        no = pygame.image.load('Images/text/small/no_b.png')
    else:
        skill = pygame.image.load('Images/text/skill_w.png')
        back = pygame.image.load('Images/text//back_w.png')
        yes = pygame.image.load('Images/text/small/yes_w.png')
        no = pygame.image.load('Images/text/small/no_w.png')

    cor = inicia_cor(botoes, small_alternativo, small_base)

    lista_basic = [[(x, y), base, skill],
                   [(x, y4), base, back]]

    lista_spec = [[(x1, y1 + soma_peq), cor[0][0], yes],
                  [(x1, y2 + soma_peq), cor[1][0], yes],
                  [(x1, y3 + soma_peq), cor[2][0], yes],
                  [(x2, y1 + soma_peq), cor[0][1], no],
                  [(x2, y2 + soma_peq), cor[1][1], no],
                  [(x2, y3 + soma_peq), cor[2][1], no]]

    return lista_basic, lista_spec


def lista_crescente(lista_basic, x, y, altura, gap, base, switch_white):
    # Coordenadas calculadas
    y1 = Print.calcula_inicio(1, y, altura, gap)
    y2 = Print.calcula_inicio(2, y, altura, gap)
    y3 = Print.calcula_inicio(3, y, altura, gap)

    if switch_white:
        helps = pygame.image.load('Images/text/help_b.png')
        fix = pygame.image.load('Images/text/fix_b.png')
        wrong = pygame.image.load('Images/text/wrong_b.png')
    else:
        helps = pygame.image.load('Images/text/help_w.png')
        fix = pygame.image.load('Images/text/fix_w.png')
        wrong = pygame.image.load('Images/text/wrong_w.png')

    lista_basic.append([(x, y1), base, helps])
    lista_basic.append([(x, y2), base, fix])
    lista_basic.append([(x, y3), base, wrong])


def imprime_texto(switch_white, dificuldade, screen, x, y, largura, altura, gap):
    # Imprime texto
    x1 = Print.calcula_inicio(1, x, largura, gap)

    if switch_white:
        colour = GREY
    else:
        colour = WHITHE

    my_font = pygame.font.SysFont("monospace", 65)
    text = str(dificuldade)
    label = my_font.render(text, 1, colour)
    centro = label.get_rect(center=(x1 + (largura / 2), y + (altura / 2)))
    screen.blit(label, centro)


def imprime_defenicoes(screen, botoes, base, alternativo, dificuldade, small_base, small_alternativo, switch_white):
    # Coordenadas iniciais
    altura = 100
    largura = 200
    gap = 50
    x = 90
    y = 100
    soma_peq = 12
    largura_peq = 150
    altura_peq = 75

    lista_basic, lista_spec = cria_listas(y, x, altura, largura, gap, switch_white, botoes, small_alternativo,
                                          small_base, base, soma_peq)

    deteta_rato_def2(lista_basic, botoes, alternativo, altura, largura)
    deteta_rato_def(lista_spec, botoes, small_alternativo, altura_peq, largura_peq)

    lista_crescente(lista_basic, x, y, altura, gap, base, switch_white)

    Print.desenha_botoes(screen, lista_basic)
    Print.desenha_botoes(screen, lista_spec)

    imprime_texto(switch_white, dificuldade, screen, x, y, largura, altura, gap)


def ativa(botoes, ecra):
    # Ativa os botoes

    if botoes[0]:
        ecra = 3
        botoes[0] = False

    if botoes[1]:
        helps = True
    else:
        helps = False

    if botoes[2]:
        fix = True
    else:
        fix = False

    if botoes[3]:
        wrong = True
    else:
        wrong = False

    if botoes[4]:
        ecra = 1
        botoes[4] = False

    return ecra, wrong, fix, helps


def defenicoes(screen, botoes, ecra, dificuldade, base, alternativo, small_base, small_alternativo, switch_white):
    # Faz o menu das defenicoes
    imprime_defenicoes(screen, botoes, base, alternativo, dificuldade, small_base, small_alternativo, switch_white)
    ecra, wrong, fix, helps = ativa(botoes, ecra)
    return wrong, helps, fix, ecra
