package leetcode;

public class MedianTwoSortedArrays {
    
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        
        if(len1 == 0 || len2 ==0) {
            //binary search
        } 
        int[] nums = new int[len1 + len2];
        int i1 = 0, i2 =0;
        while(i1 < len1 || i2 < len2) {
            
        }
        return 0.0;
    }
    
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int tlen = len1 + len2;
        double rst = 0;
        if(tlen%2 == 1) {//odd
            int median = tlen/2;
            int i = 0;
            int i1 = 0;
            int i2 = 0;
            if(len1 == 0 || len2 == 0) {
                if(len1 == 0) {
                    rst = nums2[median];
                } else {
                    rst = nums1[median];
                }
            } else {
                while(i <= median) {
                    if(nums1[i1] < nums2[i2]) {
                        rst = nums1[i1];
                        if(i1 >= len1 -1) {
                            i2++;
                        } else {
                            i1++;
                        }
                        i++;
                    } else {
                        rst = nums2[i2];
                        if(i2 >= len2 - 1) {
                            i1++;
                        } else {
                            i2++; 
                        }
                        i++;
                    }
                }
            }
        } else {//even
            int rst1 = 0;
            int rst2 = 0;
            int median = tlen/2 -1;
            int i = 0;
            int i1 = 0;
            int i2 = 0;
            if(len1 == 0 || len2 == 0) {
                if(len1 == 0) {
                    rst1 = nums2[median];
                    rst2 = nums2[median + 1];
                } else {
                    rst1 = nums1[median];
                    rst2 = nums1[median + 1];
                }
            } else {
                while(i <= median) {
                    if(nums1[i1] < nums2[i2]) {
                        rst1 = nums1[i1];
                        if(i1 >= len1 - 1) {
                        } else {
                            i1++;
                        }
                        i++;
                    } else {
                        rst1 = nums2[i2];
                        if(i2 >= len2 -1) {
                        } else {
                            i2++; 
                        }
                        i++;
                    }
                }
                
                if(i1 >= len1 - 1) {
                    rst2 = nums2[i2];
                }
                if(i2 >= len2 - 1) {
                    rst2 = nums1[i1];
                }
                if(i1 < len1 && i2 < len2) {
                    if(nums1[i1] < nums2[i2]) {
                        rst2 = nums1[i1];
                    } else {
                        rst2 = nums2[i2];
                    }
                }
            }
            rst = (rst1 + rst2)/2.0;
        }
        return rst;
    }

    public static void main(String[] args) {
        int [] nums1 = {2,2,2,2};
        int [] nums2 = {2,2,2};
        System.out.println(MedianTwoSortedArrays.findMedianSortedArrays(nums1, nums2));
        
        int [] nums3 = {1, 2};
        int [] nums4 = {3, 4};
        System.out.println(MedianTwoSortedArrays.findMedianSortedArrays(nums3, nums4));
    }

}
