package main;

import model.Account;
import model.Customer;
import service.AccountService;
import service.CustomerService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CustomerService customerService =
                new CustomerService();

        AccountService accountService =
                new AccountService();

        while (true) {

            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Register Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("\n===== REGISTER ACCOUNT =====");

                    // Name Validation
                    String name;
                    while (true) {
                        System.out.print("Enter Name: ");
                        name = sc.nextLine();

                        if (name.matches("[a-zA-Z ]+")) {
                            break;
                        } else {
                            System.out.println("Invalid Name!");
                        }
                    }

                    // Phone Validation
                    String phone;
                    while (true) {
                        System.out.print("Enter Phone Number: ");
                        phone = sc.nextLine();

                        if (phone.matches("\\d{10}")) {
                            break;
                        } else {
                            System.out.println("Invalid Phone Number!");
                        }
                    }

                    // Email Validation
                    String email;
                    while (true) {
                        System.out.print("Enter Email: ");
                        email = sc.nextLine();

                        if (email.endsWith("@gmail.com")) {
                            break;
                        } else {
                            System.out.println("Invalid Email!");
                        }
                    }

                    // Aadhaar Validation
                    String aadhaar;
                    while (true) {
                        System.out.print("Enter Aadhaar Number: ");
                        aadhaar = sc.nextLine();

                        if (aadhaar.matches("\\d{12}")) {
                            break;
                        } else {
                            System.out.println("Invalid Aadhaar!");
                        }
                    }

                    // Account Type
                    System.out.print("Enter Account Type (Savings/Current): ");
                    String accountType = sc.nextLine();

                    // PIN Validation
                    String pin;
                    while (true) {
                        System.out.print("Create 4 Digit PIN: ");
                        pin = sc.nextLine();

                        if (pin.matches("\\d{4}")) {
                            break;
                        } else {
                            System.out.println("PIN must be 4 digits.");
                        }
                    }

                    Customer customer =
                            new Customer(name, phone, email, aadhaar);

                    int customerId =
                            customerService.registerCustomer(customer);

                    if (customerId != -1) {

                        Account account =
                                new Account(customerId,
                                        accountType,
                                        pin);

                        int accountNumber =
        accountService.createAccount(account);

System.out.println("Registration Completed Successfully");
System.out.println("Your Account Number: " + accountNumber);
System.out.println("Please Remember Your PIN");
                    }

                    break;

                case 2:

                    System.out.println("\n===== LOGIN =====");

                    System.out.print("Enter Account Number: ");
                    int accountNumber = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter PIN: ");
                    String loginPin = sc.nextLine();

                    boolean isLogin =
                            accountService.login(accountNumber, loginPin);

                    if (isLogin) {

    System.out.println("Login Successful");

    while (true) {

        System.out.println("\n===== BANK MENU =====");
        System.out.println("1. Deposit Money");
        System.out.println("2. Check Balance");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Mini Statement");
        System.out.println("5. Logout");
        System.out.print("Enter Choice: ");

        int bankChoice = sc.nextInt();
        sc.nextLine();

        switch (bankChoice) {

            case 1:

    double amount = 0;
    String amountInput;

    while (true) {

        System.out.print("Enter Amount: ");
        amountInput = sc.nextLine().trim();

        if (amountInput.matches("\\d+")) {

            amount = Double.parseDouble(amountInput);

            if (amount > 0 && amount <= 100000) {
                break;
            } else {
                System.out.println("Amount must be between 1 and 100000");
            }

        } else {
            System.out.println("Invalid Amount! Numbers only.");
        }
    }

    accountService.depositMoney(accountNumber, amount);

    break;

            case 2:

    double balance =
            accountService.checkBalance(accountNumber);

    System.out.println("Current Balance: Rs " + balance);

    break;

case 3:

    double withdrawAmount = 0;
    String withdrawInput;

    while (true) {

        System.out.print("Enter Withdraw Amount: ");
        withdrawInput = sc.nextLine().trim();

        if (withdrawInput.matches("\\d+")) {

            withdrawAmount =
                    Double.parseDouble(withdrawInput);

            if (withdrawAmount > 0 &&
                    withdrawAmount <= 100000) {
                break;
            } else {
                System.out.println(
                        "Amount must be between 1 and 100000");
            }

        } else {
            System.out.println(
                    "Invalid Amount! Numbers only.");
        }
    }

    accountService.withdrawMoney(
            accountNumber,
            withdrawAmount);

    break;

case 4:
    accountService.miniStatement(accountNumber);
    break;

case 5:
    System.out.println("Logged Out Successfully");
    break;

            default:
                System.out.println("Invalid Choice!");
        }

        if (bankChoice == 5) {
    break;
}
    }

} else {
    System.out.println("Invalid Account Number or PIN");
}

                    break;

                case 3:
                 
                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
