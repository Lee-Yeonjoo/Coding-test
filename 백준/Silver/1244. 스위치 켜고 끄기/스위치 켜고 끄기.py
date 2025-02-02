import sys
input = sys.stdin.readline

N = int(input().rstrip())
switch = list(map(int, input().split()))
K = int(input().rstrip())

for _ in range(K):
    a, b = map(int, input().split())

    #남학생인 경우
    if a == 1:
        for i in range(N):
            if (i+1) % b == 0:
                if switch[i] == 0:
                    switch[i] = 1
                else:
                    switch[i] = 0
    #여학생인 경우
    else:
        for i in range(min(b-1, N-b)+1):
            #b를 기준으로 양쪽이 같을 때마다 스위치 상태 변경 
            x = (b-1) - i
            y = (b-1) + i
            if switch[x] == switch[y]:
                if switch[x] == 0:
                    switch[x] = 1
                    switch[y] = 1
                else:
                    switch[x] = 0
                    switch[y] = 0
            else:
                break  #양쪽이 다르면 바로 break를 해야함!! 다음 구간을 찾으면 안되니까 
                
for i in range(len(switch)):
    #20개 넘을 때마다 줄 바꾸기 
    if i % 20 == 0 and i > 0:
        print()
    print(switch[i], end=' ')