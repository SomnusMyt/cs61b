import java.util.*;

public class MyTrieSet implements TrieSet61B{
    private TriesNode root;

    private class TriesNode {
        private char c;
        private boolean isKey;
        private Map<Character, TriesNode> successor;

        public TriesNode(char c, boolean isKey) {
            this.c = c;
            this.isKey = isKey;
            successor = new HashMap<>();
        }
    }

    public MyTrieSet() {
        root =new TriesNode('\0', false);
    }

    /** Clears all items out of Trie */
    public void clear() {
        root = null;
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        if (key == null || key.length() < 1 || root == null) {
            return false;
        }
        int n = key.length();
        TriesNode curr = root;
        for (int i = 0; i < n; i++) {
            char c = key.charAt(i);
            if (curr.successor.containsKey(c)) {
                curr = curr.successor.get(c);
            }
            else {
                return false;
            }
        }
        return curr.isKey == true;
    }


    /** Inserts string KEY into Trie */
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        TriesNode curr = root;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            char c = key.charAt(i);
            if (curr.successor.containsKey(c)) {
                curr = curr.successor.get(c);
            }
            else {
                curr.successor.put(c, new TriesNode(c, false));
                curr = curr.successor.get(c);
            }
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TriesNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            curr = curr.successor.get(prefix.charAt(i));
        }
        if (curr.isKey == true) {
            result.add(prefix);
        }
        for (TriesNode tn : curr.successor.values()) {
            keyWithPrefix(prefix, result, tn);
        }
        return result;
    }

    private void keyWithPrefix(String prefix, List<String> result, TriesNode curr) {
        if (curr.isKey) {
            result.add(prefix + curr.c);
        }
        for (TriesNode tn : curr.successor.values()) {
            keyWithPrefix(prefix + curr.c, result, tn);
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();

        t.add("hi");
        t.add("help");
        t.add("zebra");
    }
}
