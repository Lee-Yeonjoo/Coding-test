import sys
input = sys.stdin.readline

def combination(n, r):
    if factorial[n] != 0:
        return int(factorial[n] / (factorial[n-r] * factorial[r]))
    
    for i in range(1, n+1):
        factorial[i] = i * factorial[i-1]
    
    return int(factorial[n] / (factorial[n-r] * factorial[r]))


T = int(input().rstrip())

for _ in range(T):
    N, M = map(int, input().split())
    factorial = [0] * (M+1)
    factorial[0] = 1

    print(combination(M, N))
