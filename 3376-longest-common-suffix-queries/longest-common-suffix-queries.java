class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int index = -1; // best index
    }

    TrieNode root = new TrieNode();

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        // Build Trie
        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer[i], i, wordsContainer);
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = search(wordsQuery[i]);
        }

        return ans;
    }

    private void insert(String word, int idx, String[] wordsContainer) {
        TrieNode node = root;

        // update root also (empty suffix case)
        if (node.index == -1 || isBetter(idx, node.index, wordsContainer)) {
            node.index = idx;
        }

        for (int i = word.length() - 1; i >= 0; i--) {
            int c = word.charAt(i) - 'a';

            if (node.children[c] == null) {
                node.children[c] = new TrieNode();
            }

            node = node.children[c];

            if (node.index == -1 || isBetter(idx, node.index, wordsContainer)) {
                node.index = idx;
            }
        }
    }

    private int search(String word) {
        TrieNode node = root;
        int res = node.index;

        for (int i = word.length() - 1; i >= 0; i--) {
            int c = word.charAt(i) - 'a';

            if (node.children[c] == null) break;

            node = node.children[c];
            res = node.index;
        }

        return res;
    }

    private boolean isBetter(int i, int j, String[] words) {
        if (words[i].length() != words[j].length()) {
            return words[i].length() < words[j].length();
        }
        return i < j;
    }
}