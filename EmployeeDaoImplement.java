package ems_final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDaoImplement implements EmployeeDaoInterface {
    Connection con;

    @Override
    public void createEmployee(Employee emp) {
        String query = "INSERT INTO employees (name, salary, age) VALUES (?, ?, ?)";
        try {
            con = DBConnectionE.getConnection();
            PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, emp.getName());
            pstm.setDouble(2, emp.getSalary());
            pstm.setInt(3, emp.getAge());
            int cnt = pstm.executeUpdate();
            if (cnt > 0) {
                ResultSet rs = pstm.getGeneratedKeys();
                if (rs.next()) {
                    emp.setId(rs.getInt(1)); // Set the auto-generated ID back to the employee object
                }
                System.out.println("Employee Inserted Successfully !!!");
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }

    // Other methods follow a similar pattern...



    @Override
    public void showEmployee() {
        try {
			con = DBConnectionE.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        String query = "SELECT * FROM employees";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Employee Details:");
            System.out.format("%s\t%s\t%s\t%s\t%s\n", "ID", "Name", "Salary", "Age", "Department ID");
            System.out.println("---------------------------------------------------------------");
            while (rs.next()) {
                System.out.format("%d\t%s\t%.2f\t%d\t%d\n",
                                  rs.getInt("id"),
                                  rs.getString("name"),
                                  rs.getDouble("salary"),
                                  rs.getInt("age"),
                                  rs.getInt("department_id"));
                System.out.println("---------------------------------------------------------------");
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void showEmployeeBasedOnID(int id) {
        try {
			con = DBConnectionE.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String query = "SELECT * FROM employees WHERE id = ?";
        try (PreparedStatement pstm = con.prepareStatement(query)) {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                System.out.format("ID: %d, Name: %s, Salary: %.2f, Age: %d, Department ID: %d\n",
                                  rs.getInt("id"),
                                  rs.getString("name"),
                                  rs.getDouble("salary"),
                                  rs.getInt("age"),
                                  rs.getInt("department_id"));
            } else {
                System.out.println("No Employee found with ID: " + id);
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(int id, String name) {
        try {
			con = DBConnectionE.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String query = "UPDATE employees SET name = ? WHERE id = ?";
        try (PreparedStatement pstm = con.prepareStatement(query)) {
            pstm.setString(1, name);
            pstm.setInt(2, id);
            int cnt = pstm.executeUpdate();
            if (cnt != 0) {
                System.out.println("Employee Details updated Successfully !!!");
            } else {
                System.out.println("No Employee found with ID: " + id);
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try {
			con = DBConnectionE.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String query = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement pstm = con.prepareStatement(query)) {
            pstm.setInt(1, id);
            int cnt = pstm.executeUpdate();
            if (cnt != 0) {
                System.out.println("Employee Deleted Successfully !!!");
            } else {
                System.out.println("No Employee found with ID: " + id);
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
