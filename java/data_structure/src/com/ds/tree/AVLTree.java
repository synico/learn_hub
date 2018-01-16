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
    
    //recursive method
    public static int triangle(int n) {
        System.out.println("Entering: n = " + n);
        if(n == 1) {
            System.out.println("Returning 1");
            return 1;
        } else {
            int temp = n + triangle(n - 1);
            System.out.println("Returning " + temp);
            return temp;
        }
    }

    public static void main(String[] args) {
        AVLTree.triangle(5);
    }

}
