import sys 
input = sys.stdin.readline

n = int(input().rstrip())
array = list(map(int, input().split()))

dp = [1] * n  # 최소 1개의 원소(자기자신)를 가지니까 모두 1로 초기화

for i in range(1, n):
    for j in range(0, i):
        if array[i] < array[j]:
            dp[i] = max(dp[i], dp[j] + 1)

print(n-max(dp))  # max(dp)값이 가장 긴 부분수열의 길이