import sys
input = sys.stdin.readline

N, R1, C1, R2, C2 = map(int, input().split())

n = 2*N - 1  #마름모를 담는 정사각형의 변의 길이 
for i in range(R1, R2+1):
    for j in range(C1, C2+1):
        #나머지 연산으로 마름모를 담는 정사각형 내의 좌표를 구한다.
        x = i % n
        y = j % n

        #정사각형 내의 센터좌표와의 거리 차이.
        gap_x = abs(n//2 - x)
        gap_y = abs(n//2 - y)

        #해당 위치의 알파벳 구하기
        if gap_y >= (N - gap_x):  #만약 N-gap_x이상이면 알파벳이 넘어가 .을 찍어야함 
            print('.', end = "")
        else:  #z이후엔 다시 a이므로 26으로 나눈 나머지로 알파벳을 만든다.
            print(chr(97+(gap_x + gap_y)%26), end = "")

    print()