import sys
input = sys.stdin.readline

T = int(input().rstrip())

for _ in range(T):
    N = int(input().rstrip())

    P = []
    P.append(1)
    P.append(1)
    P.append(1)
    P.append(2)
    P.append(2)

    for i in range(5, N):
        P.append(P[i-1] + P[i-5]) 
    
    print(P[N-1])