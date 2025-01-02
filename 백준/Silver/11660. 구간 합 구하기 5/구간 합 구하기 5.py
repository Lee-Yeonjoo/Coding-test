import sys
input = sys.stdin.readline

N, M = map(int, input().split())

#주어진 수를 저장장
grid = []
for _ in range(N):
    grid.append(list(map(int, input().split())))

#2차원 dp 테이블에 (i, j)에 (i, j)까지의 모든 수의 합을 저장
dp = [[0] * (N+1) for _ in range(N+1)]
for i in range(1, N+1):
    for j in range(1, N+1):
        dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1] + grid[i-1][j-1]

#dp테이블에 저장된 합을 이용해 원하는 범위의 수의 합을 구한다.
for _ in range(M):
    x1, y1, x2, y2 = map(int, input().split())

    print(dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1])