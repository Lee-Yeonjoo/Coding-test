#딕셔너리를 이용해 탐색
import sys
input = sys.stdin.readline

T = int(input().rstrip())

for _ in range(T):
    N = int(input().rstrip())
    note1 = list(map(int, input().split()))
    
    #딕셔너리에 키값으로 저장
    d = dict()
    for i in note1:
        d[i] = True

    M = int(input().rstrip())
    note2 = list(map(int, input().split()))

    for i in note2:
        if d.get(i) == True:
            print(1)
        else:
            print(0)