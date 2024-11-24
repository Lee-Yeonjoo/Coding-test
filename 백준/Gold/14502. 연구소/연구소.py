import sys
from collections import deque
import copy
input = sys.stdin.readline

N, M = map(int, input().split())
lab = [[] for i in range(N)]
result = 0

def bfs():
    global result
    queue = deque()
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    temp = copy.deepcopy(lab)

    for i in range(N):
        for j in range(M):
            if temp[i][j] == 2:
                queue.append((i, j))  #바이러스가 temp에서 동시에 확산해야하므로 꼭 큐에 한번에 넣어 줘야한다!!!

    while queue:
        x, y = queue.popleft()

        for i in range(len(dx)):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < N and 0 <= ny < M:
                if temp[nx][ny] == 0:
                    temp[nx][ny] = 2
                    queue.append((nx, ny))
    
    count = 0 
    for i in range(N):
        for j in range(M):
            if temp[i][j] == 0:
                count += 1

    result = max(result, count)

def make_wall(cnt):
    if cnt == 3:
        bfs()
        return  
    
    for i in range(N):
        for j in range(M):
            if lab[i][j] == 0:
                lab[i][j] = 1
                make_wall(cnt + 1)
                lab[i][j] = 0  #벽을 다시 원상복귀(백트래킹)
    
for i in range(N):
    lab[i] = list(map(int, input().split()))

make_wall(0)
print(result)