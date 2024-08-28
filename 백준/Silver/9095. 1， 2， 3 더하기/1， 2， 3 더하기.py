import sys
input = sys.stdin.readline

T = int(input().rstrip())

for _ in range(T):
    n = int(input().rstrip())

    dp = [0] * max(4, (n+1))  # 배열 크기를 4보다 작게 선언하면 n이 1,2일 때 런타임 에러가 날 수 있으므로 max()를 이용해 방지
    dp[1] = 1
    dp[2] = 2
    dp[3] = 4  

    for i in range(4, n+1):
        dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
    
    print(dp[n])