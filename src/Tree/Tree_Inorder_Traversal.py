## 입력은 트리형태로.
## 트리 중위 순회 알고리즘

n = int(input())

tree = {}

for i in range(n):
    root, left, right = input().split()
    tree[root] = [left, right]

def inorder(root):
    if root != '.':
        inorder(tree[root][0])
        print(root, end='')
        inorder(tree[root][1])

inorder('A')