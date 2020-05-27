public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> l = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            l.addLast(word.charAt(i));
        }
        return l;
    }

    public boolean isPalindrome(String word) {
        /*Deque<Character> l = wordToDeque(word);
        boolean flag = true;
        for (int i = 0; i < word.length() / 2; i++) {
            int j = word.length() - 1 - i;
            if (l.get(i) != l.get(j)) {
                flag = false;
                break;
            }
        }
        return flag;*/
        return isPalindrome(wordToDeque(word));
    }
    public boolean isPalindrome(Deque<Character> l) {
        while (l.size() > 1) {
            return l.removeFirst() == l.removeLast() && isPalindrome(l);
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        /*Deque<Character> l = wordToDeque(word);
        boolean flag = true;
        for (int i = 0; i < word.length() / 2; i++) {
            int j = word.length() - 1 - i;
            if (!cc.equalChars(l.get(i), l.get(j))) {
                flag = false;
                break;
            }
        }
        return flag;*/
        return isPalindrome(wordToDeque(word), cc);
    }

    public boolean isPalindrome(Deque<Character> l, CharacterComparator cc) {
        while (l.size() > 1) {
            return cc.equalChars(l.removeFirst(), l.removeLast()) && isPalindrome(l, cc);
        }
        return true;
    }
}
