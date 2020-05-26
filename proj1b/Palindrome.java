public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> l = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            l.addLast(word.charAt(i));
        }
        return l;
    }

    public boolean isPalindrome(String word) {
        /*boolean flag = true;
        for (int i = 0; i < word.length() / 2; i++) {
            int j = word.length() - 1 - i;
            if (word.charAt(i) != word.charAt(j)) {
                flag = false;
                break;
            }
        }
        return flag;*/
        Deque<Character> l = wordToDeque(word);
        boolean flag = true;
        for (int i = 0; i < word.length() / 2; i++) {
            int j = word.length() - 1 - i;
            if (l.get(i) != l.get(j)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        /*boolean flag = true;
        for (int i = 0; i < word.length() / 2; i++) {
            int j = word.length() - 1 - i;
            if (!cc.equalChars(word.charAt(i), word.charAt(j))) {
                flag = false;
                break;
            }
        }
        return flag;*/
        Deque<Character> l = wordToDeque(word);
        boolean flag = true;
        for (int i = 0; i < word.length() / 2; i++) {
            int j = word.length() - 1 - i;
            if (!cc.equalChars(l.get(i), l.get(j))) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
