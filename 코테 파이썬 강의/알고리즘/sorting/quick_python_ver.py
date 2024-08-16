# 파이썬의 리스트 슬라이싱, 컴프리헨션을 이용한 퀵정렬 - 간단해짐
array = [5, 7, 9, 0, 3, 1, 6, 2, 4, 8]

def quick_sort(array):
    if len(array) <= 1:
        return array
    
    pivot = array[0]
    tail = array[1:]  # pivot 제외한 리스트

    # 코드 한 줄로 분할이 바로 된다.
    left_side = [x for x in tail if x <= pivot]  # 왼쪽에 pivot보다 작은 값들을 바로 넣음
    right_side = [x for x in tail if x > pivot]  # 오른쪽에 pivot보다 큰 값들을 바로 넣음

    # 분할된 각 각은 다시 퀵 정렬 수행하고, pivot과 붙여서 정렬된 결과를 반환
    return quick_sort(left_side) + [pivot] + quick_sort(right_side)

print(quick_sort(array))