import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n";  // Default delimiters: comma and newline
        String numString = numbers;

        // Check for custom delimiter
        if (numbers.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(\\[.*?])+\\n").matcher(numbers);
            if (matcher.find()) {
                String delimiterPart = matcher.group();
                numString = numbers.substring(delimiterPart.length());

                // Extract delimiters
                List<String> delimiters = new ArrayList<>();
                Matcher delimMatcher = Pattern.compile("\\[(.*?)]").matcher(delimiterPart);
                while (delimMatcher.find()) {
                    delimiters.add(Pattern.quote(delimMatcher.group(1))); // Handle special chars
                }
                delimiter = String.join("|", delimiters);
            } else {
                delimiter = Pattern.quote(Character.toString(numbers.charAt(2)));
                numString = numbers.substring(4);
            }
        }

        return calculateSum(numString, delimiter);
    }

    private int calculateSum(String numbers, String delimiter) {
        String[] tokens = numbers.split(delimiter);
        List<Integer> negatives = new ArrayList<>();
        int sum = 0;

        for (String token : tokens) {
            if (!token.isEmpty()) {
                int num = Integer.parseInt(token);

                if (num < 0) {
                    negatives.add(num);
                } else if (num <= 1000) { // Ignore numbers greater than 1000
                    sum += num;
                }
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }

        return sum;
    }

    public static void main(String[] args) {
        StringCalculator calculator = new StringCalculator();

        // Test cases
        System.out.println(calculator.add("")); // 0
        System.out.println(calculator.add("1")); // 1
        System.out.println(calculator.add("1,2")); // 3
        System.out.println(calculator.add("1\n2,3")); // 6
        System.out.println(calculator.add("//;\n1;2")); // 3
        System.out.println(calculator.add("//[***]\n1***2***3")); // 6
        System.out.println(calculator.add("//[*][%]\n1*2%3")); // 6
        System.out.println(calculator.add("//[###][@@]\n1###2@@3")); // 6
        
        // Uncomment to test negative numbers (throws exception)
        // System.out.println(calculator.add("-1,2,-3"));
    }
}
