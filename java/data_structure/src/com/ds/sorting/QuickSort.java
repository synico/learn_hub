package com.ds.sorting;

import com.ds.util.PrintUtil;

public class QuickSort {
    
    public static void bubbleSort(int [] a) {
        for(int i = 0; i < a.length; i++) {
            int temp;
            for(int j = 0; j < a.length - i - 1; j++) {
                if(a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }
    
    public static void quickSort(int [] a) {
        
    }

    public static void main(String[] args) {
        int[] demo = {13, 81, 92, 43, 65, 31, 57, 26, 75, 0};
        PrintUtil.printList(demo);
        QuickSort.quickSort(demo);
        PrintUtil.printList(demo);
    }

}
