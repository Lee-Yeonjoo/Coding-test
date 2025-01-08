import sys
from collections import deque
input = sys.stdin.readline

T = int(input().rstrip())

for _ in range(T):
    p = input().rstrip()
    n = int(input().rstrip())
    x = input().rstrip()
    if n > 0:
        dq = deque(x[1:len(x)-1].split(","))
    else:
        dq = deque()
    
    is_error = False
    numR = 0
    for i in p:
        if i == 'R':
            numR += 1
        
        if i == 'D':
            #이미 배열이 비었다면 에러러
            if not dq:
                is_error = True
                break

            #R의 개수가 짝수면 배열이 그대로라 앞쪽을 pop
            if numR % 2 == 0:
                dq.popleft()
            #R의 개수가 홀수면 배열이 뒤집었을 때를 가정해 뒤쪽을 pop
            else:
                dq.pop()
    
    if is_error:
        print("error")
    else:
        #최종적으로 numR의 수가 홀수일 때만 딱 1번 reverse하므로 시간초과를 막을 수 있다. reverse연산은 O(N)이라 반복문 내에서 쓸 때 주의해야함
        if numR % 2 != 0:
            dq.reverse()
        print("[" + ",".join(dq) + "]")