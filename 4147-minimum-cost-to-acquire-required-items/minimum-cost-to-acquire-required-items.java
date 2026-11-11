
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {

        long both = Math.min(need1, need2);

        // Option 1: Buy common items for both requirements
        long costA = both * Math.min(costBoth, cost1 + cost2);

        // Remaining requirements
        long remaining1 = need1 - both;
        long remaining2 = need2 - both;

        // Option 2: Buy remaining items separately or use type 3
        long costB = (long) remaining1 * Math.min(cost1, costBoth)
                   + (long) remaining2 * Math.min(cost2, costBoth);

        return costA + costB;
    }
}
