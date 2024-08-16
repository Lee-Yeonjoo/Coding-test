import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())

# 미로 초기화, 방문 여부 초기화
maze = [[0] * M for i in range(N)]
visited = [[False] * M for i in range(N)]

# 미로 입력받기
for i in range(N):
    maze[i] = list(map(int,input().strip()))

queue = deque()

# 방향벡터 설정(상, 하, 좌, 우)
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

queue.append((0, 0))

# 큐가 빌 때까지 반복
while queue:
    curr = queue.popleft()
    visited[curr[0]][curr[1]] = True 

    for i in range(len(dx)):
        nx = curr[0] + dx[i]
        ny = curr[1] + dy[i]

        # 범위를 벗어나면 continue
        if nx < 0 or nx >= N or ny <0 or ny >= M:
            continue
        
        if maze[nx][ny] == 0:
            continue
        elif maze[nx][ny] == 1 and visited[nx][ny] == False:
            queue.append((nx, ny))
            maze[nx][ny] += maze[curr[0]][curr[1]]

print(maze[N-1][M-1])