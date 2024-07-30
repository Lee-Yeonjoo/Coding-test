# 딕셔너리
data = dict()
data['사과'] = "apple"
data['바나나'] = "banana"
data['코코넛'] = "coconut"

data.keys()  # 한 줄씩 실행하면 출력되는데 전체 실행 시 print()써야 출력됨
print(data.keys())
data.values()

for key in data.keys():
    print(data[key])

a = {
    '홍길동': 94,
    '이순신': 100
}

key_list = list(a.keys())
key_list
print(key_list, 'hello')

# set 집합 자료형
data = set([1,2,2,3,3,3,4])
print(data)
data = {1,1,1,2,2,3}
print(data)

a = set([1,2,3,4,5])
b = set([3,4,5,6,7])

print(a|b)
print(a&b)
print(a-b)

data = set([1,2,3])
print(data)
data.add(4)
print(data)
data.update([5,6,7])
print(data)
data.update([9,6,8])
print(data)
data.remove(3)
print(data)
data.add(3)
print(data)

# 입출력
n = int(input())
print(n)

data = list(map(int, input().split()))
print(data)

import sys
data = sys.stdin.readline().rstrip().split()  # 문자열
data = list(map(int, sys.stdin.readline().rstrip().split()))  # 정수로 변환
print(data)

a = 1
b = 2
print(a,b)
print(7, end=" ")
print(8, end=" ")

answer = 7
print("정답은 " + str(answer) + "입니다.")
print(f"정답은 {answer}입니다.")

# 조건문 - pass키워드
score = 70

if score >= 80:
    pass
else:
    print('불합격')

result = "합격" if score >= 80 else "불합격"
print(result)

# 반복문
i = 0
result = 0

while i <= 9:
    if i % 2 == 1:
        result += i
    i += 1

print(result)

array = [9,8,7,6,5]

for x in array:
    print(x, end=" ")


result = 0
for i in range(0,10):
    if i %2 ==0:
        continue
    result += i

print(result)

i = 1
while True:
    print("현재 i:", i)
    if i == 5:
        break
    i+=1

for i in range(2,10):
    for j in range(1,10):
        print(i, "X", j, "=", i*j)
    print()

# 함수
def add(a,b):
    return a+b
print(add(3,4))
print(add(b=3, a=9))

# global키워드
a = 0
def func():
    global a
    a += 1

for i in range(10):
    func()
print(a)

a = 8
def func2():
    print(a) # 전역 변수의 값 변경 없이 단순 읽기면 global 키워드 사용 안해도 됨.

func2()

array = [1,2,3,4]

def list_func():
    array.append(5)

list_func()
print(array)

# 패킹, 언패킹
def operator(a, b):
    add = a+b
    sub = a-b
    mul = a*b
    div = a/b
    return add, sub, mul, div  # 패킹
a, b, c, d = operator(4,2)  # 언패킹
print(a, b, c,d)

# 람다
print((lambda a,b: a+b)(4,2))

array = [('홍길동', 54), ('노홍철', 34), ('아무개', 12)]

def my_key(x):
    return x[1]

print(sorted(array, key=(lambda x: x[1])))
print(sorted(array, key=my_key))

list1 = [1,2,3,4,5]
list2 = [6,7,8,9,10]

result = list(map(lambda a,b: a+b, list1, list2))
print(result)

# 자주 쓰는 라이브러리
result = min([1,2,3,4])
print(result)

# 순열
from itertools import permutations

data = ['a', 'b', 'c', 'd']
result = list(permutations(data, 2))
print(result)

# 조합
from itertools import combinations
result = list(combinations(data, 2))
print(result)

# 중복 순열
from itertools import product
result = list(product(data, repeat=2))
print(result)

# 중복 조합
from itertools import combinations_with_replacement
result = list(combinations_with_replacement(data, 2))
print(result)

# 카운터
from collections import Counter
counter = Counter(['red', 'blue', 'red', 'green', 'blue', 'blue'])
print(counter['blue'])
print(dict(counter))

# gcd, lcm
import math
def lcm(a,b):
    return a*b//math.gcd(a,b)

a = 21
b = 14

print(math.gcd(a,b)) # 최대 공약수
print(lcm(a,b))  # 최소 공배수