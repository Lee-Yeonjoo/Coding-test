import sys
input = sys.stdin.readline

str1 = input().rstrip()
str2 = input().rstrip()
n1 = len(str1)
n2 = len(str2)

#주의. 문제에서 두 문자열의 길이가 같다고 한 적 없다.
dp = [[0] * (n2+1) for _ in range(n1+1)]  #2차원 리스트로, 두 문자열의 문자가 각 각 행과 열을 가리킴킴
for i in range(1, n1+1):
    for j in range(1, n2+1):
        if str1[i-1] == str2[j-1]:  #같은 문자인 경우, LCS가 하나 증가함함
            dp[i][j] = dp[i-1][j-1] + 1
        else:  #다른 문자인 경우, 새로 추가된 문자에 대해 각 각의 LCS 중 더 큰 것으로 가져감.
            dp[i][j] = max(dp[i-1][j], dp[i][j-1])

print(dp[n1][n2])