import sys
input = sys.stdin.readline

N = int(input().rstrip())

nums = [0] * N
sum = 0
for i in range(N):
    nums[i] = int(input().rstrip())
    sum += nums[i]

#산술평균
print(round(sum/N))

#중앙값
nums.sort()
print(nums[N//2])

#최빈값 - 딕셔너리 사용
count = dict()

maxNum = 0
maxNumList = []
for i in nums:
    if i not in count:
        count[i] = 1
    else:
        count[i] += 1
    if maxNum < count[i]:
        maxNum = count[i]
    
maxNumList = []
for i in count:
    if count[i] == maxNum:
       maxNumList.append(i)

if len(maxNumList) >= 2:
    print(maxNumList[1])
else:
    print(maxNumList[0])

#범위
print(max(nums) - min(nums))