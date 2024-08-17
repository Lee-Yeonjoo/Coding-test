import sys
input = sys.stdin.readline

N = int(input().rstrip())

array = []
for i in range(N):
    array.append(int(input().rstrip()))

array.sort(reverse=True)

for i in array:
    print(i)