import sys
input = sys.stdin.readline

N = int(input().rstrip())

dp = [0] * (N+1)
dp[1] = 1
if N >= 2:
    dp[2] = 2

for i in range(3, N+1):
    dp[i] = (dp[i-1] + dp[i-2]) % 15746  #결과 출력할 때 나머지를 구하면 메모리 초과나므로, 미리 나머지를 구해서 저장장

print(dp[N])