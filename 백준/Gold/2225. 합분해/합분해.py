#자세한 풀이 방향은 노트 참고하기 
import sys
input = sys.stdin.readline

N, K = map(int, input().split())

dp = [1] * (N+1)
for _ in range(K-1):
    for i in range(1, N+1):
        dp[i] = dp[i-1] + dp[i]  #경우의 수를 바깥 for문이 반복됨에 따라 계속 갱신해나간다. 

print(dp[N] % 1000000000)