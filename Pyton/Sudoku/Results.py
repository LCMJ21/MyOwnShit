def adiciona_lista(tempo, skill, lista):
    coef = tempo * (82 - skill)

    none = True
    cc = 0
    for tempo_comb, skill_comb in lista:
        coef_comb = tempo_comb * (82 - skill_comb)
        if coef < coef_comb:
            lista.insert(cc, (tempo, skill))
            none = False
            break
        cc += 1

    if none:
        lista.insert(cc, (tempo, skill))

    if len(lista) > 10:
        lista.pop()


def gravar_resultados(lista):
    with open('resultados.txt', 'w') as file:
        for (temp, skill) in lista:
            file.write("%f\n%d\n" % (temp, skill))


def ler_resultados():
    lista = []
    with open('resultados.txt', 'r') as file:
        lines = file.readlines()
        cc = 0
        while cc < len(lines):
            tempo = float(lines[cc])
            skill = int(lines[cc + 1])
            lista.append((tempo, skill))
            cc += 2

    return lista