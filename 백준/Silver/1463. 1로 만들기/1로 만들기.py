import sys
input = sys.stdin.readline

N = int(input().rstrip())

dp = [1000001] * (N+1)  # dp[0]은 쓰지 않는다.
dp[1] = 0

for i in range(2, N+1):
    if i % 3 == 0:
        dp[i] = min(dp[i], dp[i//3] + 1)
    
    if i % 2 == 0:
        dp[i] = min(dp[i], dp[i//2] + 1)

    dp[i] = min(dp[i], dp[i-1] + 1)

print(dp[N])