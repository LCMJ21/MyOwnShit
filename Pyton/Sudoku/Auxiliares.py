from random import randint


def nao_pertence(lista, valor):
    # Verfifca se o valor esta na lista

    for num in lista:
        if valor == num:
            return False
    return True


def lista_aleatoria():
    # Cria uma linha com inteiros aleatorios

    lista = []
    cc = 9
    while cc:
        value = randint(1, 9)
        if nao_pertence(lista, value):
            lista.append(value)
            cc -= 1
    return lista


def copia_lista(lista):
    # Copia uma lista para uma nova

    new = []
    for valor in lista:
        new.append(valor)
    return new


def empurra(lista, quant):
    # dá shift a lista quant vezes, numa lista nova

    new = copia_lista(lista)
    while quant:
        new.append(new.pop(0))
        quant -= 1

    return new


def print_mat(mat):
    # Dá print a uma matriz
    for linha in mat:
        print(*linha)


def verifa_lc(linha):
    # Verifca uma coluna ou linha está correta
    linha.sort()
    if linha[0] == -1:
        return False
    else:
        cc = 1
        for elem in linha:
            if not cc == elem:
                return False
            cc += 1
    return True


def builcoluna(mat, coluna):
    # Constroi uma coluna de uma determina matriz
    col = []
    for linha in mat:
        col.append(linha[coluna])
    return col
