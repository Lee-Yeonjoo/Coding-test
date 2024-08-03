import sys
input = sys.stdin.readline

s = input().strip()  # 문자열로 받아야 간단. strip()을 통해 개행문자를 꼭 제거

result = int(s[0])

for i in range(1, len(s)):
    x = int(s[i])

    if x == 0 or x == 1 or result == 0 or result ==1:
        result += x
    else:
        result *= x

print(result)