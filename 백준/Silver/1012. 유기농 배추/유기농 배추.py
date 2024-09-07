import sys
from collections import deque
input = sys.stdin.readline

T = int(input().rstrip())

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(x, y):
    graph[x][y] = 0  # 현재 노드 방문 처리
    queue = deque([(x, y)])
    
    while queue:
        v = queue.popleft()

        for i in range(4):
            nx = v[0] + dx[i]
            ny = v[1] + dy[i]

            if nx >= 0 and nx < N and ny >= 0 and ny < M:
                if graph[nx][ny] == 1:
                    queue.append((nx, ny))
                    graph[nx][ny] = 0  # 큐에 넣고 바로 방문 처리를 해야 시간을 줄일 수 있다!

for _ in range(T):
    M, N, K = map(int, input().split())
    count = 0

    graph = [[0]*M for _ in range(N)]
    for _ in range(K):
        j, i = map(int, input().split())
        graph[i][j] = 1

    for i in range(N):
        for j in range(M):
            if graph[i][j] == 1:
                bfs(i,j)
                count += 1

    print(count)