import sys
input = sys.stdin.readline

N = int(input().rstrip())
words = []
for _ in range(N):
    words.append(input().rstrip())

result = sorted(words, key = lambda x : (len(x), x))
print(result[0])
for i in range(1, len(result)):
    if result[i] != result[i-1]:
        print(result[i])