import sys
input = sys.stdin.readline

n, k = map(int, input().split())
coins = []
for _ in range(n):
    coins.append(int(input().rstrip()))

dp = [0] * (k+1)
dp[0] = 1
#동전마다의 경우의 수를 점차 더해준다. 한 번에 구할 수 있는게 x
for c in coins:
    for i in range(c, k+1):
        dp[i] += dp[i-c]

print(dp[k])