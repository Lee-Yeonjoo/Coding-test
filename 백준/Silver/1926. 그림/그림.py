import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n, m = map(int, input().split())
picture = []
for _ in range(n):
    picture.append(list(map(int, input().split())))

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
visited = [[False] * m for _ in range(n)]
area = 0
def dfs(x, y):
    global area
    area += 1
    visited[x][y] = True

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            continue

        if picture[nx][ny] == 1 and visited[nx][ny] == False:
            dfs(nx, ny)

count = 0
max_picture = 0
for i in range(n):
    for j in range(m):
        if picture[i][j] == 1 and visited[i][j] == False:
            dfs(i, j)
            max_picture = max(max_picture, area)
            area = 0
            count += 1

print(count)
print(max_picture)