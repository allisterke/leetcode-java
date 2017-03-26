package a537;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private List<Integer> getCoe(String a) {
        String[] splitted = a.split("\\+|i");
        return Arrays.asList(Integer.valueOf(splitted[0]), Integer.valueOf(splitted[1]));
    }

    private List<Integer> multiply(List<Integer> a, List<Integer> b) {
        return Arrays.asList(a.get(0)*b.get(0) - a.get(1)*b.get(1), a.get(0)*b.get(1) + a.get(1)*b.get(0));
    }

    private String asString(List<Integer> a) {
        StringBuilder sb = new StringBuilder();
        sb.append(a.get(0));
        sb.append('+');
        sb.append(a.get(1));
        sb.append('i');
        return sb.toString();
    }

    public String complexNumberMultiply(String a, String b) {
        return asString(multiply(getCoe(a), getCoe(b)));
    }

    public static void main(String[] args) {
        List<List<String>> tests = Arrays.asList(
                Arrays.asList("1+1i", "1+1i"),
                Arrays.asList("1+-1i", "1+-1i")
        );
        List<String> results = Arrays.asList(
                "0+2i",
                "0+-2i"
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.println(i);
            System.out.println(s.complexNumberMultiply(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}