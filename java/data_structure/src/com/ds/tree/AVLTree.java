package com.ds.tree;

public class AVLTree {
    
    private static class AVLNode<T extends Comparable<?>> {
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
    }

    public static void main(String[] args) {
        System.out.println(5/2);
    }

}
