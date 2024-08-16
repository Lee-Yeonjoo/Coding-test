array = [5, 7, 9, 0, 3, 1, 6, 2, 4, 8]

def quick_sort(array, start, end):
    # 원소가 1개인 경우 종료
    if start >= end:
        return
    pivot = start
    left = start + 1
    right = end

    while left <= right:
        # 왼쪽부터 선형탐색하면서 피봇보다 큰 값을 찾을 때까지 반복
        while left <= end and array[left] <= array[pivot]:
            left += 1
        
        # 오른쪽에서부터 피봇보다 작은 값을 찾을 때까지 반복
        while right > start and array[right] >= array[pivot]:
            right -= 1
        
        # left와 right이 엇갈린 경우, pivot과 right값 교환
        if left > right:
            array[right], array[pivot] = array[pivot], array[right]
        else: # 엇갈리지 않았으면, left값과 right값 교환
            array[left], array[right] = array[right], array[left]
    
    # 분할 후 각 각에 대해 퀵정렬
    quick_sort(array, start, right-1)
    quick_sort(array, right+1, end)

quick_sort(array, 0, len(array)-1)
print(array)