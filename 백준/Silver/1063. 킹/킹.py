import sys
input = sys.stdin.readline

x, y, N = input().split()
king = [ord(x[0]), int(x[1])]  #ord는 문자를 아스키코드로 변환하는 함수 
rock = [ord(y[0]), int(y[1])]

#입력 방향에 따른 이동 좌표를 딕셔너리에 저장
command = {'R':(1,0), 'L':(-1,0), 'B':(0,-1), 'T':(0,1), 'RT':(1,1), 'LT':(-1,1), 'RB':(1,-1), 'LB':(-1,-1)}

for _ in range(int(N)):
    x = input().rstrip()

    nx = king[0] + command[x][0]  
    ny = king[1] + command[x][1]
    #이동할 위치가 범위 내인지 확인 
    if 65 > nx or 72 < nx or ny < 1 or ny > 8:
        continue
    
    #킹이 이동할 위치에 돌이 있는 경우 
    if nx == rock[0] and ny == rock[1]:
        nrx = rock[0] + command[x][0]
        nry = rock[1] + command[x][1]

        if 65 > nrx or 72 < nrx or nry < 1 or nry > 8:
            continue
        #돌의 위치도 이동 
        rock[0] = nrx
        rock[1] = nry

    king[0] = nx 
    king[1] = ny

#chr함수로 아스키코드를 문자로 변환 
print(chr(king[0]) + str(king[1]))
print(chr(rock[0]) + str(rock[1]))