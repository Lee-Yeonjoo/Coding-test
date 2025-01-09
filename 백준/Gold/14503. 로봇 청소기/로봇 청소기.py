#반시계 방향으로 좌표 찾는 로직을 연산을 통해 간단히 한 코드
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N, M = map(int, input().split())
r, c, d = map(int, input().split())
room = []
for _ in range(N):
    room.append(list(map(int, input().split())))

#북동남서(0,1,2,3)순서로 설정 
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
count = 0
def dfs(x, y, d):
    global count

    if room[x][y] == 0:  #후진하면서 다시 방문할 수도 있어서 조건문 필요
        room[x][y] = 2  #현재 칸 청소
        count += 1

    #반시계 방향으로 청소 안한 칸 탐색
    for i in range(4):
        nd = (d + 3) % 4  #왼쪽으로 90도 회전하는 식
        nx = x + dx[nd]
        ny = y + dy[nd]
            
        if room[nx][ny] == 0:
            dfs(nx, ny, nd)
            #중요!! 여기서 리턴을 꼭 해줘야 한다. 한번 dfs해준 후 다른 방향에서 다시 dfs하지 않고 그냥 손절.
            return
        
        #현재 방향을 돌린 방향으로
        d = nd
    
    #주변을 다 봤는데 청소하지 않은 빈칸이 없는 경우
    #각 방향에 대한 bx,by는 후진했을 때의 좌표
    nd = (d+2) % 4  #현재 방향과 반대(후진 방향) 방향을 구하는 식.
    bx = x + dx[nd]
    by = y + dy[nd]
    
    #후진했을 때 벽이면 리턴
    if room[bx][by] == 1:
        return

    #후진했을 때 벽이 아니므로 탐색
    dfs(bx, by, d)

dfs(r, c, d)
print(count)