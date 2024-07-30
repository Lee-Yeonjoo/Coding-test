class Node:
    def __init__(self, key=None):
        self.key = key
        self.prev = self
        self.next = self
    def __str__(self):
        return str(self.key)  # 자바의 toString()같은 작업인듯

class DoublyLinkedList:
    def __init__(self):
        self.head = Node()
        self.head.prev = self.head
        self.head.next = self.head
        self.size = 0

    def __iter__(self):
        v = self.head.next
        while(v != self.head):
            yield v
            v = v.next

    def __len__(self):
        return self.size
    
    def splice(self, a, b, x):
        if(a == None or b==None or x ==None): return
        ap = a.prev
        bn = b.next
        xn = x.next

        ap.next = bn
        bn.prev = ap

        x.next = a
        xn.prev = b
        a.prev = x
        b.next = xn
    
    # 이동 연산
    # a가 x의 뒤로 이동하는 연산
    def moveAfter(self, a, x):
        self.splice(a, a, x)
    
    # a가 x의 앞으로 이동하는 연산
    def moveBefore(self, a, x):
        self.splice(a, a, x.prev)
    
    # 삽입 연산
    # 새 노드를 x의 뒤에 삽입. key는 새 노드의 data
    def insertAfter(self, x, key):
        newNode = Node(key)
        self.moveAfter(newNode, x)
        self.size += 1
    
    # 새 노드를 x의 앞에 삽입.
    def insertBefore(self, x, key):
        newNode = Node(key)
        self.moveBefore(newNode, x)
        self.size += 1
    
    # 맨 앞에 노드 추가
    def pushFront(self, key):
        self.insertAfter(self.head, key)
    
    # 맨 뒤에 노드 추가
    def pushBack(self, key):
        self.insertBefore(self.head, key)
    
    # 탐색 연산
    def search(self, key):
        v = self.head
        while(v.next != self.head):
            if v.key == key:
                return v
            v = v.next
        if v.key == key:
            return v 
        return None
    
    # 삭제 연산
    def remove(self, x):
        if (x == None or x == self.head): return
        x.prev.next = x.next
        x.next.prev = x.prev
        del x  # 해당 변수의 선언을 무효화. 메모리 상에서 삭제
        self.size -= 1

    # 인덱스로 접근
    def index(self, idx):
        if idx < 0 or idx > self.size:
            return None
        v = self.head
        for i in range(idx):
            v = v.next
        return v
        

import sys
str = sys.stdin.readline().rstrip()
linked_list = DoublyLinkedList()

# 커서 노드를 이동시키면서 명령 수행하기 위해 커서 노드 생성
cursor = Node('|')
linked_list.head.next = cursor
linked_list.head.prev = cursor
cursor.next = linked_list.head
cursor.prev = linked_list.head

# 연결 리스트에 문자열 담기
for i in range(len(str)):
    linked_list.insertBefore(cursor, str[i])

# 입력할 명령의 개수
M = int(sys.stdin.readline().rstrip())

for i in range(M):
    order = list(sys.stdin.readline().rstrip().strip())
    
    if order[0] == 'L':
        if cursor.prev != linked_list.head:
            linked_list.moveBefore(cursor, cursor.prev)
    elif order[0] == 'D':
        if cursor.next != linked_list.head:
            linked_list.moveAfter(cursor, cursor.next)
    elif order[0] == 'B':
        linked_list.remove(cursor.prev)
    else:
        linked_list.insertBefore(cursor, order[2])

linked_list.remove(cursor)  # 커서 노드 삭제

for node in linked_list:
    print(node.key, end="")