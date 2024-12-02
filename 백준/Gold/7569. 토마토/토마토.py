import sys
from collections import deque
input = sys.stdin.readline

M, N, H = map(int, input().split())

def bfs():
    queue = deque()
    #방향벡터 - 위, 아래, 왼, 오, 앞, 뒤
    dx = [0, 0, 0, 0, 1, -1]
    dy = [0, 0, -1, 1, 0, 0]
    dz = [1, -1, 0, 0, 0, 0]

    #익은 토마토의 좌표를 모두 큐에 넣기. 동시에 출발
    for i in range(H):
        for j in range(N):
            for k in range(M):
                if tomatos[i][j][k] == 1:
                    queue.append((i, j, k))

    while queue:
        v = queue.popleft()

        for i in range(6):
            nz = v[0] + dz[i]
            nx = v[1] + dx[i]
            ny = v[2] + dy[i]

            if 0 <= nz < H and 0 <= nx < N and 0 <= ny < M:
                if tomatos[nz][nx][ny] == 0:
                    tomatos[nz][nx][ny] = tomatos[v[0]][v[1]][v[2]] + 1  #시작이 1부터라 마지막에 1 빼야함
                    queue.append((nz, nx, ny))

tomatos = []
for i in range(H):
    temp = []
    for j in range(N):
        temp.append(list(map(int, input().split())))
    tomatos.append(temp)

bfs()

ans = 1
for i in tomatos:
    for j in i:
        if 0 in j:
            ans = 0
            break
        max_temp = max(j)
        if ans < max_temp:
            ans = max_temp
    if ans == 0:
        break

print(ans - 1)