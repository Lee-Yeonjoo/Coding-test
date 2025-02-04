import sys
input = sys.stdin.readline

N, M = map(int, input().split())
rectangles = []
for _ in range(N):
    rectangles.append(list(map(int, input().rstrip())))

answer = 1
for line in range(2, min(N, M)+1):
    for i in range(N-line+1):
        if answer == line * line:  #크기가 line인 정사각형을 이미 찾았으므로 더 찾지 않고 break
            break

        for j in range(M-line+1):
            ni = i + line - 1
            nj = j + line - 1
            #각 꼭짓점의 숫자가 모두 같으면 answer 갱신 
            if rectangles[i][j] == rectangles[ni][j] == rectangles[i][nj] == rectangles[ni][nj]:
                answer = line * line
                break     

print(answer)