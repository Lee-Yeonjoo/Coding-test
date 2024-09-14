import sys
from bisect import bisect_left, bisect_right
input = sys.stdin.readline

N = int(input().rstrip())
card = list(map(int, input().split()))
M = int(input().rstrip())
array = list(map(int, input().split()))

def count_by_range(array, left_value, right_value):
    right_index = bisect_right(array, right_value)
    left_index = bisect_left(array, left_value)
    return right_index - left_index

card.sort()

result = []
for i in array:
    print(count_by_range(card, i, i), end = " ")
