import sys
input = sys.stdin.readline

N = int(input().rstrip())

points = []
for _ in range(N):
    x, y = map(int, input().split())
    points.append((x, y))

result = sorted(points, key= lambda x : (x[0], x[1]))
for i in result:
    print(i[0], i[1])