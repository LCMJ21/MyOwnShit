import pygame
import sys
import Jogo
import Print
import InterfaceInicial
import InterfaceDificuldade
import InterfaceFinal
import InterfaceDefenições
import InterfaceCores
import InterfaceSelectCores
import files
import InterfaceResultados
import Results

# Ecra para definir a fase que o jogo representra
# INICIO = 1
# DEFENIÇÕES = 2
# SKILL = 3
# JOGO = 4
# FINAL = 5
# CORES = 6
# SELECT_CORES = 7
ecra = 1

# Tamanho do ecra
TAMANHO = 880
pygame.init()
screen = pygame.display.set_mode((TAMANHO, TAMANHO))
game_over = False
pygame.display.set_caption("Definitely Not Sudoku")
icon = pygame.image.load('Images/Logo.png')
pygame.display.set_icon(icon)

# Variaveis de inicialização
dificuldade = 35
n_select = False
times = 1
strart_time = -times
botoes_jogo = [False, False, False, False]
botoes_inicio = [False, False, False, False, False, False]
botoes_defenicao = [False, True, True, True, False]
botoes_dificuldade = [False, False, False, False, False, False]
botoes_cores = [False, False, False, False, False, False, False]
botoes_select_cores = [False, False, False, False, False, False, False, False, False, False, False]
botoes_fim = [False, False]
botoes_resultados = [False, False]
inicio = True
result = "Erro"
switch_white = False

# Variaveis de jogo
errados = []
mat = []
alterados = []
jogo = []
coluna = 0
linha = 0
tempo = 0
best_results = Results.ler_resultados()

# Cores
first = 1
second = 0
third = 4
fourth = 7

defenicoes = files.gera_def(first, second, third, fourth, switch_white, dificuldade, botoes_defenicao[1], botoes_defenicao[2], botoes_defenicao[3])
defenicoes = files.ler_def(defenicoes)
first, second, third, fourth, switch_white, dificuldade, botoes_defenicao[1], botoes_defenicao[2], botoes_defenicao[3] = files.act_def(defenicoes)

wrong = botoes_defenicao[1]
helps = botoes_defenicao[2]
fix = botoes_defenicao[3]

# Carrega corres botoes
base, alternativo, small_base, small_alternativo, mini_base, mini_alternativo, small_third, mini_third, small_fourth, \
    mini_fourth = Print.carrega_cores(first, second, third, fourth)
colour = mini_base
tela_cor = 0

# Ciclo do jogo
while not game_over:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()

        base, alternativo, small_base, small_alternativo, mini_base, mini_alternativo, small_third, mini_third,\
            small_fourth, mini_fourth = Print.carrega_cores(first, second, third, fourth)
        cor_letras = Print.print_fundo(screen, switch_white)

        if ecra == 1:
            # Ecra inicial do jogo
            inicio = True
            ecra, game_over = InterfaceInicial.strart(screen, botoes_inicio, ecra, base, alternativo, game_over, first, second,
                                                      third, fourth, switch_white, dificuldade, wrong, helps, fix)
        elif ecra == 2:
            # Menu das defenições
            wrong, helps, fix, ecra = InterfaceDefenições.defenicoes(screen, botoes_defenicao, ecra, dificuldade, base,
                                                                     alternativo, small_base, small_alternativo,
                                                                     switch_white)
        elif ecra == 3:
            # Menu das dificuldades
            dificuldade, ecra = InterfaceDificuldade.imprime_dificuldade(screen, dificuldade, botoes_dificuldade, base,
                                                                         alternativo, switch_white)
        elif ecra == 4:
            # Jogo
            mat, n_select, colour, jogo, linha, coluna, botoes_jogo, errados, alterados, strart_time, times, \
                inicio, ecra, tempo, result, best_results = Jogo.sudoku(screen, event, n_select, colour, linha, coluna, alterados,
                                                          errados, mat, jogo, botoes_jogo, strart_time, times, wrong,
                                                          helps, fix, dificuldade, inicio, ecra, tempo, small_base,
                                                          small_alternativo, mini_base, mini_alternativo, mini_third,
                                                          mini_fourth, switch_white, cor_letras, best_results)
        elif ecra == 5:
            # Ecra no fim do jogo
            ecra, game_over = InterfaceFinal.final(screen, botoes_fim, ecra, result, tempo, game_over, base,
                                                   alternativo, switch_white, cor_letras)
        elif ecra == 6:
            # Ecra de escolha de cores
            ecra, tela_cor, switch_white = InterfaceCores.i_cores(screen, botoes_cores, ecra, base, alternativo, tela_cor, small_base,
                                                    small_alternativo, small_third, small_fourth, switch_white)
        elif ecra == 7:
            # Ecra de escolha de uma cor
            ecra, tela_cor, first, second, third, fourth = InterfaceSelectCores.i_select_cores(screen,
                                                                                               botoes_select_cores,
                                                                                               ecra, base, alternativo,
                                                                                               tela_cor, first, second,
                                                                                               third, fourth,
                                                                                               switch_white)
        elif ecra == 8:
            ecra, best_results = InterfaceResultados.resultados(screen, botoes_resultados, ecra, base, alternativo, switch_white, best_results, cor_letras)

    pygame.display.update()
