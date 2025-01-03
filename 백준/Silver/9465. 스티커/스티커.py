import sys
input = sys.stdin.readline

T = int(input().rstrip())
for _ in range(T):
    n = int(input().rstrip())

    #스티커 점수 입력 받기
    sticker = []
    for _ in range(2):
        sticker.append(list(map(int, input().split())))
    
    #dp 2차원 테이블 초기화
    dp = [[0] * (n+1) for _ in range(2)]
    dp[0][1] = sticker[0][0]
    dp[1][1] = sticker[1][0]
    
    #dp 점화식. 각 행의 i번재 요소에서 될 수 있는 경우 중 더 큰 거 선택
    for i in range(2, n+1):
        dp[0][i] = max(dp[1][i-2], dp[1][i-1]) + sticker[0][i-1]
        dp[1][i] = max(dp[0][i-2], dp[0][i-1]) + sticker[1][i-1]
    
    print(max(dp[0][n], dp[1][n]))