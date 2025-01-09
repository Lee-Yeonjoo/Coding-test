import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N, M = map(int, input().split())
r, c, d = map(int, input().split())
room = []
for _ in range(N):
    room.append(list(map(int, input().split())))

#d가 북쪽일 때 방향
dx_N = [0, 1, 0, -1]
dy_N = [-1, 0, 1, 0]
d_N = [3, 2, 1, 0]

#d가 동쪽일 때 방향
dx_E = [-1, 0, 1, 0]
dy_E = [0, -1, 0, 1]
d_E = [0, 3, 2, 1]

#d가 남쪽일 때 방향
dx_S = [0, -1, 0, 1]
dy_S = [1, 0, -1, 0]
d_S = [1, 0, 3, 2]

#d가 서쪽일 때 방향
dx_W = [1, 0, -1, 0]
dy_W = [0, 1, 0, -1]
d_W = [2, 1, 0, 3]

count = 0
nd = 0
def dfs(x, y, d):
    global count
    global nd

    if room[x][y] == 0:
        room[x][y] = 2  #현재 칸 청소
        count += 1

    for i in range(4):
        if d == 0:
            nx = x + dx_N[i]
            ny = y + dy_N[i]
            nd = d_N[i]
        
        if d == 1:
            nx = x + dx_E[i]
            ny = y + dy_E[i]
            nd = d_E[i]
        
        if d == 2:
            nx = x + dx_S[i]
            ny = y + dy_S[i]
            nd = d_S[i]

        if d == 3:
            nx = x + dx_W[i]
            ny = y + dy_W[i]
            nd = d_W[i]
            
        if room[nx][ny] == 0:
            dfs(nx, ny, nd)
            return
    
    #주변을 다 봤는데 청소하지 않은 빈칸이 없는 경우
    #각 방향에 대한 bx,by는 후진했을 때의 좌표
    if nd == 0:
        bx = x + dx_N[1]
        by = y + dy_N[1]
    if nd == 1:
        bx = x + dx_E[1]
        by = y + dy_E[1]
    if nd == 2:
        bx = x + dx_S[1]
        by = y + dy_S[1]
    if nd == 3:
        bx = x + dx_W[1]
        by = y + dy_W[1]
    
    #후진했을 때 벽이면 리턴
    if room[bx][by] == 1:
        return

    #후진했을 때 벽이 아니므로 탐색색
    dfs(bx, by, d)

dfs(r, c, d)
print(count)