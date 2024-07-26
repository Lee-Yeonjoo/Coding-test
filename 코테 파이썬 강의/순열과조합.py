# 순열
from itertools import permutations

data = ['A', 'B', 'C']

result = list(permutations(data, 3)) # 3개 선택
print(result)

# 조합
from itertools import combinations

result = list(combinations(data, 2)) # 2개 선택