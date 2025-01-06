import sys
input = sys.stdin.readline

N = int(input().rstrip())
seq = list(map(int, input().split()))

dp = seq.copy()
for i in range(1, N):
    #수열 앞쪽에 자기자신보다 작은 수를 찾는다. while문 최대 1000번 반복. 바깥for문도 1000번 반복 -> 총 백만번 반복이므로 시간초과 나지 않는다.
    j = i
    while j >= 0:
        #자기자신보다 작은 수를 만날때마다 더 큰 값으로 갱신
        if seq[i] > seq[j]:
            dp[i] = max(dp[i], dp[j] + seq[i])
        j -= 1

print(max(dp))