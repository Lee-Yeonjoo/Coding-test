import sys
input = sys.stdin.readline

n = int(input().rstrip())
seq = list(map(int, input().split()))

dp = [[0] * n for _ in range(2)]
dp[0][0] = seq[0]
dp[1][0] = seq[0]

for i in range(1, n):
    dp[0][i] = max(dp[0][i-1] + seq[i], seq[i])
    dp[1][i] = max(dp[0][i-1], dp[1][i-1])

print(max(dp[0][n-1], dp[1][n-1]))