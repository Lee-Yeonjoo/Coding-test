import sys
input = sys.stdin.readline
INF = 1e9

N, M, B = map(int, input().split())
ground = []
min_height = INF
max_height = 0
for _ in range(N):
    x = list(map(int, input().split()))
    ground.append(x)
    min_x = min(x)
    max_x = max(x)
    if min_height > min_x:
        min_height = min_x
    if max_height < max_x:
        max_height = max_x

time = INF
height = 0
for h in range(min_height, max_height+1):
    count = 0
    block = B
    install = 0
    remove = 0
    #깎는 과정을 먼저하여 블록을 확보 
    for i in range(N):
        for j in range(M):
            x = ground[i][j] - h
            if x == 0:
                continue
            
            #몇 번 쌓아야 하는지 카운트
            if x < 0:
                install += abs(x)

            #몇 번 깎아야 하는지 카운트
            if x > 0:
                remove += x
    
    block += remove  #깎은 만큼 블록의 수 증가
    count += 2*remove
    if install <= block:
        count += install
    else:  #설치할 블록의 수가 모자라므로 continue
        continue

    if time >= count:
        time = count
        if height < h:
            height = h

print(time, height)