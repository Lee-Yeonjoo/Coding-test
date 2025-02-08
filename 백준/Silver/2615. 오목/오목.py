import sys
input = sys.stdin.readline

baduk = []
for _ in range(19):
    baduk.append(list(map(int, input().split())))

#우, 하, 우상, 우하 - 왼쪽, 위의 좌표를 얻기 위해 
dx = [0, 1, -1, 1]
dy = [1, 0, 1, 1]

for x in range(19):
    for y in range(19):
        if baduk[x][y] != 0:
            color = baduk[x][y]

            for i in range(4):
                cnt = 1
                nx = x + dx[i]
                ny = y + dy[i]
                
                while nx >= 0 and nx < 19 and ny >= 0 and ny < 19 and baduk[nx][ny] == color:
                    cnt += 1

                    if cnt == 5:
                        #육목 체크
                        other_side_x = x - dx[i]
                        other_side_y = y - dy[i]
                        nnx = nx + dx[i]
                        nny = ny + dy[i]
                        if 0 <= other_side_x < 19 and 0 <= other_side_y < 19 and baduk[other_side_x][other_side_y] == color:
                            break
                        if 0 <= nnx < 19 and 0 <= nny < 19 and baduk[nnx][nny] == color:
                            break
                        
                        #육목이 아니므로 프린트 후 종료 
                        print(color)
                        print(x+1, y+1)
                        sys.exit(0)
                    
                    #같은 방향으로 계속 나아가기 
                    nx = nx + dx[i]
                    ny = ny + dy[i]

#승부가 나지 않음 
print(0)