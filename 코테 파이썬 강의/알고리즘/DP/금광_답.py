import sys
input = sys.stdin.readline

for tc in range(int(input().rstrip())):
    n, m = map(int, input().split())
    array = list(map(int, input().split()))

    dp = []
    for i in range(n):
        dp.append(array[i*m:(i+1)*m])
    
    for j in range(1, m):
        for i in range(n):

            if i == 0:  # i가 0이면 왼쪽 위인 i-1은 범위를 벗어남
                left_up = 0
            else:
                left_up = dp[i-1][j-1]
            
            if i == n-1:  # i가 n-1이면 왼쪽 아래인 i+1은 범위를 벗어남
                left_down = 0
            else:
                left_down = dp[i+1][j-1]
            
            left = dp[i][j-1]
            dp[i][j] = dp[i][j] + max(left_up, left_down, left)
    
    result = 0
    for i in range(n):
        result = max(result, dp[i][m-1])
    
    print(result)