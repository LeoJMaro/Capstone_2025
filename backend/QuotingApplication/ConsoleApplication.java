package QuotingApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    public static void main(String[] args) {
        Customer currentCustomer = null;

        // Services
        AutoPolicyService autoPolicyService = new AutoPolicyService();
        HomePolicyService homePolicyService = new HomePolicyService();
        CustomerService customerService = new CustomerService();

        // Create mock customer
        Date mockDob = new Date(90, Calendar.DECEMBER, 21); // December 21, 1990
        customerService.registerCustomer(
                "John",
                "Doe",
                "john.doe@test.com",
                "123456789",
                "123 test road",
                mockDob,
                "Password1"
        );

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
                String input = scanner.nextLine();
                // Temp hidden debug option
                if (input.equals("99")) {
                    printAllCustomerDetails(customerService);
                    continue;
                }
                authChoice = Integer.parseInt(input);
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
                    } catch (ParseException e) {
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
            System.out.println("\n=== Insurance Quote System ===");
            System.out.println("1. Display auto policy quotes");
            System.out.println("2. Display home policy quotes");
            System.out.println("3. Get an auto quote");
            System.out.println("4. Get a home quote");
            System.out.println("5. View profile");
            System.out.println("6. Log out");
            System.out.println("7. Exit");
            System.out.print("Please select an option: ");

            int choice;
            try {
                String input = scanner.nextLine();
                // Hidden debug option
                if (input.equals("99")) {
                    printAllCustomerDetails(customerService);
                    continue;
                }
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    // Display auto policy quotes
                    boolean hasAutoPolicies = false;
                    System.out.println("\n=== Your Auto Policy Quotes ===");

                    for (int i = 0; i < currentCustomer.getPolicyList().size(); i++) {
                        if (currentCustomer.getPolicyList().get(i) instanceof AutoPolicy) {
                            hasAutoPolicies = true;
                            AutoPolicy policy = (AutoPolicy) currentCustomer.getPolicyList().get(i);
                            System.out.printf("Policy #%d: Premium $%.2f\n", i+1, policy.getPremium());
                        }
                    }

                    if (!hasAutoPolicies) {
                        System.out.println("You have no auto policies. Select option 3 to get a quote.");
                    }
                }
                case 2 -> {
                    // Display home policy quotes
                    boolean hasHomePolicies = false;
                    System.out.println("\n=== Your Home Policy Quotes ===");

                    for (int i = 0; i < currentCustomer.getPolicyList().size(); i++) {
                        if (currentCustomer.getPolicyList().get(i) instanceof HomePolicy) {
                            hasHomePolicies = true;
                            HomePolicy policy = (HomePolicy) currentCustomer.getPolicyList().get(i);
                            System.out.printf("Policy #%d: Premium $%.2f\n", i+1, policy.getPremium());
                        }
                    }

                    if (!hasHomePolicies) {
                        System.out.println("You have no home policies. Select option 4 to get a quote.");
                    }
                }
                case 3 -> {
                    // Get an auto quote
                    System.out.println("\n=== Get Auto Insurance Quote ===");

                    int accidentCount;
                    int vehicleAge;

                    try {
                        System.out.print("In the last 5 years, how many accidents have you been in? ");
                        accidentCount = Integer.parseInt(scanner.nextLine());

                        if (accidentCount < 0) {
                            System.out.println("Accident count cannot be negative.");
                            continue;
                        }

                        System.out.print("How old is your vehicle (in years)? ");
                        vehicleAge = Integer.parseInt(scanner.nextLine());

                        if (vehicleAge < 0) {
                            System.out.println("Vehicle age cannot be negative.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        continue;
                    }

                    System.out.printf("\nConfirm: You have been in %d accident(s) in the last 5 years, and your vehicle is %d year(s) old. Is this accurate? (Y/N) ",
                            accidentCount, vehicleAge);
                    String accurateInfoQuestion = scanner.nextLine();

                    if (accurateInfoQuestion.equalsIgnoreCase("y")) {
                        try {
                            AutoPolicy autoPolicy = new AutoPolicy(customerService.getCustomerAge(currentCustomer), accidentCount, vehicleAge);
                            autoPolicyService.calculateAutoPolicy(autoPolicy);
                            customerService.addPolicy(currentCustomer, autoPolicy);
                            System.out.printf("Quote successful, your premium is: $%.2f\n", autoPolicy.getPremium());
                        } catch (Exception e) {
                            System.out.println("Error calculating quote: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Quote cancelled. Please try again with correct information.");
                    }
                }
                case 4 -> {
                    System.out.println("Home quote functionality not implemented yet!");
                }
                case 5 -> {
                    System.out.println("\n=== Your Profile ===");
                    System.out.println("Name: " + currentCustomer.getFirstName() + " " + currentCustomer.getLastName());
                    System.out.println("Email: " + currentCustomer.getEmail());
                    System.out.println("Phone: " + currentCustomer.getPhone());
                    System.out.println("Address: " + currentCustomer.getAddress());

                    try {
                        int age = customerService.getCustomerAge(currentCustomer);
                        System.out.println("Age: " + age);
                    } catch (Exception e) {
                        System.out.println("Age: Error calculating age");
                    }

                    System.out.println("Total Policies: " + currentCustomer.getPolicyList().size());
                }
                case 6 -> {
                    System.out.println("Logging out...");
                    loggedIn = false;
                    currentCustomer = null;
                    // Return to login menu
                    return;
                }
                case 7 -> {
                    System.out.println("Thank you for using the Insurance Quote System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private static void printAllCustomerDetails(CustomerService customerService) {
        System.out.println("\n=== DEBUG: All Customer Records ===");
        List<Customer> customers = customerService.getCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers in the system.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.println("\nCustomer #" + (i + 1));
            System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Phone: " + customer.getPhone());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("Date of Birth: " + dateFormat.format(customer.getDateOfBirth()));

            try {
                int age = customerService.getCustomerAge(customer);
                System.out.println("Age: " + age);
            } catch (Exception e) {
                System.out.println("Age: Error calculating age - " + e.getMessage());
            }

            System.out.println("Password Hash: " + customer.getPasswordHash());

            System.out.println("Policies:");
            if (customer.getPolicyList().isEmpty()) {
                System.out.println("  None");
            } else {
                for (int j = 0; j < customer.getPolicyList().size(); j++) {
                    Object policy = customer.getPolicyList().get(j);
                    if (policy instanceof AutoPolicy) {
                        System.out.printf("  Auto Policy #%d: Premium $%.2f\n",
                                j+1, ((AutoPolicy) policy).getPremium());
                    } else if (policy instanceof HomePolicy) {
                        System.out.printf("  Home Policy #%d: Premium $%.2f\n",
                                j+1, ((HomePolicy) policy).getPremium());
                    } else {
                        System.out.println("  Unknown Policy Type #" + (j+1));
                    }
                }
            }
            System.out.println("---------------------------------");
        }
    }
}