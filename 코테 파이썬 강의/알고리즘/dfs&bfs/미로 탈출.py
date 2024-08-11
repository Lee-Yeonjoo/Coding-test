import sys
from collections import deque
input = sys.stdin.readline

def bfs(x, y):
    queue = deque()
    queue.append((x, y))  # 튜플을 이용해 좌표 표현

    while queue:
        x, y = queue.popleft()

        for i in range(4):  # 현재 노드의 상하좌우 노드에 대해 탐색
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue

            if graph[nx][ny] == 0:
                continue

            if graph[nx][ny] == 1:
                graph[nx][ny] = graph[x][y] + 1
                queue.append((nx, ny))

    return graph[n-1][m-1]  # 탈출구 지점에 저장된 거리를 리턴


n, m = map(int, input().split())

graph = []
for i in range(n):
    graph.append(list(map(int, input().rstrip())))

# 상, 하, 좌, 우의 방향벡터
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

print(bfs(0,0))
