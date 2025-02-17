import sys
input = sys.stdin.readline

N = int(input().rstrip())

dp = [[0] * 2 for _ in range(N+1)]
#행은 N을 가리키고, 첫번째 열은 빈 우리의 수이고, 두번째 열은 사자가 있는 우리의 수이다. 
dp[1][0] = 1
dp[1][1] = 2
for i in range(2, N+1):
    dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % 9901  #빈 우리의 경우의 수는 이전 우리의 각 경우의 수를 더한 것과 같음
    dp[i][1] = (dp[i-1][0] * 2 + dp[i-1][1]) % 9901  #사자가 있는 우리의 경우의 수는 빈 우리의 경우 두가지 경우가 나오고, 사자가 있는 경우는 1가지 경우가 나온다.

print((dp[N][0] + dp[N][1]) % 9901)