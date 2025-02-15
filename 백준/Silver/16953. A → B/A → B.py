import sys
input = sys.stdin.readline

A, B = map(int, input().split())

count = 0
while B > A:
    #B가 2로 나누어진다면 2로 나누기
    if B % 2 == 0:
        B = B / 2
        count += 1

    #B가 1로 끝난다면 1을 떼기
    elif B % 10 == 1:
        B = B // 10
        count += 1
    
    # 둘다 해당 안된다면 break 
    else:
        break
        
if B == A:
    print(count + 1)
else:
    print(-1)