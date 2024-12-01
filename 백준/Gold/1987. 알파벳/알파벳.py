#dfs를 재귀함수 대신 스택(set)을 사용해 시간초과 해결
import sys
input = sys.stdin.readline

R, C = map(int, input().split())

alphabet = []
for i in range(R):
    alphabet.append(list(input().rstrip()))

#상하좌우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def dfs(x, y):
    max_count = 0
    
    stack = set()
    stack.add((x, y, alphabet[x][y]))  #스택에 알파벳도 넣는 이유는 visited 따로 안 쓰기 위해

    while stack:
        cx, cy, c_alphabet = stack.pop()

        max_count = max(max_count, len(c_alphabet))
        if max_count == 26:
            return 26

        for i in range(4):
            nx = cx + dx[i]
            ny = cy + dy[i]

            if nx >= 0 and nx < R and ny >= 0 and ny < C:
                if alphabet[nx][ny] not in c_alphabet:
                    stack.add((nx, ny, c_alphabet + alphabet[nx][ny]))
    
    return max_count

print(dfs(0, 0))