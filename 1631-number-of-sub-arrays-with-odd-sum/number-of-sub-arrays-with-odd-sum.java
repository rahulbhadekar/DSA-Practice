
class Solution {
    public int numOfSubarrays(int[] arr) {

        int MOD = 1000000007;

        int even = 1;
        int odd = 0;

        int prefixSum = 0;
        int count = 0;

        for (int num : arr) {

            prefixSum += num;

            if (prefixSum % 2 == 0) {

                count = (count + odd) % MOD;
                even++;

            } else {

                count = (count + even) % MOD;
                odd++;
            }
        }

        return count;
    }
}
