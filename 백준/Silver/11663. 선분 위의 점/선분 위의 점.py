import sys
input = sys.stdin.readline

N, M = map(int, input().split())
points = list(map(int, input().split()))
points.sort()

def front_bs(front):
    start = 0
    end = N-1

    while start <= end:
        mid = (start + end) // 2

        if points[mid] < front:
            start = mid + 1
        elif points[mid] > front:
            end = mid - 1
        elif points[mid] == front:
            return mid
    
    return end + 1

def back_bs(back):
    start = 0
    end = N-1

    while start <= end:
        mid = (start + end) // 2

        if points[mid] < back:
            start = mid + 1
        elif points[mid] > back:
            end = mid - 1
        elif points[mid] == back:
            return mid
    
    return start - 1

for _ in range(M):
    front, back = map(int, input().split())

    print(back_bs(back) - front_bs(front) + 1)