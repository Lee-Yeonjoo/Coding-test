import sys
input = sys.stdin.readline

S = input().rstrip()
result = []
stack = []

tag = False  #True면 태그 안이고, False면 태그 밖임을 의미 
for i in S:
    #태그 밖에서 단어마다 stack pop을 통해 뒤집는 과정
    if not tag and i == ' ' or i == '<':
        while stack:
            result.append(stack.pop())
        if i == ' ':
            result.append(i)

    #태그가 시작됨 
    if i == '<':
        tag = True
        result.append(i)
    
    #태그가 끝남 
    elif i == '>':
        tag = False
        result.append(i)
    
    #태그 안에서는 문자열이 그대로 됨 
    elif tag:
        result.append(i)
    #태그 안일 경우 stack에 push
    elif not tag and i != ' ':
        stack.append(i)

#stack에 남은 문자열이 있다면 마저 pop
while stack:
    result.append(stack.pop())

for i in result:
    print(i, end="")