def recursive_function(i):
    # 재귀함수의 시작 부분에 종료조건을 명시한다.
	if i == 100:
		return
	print(i, '번째 재귀함수에서', i+1, '번째 재귀함수를 호출')
	recursive_function(i+1)
	print(i, '번째 재귀함수를 종료')

recursive_function(1)