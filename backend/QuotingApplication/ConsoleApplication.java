package QuotingApplication;

import java.text.SimpleDateFormat;
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

        boolean loggedIn = false;

        System.out.println("Welcome to the Taylor Insurance Quote System");

        while (!loggedIn) {
            System.out.println("\n1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");

            int authChoice;
            try {
                authChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (authChoice) {
                case 1 -> {
                    // Sign In
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();

                    currentCustomer = customerService.getCustomerByEmail(email);

                    if (currentCustomer != null && customerService.validateEmail(currentCustomer, email)
                            && customerService.validatePassword(currentCustomer, password)) {
                        System.out.println("You have successfully logged in!");
                        loggedIn = true;
                    } else {
                        System.out.println("Invalid email or password, please try again");
                    }
                }
                case 2 -> {
                    // Sign Up
                    System.out.println("\n=== Create New Account ===");
                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    // Check if email already exists
                    if (customerService.getCustomerByEmail(email) != null) {
                        System.out.println("An account with this email already exists.");
                        continue;
                    }

                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();

                    System.out.print("Address: ");
                    String address = scanner.nextLine();

                    System.out.print("Date of Birth (MM/DD/YYYY): ");
                    String dobString = scanner.nextLine();
                    Date dob;
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                        dateFormat.setLenient(false);
                        dob = dateFormat.parse(dobString);

                        // Validate the age (must be at least 18)
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.YEAR, -18);
                        Date minDate = calendar.getTime();

                        if (dob.after(minDate)) {
                            System.out.println("You must be at least 18 years old to register.");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use MM/DD/YYYY.");
                        continue;
                    }

                    System.out.print("Create Password: ");
                    String password = scanner.nextLine();

                    // Basic password validation
                    if (password.length() < 8) {
                        System.out.println("Password must be at least 8 characters long.");
                        continue;
                    }

//                     Register the new customer
                    currentCustomer = customerService.registerCustomer(firstName, lastName, email,
                            phone, address, dob, password);

                    if (currentCustomer != null) {
                        System.out.println("Account successfully created! You are now logged in.");
                        loggedIn = true;
                    } else {
                        System.out.println("Error creating account. Please try again.");
                    }
                }
                case 3 -> {
                    System.out.println("Thank you for using the Taylor Insurance Quote System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Please try again.");
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
