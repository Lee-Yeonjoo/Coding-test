import sys
input = sys.stdin.readline

N = int(input().rstrip())

score = [0] * (N+1)
for i in range(N):
    score[i+1] = int(input().rstrip())

dp = [0] * (N+1)
dp[1] = score[1]
if N >= 2:
    dp[2] = score[1] + score[2]
for i in range(3, N+1):
    dp[i] = max(dp[i-2], dp[i-3] + score[i-1]) + score[i]
    
print(dp[N])