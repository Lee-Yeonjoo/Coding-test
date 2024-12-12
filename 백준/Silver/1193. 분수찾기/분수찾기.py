#X가 있는 라인 번호만큼 반복하는 더 효율적인 코드
import sys
input = sys.stdin.readline

X = int(input().rstrip())

# 1/1
# 1/2, 2/1
# 3/1, 2/2, 1/3
# 1/4, 2/3, 3/2, 4/1
# 문제의 조건대로 위와 같은 배열을 만들 수 있다.
# line은 각 행을 나타냄. 라인의 분수의 개수 = 라인, 행

line = 1
while X > line:
    X -= line  # X에서 각 라인의 분수의 개수를 뺀다. 
    line += 1   # 다음 라인으로 증가

# X번째 수가 짝수 라인인 경우
if line % 2 == 0:
    a = X
    b = line - X + 1

# X번째 수가 홀수 라인인 경우
else:
    a = line - X + 1
    b = X

print(f"{a}/{b}")