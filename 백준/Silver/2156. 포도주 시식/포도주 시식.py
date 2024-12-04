import sys
input = sys.stdin.readline

n = int(input().rstrip())
wine = [0] * 10001
for i in range(n):
    wine[i] = int(input().rstrip())

dp = [0] * 10001
dp[0] = wine[0]
dp[1] = dp[0] + wine[1]
dp[2] = max(dp[1], dp[0] + wine[2], wine[1] + wine[2])

for i in range(3, n):
    #max{자신을 포함x, 자신을 포함하는데 이전꺼x, 자신 포함하는데 이전꺼 o)
    dp[i] = max(dp[i-1], dp[i-2] + wine[i], dp[i-3] + wine[i-1] + wine[i])

print(dp[n-1])