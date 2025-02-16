import sys
input = sys.stdin.readline

N = int(input().rstrip())
people = list(map(int, input().split()))

answer = [11] * N

#작은 수부터 처리하는게 핵심 
for i in range(N):
    count = 0  #빈 자리의 개수, 빈 자리는 자신보다 큰 수가 올 자리 
    for j in range(N):
        if answer[j] == 11:  #빈 자리라면 카운트
            count += 1

        #자신의 자리를 찾음. 빈 자리가 입력받은 수+1이어야 자기 자리 
        if count == people[i] + 1:
            answer[j] = i + 1  #j는 자기 자리의 인덱스 
            break
 
for i in answer:
    print(i, end=" ")    