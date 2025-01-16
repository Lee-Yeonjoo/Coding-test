import sys
input = sys.stdin.readline

N, M = map(int, input().split())
lectures = list(map(int, input().split()))

start = max(lectures)
end = sum(lectures) #가능한 블루레이의 크기 중 최대값

while start <= end:
    mid = (start + end) // 2
    #print(mid)

    sum = 0
    i = 0
    #모든 강의의 길이를 순회하면서 차곡차곡 mid값을 넘지 않게 저장하고, 마지막 칸에는 나머지 강의 모두 저장
    for j in range(len(lectures)):

        sum += lectures[j]
        if sum > mid:
            i += 1  #영역이 증가함 
            sum = lectures[j]
            if i == M:  #영역의 개수를 넘음
                start = mid + 1
                break
        if j == len(lectures) - 1:
            end = mid - 1
            break
        
print(end + 1)