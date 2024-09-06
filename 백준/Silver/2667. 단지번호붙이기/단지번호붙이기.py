import sys
input = sys.stdin.readline

N = int(input().rstrip())
array = [[] for _ in range(N)]
for i in range(N):
    str = input().rstrip()
    for j in range(N):
        array[i].append(int(str[j]))

# 방향 벡터
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

visited = [[False] * N for _ in range(N)]  # 방문 여부를 기록
complex = 0  # 단지 수를 세는 변수
cnt = [0] * (N*N)  # 각 단지의 집의 수를 기록
def dfs(array, visited, i, j):
    global complex

    if array[i][j] == 1 and visited[i][j] == False:
        visited[i][j] = True
        cnt[complex] += 1

        for k in range(4):
            nx = i + dx[k]
            ny = j + dy[k]

            if (nx >= 0 and nx < N) and (ny >= 0 and ny < N):
                dfs(array, visited, nx, ny)

for i in range(N):
    for j in range(N):
        if array[i][j] == 1 and visited[i][j] == False:
            complex += 1
            dfs(array, visited, i, j)

cnt.sort()
print(complex)
for i in cnt:
    if i != 0:
        print(i)