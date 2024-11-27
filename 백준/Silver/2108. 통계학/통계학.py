import sys
from collections import Counter
input = sys.stdin.readline

N = int(input().rstrip())

nums = [0] * N
for i in range(N):
    nums[i] = int(input().rstrip())

#산술평균
print(round(sum(nums)/N))

#중앙값
nums.sort()
mid = int((0 + N-1)/2)
print(nums[mid])

#최빈값
temp = []
for i in nums:
    temp.append(abs(i))

positive = [0] * (max(temp)+1)
negative = [0] * (max(temp)+1)

maxNum = 0
maxNumList = []
for i in nums:
    if i >=0:
        positive[i] += 1
        if maxNum < positive[i]:
            maxNum = positive[i]
    else:
        negative[-1 * i] += 1
        if maxNum < negative[-1 * i]:
            maxNum = negative[-1 * i]

maxNumList = []
for i in range(len(positive)):
    if positive[i] == maxNum:
        maxNumList.append(i)
    if negative[i] == maxNum:
        maxNumList.append(-1 * i)

maxNumList.sort()
if len(maxNumList) >= 2:
    print(maxNumList[1])
else:
    print(maxNumList[0])

#범위
print(max(nums) - min(nums))