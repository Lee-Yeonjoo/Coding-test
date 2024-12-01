import sys
#sys.setrecursionlimit(10**6)
input = sys.stdin.readline

R, C = map(int, input().split())

alphabet = []
for i in range(R):
    alphabet.append(list(input().rstrip()))

#상하좌우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
visited = set()  #속도 향상을 위해 list대신 set 사용
max_count = 0
def dfs(x, y, count):
    global max_count
    #방문한 최대 알파벳 수 찾기
    if max_count < count:
        max_count = count

    visited.add(alphabet[x][y])   #방문한 알파벳 기록

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if nx >= 0 and nx < R and ny >= 0 and ny < C:
            if alphabet[nx][ny] not in visited:
                dfs(nx, ny, count + 1)
                visited.remove(alphabet[nx][ny])  #이웃노드의 dfs 끝나면 방문 알파벳에서 삭제

dfs(0, 0, 1)
print(max_count)