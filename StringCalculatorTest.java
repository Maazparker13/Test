class StringCalculator {

    //Created an add method which takes a string input called numbers
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        //Created an array of string by splitting the inpur ","
        String[] nums = numbers.split(",");
        int sum = 0;

        //Used Enhanced For Loop
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
