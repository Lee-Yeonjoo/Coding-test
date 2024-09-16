import sys
input = sys.stdin.readline

T = int(input().rstrip())

def fibo(n):
    if n == 0 or n == 1 or dp[n] != [0, 0]:
        return dp[n]
    
    x = fibo(n-1)
    y = fibo(n-2)
    dp[n][0] = x[0] + y[0]
    dp[n][1] = x[1] + y[1]
    return dp[n]

for _ in range(T):
    N = int(input().rstrip())
    dp = [[0,0] for i in range(41)]
    dp[0] = [1, 0]
    dp[1] = [0, 1]
    result = fibo(N)
    print(result[0], result[1])