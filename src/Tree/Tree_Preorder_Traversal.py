## 입력은 트리형태로.
## 트리 전위 순회 알고리즘

n = int(input())

tree = {}

for i in range(n):
    root, left, right = input().split()
    tree[root] = [left, right]

def preorder(root):
    if root != '.':
        print(root, end='')
        preorder(tree[root][0])
        preorder(tree[root][1])

preorder('A')