import sys
input = sys.stdin.readline

N = int(input().rstrip())

dp = [[1] * 10 for _ in range(N)]

#0~9가 i번째 자리에서 각 각 몇 개 나올 수 있는지를 2차원 리스트에 저장
for i in range(1, N):
    for j in range(1, 10):
        dp[i][j] = dp[i-1][j] + dp[i][j-1]

print(sum(dp[N-1]) % 10007)