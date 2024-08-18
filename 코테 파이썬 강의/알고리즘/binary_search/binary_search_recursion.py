# 이진 탐색

# 재귀함수를 이용한 이진탐색
def binary_search(array, target, start, end):
    if start > end:
        return None
    mid = (start + end) // 2

    if array[mid] == target:
        return mid
    elif array[mid] > target:
        return binary_search(array, target, start, mid-1)
    else:
        return binary_search(array,target, mid+1, end)
    
def binary_search_iter(array, target, start, end):
    while start <= end:
        mid = (start+end)//2

        if array[mid] == target:
            return mid
        elif array[mid] > target:
            end = mid -1
        else:
            start = mid + 1
    return None
    
n, target = list(map(int, input().split()))

array = list(map(int, input().split()))

result = binary_search(array, target, 0, n-1)
if result == None:
    print("원소가 존재하지 않습니다.")
else:
    print(result+1)

result2 = binary_search_iter(array, target, 0, n-1)
if result2 == None:
    print("원소가 존재하지 않습니다.")
else:
    print(result2+1)
