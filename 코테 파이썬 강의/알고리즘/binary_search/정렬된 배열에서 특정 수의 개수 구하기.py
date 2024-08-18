import sys
from bisect import bisect_left, bisect_right

input = sys.stdin.readline

n, x = map(int, input().split())

# 입력 데이터가 '정렬'되어 있고, 로그 시간에 수행되기 위해 이진 탐색 라이브러리 사용
array = list(map(int, input().split()))

print(bisect_right(array, x) - bisect_left(array, x))