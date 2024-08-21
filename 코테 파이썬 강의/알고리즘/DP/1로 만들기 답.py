x = int(input())

d = [0] * 30001  # x의 범위가 최대 30000

for i in range(2, x+1):
    d[i] = d[i-1] + 1

    # d[i]를 더 작은 수로 갱신
    if i % 2 == 0:
        d[i] = min(d[i], d[i//2]+1)
    
    if i % 3 == 0:
        d[i] = min(d[i], d[i//3]+1)
    
    if i % 5 == 0:
        d[i] = min(d[i], d[i//5]+1)

print(d[x])