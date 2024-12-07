import sys
input = sys.stdin.readline

N = int(input().rstrip())
T = [0] * N
P = [0] * N

for i in range(N-1, -1, -1):  #입력을 뒤에서부터 받기
    T[i], P[i] = map(int, input().split())

dp = [0] * N
if T[i] <= 1:
    dp[0] = P[0]

for i in range(1, N):
    if (i-T[i]) < -1:
        dp[i] = dp[i-1]  #continue하면 값이 0 그대로니까 i-1에서의 값을 가져가야함
    else: 
        dp[i] = max(dp[i-1], dp[i - T[i]] + P[i])  #i일에 배정된 상담을 안한는 경우와 하는 경우의 크기 비교

print(dp[N-1])