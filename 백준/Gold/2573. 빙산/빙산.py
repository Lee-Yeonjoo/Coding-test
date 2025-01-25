import sys
import copy
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
iceberg = []
for i in range(N):
    iceberg.append(list(map(int, input().split())))

#1년 후의 빙산을 기록할 배열 deep copy 해줘야 함 
iceberg_later = copy.deepcopy(iceberg)

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]
def bfs(i, j):
    q = deque()
    q.append((i, j))
    iceberg[i][j] = -1  #시작 노드 방문 처리 
 
    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            #바다(0) 옆이고, 빙산이 다 녹지 않았다면 -1
            if iceberg[nx][ny] == 0 and iceberg_later[x][y] > 0:
                iceberg_later[x][y] -= 1

            #바다 옆이 아닌 경우 dfs
            if iceberg[nx][ny] > 0:
                q.append((nx, ny))
                iceberg[nx][ny] = -1  #방문처리리

count = 0  #빙산 덩어리의 개수
year = 0   #몇 년 지났는지 세는 변수
#빙산 덩어리가 두 덩어리가 될 때까지 반복
while count < 2:
    count = 0
    check = 0  #모두 녹았는지 체크용
    for i in range(N):
        for j in range(M):
            if iceberg[i][j] > 0:
                bfs(i, j)
                count += 1
    year += 1
    
    for i in range(N):
        for j in range(M):
            if iceberg_later[i][j] == 0:
                check += 1
    #두 덩어리 되기 전에 전부 녹았다면 break
    if check == N*M:
        break
    
    #다음 1년 후를 위해 초기화
    iceberg = copy.deepcopy(iceberg_later)

if check == N*M and count < 2:
    print(0)
else:
    print(year-1)