import sys
input = sys.stdin.readline

N, K = map(int, input().split())

weight = [0] * (N+1) 
profit = [0] * (N+1)
for i in range(1, N+1):
    weight[i], profit[i] = map(int, input().split())

dp = [[0] * (K+1) for _ in range(N+1)]  #각 물건에 대해 용량마다 최대 profit을 저장

for i in range(1, N+1):
    for j in range(1, K+1):

        if j < weight[i]:  #현재 물건의 무게가 용량보다 커서 담을 수 없음 -> 이전 값 그대로
            dp[i][j] = dp[i-1][j]
        else:  #담을 수 있는 경우, 담는 것과 안 담는 것 중 더 큰 값을 선택
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-weight[i]] + profit[i])

print(dp[N][K])