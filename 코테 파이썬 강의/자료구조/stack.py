# 파이썬에서 스택은 리스트의 append, pop 메서드로 구현한다.

stack = []

stack.append(5)
stack.append(2)
stack.append(3)
stack.append(7)
stack.pop()
stack.append(1)
stack.append(4)
stack.pop()

print(stack[::-1])  # 뒤에서부터 출력(스택의 최상단)
print(stack)  # 앞에서부터 출력(스택의 최하단)