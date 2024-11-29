import sys
sys.setrecursionlimit(10000001)
input = sys.stdin.readline

N = int(input().rstrip())
height = []
rain_max = 0
rain_min = 100

#잠기지 않은 지역 dfs
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
def dfs(x, y, r):
    visited[x][y] = True

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if nx >= 0 and nx < N and ny >= 0 and ny < N:
            if height[nx][ny] > r and visited[nx][ny] == False:
                dfs(nx, ny, r)

#높이 정보 입력 받으면서 비의 최대량, 최소량 찾기
for _ in range(N):
    temp = list(map(int, input().split()))
    temp_max = max(temp)
    temp_min = min(temp)
    if rain_max < temp_max:
        rain_max = temp_max
    
    if rain_min > temp_min:
        rain_min = temp_min
    height.append(temp)

max_cnt = 0
for r in range(rain_min, rain_max):
    visited = [[False] * N for i in range(N)]
    cnt = 0
    for i in range(N):
        for j in range(N):
            if height[i][j] > r and visited[i][j] == False:
                dfs(i, j, r)
                cnt += 1
    if max_cnt < cnt:
        max_cnt = cnt

if rain_min == rain_max: #지대가 같은 경우 dfs 돌지x
    print(1)
else:
    print(max_cnt)