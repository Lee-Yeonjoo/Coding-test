import sys
input = sys.stdin.readline

N = int(input().rstrip())

array = []
for _ in range(N):
    array.append(int(input().rstrip()))

array.sort()
for i in array:
    print(i)