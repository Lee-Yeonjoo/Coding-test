import sys
input = sys.stdin.readline

n, m = map(int, input().split())

array=[]
for i in range(n):
    array.append(int(input().rstrip()))

dp = [10001] * (m+1)
dp[0] = 0

for i in range(1, m+1):
    for j in array:
        if i-j >=0:
            dp[i] = min(dp[i], dp[i-j] + 1)

if dp[m] == 10001:
    print(-1)
else:
    print(dp[m])
print(dp)