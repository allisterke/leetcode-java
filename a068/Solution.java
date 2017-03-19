package a068;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < words.length; ) {
            int length = words[i].length();
            int j = i+1;
            for(; j < words.length; ++ j) {
                int nl = length + 1 + words[j].length();
                if(nl > maxWidth) {
                    break;
                }
                length = nl;
            }

            sb.append(words[i]);

            int wc = j - i;
            if(wc == 1 || j == words.length) {
                for(int k = 1; k < wc; ++ k) {
                    sb.append(' ');
                    sb.append(words[i + k]);
                }
                while (sb.length() < maxWidth) {
                    sb.append(' ');
                }
            }
            else {
                int avg = (maxWidth - length) / (wc - 1);
                int more = (maxWidth - length) % (wc - 1);
                for(int k = 0; k < wc-1; ++ k) {
                    for(int d = 0; d <= avg + (k < more ? 1 : 0); ++ d, sb.append(' ')) ;
                    sb.append(words[i + k + 1]);
                }
            }

            result.add(sb.toString());
            sb.setLength(0);
            i = j;
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<String>> tests = Arrays.asList(
                Arrays.asList("This", "is", "an", "example", "of", "text", "justification."),
                Arrays.asList("What","must","be","shall","be.")
        );
        List<Integer> widths = Arrays.asList(16, 12);
        List<List<String>> results = Arrays.asList(
                Arrays.asList("This    is    an", "example  of text", "justification.  "),
                Arrays.asList("What must be","shall be.   ")
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            List<String> r = s.fullJustify(tests.get(i).toArray(new String[0]), widths.get(i));
            assert Arrays.equals(r.toArray(), results.get(i).toArray());
        }
    }
}