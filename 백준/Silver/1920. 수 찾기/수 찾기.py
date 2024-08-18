# 이진 탐색
# 배열의 길이가 10만이므로 이중 for문 돌리면 100억번이라 시간초과가 난다. 따라서 이진탐색
import sys
input = sys.stdin.readline

N = int(input().rstrip())
A = list(map(int, (input().split())))
M = int(input().rstrip())
B = list(map(int, input().split()))

def binary_search(array, target, start, end):
    if start > end:
        return 0

    mid = (start+end)//2

    if array[mid] == target:
        return 1
    elif array[mid] > target:
        return binary_search(array, target, start, mid - 1)
    else:
        return binary_search(array, target, mid + 1, end)

# 이진탐색이므로 정렬을 꼭 해줘야 한다. 이거 깜박해서 헤맸음
A.sort()
# 시간복잡도 O(MlogN)
for x in B:
    print(binary_search(A, x, 0, N - 1))