/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning.domain;

/**
 *
 * @author Erik
 */
public class Employee {
    
    private int employeeNumber;
    private String forname;
    private String surname;
    private String accountType;
    
    public Employee(int employeeNumber, String forname, String surname, String accountType){
        
        this.employeeNumber = employeeNumber;
        this.forname = forname;
        this.surname = surname;
        this.accountType = accountType;
    }
    
    public int getEmployeeNumber(){
        
        return employeeNumber;
    }
    
    public void setEmployeeNumber(int number){
        
        employeeNumber = number;
    }
    
    public String getForname(){
        
        return forname;
    }
    
    public void setForname(String forname){
        
        this.forname = forname;
    }
    
    public String getSurname(){
        
        return surname;
    }
    
    public void setSurname(String surname){
        
        this.surname = surname;
    }
    
    public String getAccountType(){
        
        return accountType;
    }
    
    public void setAccountType(String accountType){
        
        this.accountType = accountType;
    }
    
     @Override
    public boolean equals(Object o)
    {
        boolean equal = false;
        
        if(o == this)
        {
            // Dezelfde instantie van de klasse, dus per definitie hetzelfde.
            equal = true;
        }
        else
        {
            if(o instanceof Employee)
            {
                Employee l = (Employee)o;
                
                // Boek wordt geidentificeerd door ISBN, dus alleen hierop
                // controlleren is voldoend.
                equal = this.employeeNumber == l.employeeNumber;
            }
        }
        
        return equal;
    }
    
    @Override
    public int hashCode()
    {
        // Deze implementatie is gebaseerd op de best practice zoals beschreven
        // in Effective Java, 2nd edition, Joshua Bloch.
        
        // employeeNumber is uniek, dus voldoende als hashcode.
        return employeeNumber;
    }
}
