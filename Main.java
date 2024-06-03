package ems_final;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            String name;
            double salary;
            int id, age;
            EmployeeDaoInterface employeeDao = new EmployeeDaoImplement();
            DepartmentDaoInterface departmentDao = new DepartmentDaoImplement();

            System.out.println("Welcome to the Employee and Department Management Application");

            while (true) {
                System.out.println("\n1. Employee Operations\n" +
                                   "2. Department Operations\n" +
                                   "3. Exit");
                System.out.println("Enter your choice:");
                int choice = safeNextInt(sc);
                switch (choice) {
                    case 1:
                        employeeOperations(sc, employeeDao);
                        break;
                    case 2:
                        departmentOperations(sc, departmentDao);
                        break;
                    case 3:
                        System.out.println("Thank you for using the Employee and Department Management Application");
                        return;
                    default:
                        System.out.println("Invalid choice! Please enter a number between 1 and 3.");
                        break;
                }
            }
        } finally {
            sc.close();  // Ensures the scanner is closed on exiting the application
        }
    }

    private static void employeeOperations(Scanner sc, EmployeeDaoInterface employeeDao) {
        System.out.println("Employee Operations:");
        System.out.println("1. Add Employee\n" +
                           "2. Show All Employees\n" +
                           "3. Show Employee Based on ID\n" +
                           "4. Update Employee\n" +
                           "5. Delete Employee");
        System.out.println("Enter your choice:");
        int empChoice = safeNextInt(sc);
        switch (empChoice) {
            case 1:
                addEmployee(sc, employeeDao);
                break;
            case 2:
                employeeDao.showEmployee();
                break;
            case 3:
                System.out.println("Enter Employee ID:");
                int id = safeNextInt(sc);
                employeeDao.showEmployeeBasedOnID(id);
                break;
            case 4:
                updateEmployee(sc, employeeDao);
                break;
            case 5:
                System.out.println("Enter Employee ID to delete:");
                id = safeNextInt(sc);
                employeeDao.deleteEmployee(id);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    private static void departmentOperations(Scanner sc, DepartmentDaoInterface departmentDao) {
        System.out.println("Department Operations:");
        System.out.println("1. Add Department\n" +
                           "2. Show All Departments\n" +
                           "3. Show Department by ID\n" +
                           "4. Update Department\n" +
                           "5. Delete Department");
        System.out.println("Enter your choice:");
        int depChoice = safeNextInt(sc);
        // Implement department operations similar to employee operations
    }

    private static void addEmployee(Scanner sc, EmployeeDaoInterface employeeDao) {
        try {
            System.out.println("Enter Employee Name:");
            String name = sc.next();
            System.out.println("Enter Employee Salary:");
            double salary = safeNextDouble(sc);
            System.out.println("Enter Employee Age:");
            int age = safeNextInt(sc);
            Employee emp = new Employee(0, name, salary, age);
            employeeDao.createEmployee(emp);
        } catch (InputMismatchException e) {
            System.err.println("Input error. Please enter valid data types.");
            sc.nextLine();  // Consume the invalid input
        }
    }

    private static void updateEmployee(Scanner sc, EmployeeDaoInterface employeeDao) {
        try {
            System.out.println("Enter Employee ID to update:");
            int id = safeNextInt(sc);
            System.out.println("Enter New Employee Name:");
            String name = sc.next();
            employeeDao.updateEmployee(id, name);
        } catch (InputMismatchException e) {
            System.err.println("Input error. Please enter valid data types.");
            sc.nextLine();  // Consume the invalid input
        }
    }

    private static int safeNextInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            sc.next();  // Consume the wrong input
        }
        return sc.nextInt();
    }

    private static double safeNextDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid double.");
            sc.next();  // Consume the wrong input
        }
        return sc.nextDouble();
    }
}
