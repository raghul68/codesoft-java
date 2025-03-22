import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        // Create a Scanner object to take input from the user
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the marks
        System.out.println("Enter the marks of the student:");

        // Taking input for different subjects
        System.out.print("Enter marks for Subject 1: ");
        int subject1 = scanner.nextInt();
        
        System.out.print("Enter marks for Subject 2: ");
        int subject2 = scanner.nextInt();
        
        System.out.print("Enter marks for Subject 3: ");
        int subject3 = scanner.nextInt();
        
        System.out.print("Enter marks for Subject 4: ");
        int subject4 = scanner.nextInt();
        
        System.out.print("Enter marks for Subject 5: ");
        int subject5 = scanner.nextInt();

        // Calculate the total and average marks
        int totalMarks = subject1 + subject2 + subject3 + subject4 + subject5;
        double averageMarks = totalMarks / 5.0;

        // Output the total and average marks
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Marks: " + averageMarks);

        // Determine the grade based on average marks
        char grade;
        if (averageMarks >= 90) {
            grade = 'A';
        } else if (averageMarks >= 80) {
            grade = 'B';
        } else if (averageMarks >= 70) {
            grade = 'C';
        } else if (averageMarks >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Output the grade
        System.out.println("Grade: " + grade);

        // Close the scanner
        scanner.close();
    }
}
