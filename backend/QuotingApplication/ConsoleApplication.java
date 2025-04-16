package QuotingApplication;

import QuotingApplication.factories.AutoPolicyFactory;
import QuotingApplication.factories.HomePolicyFactory;
import QuotingApplication.pojos.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ConsoleApplication {
    public static void main(String[] args) {
        LocalDate birthDate = LocalDate.of(2003, 1, 2);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 28);
        Date startDate = calendar.getTime();

        calendar.set(2026, Calendar.MARCH, 28);
        Date endDate = calendar.getTime();

        List<Customer> customers = new ArrayList<>();

        Customer customer = new Customer(7, "Joshua", "Crocker", "joshua01crocker@icloud.com", "7097702050", "28 Moffatt Rd, Mount Pearl, Newfoundland, A1N 0G3", birthDate, "now");
        customers.add(customer);
        Vehicle vehicle = new Vehicle("Mazda", "CX30", 2023, 0);
        Dwelling dwelling = new Dwelling("Home", "Wood", "Urban", 25, 500000);

        AutoPolicyFactory autoPolicyFactory = new AutoPolicyFactory();
        HomePolicyFactory homePolicyFactory = new HomePolicyFactory();

        AutoPolicy autoPolicy = (AutoPolicy) AutoPolicyFactory.createPolicy(1, customer.getId(), startDate, endDate, 750, 0, "AutoPolicy", "Active");
        HomePolicy homePolicy = (HomePolicy) HomePolicyFactory.createPolicy(2, customer.getId(), startDate, endDate, 500, 0, "HomePolicy", "Active", dwelling);

        autoPolicyFactory.calculateAutoPolicy(autoPolicy, 7, vehicle, customers);
        homePolicyFactory.calculateHomePolicy(homePolicy, customer, homePolicy.getDwelling());

        System.out.println(autoPolicy.getPremium());
        System.out.println(homePolicy.getPremium());


    }
}