
class Solution {
    public int minMoves(int[] nums) {

        // Step 1: Find maximum element
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        // Step 2: Calculate total moves
        int moves = 0;

        for (int i = 0; i < nums.length; i++) {
            moves += max - nums[i];
        }

        // Step 3: Return total moves
        return moves;
    }
}
