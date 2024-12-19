import sys
input = sys.stdin.readline

N = int(input().rstrip())
dp = [0] * (N+1)
dp[1] = 1  #out of index 에러 주의

for i in range(2, N+1):
    dp[i] = dp[i-1] + dp[i-2]

print(dp[N])