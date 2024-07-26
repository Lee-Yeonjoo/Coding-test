import sys
n = int(input())
data = list(map(int, input().split()))

print(data)

n, m, k = map(int, input().split())
print(n, m, k)

# 표준 라이브러리로 빠른 입출력
data = sys.stdin.readline().rstrip()
print(data, 123)

# f-string 예제
answer = 9
print(f"정답은 {answer}입니다.")
