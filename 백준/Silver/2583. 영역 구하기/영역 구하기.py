import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

M, N, K = map(int, input().split())

area = [[0] * N for _ in range(M)]
for _ in range(K):
    lx, ly, rx, ry = map(int, input().split())
    for i in range(ly, ry):
        for j in range(lx, rx):
            area[i][j] = 1

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
def dfs(x, y):
    global area_extent
    area[x][y] = 1  #방문표시

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if nx >= 0 and nx < M and ny >= 0 and ny < N:
            if area[nx][ny] == 0:
                dfs(nx, ny)
                area_extent += 1

count = 0   # 영역의 개수
area_extent = 1  # 영역의 넓이
ans = []  
for i in range(M):
    for j in range(N):
        if area[i][j] == 0:
            dfs(i, j)
            count += 1
            ans.append(area_extent)
            area_extent = 1

print(count)
ans.sort()
for i in ans:
    print(i, end=" ")