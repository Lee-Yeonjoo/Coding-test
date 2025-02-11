import sys
input = sys.stdin.readline

#dp[a][b][c]를 저장하기 위한 3차원 리스트 
dp = [[[1] * 21 for _ in range(21)] for _ in range(21)]

while True:
    a, b, c = map(int, input().split())
    d, e, f = a, b, c  #출력 형식때문 

    if a == -1 and b == -1 and c == -1:
        break

    #답이 바로 나오는 경우 
    if a <= 0 or b <= 0 or c <= 0:
        print(f"w({d}, {e}, {f}) = 1")
        continue

    #20보다 큰 경우 
    if a > 20 or b > 20 or c > 20:
        a, b, c = 20, 20, 20
    
    #이미 dp값을 아는 경우 바로 출력 
    if dp[a][b][c] > 1:
        print(f"w({d}, {e}, {f}) = {dp[a][b][c]}")
    else: 
        #a, b, c의 값의 범위가 크지 않기에 3중 for문으로 dp값 구하기 
        if a < b and b < c:
            for i in range(1, a+1):
                for j in range(1, b+1):
                    for k in range(1, c+1):
                        dp[i][j][k] = dp[i][j][k-1] + dp[i][j-1][k-1] - dp[i][j-1][k]
        else:
            for i in range(1, a+1):
                for j in range(1, b+1):
                    for k in range(1, c+1):
                        dp[i][j][k] = dp[i-1][j][k] + dp[i-1][j-1][k] + dp[i-1][j][k-1] - dp[i-1][j-1][k-1]

        print(f"w({d}, {e}, {f}) = {dp[a][b][c]}")