
def findpos(pos):
    pos = pos.replace(" ", "")

    if pos == "G" or pos == "SK":
        return 0
    elif pos == "CD" or pos == "BPD" or pos == "NCB":
        return 1
    elif pos == "WB" or pos == "FB" or pos == "NFB" or pos == "CWB" or pos == "IWB":
        return 2
    elif pos == "DM" or pos == "DLP" or pos == "BWM" or pos == "A" or pos == "HB" or pos == "RGA" or pos == "RPM" \
            or pos == "CM" or pos == "BBM" or pos == "AP" or pos == "BWM" or pos == "AM" or pos == "T" \
            or pos == "EG" or pos == "ST" or pos == "MEZ" or pos == "CAR":
        return 3
    elif pos == "WM" or pos == "W" or pos == "DW" or pos == "WP" or pos == "IW" or pos == "AP" or pos == "IF" \
            or pos == "T" or pos == "WTM" or pos == "RMD":
        return 4
    elif pos == "DLF" or pos == "AF" or pos == "TM" or pos == "P" or pos == "CF" or pos == "PF" or pos == "F9":
        return 5


def formatNome(nome):
    nome = nome.replace(" ", "")

    cc = 0
    for c in nome:
        if cc != 0 and 'A' <= c <= 'Z':
            nome = nome[:cc] + " " + nome[cc:]
            return nome
        cc += 1

    return nome


def leEquipa(filepath, liga, equipa):
    rand = 95
    f = open(filepath, "r", encoding="utf-8")
    liga[equipa] = []

    for x in range(0, 9):
        f.readline()

    while f.readline():
        jogador = []
        linha = f.readline()
        if linha == "\n":
            break

        linha = linha.split("|")

        if (linha[3] == ' -   '):
            linha[3] = str(rand)
            rand -= 1

        jogador.append(formatNome(linha[1]))
        jogador.append(findpos(linha[2]))

        jogador.append(int(linha[3]))
        jogador.append(int(linha[4]) * 5)
        jogador.append(int(linha[5]) * 5)
        jogador.append(int(linha[6]) * 5)
        jogador.append(int(linha[7]) * 5)
        jogador.append(int(linha[8]) * 5)
        jogador.append(int(linha[9]) * 5)
        jogador.append(int(linha[10]) * 5)
        jogador.append(int(linha[11]) * 5)
        jogador.append(int(linha[12]) * 5)
        jogador.append(int(linha[13]) * 5)
        jogador.append(int(linha[14]) * 5)
        jogador.append(int(linha[15]) * 5)
        jogador.append(int(linha[16]) * 5)
        jogador.append(int(linha[17]) * 5)
        jogador.append(int(linha[18]) * 5)
        jogador.append(int(linha[19]) * 5)
        jogador.append(int(linha[20]) * 5)
        jogador.append(int(linha[21]) * 5)
        jogador.append(int(linha[22]) * 5)
        jogador.append(int(linha[23]) * 5)
        jogador.append(int(linha[24]) * 5)
        jogador.append(int(linha[25]) * 5)
        jogador.append(int(linha[26]) * 5)
        jogador.append(int(linha[27]) * 5)
        jogador.append(int(linha[28]) * 5)
        jogador.append(int(linha[29]) * 5)
        jogador.append(int(linha[30]) * 5)
        jogador.append(int(linha[31]) * 5)
        jogador.append(int(linha[32]) * 5)
        jogador.append(int(linha[33]) * 5)
        jogador.append(int(linha[34]) * 5)
        jogador.append(int(linha[35]) * 5)
        jogador.append(int(linha[36]) * 5)
        jogador.append(int(linha[37]) * 5)

        #print(jogador)

        liga[equipa].append(jogador)


def leLiga(liga, equipas, path):
    for eq in equipas:
        leEquipa(path + "/" + eq, liga, eq)


def printliga(filepath, liga):
    f = open(filepath, "w", encoding="utf-8")

    for equipa in liga:
        f.write(equipa[:-4] + "\n")
        for j in liga[equipa]:
            f.write(j[0] + ";")
            i = 1
            while i < 37:
                f.write(str(j[i]) + ";")
                i += 1
            f.write("\n")
        f.write("\n")


ALL = {}

ING_Teams = ["Arsenal.rtf","Chelsea.rtf",
             "Everton.rtf","Leicester City.rtf","Liverpool.rtf","Manchester City.rtf",
             "Manchester United.rtf","Tottenham.rtf","West Ham United.rtf","Wolves.rtf"]
leLiga(ALL, ING_Teams, "Premier League")

equipaslaLiga = ["Atl. Madrid.rtf", "Barcelona.rtf", "Real Madrid.rtf", "Real Sociedad.rtf", "Sevilla.rtf", "Real Betis.rtf", "Villarreal.rtf"
    , "Ath. Bilbao.rtf", "Valencia.rtf"]
leLiga(ALL, equipaslaLiga, "La Liga")

equipasSerieA = ["Inter.rtf", "AC Milan.rtf", "Juventus.rtf", "Atalanta.rtf", "Napoli.rtf", "Roma.rtf"
    , "Lazio.rtf", "Sassuolo.rtf", "Udinese.rtf", "Fiorentina.rtf"]
leLiga(ALL, equipasSerieA, "Serie A")

equipasNOS = ["Benfica.rtf", "Boavista.rtf", "Belenenses.rtf", "Famalicão.rtf", "Farense.rtf", "Porto.rtf"
    , "Gil Vicente.rtf", "Marítimo.rtf", "Moreirense.rtf", "Nacional.rtf", "Paços Ferreira.rtf", "Portimonense.rtf"
    , "Rio Ave.rtf", "Santa Clara.rtf", "Braga.rtf", "Sporting.rtf", "Tondela.rtf", "V. Guimarães.rtf"]
leLiga(ALL, equipasNOS, "Liga Nos")

equipasLigue1 = ["LOSC Lille.rtf","Paris SG.rtf","Olympique Lyon.rtf","Monaco.rtf","Marseille.rtf"]
leLiga(ALL, equipasLigue1, "Ligue 1")

equipasBundesliga = ["Bayern Munich.rtf","RB Leipzig.rtf","VfL Wolfsburg.rtf","Eintracht Frankfurt.rtf","Borussia Dortmund.rtf","Bayer 04 Leverkusen.rtf",
                     "Borussia Mönchengladbach.rtf","TSG Hoffenheim.rtf","Schalke 04.rtf"]
leLiga(ALL, equipasBundesliga, "Bundesliga")




printliga("database.txt", ALL)
