package a273;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public String numberToWords(int num) {
        if(num == 0) {
            return "Zero";
        }

        StringBuilder sb = new StringBuilder();
        int[] segments = {1000*1000*1000, 1000*1000, 1000, 1};
        String[] units = {"Billion", "Million", "Thousand", ""};

        for(int i = 0; i < segments.length; ++ i) {
            int n = num / segments[i];
            num %= segments[i];
            if(n != 0) {
                sb.append(numberToWordsLessThan1000(n));
                sb.append(' ');
                sb.append(units[i]);
                sb.append(' ');
            }
        }
        while (sb.charAt(sb.length() - 1) == ' ') {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    private String numberToWordsLessThan1000(int num) {
        if(num <= 0 || num >= 1000) {
            return "";
        }
        String[] orders = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"
        };
        String[] decades = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        StringBuilder sb = new StringBuilder();
        if(num >= 100) {
            sb.append(orders[num / 100]);
            sb.append(' ');
            sb.append("Hundred");
            sb.append(' ');
            num %= 100;
        }
        if(num >= 20) {
            sb.append(decades[num / 10]);
            sb.append(' ');
            num %= 10;
        }
        if(num > 0) {
            sb.append(orders[num]);
        }
        while (sb.charAt(sb.length() - 1) == ' ') {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(123, 12345, 1234567, 50868);
        List<String> results = Arrays.asList(
                "One Hundred Twenty Three",
                "Twelve Thousand Three Hundred Forty Five",
                "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven",
                "Fifty Thousand Eight Hundred Sixty Eight"
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.println(i);
            System.out.println(s.numberToWords(tests.get(i)));
            System.out.println(s.numberToWords(tests.get(i)).equals(results.get(i)));
        }
    }
}