import sys
input = sys.stdin.readline

T = int(input().rstrip())

for i in range(T):
    n, m = map(int, input().split())

    data = list(map(int, input().split()))
    dp = []
    for i in range(n):
        dp.append(data[i*m : (i+1)*m])
    
    for j in range(1, m):
        for i in range(n):
            if i-1 >= 0:
                left_up = dp[i-1][j-1]
            else:
                left_up = 0
            
            left = dp[i][j-1]

            if i+1 < n:
                left_down = dp[i+1][j-1]
            else:
                left_down = 0
            
            dp[i][j] = max(left,left_down,left_up) + dp[i][j]
    
    max = 0
    for i in range(n):
        if max < dp[i][m-1]:
            max = dp[i][m-1]
    print(max)
    print(dp)
    del max