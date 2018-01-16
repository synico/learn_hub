package com.ds.tree;

public class AVLTree {
    
    class AVLNode<T extends Comparable<?>> {
        T element;
        AVLNode<T> left;
        AVLNode<T> right;
        int height;
        
        AVLNode(T theElement) {
            this(theElement, null, null);
        }
        
        AVLNode(T element, AVLNode<T> leftNode, AVLNode<T> rightNode) {
            this.element = element;
            this.left = leftNode;
            this.right = rightNode;
            this.height = 0;
        }
        
        public String toString() {
            return "" + element;
        }
    }
    
    public <T extends Comparable<?>> AVLNode<T> insert(T val, AVLNode<T> root) {
        if(root == null) {
            root = new AVLNode<T>(val);
        } else {
            if(this.myCompareTo(val, root.element) < 0) {
                root.left = insert(val, root.left);
            } else {
                root.right = insert(val, root.right);
            }
        }
        return root;
    }
    
    private <T> int myCompareTo(T lv, T rv) {
        return ((Comparable<T>)lv).compareTo(rv);
    }


    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        AVLNode<Integer> root = avlTree.insert(1, null);
        avlTree.insert(2, root);
        System.out.println(avlTree);
    }

}
