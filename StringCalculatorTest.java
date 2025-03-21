class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] nums = numbers.split(",");
        int sum = 0;

        for (String num : nums) {
            sum += Integer.parseInt(num.trim());
        }

        return sum;
    }
}

public class StringCalculatorTest {
    public static void main(String[] args) {
        StringCalculator calculator = new StringCalculator();

        System.out.println("Input: \"\" , Output: " + calculator.add("")); // Expected Output: 0
        System.out.println("Input: \"1\" , Output: " + calculator.add("1")); // Expected Output: 1
        System.out.println("Input: \"1,5\" , Output: " + calculator.add("1,5")); // Expected Output: 6
    }
}
