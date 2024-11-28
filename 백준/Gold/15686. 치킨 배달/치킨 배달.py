import sys
from itertools import combinations
input = sys.stdin.readline

N, M = map(int, input().split())
city = [] * N

#도시값 입력 받기
for _ in range(N):
    city.append(list(map(int, input().split())))

#치킨집 좌표 따로 저장
chickens = []
for i in range(N):
    for j in range(N):
        if city[i][j] == 2:
            chickens.append((i, j))

#치킨집들 중 M개 선택하는 조합
chickens = list(combinations(chickens, M))

answer =  10000000
#치킨집 조합 탐색
for k in chickens:  
    dist_sum = 0  #해당 치킨집 조합일 때의 도시 치킨거리
    for i in range(N):
        for j in range(N):
            if city[i][j] != 1:
                continue
        
            #어떤 집에서 치킨집들과의 거리 중 최소거리 저장
            dist_min = 10000000
            for s in k:
                dist = abs(i-s[0]) + abs(j-s[1])
                if dist_min > dist:
                    dist_min = dist
            dist_sum += dist_min

    if answer > dist_sum:
        answer = dist_sum
        
print(answer)