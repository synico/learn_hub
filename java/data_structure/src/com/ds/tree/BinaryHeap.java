package com.ds.tree;

public class BinaryHeap<T extends Comparable<? super T>> {
    
    private int currentSize;
    private T [] array;
    
    public BinaryHeap() {}
    
    public BinaryHeap(int capacity) {}
    
    public BinaryHeap(T [] items) {
        this.array = items;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public void makeEmpty() {
        
    }
    
    public boolean insert(T x) {
        return true;
    }
    
    public T deleteMin() {
        return null;
    }
    
    private void precolateDown(int hole) {
        
    }
    
    private void precolateUp(int hole) {
        
    }
    
    private void buildHeap() {
        
    }
    
    private boolean enlargeArray(int newSize) {
        return true;
    }

    public static void main(String[] args) {

    }

}
