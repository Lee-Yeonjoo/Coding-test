import sys
input = sys.stdin.readline

gear1 = list(map(int, input().rstrip()))
gear2 = list(map(int, input().rstrip()))

length = len(gear1) + len(gear2)
answer = length

#브루트포스 - 완전 탐색 
for i in range(length):
    result = []
    
    #각 경우에서 두 기어의 겹치는 범위를 맞물린다(더한다)
    for j in range(i):
        if j < len(gear1) and (-i+j) >= (-1*len(gear2)):
            result.append(gear1[j] + gear2[-i+j])
    
    #더한 값 중 4가 있다면 맞물릴 수 없는 경우이다.
    if 4 in result:
        continue
    
    #맞물렸을 때의 길이가 최소값이라면 갱신 
    len_result = len(result)
    if answer > length - len_result:
        answer = length - len_result

print(answer)