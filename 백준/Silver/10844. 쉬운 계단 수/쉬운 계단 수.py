import sys
input = sys.stdin.readline

N = int(input().rstrip())

dp = [[1] * 10 for _ in range(N+1)]

for i in range(2, N+1):
    for j in range(10):
        if j == 0:
            dp[i][j] = dp[i-1][j+1]
        elif j == 9:
            dp[i][j] = dp[i-1][j-1]
        else:
            dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1]

ans = 0
for i in range(9):
    ans += dp[N][i+1]

print(ans % 1000000000)