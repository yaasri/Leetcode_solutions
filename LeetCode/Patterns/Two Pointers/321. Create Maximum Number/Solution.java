class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int[] result = new int[k];

        for (int i = 0; i <= k; i++) {

            if (i > nums1.length || k - i > nums2.length) {
                continue;
            }

            int[] a = maxArray(nums1, i);
            int[] b = maxArray(nums2, k - i);

            int[] merged = merge(a, b);

            if (greater(merged, 0, result, 0)) {
                result = merged;
            }
        }

        return result;
    }

    public int[] maxArray(int[] nums, int k) {

        int[] stack = new int[k];
        int top = 0;

        int remove = nums.length - k;

        for (int num : nums) {

            while (top > 0 &&
                   remove > 0 &&
                   stack[top - 1] < num) {

                top--;
                remove--;
            }

            if (top < k) {
                stack[top++] = num;
            } else {
                remove--;
            }
        }

        return stack;
    }

    public int[] merge(int[] a, int[] b) {

        int[] result = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length || j < b.length) {

            if (greater(a, i, b, j)) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        return result;
    }

    public boolean greater(int[] a, int i, int[] b, int j) {

        while (i < a.length && j < b.length &&
               a[i] == b[j]) {
            i++;
            j++;
        }

        return j == b.length ||
               (i < a.length && a[i] > b[j]);
    }
}