import sys
input = sys.stdin.readline
INF = 1e9

N = int(input().rstrip())
dp = [INF] * (N+1)
dp[1] = 1  #항의 최소 개수
for i in range(2, N+1):
    x = 1
    while i-(x**2) >= 0:
        if i-(x**2) == 0:  #i가 제곱수인 경우 항은 1개
            dp[i] = 1  
        else:  #i가 제곱수가 아닌 경우 일일히 비교하여 최소값을 탐색 
            dp[i] = min(dp[i], dp[i-(x**2)] + 1)
        x += 1

print(dp[N])