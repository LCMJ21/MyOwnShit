# carro é da forma [(timer, rua, lista)]
def updateTime(carros):
    for c in carros:
        if (c[0] > 0):
            c[0] -= 1

    return carros

# ruas = (ini, fin, len)
# lista = [ruas]
# carro é da forma [(timer, interinse, lista)]
def updateRua(carros, rua, dictruas):
    for c in carros:
        if c[0] == 0:
            proxrua = dictruas[c[2][0]]
            if c[1] == rua and c[2][0] != []:
                c[0] = proxrua[2] + 1
                c[1] = c[2][0]
                c[2] = c[2][1:]

    return carros

def addrua(rua, resposta):
    if (resposta and resposta[-1][1] == rua):
        resposta[-1][0] += 1
    else:
        add = [1, rua]
        resposta.append(add)

# carro (timer,nodo,[ruas])
def escolheRua(temp, ruas):
    distancias = []
    for carro in temp:
        tam = 0
        for street in carro[2]:
            tam += ruas[street][2]
        distancias.append([tam, street])

    distancias.sort(key=lambda x: x[0])

    return distancias[0][1]


# Opening file
with open("a.txt", 'r', encoding='utf-8') as f:
    # Input primeira linha
    inicio = f.readline()
    inicio = inicio.split(" ")

    duracao = int(inicio[0])  # Duração simulacao
    intersec = int(inicio[1])  # Número interseções
    nruas = int(inicio[2])  # Número de ruas
    ncarros = int(inicio[3])  # Número de carros
    pontos = int(inicio[4])  # Bónus - chegar destino antes do tempo

    # ler info ruas
    # Formato do dicionário
    #   ruas[] = { ("oxford st" : (init, fin, time) ),
    #              ("augusta st": (init, fin, time))
    #            }
    # init -> nodo que marca o início da rua
    # fin  -> nodo que marca o final da rua
    # time -> tempo que a rua demora a ser atravessada

    ruas = {}
    for i in range(0, nruas):
        linhaR = f.readline()
        linhaR = linhaR.split(" ")
        ruas[linhaR[2]] = (int(linhaR[0]), int(linhaR[1]), int(linhaR[3]))

    # ler caminhos dos carros
    # caminhos[i] diz respeito ao caminho efetuado pelo i-ésimo carro
    carros = []
    for i in range(0, ncarros):
        linhaC = f.readline()
        linhaC = linhaC.replace("\n", "")
        linhaC = linhaC.split(" ")
        carros.append([0, ruas[linhaC[1]][0], linhaC[1:]])

    carros.sort(key=lambda x: (x[0], x[1]))

    resposta = []
    temp = []
    for t in range(0, duracao):

        # analisar timer=0

        # loop que analiza uma interseção de cada vez e atualiza o array carros
        for intersecao in range(0, intersec):
            temp = list(filter(lambda x: x[0] == 0 and x[1] == intersecao, carros))
            if len(temp) > 0:
                rua = escolheRua(temp, ruas)
                carros = updateRua(carros, rua, ruas)
                addrua(rua, resposta)

        # update - vai decrementar os timers que não estão a 0
        carros = updateTime(carros)

with open("res.txt", 'w', encoding='utf-8') as file:
    file.write(str(len(resposta)) + "\n")
    for inter in resposta:
        file.write(str(inter) + "\n")
        for (num, rua) in inter:
            file.write(rua + " " + str(num) + "\n")
