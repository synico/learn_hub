package com.ds.sorting;

public class InsertionSort {
	
	public static int[] insertionSort1(int[] array) {
		for(int i=1; i < array.length; i++) {
			int flag = array[i];
			for(int j=i; j > 0 ;) {
				j--;// continue to move ahead
				if(flag < array[j]) {
					array[i] = array[j];//bigger element transfer to position of 'flag'
					array[j] = flag;
					i = j;
				}
//				debugInfo(i, j, array);
			}
		}
		return array;
	}
	
	public static int[] insertionSort2(int[] array) {
		int j;
		for(int p = 1; p < array.length; p++) {
			int temp = array[p];
			for(j = p; j> 0 && temp < array[j - 1]; j--) {
				array[j] = array[j - 1];
			}
			array[j] = temp;
		}
		return array;
	}
	
	private static void debugInfo(int idx1, int idx2, int[] array) {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for(int i : array) {
			str.append(i);
			str.append(",");
		}
		str.append("]");
		System.out.println("i: " + idx1 + ", j: " + idx2 + ", array: " + str.toString());
	}

	public static void main(String[] args) {
		int[] demo = {34, 8, 64, 51, 32, 21};
		debugInfo(0, 0, demo);
		int[] sortedArray = insertionSort1(demo);
		debugInfo(0, 0, sortedArray);
	}

}
