import sys
from functools import cache
input = sys.stdin.readline

n = int(input().rstrip())
dp = [[0] * n for _ in range(3)]  #3가지 경우를 저장
x = int(input().rstrip())
dp[0][0] = x
dp[1][0] = x
dp[2][0] = 0

for i in range(1, n):
    x = int(input().rstrip())

    dp[0][i] = dp[1][i-1] + x
    dp[1][i] = dp[2][i-1] + x
    dp[2][i] = max([dp[0][i-1], dp[1][i-1], dp[2][i-1]])

print(max([dp[0][n-1], dp[1][n-1], dp[2][n-1]]))