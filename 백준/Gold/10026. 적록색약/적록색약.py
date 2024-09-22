import sys
sys.setrecursionlimit(10 ** 5)
input = sys.stdin.readline

# 방향벡터
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def dfs(x, y):
    visited[x][y] = True

    for i in range(len(dx)):
        nx = x + dx[i]
        ny = y + dy[i]

        if nx < 0 or nx >= N or ny < 0 or ny >= N:
            continue
        if visited[nx][ny] == False and img[x][y] == img[nx][ny]:
            dfs(nx, ny)

# 적록색약.ver
def dfs_RG(x, y):
    visited_RG[x][y] = True

    for i in range(len(dx)):
        nx = x + dx[i]
        ny = y + dy[i]

        if nx < 0 or nx >= N or ny < 0 or ny >= N or visited_RG[nx][ny]:
            continue
        if img[x][y] == 'B':
            if img[nx][ny] == 'B':
                dfs_RG(nx, ny)
        if img[x][y] == 'R' or img[x][y] == "G": 
            if img[nx][ny] == 'R' or img[nx][ny] == 'G':
                dfs_RG(nx, ny)

N = int(input().rstrip())
img = []
for _ in range(N):
    img.append(list(input().rstrip()))

visited = [[False] * (N) for _ in range(N)]
visited_RG = [[False] * (N) for _ in range(N)]

cnt = 0
cnt_RG = 0
for i in range(N):
    for j in range(N):
        if visited[i][j] == False:
            dfs(i,j)
            cnt += 1
        if visited_RG[i][j] == False:
            dfs_RG(i,j)
            cnt_RG += 1
            
print(cnt, cnt_RG)