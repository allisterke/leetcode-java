package a324;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Solution {
    Random r = new Random();

    private void nthElement(int[] nums, int begin, int end, int k) {
        if(begin >= end) {
            return;
        }
        int bound = end - begin;
        int index = begin + r.nextInt(bound);
        int pivot = nums[index];
        nums[index] = nums[begin];
        nums[begin] = pivot;

        int left = begin;
        int right = end - 1;
        while(left < right) {
            while (right > left && nums[right] >= pivot) -- right;
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) ++ left;
            nums[right] = nums[left];
        }
        nums[left] = pivot;

        if(left > k) {
            nthElement(nums, begin, left, k);
        }
        else if(left < k) {
            nthElement(nums, left+1, end, k);
        }
    }

    private void nthElementWithIntervalOne(int[] nums, int begin, int end, int k) {
        int bound = end - begin;
        int index = begin + r.nextInt(bound);
        int pivot = nums[index*2];
        nums[index*2] = nums[begin*2];
        nums[begin*2] = pivot;

        int left = begin;
        int right = end - 1;
        while(left < right) {
            while (right > left && nums[right*2] >= pivot) -- right;
            nums[left*2] = nums[right*2];
            while (left < right && nums[left*2] <= pivot) ++ left;
            nums[right*2] = nums[left*2];
        }
        nums[left*2] = pivot;

        if(left > k) {
            nthElementWithIntervalOne(nums, begin, left, k);
        }
        else if(left < k) {
            nthElementWithIntervalOne(nums, left+1, end, k);
        }
    }

    private void rotate(int[] nums, int newBegin) {
        int begin1 = 0;
        int begin2 = newBegin;
        while(true) {
            int tmp = nums[begin1];
            nums[begin1] = nums[begin2];
            nums[begin2] = tmp;
            ++ begin1;
            ++ begin2;
            if(begin1 == newBegin && begin2 == nums.length) {
                break;
            }
            if(begin1 == newBegin) {
                newBegin = begin2;
            }
            else if(begin2 == nums.length) {
                begin2 = newBegin;
            }
        }
    }

//    private void reverse(int[] nums) {
//        int left = 0, right = nums.length - 1;
//        while(left < right) {
//            int tmp = nums[left];
//            nums[left] = nums[right];
//            nums[right] = tmp;
//            ++ left;
//            -- right;
//        }
//    }

    public void wiggleSort(int[] nums) {
        if(nums.length <= 1) {
            return;
        }

        nthElement(nums, 0, nums.length, nums.length/2);
//        for(int i = 0; i < nums.length; ++ i) {
//            System.out.print(nums[i]);
//            System.out.print(' ');
//        }
//        System.out.println();

        nthElement(nums, nums.length/2+1, nums.length, nums.length*3/4);

//        for(int i = 0; i < nums.length; ++ i) {
//            System.out.print(nums[i]);
//            System.out.print(' ');
//        }
//        System.out.println();

        for(int i = (nums.length+1)/2, j = 1; i < nums.length; ++ i, j += 2) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

//        for(int i = 0; i < nums.length; ++ i) {
//            System.out.print(nums[i]);
//            System.out.print(' ');
//        }
//        System.out.println();

        nthElementWithIntervalOne(nums, 0, (nums.length+1)/2, nums.length/4);

//        for(int i = 0; i < nums.length; ++ i) {
//            System.out.print(nums[i]);
//            System.out.print(' ');
//        }
//        System.out.println();

        for(int i = 0; i < nums.length - 1; ++ i) {
            if(nums[i] == nums[i+1]) {
                rotate(nums, i+1);
                break;
            }
        }
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {1, 5, 1, 1, 6, 4, 1},
                new int[] {1, 1, 2, 2, 3, 3},
                new int[] {1,2,1,2,1,1,2,2,1},
                new int[] {1,2,2,1,2,1,1,1,1,2,2,2},
                new int[] {1,1,2,2,2,1},
                new int[] {4,5,5,5,5,6,6,6},
                new int[] {2,3,3,2,2,2,1,1},
                new int[] {5,3,1,2,6,7,8,5,5},
                new int[] {1,3,2,2,2,1,1,3,1,1,2}
        );
        List results = Arrays.asList();

        Solution s = new Solution();;
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            s.wiggleSort(tests.get(i));
            for(int j = 0; j < tests.get(i).length; ++ j) {
                System.out.print(tests.get(i)[j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}