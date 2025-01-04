import sys
input = sys.stdin.readline

N = int(input().rstrip())
#카드의 금액 저장
P = list(map(int, input().split()))

dp = [0] * (N+1)
#dp[i]는 i개의 카드를 살 때 지불해야 하는 금액의 최댓값
for i in range(1, N+1):  #n번 반복
    for j in range(i):   #i가 증가함에 따라 더 반복하므로 등차수열의 합 공식인 n(n+1)/2번 반복
        dp[i] = max(dp[i], dp[j] + P[i-j-1])

print(dp[N])