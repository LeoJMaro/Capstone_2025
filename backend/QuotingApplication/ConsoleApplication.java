package QuotingApplication;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ConsoleApplication {
    public static void main(String[] args) {
        Customer currentCustomer = null;

        // Services
        AutoPolicyService autoPolicyService = new AutoPolicyService();
        HomePolicyService homePolicyService = new HomePolicyService();
        CustomerService customerService = new CustomerService();

        /*
        * TODO: Fix customer age calculation in CustomerService
        * */
        // Create mock customer - Date object uses years since 1990
        Customer mockCustomer = new Customer("John", "Doe", "john.doe@test.com", "123456789", "123 test road", new Date(90, 11, 21), "Password1");
        customerService.addCustomer(mockCustomer);

        // Create mock policies
//        AutoPolicy mockAutoPolicy = new AutoPolicy(customerService.getCustomerAge(mockCustomer), 1, 5);
//        autoPolicyService.calculateAutoPolicy(mockAutoPolicy);
//        customerService.addPolicy(mockCustomer, mockAutoPolicy);
//
//        HomePolicy mockHomePolicy = new HomePolicy(500000, 25, "Electric", "Urban");
//        homePolicyService.calculateHomePolicy(mockHomePolicy);
//        customerService.addPolicy(mockCustomer, mockHomePolicy);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Enter your email: ");
            String email = scanner.nextLine();

            System.out.println("Enter your password: ");
            String password = scanner.nextLine();

            currentCustomer = customerService.getCustomerByEmail(email);

            if (currentCustomer != null && customerService.validateEmail(currentCustomer, email) && customerService.validatePassword(currentCustomer, password)) {
                System.out.println("You have successfully logged in!");
                break;
            } else {
                System.out.println("Invalid email or password, please try again");
            }
        }

        while(true) {
            System.out.println("1. Display auto policy quotes");
            System.out.println("2. Display home policy quotes");
            System.out.println("3. Get an auto quote.");
            System.out.println("4. Get a home quote.");
            System.out.println("7. Quit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    for (int i = 0; i < currentCustomer.getPolicyList().toArray().length; i++) {
                        if (currentCustomer.getPolicyList().get(i) instanceof AutoPolicy) {
                            System.out.println(((AutoPolicy) currentCustomer.getPolicyList().get(i)).getPremium());
                        }
                    }
                }
                case 2 -> {
                    for (int i = 0; i < currentCustomer.getPolicyList().toArray().length; i++) {
                        if (currentCustomer.getPolicyList().get(i) instanceof HomePolicy) {
                            System.out.println(((HomePolicy) currentCustomer.getPolicyList().get(i)).getPremium());
                        } else {
                            System.out.println("You have no home policies.");
                        }
                    }
                }
                case 3 -> {
                    scanner.nextLine();
                    System.out.println("Please answer the following questions: ");
                    System.out.println();
                    System.out.println("In the last 5 years, how many accidents have you been in?");
                    String accidentCount = scanner.nextLine();

                    System.out.println("How hold is your vehicle?");
                    String vehicleAge = scanner.nextLine();

                    System.out.println("is this accurate? Y/N");
                    System.out.printf("You have been in %d accidents in the last 5 years, and your vehicle is %d years old.\n", Integer.parseInt(accidentCount), Integer.parseInt(vehicleAge));
                    String accurateInfoQuestion = scanner.nextLine();

                    if (accurateInfoQuestion.equalsIgnoreCase("y")) {
                        AutoPolicy autoPolicy = new AutoPolicy(customerService.getCustomerAge(currentCustomer), Integer.parseInt(accidentCount), Integer.parseInt(vehicleAge));
                        autoPolicyService.calculateAutoPolicy(autoPolicy);
                        customerService.addPolicy(currentCustomer, autoPolicy);
                        System.out.printf("Quote successful, your premium is: %.2f\n", autoPolicy.getPremium());
                    }
                }
                case 4 -> {
                    System.out.println("Not working yet");
                }
                case 7 -> {
                    System.out.println("Exiting application");
                    scanner.close();
                    System.exit(0);
                }
            }
        }
    }
}
