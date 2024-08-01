import sys
import heapq

# input을 sys라이브러리로 한다는 코드
input = sys.stdin.readline

def heapsort(iterable):
    h = []
    result = []

    # 모든 원소를 힙에 삽입
    for value in iterable:
        heapq.heappush(h, value)

    # 힙에 삽입된 원소를 다시 꺼내기
    for i in range(len(h)):
        result.append(heapq.heappop(h))
    
    return result

n = int(input())
arr = []

for i in range(n):
    arr.append(int(input()))

res = heapsort(arr)

for i in range(n):
    print(res[i])