import sys
from collections import deque
input = sys.stdin.readline

T = int(input().rstrip())

dx = [-2, -1, 1, 2, 2, 1, -1, -2]
dy = [1, 2, 2, 1, -1, -2, -2, -1]
def bfs(i, j, end_i, end_j):
    queue = deque()
    queue.append((i, j))
    visited[i][j] = True

    while queue:
        x, y = queue.popleft()

        if x == end_i and y == end_j:  #답을 찾았으므로 탐색을 멈춤
            return

        for i in range(8):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx >=0 and nx < I and ny >= 0 and ny < I:
                if visited[nx][ny] == False:
                    queue.append((nx, ny))
                    chess[nx][ny] = chess[x][y] + 1
                    visited[nx][ny] = True
            
for _ in range(T):
    I = int(input().rstrip())

    chess = [[0] * I for _ in range(I)]
    visited = [[False] * I for _ in range(I)]

    start_i, start_j = map(int, input().split())
    end_i, end_j = map(int, input().split())
    bfs(start_i, start_j, end_i, end_j)

    print(chess[end_i][end_j])