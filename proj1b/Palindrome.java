public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> l = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            l.addLast(word.charAt(i));
        }
        return l;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> l = wordToDeque(word);
        while (l.size() > 1) {
            if (l.removeFirst() != l.removeLast()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> l = wordToDeque(word);
        while (l.size() > 1) {
            Character first = l.removeFirst();
            Character last = l.removeLast();
            if (!(cc.equalChars(first, last))) {
                return false;
            }
        }
        return true;
    }

}
