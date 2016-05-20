/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning.datastorage;

import planning.domain.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Erik
 */
public class EmployeeDAO {
    
    public EmployeeDAO(){
        // Nothing to be initialized. This is a stateless class. Constructor
        // has been added to explicitely make this clear.
    }
    
    /**
     * 
     * @param employeeNr identifies the member to be loaded from the database
     * 
     * @return the Employee object to be found. In case employee could not be found,
        null is returned.
     */
    
    public Employee findEmployee(int employeeNr)
    {
        Employee employee = null;
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            // If a connection was successfully setup, execute the SELECT statement.
            ResultSet resultset = connection.executeSQLSelectStatement(
                "SELECT * FROM employee WHERE EmployeeNr = " + employeeNr + ";");

            if(resultset != null)
            {
                try
                {
                    // The gerechtNumber for a gerecht is unique, so in case the
                    // resultset does contain data, we need its first entry.
                    if(resultset.next())
                    {
                        int employeeNumberFromDb = resultset.getInt("EmployeeNr");
                        String fornameFromDb = resultset.getString("EmployeeName");
                        String surnameFromDb = resultset.getString("EmployeeLastname");
                        String accountTypeFromDb = resultset.getString("AccountType");

                        employee = new Employee(
                            employeeNumberFromDb,
                            fornameFromDb,
                            surnameFromDb,
                            accountTypeFromDb);
                    }
                }
                catch(SQLException e)
                {
                    System.out.println(e);
                    employee = null;
                }
            }
            // else an error occurred leave 'gerecht' to null.
            
            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.closeConnection();
        }
        
        return employee;
    }
    
    public boolean addEmployee(String EmployeeName, String EmployeeLastname, String EmployeeType)
    {
        
        boolean result = false;
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            // If a connection was successfully setup, execute the SELECT statement.
            
            result = connection.executeSQLInsertStatement(
            "INSERT INTO employee " + "VALUES (1,'" + EmployeeName + "','" + EmployeeLastname + "','" + EmployeeType + "')");
            
            

           
            }
            // else an error occurred leave 'member' to null.
            
            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.closeConnection();
      return result;
    }
    
    public Employee removeEmployee(int employeeNr)
    {
        boolean result = false;
        
        Employee employee = null;
        {
            // First open the database connection.
            DatabaseConnection connection = new DatabaseConnection();
            if(connection.openConnection())
            {
                // Execute the delete statement using the membership number to
                // identify the member row.
                result = connection.executeSQLDeleteStatement(
                    "DELETE FROM employee WHERE EmployeeNr = " + employeeNr + ";");
                
                // Finished with the connection, so close it.
                connection.closeConnection();
            }
            // else an error occurred leave 'member' to null.
        }
        
        return employee;
    }
    
    public boolean showEmployee()
    {        
        boolean result = false;
        
       
      return result;
    }
    
}
