import sys
input = sys.stdin.readline

x = int(input().rstrip())

d = [0] * (x+1)
d[1] = 0
d[2] = 1
d[3] = 1
d[4] = 2
d[5] = 1

for i in range(6, x+1):
    if i % 30 == 0:
        d[i] = min(d[i//5]+1, d[i//3]+1, d[i//2]+1, d[i-1]+1)
    elif i % 15 ==0:
        d[i] = min(d[i//5]+1, d[i//3]+1, d[i-1]+1)
    elif i % 10 ==0:
        d[i] = min(d[i//5]+1, d[i//2]+1, d[i-1]+1)
    elif i % 6 ==0:
        d[i] = min(d[i//3]+1, d[i//2]+1, d[i-1]+1)
    elif i % 5 ==0:
        d[i] = min(d[i//5]+1, d[i-1]+1)
    elif i % 3 ==0:
        d[i] = min(d[i//3]+1, d[i-1]+1)
    elif i % 2 ==0:
        d[i] = min(d[i//2]+1, d[i-1]+1)
    else:
        d[i] = d[i-1]+1

print(d[x])