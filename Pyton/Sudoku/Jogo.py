import pygame
import time
import PrintJogo
import Logic
import Print
import Results

# Cores
BACKGROUND = (75, 75, 75)
BLACK = (0, 0, 0)


def pertence(coord, alterados):
    for elem in alterados:
        if elem == coord:
            return True
    return False


def keys_numbers(key, mat, linha, coluna, jogo):
    if key == pygame.K_1 or key == pygame.K_KP1:
        return 1
    elif key == pygame.K_2 or key == pygame.K_KP2:
        return 2
    elif key == pygame.K_3 or key == pygame.K_KP3:
        return 3
    elif key == pygame.K_4 or key == pygame.K_KP4:
        return 4
    elif key == pygame.K_5 or key == pygame.K_KP5:
        return 5
    elif key == pygame.K_6 or key == pygame.K_KP6:
        return 6
    elif key == pygame.K_7 or key == pygame.K_KP7:
        return 7
    elif key == pygame.K_8 or key == pygame.K_KP8:
        return 8
    elif key == pygame.K_9 or key == pygame.K_KP9:
        return 9
    elif key == pygame.K_h:
        return mat[linha][coluna]
    elif key == pygame.K_BACKSPACE:
        return -1
    else:
        return jogo[linha][coluna]


def keys_select(key, linha, coluna):
    if key == pygame.K_LEFT and coluna > 0:
        coluna -= 1
    elif key == pygame.K_RIGHT and coluna < 8:
        coluna += 1
    elif key == pygame.K_UP and linha > 0:
        linha -= 1
    elif key == pygame.K_DOWN and linha < 8:
        linha += 1

    return linha, coluna


def ativa(selecionado, linha, coluna, alterados, first, second):
    if selecionado:
        return False, first
    elif pertence((linha, coluna), alterados):
        return True, second


def ativabotoes(botoes_jogo, errados, alterados, jogo, mat, strart_time, times, n_select, linha, coluna, ecra, tempo, best_results, dificuldade):
    result = False

    if botoes_jogo[0]:
        errados = Logic.constroi_errados(alterados, jogo, mat)
        strart_time = time.time()
        botoes_jogo[0] = False
    else:
        actual_time = time.time() - strart_time
        if actual_time >= times:
            errados = []

    if botoes_jogo[1]:
        jogo = Logic.copia_matriz(mat)
        botoes_jogo[1] = False

    if botoes_jogo[2] and n_select:
        jogo[linha][coluna] = mat[linha][coluna]
        botoes_jogo[2] = False

    if botoes_jogo[3]:
        ecra = 5
        tempo = time.time() - tempo
        result = Print.correto(jogo)
        if result:
            Results.adiciona_lista(tempo, dificuldade, best_results)
        Results.gravar_resultados(best_results)
        botoes_jogo[3] = False

    return errados, strart_time, jogo, ecra, tempo, best_results, result


def sudoku(screen, event, n_select, colour, linha, coluna, alterados, errados, mat, jogo, botoes_jogo, strart_time,
           times, wrong, helps, fix, dificuldade, inicio, ecra, tempo, base, alternativos, first, second, third, fourth,
           switch_white, cor_letras, best_results):
    if inicio:
        mat = Logic.matrizaleatoria()
        alterados = []
        jogo = Logic.prepara_mat(mat, dificuldade, alterados)
        tempo = time.time()
        inicio = False
        linha = 0
        coluna = 0
        n_select = False
        colour = first

    if event.type == pygame.KEYDOWN:
        if event.key == pygame.K_KP_ENTER and pertence((linha, coluna), alterados):
            n_select, colour = ativa(n_select, linha, coluna, alterados, first, second)

        if n_select:
            jogo[linha][coluna] = keys_numbers(event.key, mat, linha, coluna, jogo)
        else:
            linha, coluna = keys_select(event.key, linha, coluna)

    PrintJogo.botoes_jogo(screen, botoes_jogo, n_select,  wrong, helps, fix, base, alternativos, switch_white)

    errados, strart_time, jogo, ecra, tempo, best_results, result = ativabotoes(botoes_jogo, errados, alterados, jogo, mat, strart_time,
                                                          times, n_select, linha, coluna, ecra, tempo, best_results, dificuldade)
    PrintJogo.imprime_alt_erd(alterados, screen, third)
    PrintJogo.imprime_jog(linha, coluna, colour, screen)
    PrintJogo.imprime_alt_erd(errados, screen, fourth)
    PrintJogo.imprime_mat(jogo, screen, cor_letras)

    return mat, n_select, colour, jogo, linha, coluna, botoes_jogo, errados, alterados, strart_time, times, inicio, \
                ecra, tempo, result, best_results
