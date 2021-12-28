## 입력은 트리형태로.
## 트리 후위 순회 알고리즘

n = int(input())

tree = {}

for i in range(n):
    root, left, right = input().split()
    tree[root] = [left, right]

def postorder(root):
    if root != '.':
        postorder(tree[root][0])
        postorder(tree[root][1])
        print(root, end='')

postorder('A')