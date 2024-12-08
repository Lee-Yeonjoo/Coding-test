ans = [1, 3, 5, 7, 9, 20, 31, 42, 53, 64, 75, 86, 97]

def d(n):
    sum = n
    while n != 0:
        sum += n%10
        n //= 10
    return sum

no_self_num = []
for i in range(86, 10001):
    no_self_num.append(d(i))

for i in ans:
    print(i)

for i in range(100, 10001):
    if i not in no_self_num:
        print(i)
