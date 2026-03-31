class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int len = n + m - 1;

        char[] word = new char[len];

        // Step 1: fill with '?'
        Arrays.fill(word, '?');

        // Step 2: Apply 'T'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (word[i + j] == '?' || word[i + j] == str2.charAt(j)) {
                        word[i + j] = str2.charAt(j);
                    } else {
                        return "";
                    }
                }
            }
        }

        // Step 3: fill remaining with 'a'
        for (int i = 0; i < len; i++) {
            if (word[i] == '?') word[i] = 'a';
        }

        // Step 4: Fix 'F'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {

                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (word[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    boolean fixed = false;

                    for (int j = m - 1; j >= 0 && !fixed; j--) {
                        char original = word[i + j];

                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == original) continue;

                            word[i + j] = c;

                            if (isValid(word, str1, str2)) {
                                fixed = true;
                                break;
                            }
                        }

                        if (!fixed) word[i + j] = original;
                    }

                    if (!fixed) return "";
                }
            }
        }

        return new String(word);
    }

    // Check all T constraints again
    private boolean isValid(char[] word, String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (word[i + j] != str2.charAt(j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}