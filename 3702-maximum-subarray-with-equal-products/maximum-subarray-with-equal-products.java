class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 2;
        int left = 0;
        int product = 1;

        for (int right = 0; right < n; right++) {
            while (gcd(product, nums[right]) > 1) {
                product /= nums[left];
                left++;
            }

            product *= nums[right];
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}