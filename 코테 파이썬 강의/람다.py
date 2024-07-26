array = [('홍길동', 50), ('이순신', 32), ('아무개', 74)]

def my_key(x):
	return x[1]

# array의 원소인 튜플(이름, 점수)을 점수(x[1])를 기준으로 정렬
print(sorted(array, key=my_key))  # 람다 사용 안하고, 함수를 전달하는 경우
print(sorted(array, key=(lambda x: x[1])))  # 람다 사용.

#리스트에 적용하는 예시
list1 = [1,2,3,4,5]
list2 = [6,7,8,9,10]

result = list(map(lambda a,b : a+b, list1, list2))

print(result)

x = (lambda a, b: a+b)(3,7)
print(x) 