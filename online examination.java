import java.util.*;

public class OnlineExaminationSystem {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, String> users = new HashMap<>();
    static Map<String, String> userProfiles = new HashMap<>();
    static Map<String, String> questions = new HashMap<>();
    static Map<String, String> answers = new HashMap<>();
    static String currentUser = null;
    static long examTimeLimit = 30 * 1000; // 30 seconds for demonstration
    static long startTime;

    public static void main(String[] args) {
        users.put("student1", "password1");
        users.put("student2", "password2");

        questions.put("What is 2 + 2?", "A: 3\nB: 4\nC: 5\nD: 6");
        answers.put("What is 2 + 2?", "B");

        System.out.println("Welcome to the Online Examination System!");
        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            if (choice == 1) {
                login();
                startExam();
                logout();
            } else {
                System.out.println("Exiting...");
                break;
            }
        }
    }

    public static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            currentUser = username;
            System.out.println("Login successful!");
            updateProfile();
        } else {
            System.out.println("Invalid username or password. Try again.");
        }
    }

    public static void updateProfile() {
        System.out.println("\nProfile Management");
        System.out.println("1. Update Profile");
        System.out.println("2. Change Password");
        System.out.println("3. Skip");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 1) {
            System.out.print("Enter new profile details: ");
            String profileDetails = scanner.nextLine();
            userProfiles.put(currentUser, profileDetails);
            System.out.println("Profile updated.");
        } else if (choice == 2) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            users.put(currentUser, newPassword);
            System.out.println("Password changed.");
        } else {
            System.out.println("Skipping profile update.");
        }
    }

    public static void startExam() {
        System.out.println("\nStarting the exam...");
        startTime = System.currentTimeMillis();
        int score = 0;

        for (Map.Entry<String, String> question : questions.entrySet()) {
            long elapsedTime = System.currentTimeMillis() - startTime;

            if (elapsedTime >= examTimeLimit) {
                System.out.println("Time's up! Submitting your exam...");
                break;
            }

            System.out.println(question.getKey());
            System.out.println(question.getValue());
            System.out.print("Choose an answer: ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase(answers.get(question.getKey()))) {
                score++;
            }
        }

        System.out.println("Your score is: " + score);
    }

    public static void logout() {
        System.out.println("Logging out...");
        currentUser = null;
    }
}
