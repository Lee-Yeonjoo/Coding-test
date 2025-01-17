import sys
input = sys.stdin.readline

N = int(input().rstrip())
solution = list(map(int, input().split()))
solution.sort()

left = 0
right = len(solution) - 1

result = []
#left와 right이 같아지면 빠져나옴
while left < right:
    x = solution[left] + solution[right]
    #절대값으로 튜플의 맨 앞에 저장해서 min() 쓸 수 있도록 
    result.append((abs(x), solution[left], solution[right]))

    #합이 음수면 커져야되므로 left를 이동
    if x < 0:
        left += 1
    #합이 양수면 작아져야하니 right을 이동
    elif x > 0:
        right -= 1
    #합이 0이면 답이니까 더 찾지 않고 break 
    elif x == 0:
        break

a, b, c = min(result)
print(b, c)