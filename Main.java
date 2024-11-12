import com.sece.ece.entities.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sece.ece.Functions.BakeryItemsFunctions.OrderStatus;
import static com.sece.ece.Functions.BakeryItemsFunctions.displayItems;
import static com.sece.ece.Functions.CreateAccount.createAccount;
import static com.sece.ece.Functions.CreateAccount.foundCustomer;
import static com.sece.ece.Functions.OrderFunctions.showLogHistories;

public class Main {
    private static String DATABASE_URL = "jdbc:mysql://localhost:3306/bakeryordermanagement";
    private static String USERNAME = "root";
    private static String PASSWORD = "karkey";
    private static List<Customer> customers = new ArrayList<Customer>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(DATABASE_URL , USERNAME , PASSWORD);
            while(true) {
                System.out.println("********************Welcome to Bakery Order Management System*********************");
                System.out.println("*****************************************************by Karthikeyan **************");
                System.out.println("1.Create new Account");
                System.out.println("2.Open Existing Account");
                System.out.println("0.Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("For Creating a new Account , it require some details");
                        System.out.print("Enter your name: ");
                        String customer_name = sc.nextLine();
                        System.out.print("Enter your address (eg:Coimbatore): ");
                        String customer_address = sc.nextLine();
                        System.out.print("Enter your phone number: ");
                        String customer_phoneNumber = sc.nextLine();
                        System.out.print("Enter your email: ");
                        String customer_email = sc.nextLine();
                        Customer customer = new Customer(getCustomerId(conn), customer_name, customer_email, customer_phoneNumber, customer_address);
                        customers.add(customer);
                        createAccount(conn, customers);
                        System.out.println("Your account has been created successfully");
                        System.out.println("The customer details" + customer.toString());
                        break;
                    case 2:
                        openexistingAccount(conn);
                        break;
                    case 0:
                        System.out.println("Thank you for using Bakery Order Management System");
                        return;

                }
            }


        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    private static void openexistingAccount(Connection conn) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Open Existing Account :");
        System.out.print("Enter your customer_id: ");
        int customer_id = sc.nextInt();

        Customer foundCustomer = foundCustomer(conn , customer_id);

        if (foundCustomer != null) {
            System.out.println("Welcome back to bakery application " + foundCustomer.getCustomer_Name());
            while (true) {
                System.out.println("1. Check available bakery items in our store");
                System.out.println("2. Check order status in our store");
                System.out.println("3. See Order Histories");
                System.out.println("4. Exit");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        displayItems(conn , customer_id);
                        break;
                    case 2:
                        System.out.print("Enter your order_id: ");
                        int order_id = sc.nextInt();
                        OrderStatus(conn , order_id);
                        break;
                    case 3:
                        System.out.println("Your order log history is :");
                        showLogHistories(conn , customer_id);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        System.out.println("Thank you for Visiting our store ! ! !");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("No such id found in our application ");
            System.out.println("Create a new account.");
        }
    }
    private static int getCustomerId(Connection conn) {
        int customer_id = 0;
        PreparedStatement preparedStatement = null;
        try {
            String query = "SELECT count(*) FROM customer";
            preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer_id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer_id + 1;
    }
    }














