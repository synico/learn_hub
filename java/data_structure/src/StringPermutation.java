
public class StringPermutation {
    
    public static void permutationString(String input) {
        if(input == null || input.trim().length() == 0) {
            return;
        }
//        permutation(input.toCharArray());
    }
    
    public static void permutation(char [] input, int start) {
        for(int i = 0;i < input.length; i++) {
            //TODO
        }
    }

    public static void main(String[] args) {
        StringPermutation.permutationString("abc");
    }

}
