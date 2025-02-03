import sys
input = sys.stdin.readline

N, M = map(int, input().split())
board = []
for _ in range(N):
    board.append(list(input().rstrip()))

answer = 64
#(i,j)가 체스판의 왼쪽 위인 경우에 대해 모두 탐색
for i in range(N-7):
    for j in range(M-7):
        B_count = 0  #왼쪽 위가 검정인 체스판의 경우, 다시 칠해야 하는 칸 수를 세는 변수
        W_count = 0  #왼쪽 위가 흰색인 체스판의 경우, 다시 칠해야 하는 칸 수를 세는 변수 
        for s in range(8):
            for t in range(8):
                x, y = i+s, j+t
                #좌표가 짝짝이거나 홀홀인 경우 
                if (x % 2 == 0 and y % 2 == 0) or (x % 2 == 1 and y % 2 == 1):
                    if board[x][y] == 'W':
                        B_count += 1  #흰색일 경우 다시 칠해야 함
                    else:
                        W_count += 1  #검정일 경우 다시 칠해야 함 
                #좌표가 짝홀이거나 홀짝인 경우
                else:
                    if board[x][y] == 'B':
                        B_count += 1
                    else:
                        W_count += 1

        count = min(B_count, W_count)
        if answer > count:
            answer = count

print(answer)