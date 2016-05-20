/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning.businesslogic;

import java.util.HashMap;
import planning.datastorage.EmployeeDAO;
import planning.datastorage.PlanningDAO;
import planning.domain.Employee;
import planning.domain.Planning;

/**
 *
 * @author Erik
 */
public class PlanningManager {
    
    private final HashMap <Integer, Employee> employees;
    
    public PlanningManager(){
        
        employees = new HashMap();
    }
    
    public Employee findEmployee(int employeeNr){
        
        Employee employee = employees.get(employeeNr);
        
        if(employee == null){
            //Employee may not have been loaded from the database yet. Try to do so
            EmployeeDAO employeeDAO = new EmployeeDAO();
            employee = employeeDAO.findEmployee(employeeNr);
            
        }
        
        return employee;
    }
    
    public Planning findEmployeePlanning(int employeeNr){
        
        Planning planning;
        PlanningDAO planningdao = new PlanningDAO();
        planning = planningdao.findEmployeePlanning(employeeNr);
        
        return planning;
    }
    
    public void addEmployee(String EmployeeName, String EmployeeLastname, String EmployeeType)
    {
    EmployeeDAO employeeDAO = new EmployeeDAO();
    employeeDAO.addEmployee(EmployeeName, EmployeeLastname, EmployeeType);
    }
    
    public void removeEmployee(int employeeNr)
    {
        boolean result = true;
            //Employee employee = employees.get(employeeNr);
            //int employeeNumber = employee.getEmployeeNumber();
                    
            // Let the member remove itself as a domain object. Do this before
            // removing from the database. In case something goes wrong, we
            // still have the data in the database. If first the member was
            // removed from the database, we might end up in an inconsistent
            // state.
            //result = employee.remove();
            
            if(result)
            {
                // Let the member remove itself from the database.
                EmployeeDAO employeeDAO = new EmployeeDAO();
                employeeDAO.removeEmployee(employeeNr);
                
                // In case something goes wrong here, we need to roll back.
                // But that's too much for this version of the POC.
            }
            
            // Finally, remove the member from the map in this manager.
            employees.remove(employeeNr);
        
        
        //return employee;
    }
}
