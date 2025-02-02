import sys
input = sys.stdin.readline

N, K = map(int, input().split())

x = [i for i in range(2, N+1)]

result = []
while len(result) <= K-1:
    #맨 앞 작은 수를 제거하고, result에 추가 
    v = x[0]
    x.remove(v)
    result.append(v)

    #맨 앞 작은 수의 배수들을 제거하고, result에 추가 
    for i in x:
        if i % v == 0:
            result.append(i)
            x.remove(i)

print(result[K-1])         