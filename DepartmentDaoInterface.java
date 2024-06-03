package ems_final;

public interface DepartmentDaoInterface {
	
    void createDepartment(Department department);
    
    void showDepartments();
    
    void showDepartmentById(int id);
    
    void updateDepartment(int id, String name, String location);
    
    void deleteDepartment(int id);
}
