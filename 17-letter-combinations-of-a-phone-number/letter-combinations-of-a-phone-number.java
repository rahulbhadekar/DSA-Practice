
import java.util.*;

class Solution {

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        if (digits.length() == 0) {
            return result;
        }

        String[] map = {
            "", "", "abc", "def",
            "ghi", "jkl", "mno",
            "pqrs", "tuv", "wxyz"
        };

        backtrack(digits, 0, new StringBuilder(), result, map);

        return result;
    }

    public void backtrack(
        String digits,
        int index,
        StringBuilder current,
        List<String> result,
        String[] map
    ) {

        // Base condition
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get letters for current digit
        String letters = map[digits.charAt(index) - '0'];

        // Try every possible letter
        for (char ch : letters.toCharArray()) {

            current.append(ch);

            backtrack(digits, index + 1, current, result, map);

            // Backtracking
            current.deleteCharAt(current.length() - 1);
        }
    }
}
