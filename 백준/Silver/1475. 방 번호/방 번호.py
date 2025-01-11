import sys
import math
input = sys.stdin.readline

#round함수는 가장 가까운 '짝수'로 반올림을 해주므로 반올림 함수를 따로 만들어야함
def custom_round(value):
    return int(math.floor(value + 0.5))

N = input().rstrip()

#6과 9는 구분 안하므로 합해서 인덱스6에 기록
nums = [0] * 9
for n in N:
    if n == '9':
        nums[6] += 1
    else:
        nums[int(n)] += 1

nums[6] = custom_round(nums[6] / 2)

print(max(nums))