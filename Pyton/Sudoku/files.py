def gera_def(i1, i2, i3, i4, s1, i5, s2, s3, s4):
    defenicoes = [i1, i2, i3, i4, int(s1), i5, int(s2), int(s3), int(s4)]
    return defenicoes


def act_def(defenicoes):
    return defenicoes[0], defenicoes[1], defenicoes[2], defenicoes[3], bool(defenicoes[4]), defenicoes[5]\
        , bool(defenicoes[6]), bool(defenicoes[7]), bool(defenicoes[8])

def gravar_def(defenicoes):
    with open('def.txt', 'w') as file:
        for num in defenicoes:
            file.write("%d\n" % num)


def ler_def(defenicoes):
    with open('def.txt', 'r') as file:
        cc = 0
        for line in file:
            defenicoes[cc] = int(line)
            cc += 1

    return defenicoes
