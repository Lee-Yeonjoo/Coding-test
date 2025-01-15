from bisect import bisect_left, bisect_right
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
points = list(map(int, input().split()))
points.sort()

for _ in range(M):
    front, back = map(int, input().split())
    left_index = bisect_left(points, front)  #정렬을 유지하면서 points에 front를 삽입할 가장 왼쪽 인덱스 반환
    right_index = bisect_right(points, back) #정렬을 유지하면서 points에 back을 삽입할 가장 오른쪽 인덱스 반환

    print(right_index - left_index)