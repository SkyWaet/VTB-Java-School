package slepenkov.gleb.hw2;

public class HW2 {
    public static boolean checkRepeat(String input) {
        String half = input.substring(0, input.length() / 2);
        String quarter = half.substring(0, half.length() / 2);
        return input.equals(half + half) && !half.equals(quarter + quarter);
    }

    public static void main(String[] args) {
        System.out.println(checkRepeat("вор"));
        System.out.println(checkRepeat("ворвор"));
        System.out.println(checkRepeat("ворворвор"));
        System.out.println(checkRepeat("ворворворворворворворвор"));
    }
}
