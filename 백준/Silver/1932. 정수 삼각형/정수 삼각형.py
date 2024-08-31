import sys
input = sys.stdin.readline

n = int(input().rstrip())
triangle = [list(map(int, input().split())) for _ in range(n)]  # 2차원 리스트에 정수 삼각형 입력

dp = [[0]*n for _ in range(n)]
dp[0][0] = triangle[0][0]
for i in range(1, n):
    for j in range(len(triangle[i])):
        if j-1 >= 0:
            dp[i][j] = max(dp[i][j], dp[i-1][j-1])
        
        if j <= i-1:
            dp[i][j] = max(dp[i][j], dp[i-1][j])
        dp[i][j] += triangle[i][j]
        
print(max(dp[n-1]))