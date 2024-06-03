package ems_final;

public interface EmployeeDaoInterface {
	
	void createEmployee(Employee emp);
	
    void showEmployee();
    
    void showEmployeeBasedOnID(int id);
    
    void updateEmployee(int id, String name);
    
    void deleteEmployee(int id);

}
