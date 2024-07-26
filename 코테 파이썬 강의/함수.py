a = 10

def func():
    global a   # 함수 바깥의 a를 참조. 
    a += 1

def func2():   # 값 수정 없이 단순 참조라면 global 키워드를 안써도 됨
    #a = 0     # 단, 함수 내에 동일한 이름의 지역변수가 존재하면 안됨 
    print(a + 2)

for i in range(10):
    func()

print(a)
func2()