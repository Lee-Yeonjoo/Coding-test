#좀 다른 풀이 방법
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
lectures = list(map(int, input().split()))

start = max(lectures)
end = sum(lectures) #가능한 블루레이의 크기 중 최대값

while start <= end:
    mid = (start + end) // 2

    sum = 0    #강의 시간의 총합
    count = 1  #블루레이의 개수를 세기 위한 변수 
    #강의 시간을 순회하면서 mid와 비교해 블루레이의 개수를 카운트
    for lect in lectures:
        if sum + lect > mid:
            count += 1
            sum = 0
        sum += lect
    
    if count <= M:
        answer = mid 
        end = mid - 1
    else:
        start = mid + 1
    
print(answer)