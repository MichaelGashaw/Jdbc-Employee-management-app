package ems_final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDaoImplement implements DepartmentDaoInterface {
    Connection con;

    @Override
    public void createDepartment(Department department) {
        String query = "INSERT INTO department (name, location) VALUES (?, ?)";
        try {
            con = DBConnectionE.getConnection();
            PreparedStatement pstm = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, department.getName());
            pstm.setString(2, department.getLocation());
            int cnt = pstm.executeUpdate();
            if (cnt > 0) {
                ResultSet rs = pstm.getGeneratedKeys();
                if (rs.next()) {
                    department.setId(rs.getInt(1)); // Set the auto-generated ID back to the department object
                }
                System.out.println("Department Inserted Successfully !!!");
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

    @Override
    public void showDepartments() {
        try {
            con = DBConnectionE.getConnection();
            String query = "SELECT * FROM department";
            PreparedStatement pstm = con.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                                   ", Location: " + rs.getString("location"));
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

    @Override
    public void showDepartmentById(int id) {
        try {
            con = DBConnectionE.getConnection();
            String query = "SELECT * FROM department WHERE id = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                                   ", Location: " + rs.getString("location"));
            } else {
                System.out.println("No Department found with ID: " + id);
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

    @Override
    public void updateDepartment(int id, String name, String location) {
        try {
            con = DBConnectionE.getConnection();
            String query = "UPDATE department SET name = ?, location = ? WHERE id = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, location);
            pstm.setInt(3, id);
            int cnt = pstm.executeUpdate();
            if (cnt > 0) {
                System.out.println("Department Updated Successfully !!!");
            } else {
                System.out.println("No Department found with ID: " + id);
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
    

    @Override
    public void deleteDepartment(int id) {
        try {
            con = DBConnectionE.getConnection();
            String query = "DELETE FROM department WHERE id = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            int cnt = pstm.executeUpdate();
            if (cnt > 0) {
                System.out.println("Department Deleted Successfully !!!");
            } else {
                System.out.println("No Department found with ID: " + id);
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        
    }
            }
