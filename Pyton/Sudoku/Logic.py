from random import randint
import Auxiliares


def chose_one(lista):
    # Escolhe uma elemento aleatorio de uma lista
    value = randint(0, len(lista) - 1)
    return lista[value]


def copia_matriz(mat):
    # Copia uma matriz para ter duas matrizes iguais
    jogo = []

    for line in mat:
        jogo.append(Auxiliares.copia_lista(line))

    return jogo


def matrizaleatoria():
    # Constroi uma matriz de numeros aleatorios

    mat = []
    linha = Auxiliares.lista_aleatoria()
    order = [0, 3, 3, 1, 3, 3, 1, 3, 3]

    for indice in order:
        linha = Auxiliares.empurra(linha, indice)
        mat.append(linha)

    return mat


def jogo_acabou(mat):
    # Verifica se a matriz mostra um jogo acabado
    linha = 0
    coluna = 0
    while linha < 9:
        if not Auxiliares.verifa_lc(Auxiliares.copia_lista(mat[linha])):
            return False
        while coluna < 9:
            if not Auxiliares.verifa_lc(Auxiliares.builcoluna(mat, coluna)):
                return False
            coluna += 1
        linha += 1
    return True


def prepara_mat(mat, cc, retirados):
    # Prepara a matriz para um novo jogo

    jogo = copia_matriz(mat)

    while cc:
        linha = randint(0, 8)
        coluna = randint(0, 8)
        valor = jogo[linha][coluna]
        if not valor == -1:
            jogo[linha][coluna] = -1
            retirados.append((linha, coluna))
            cc -= 1

    return jogo


def constroi_errados(alterados, jogo, mat):
    errados = []

    for x, y in alterados:
        if jogo[x][y] != -1 and jogo[x][y] != mat[x][y]:
            errados.append((x, y))

    return errados
