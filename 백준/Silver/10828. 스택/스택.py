import sys
input = sys.stdin.readline

N = int(input().rstrip())

stack = []
for _ in range(N):
    command = input().split()
    size = len(stack)  #시간복잡도?

    if command[0] == "push":
        stack.append(command[1])
    elif command[0] == "pop":
        if size == 0:
            print(-1)
            continue
        print(stack.pop())
    elif command[0] == "size":
        print(size)
    elif command[0] == "empty":
        if size == 0:
            print(1)
        else:
            print(0)
    else:
        if size == 0:
            print(-1)
        else:
            print(stack[size-1])
