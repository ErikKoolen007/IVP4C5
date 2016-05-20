/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning.datastorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import planning.domain.Planning;
/**
 *
 * @author Erik
 */
public class PlanningDAO {
    
    private ArrayList dates = new ArrayList();
    private ArrayList daytimes = new ArrayList();
    
    public PlanningDAO(){}
    
    public Planning findEmployeePlanning(int employeeNr){       
        
        Planning planning = new Planning(dates,daytimes);
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            // If a connection was successfully setup, execute the SELECT statement.
            ResultSet resultset = connection.executeSQLSelectStatement(
                "SELECT Daytime,Date FROM schedule WHERE EmployeeNr = " + employeeNr + ";");

            if(resultset != null)
            {
                try
                {
                    // The gerechtNumber for a gerecht is unique, so in case the
                    // resultset does contain data, we need its first entry.
                    while(resultset.next())
                    {
                        
                        String dateFromDb = resultset.getString("Date");
                        String daytimeFromDb = resultset.getString("Daytime");
                        
                        dates.add(dateFromDb);
                        daytimes.add(daytimeFromDb);
                        
                        //System.out.println(planning.getDates());
                    }
                }
                catch(SQLException e)
                {
                    System.out.println(e);
                    
                }
            }
            // else an error occurred leave 'gerecht' to null.
            
            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.closeConnection();
        }
        return planning;
    }
}
