import sys
input = sys.stdin.readline

T = int(input().rstrip())

#이진 탐색
def binary_search(x, note):
    start = 0
    end = len(note) - 1 

    while start <= end:
        mid = (start + end) // 2

        if note[mid] == x:
            return mid
        elif note[mid] > x:
            end = mid - 1
        elif note[mid] < x:
            start = mid + 1
    return -1

for _ in range(T):
    N = int(input().rstrip())
    note1 = list(map(int, input().split()))
    M = int(input().rstrip())
    note2 = list(map(int, input().split()))

    #이진 탐색을 하기 위한 정렬 과정 O(NlogN)
    note1.sort()
    #최대 백만 번 반복 - 이진탐색도 고려하면 O(NlogN)
    for i in note2:
        result = binary_search(i, note1)

        if result == -1:
            print(0)
        else:
            print(1)
    