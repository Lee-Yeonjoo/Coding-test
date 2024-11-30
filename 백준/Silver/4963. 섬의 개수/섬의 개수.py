import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

#방향벡터 - 10시 방향부터 시계방향으로 
dx = [-1, -1, -1, 0, 1, 1, 1, 0]
dy = [-1, 0, 1, 1, 1, 0, -1, -1]

def dfs(i, j):
    visited[i][j] = True

    for k in range(8):
        nx = i + dx[k]
        ny = j + dy[k]

        if (nx >= 0 and nx < h) and (ny >= 0 and ny < w):
            if visited[nx][ny] == False and land[nx][ny] == 1:
                dfs(nx, ny)

while True:
    w, h = map(int, input().split())

    if w == 0 and h == 0:
        break

    land = []
    for _ in range(h):
        land.append(list(map(int, input().split())))
    
    visited = [[False] * w for _ in range(h)]
    count = 0
    for i in range(h):
        for j in range(w):
            if visited[i][j] == False and land[i][j] == 1:
                dfs(i, j)
                count += 1
    
    print(count) 