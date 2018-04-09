package leetcode.list;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMax {
    
    /**
     * @param nums
     * @param 1 =< k =< nums.length
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] rst = new int[nums.length - k + 1];
        Deque<Integer> sw = new ArrayDeque<>(k);
        for(int i = 0; i < nums.length; i++) {
            sw.offer(nums[i]);
        }
        return rst;
    }

    public static void main(String[] args) {
        SlidingWindowMax swm = new SlidingWindowMax();
        //case 1
        int[] nums1 = {1,3,-1,-3,5,3,6,7};
        int k1 = 3;
        int[] rst1 = swm.maxSlidingWindow(nums1, k1);
        for(int v : rst1) {
            System.out.print(v + "|");
        }
        System.out.println();
        System.out.println("--------------------------");
        //case 2
        int[] nums2 = {7, 4};
        int k2 = 1;
        int[] rst2 = swm.maxSlidingWindow(nums2, k2);
        for(int v : rst2) {
            System.out.print(v + "|");
        }
    }

}
