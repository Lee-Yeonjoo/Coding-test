import sys
from collections import deque
input = sys.stdin.readline

#최단 경로 문제?
M, N = map(int, input().split())
tomato = []
for _ in range(N):
    tomato.append(list(map(int, input().split())))

queue = deque()
#익은 토마토를 먼저 큐에 넣기
cnt = 0
for i in range(N):
    for j in range(M):
        if tomato[i][j] == 1:
            queue.append((i, j))
        
        if tomato[i][j] == -1:
            cnt += 1

#방향 벡터
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
while queue:
    v = queue.popleft()
    
    for i in range(4):
        nx = v[0] + dx[i]
        ny = v[1] + dy[i]

        if (nx >= 0 and nx < N) and (ny >= 0 and ny < M) and tomato[nx][ny] == 0:
            queue.append((nx, ny))
            tomato[nx][ny] = tomato[v[0]][v[1]] + 1

for i in tomato:
    if 0 in i or cnt == N*M:
        print(-1)
        break
else:
    print(max(map(max, tomato)) - 1)