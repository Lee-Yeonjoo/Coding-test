def operator(a, b):
	add = a+b
	sub = a-b
	mul = a*b
	div = a/b
	return add, sub, mul, div

a, b, c, d = operator(7,3)
print(a,b,c,d)