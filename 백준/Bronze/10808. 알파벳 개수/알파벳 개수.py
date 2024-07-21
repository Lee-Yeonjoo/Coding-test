arr = []

for i in range(0, 26):
    arr.append(0)

S = input()

for i in S:
    arr[ord(i)-97] += 1

for i in arr:
    print(i, end=" ")