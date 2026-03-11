class Solution {
    public int lengthOfLastWord(String s) {
        int length = 0;
        int i = s.length() - 1;

        // skip trailing spaces
        while(i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // count last word characters
        while(i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }

        return length;
    }
}