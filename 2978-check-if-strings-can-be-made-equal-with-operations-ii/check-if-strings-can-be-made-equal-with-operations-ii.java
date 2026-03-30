class Solution {
    public boolean checkStrings(String s1, String s2) {
        int[][] freq = new int[2][26];
        
        for (int i = 0; i < s1.length(); i++) {
            freq[i % 2][s1.charAt(i) - 'a']++;
            freq[i % 2][s2.charAt(i) - 'a']--;
        }
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 26; j++) {
                if (freq[i][j] != 0) return false;
            }
        }
        
        return true;
    }
}