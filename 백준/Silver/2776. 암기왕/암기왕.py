#in 연산자를 사용
import sys
input = sys.stdin.readline

T = int(input().rstrip())

for _ in range(T):
    N = int(input().rstrip())
    #in연산자는 set에서 사용할 때 더 빠르다.
    note1 = set(map(int, input().split()))
    M = int(input().rstrip())
    note2 = list(map(int, input().split()))

    for i in note2:
        if i in note1:
            print(1)
        else:
            print(0)