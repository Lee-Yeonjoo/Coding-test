import sys
input = sys.stdin.readline

X = int(input().rstrip())

fraction = [1] * 2  #1/1을 표현. fraction[0]은 분자, fraction[1]은 분모
direction = 0
count = 1
while count < X :
    if direction == 0:
        fraction[1] += 1
        count += 1
        while count < X and fraction[1] > 1:
            fraction[0] += 1
            fraction[1] -= 1
            count += 1
        direction = 1
    else:
        fraction[0] += 1
        count += 1
        while count < X and fraction[0] > 1:
            fraction[0] -= 1
            fraction[1] += 1
            count += 1
        direction = 0

print(f"{fraction[0]}/{fraction[1]}")