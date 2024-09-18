import sys
input = sys.stdin.readline

T = int(input().rstrip())

for _ in range(T):
    dp = [[0]*15 for _ in range(15)]

    for i in range(1, 15):
        dp[0][i] = i
        dp[i][1] = 1
    
    k = int(input().rstrip())
    n = int(input().rstrip())

    for i in range(1, k+1):
        for j in range(2, n+1):
            dp[i][j] = dp[i][j-1] + dp[i-1][j]
    
    print(dp[k][n])