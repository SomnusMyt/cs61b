package byow.Core;

public class StringInputSource implements InputSource {
    private String input;
    private int index;

    public StringInputSource(String s) {
        input = s;
        index = 0;
    }

    public char getNextKey() {
        char returnChar = input.charAt(index);
        index++;
        return returnChar;
    }

    public boolean possibleNextInput() {
        return index < input.length();
    }
}
