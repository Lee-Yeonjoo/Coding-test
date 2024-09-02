import sys
input = sys.stdin.readline

N = int(input().rstrip())
dp = [5001] *  (N+1)
dp[0] = 0  # 0일 때는 0봉지

for i in range(3, N+1):
    if i >= 3:
        dp[i] = min(dp[i], dp[i-3])
    if i>= 5:
        dp[i] = min(dp[i], dp[i-5])
    dp[i] += 1

if dp[N] >= 5001:
    print(-1)
else:
    print(dp[N])