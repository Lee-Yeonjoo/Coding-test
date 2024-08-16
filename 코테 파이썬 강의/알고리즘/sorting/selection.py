# 선택 정렬
array = [7, 5, 9, 0, 3, 1, 6, 2, 4, 8]

# i는 처리되지 않은 데이터 중 맨 앞 원소를 나타냄
for i in range(len(array)):
	min_index = i
	for j in range(i+1, len(array)):
		if array[min_index] > array[j]:
			min_index = j
	array[i], array[min_index] = array[min_index], array[i]

print(array)