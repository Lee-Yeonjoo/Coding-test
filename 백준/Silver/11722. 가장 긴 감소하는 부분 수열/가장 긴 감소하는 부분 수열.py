import sys
input = sys.stdin.readline

N = int(input().rstrip())
A = list(map(int, input().split()))

dp = [1] * N
#수열 A 전체를 탐색
for i in range(N):
    #i번째 수의 앞의 수들 중, i번째 수보다 크면서 가장 긴 감소하는 부분 수열의 길이를 가진 dp값을 찾는다.
    for j in range(i):
        if A[i] < A[j]:
            dp[i] = max(dp[i], dp[j] + 1)

print(max(dp))