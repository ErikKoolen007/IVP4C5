/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning.presentation;

import planning.businesslogic.PlanningManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import planning.domain.Employee;
/**
 *
 * @author Erik
 */
public class PersonnelDeletePanel extends JPanel {
    JPanel midPanel;
    private JTextField personnelNr, personnelName, personnelNameLast, personnelType;
    private JLabel personnelNrText, personnelNameText, personnelNameLastText, personnelTypeText;
    private JButton searchButton, deleteButton;
    
    private final PlanningManager manager;
    private Employee currentEmployee;
    
    public PersonnelDeletePanel(){    
        manager = new PlanningManager();
        currentEmployee = null;
        setLayout( new BorderLayout());
        Border border = BorderFactory.createEmptyBorder( 10, 10, 10, 10);
        setBorder( border );
        
        midPanel = new JPanel();
        midPanel.setLayout( new GridLayout( 5, 4, 10, 10 ));
        
        deleteButton = new JButton("verwijder");
        DeleteFunction bf1 = new DeleteFunction();
        deleteButton.addActionListener( bf1 );
        searchButton = new JButton("zoek");
        SearchFunction bf2 = new SearchFunction();
        searchButton.addActionListener( bf2 );
        
        personnelNrText = new JLabel("Personeelsnummer");
        personnelNameText = new JLabel("Voornaam");
        personnelNameLastText = new JLabel("Achternaam");
        personnelTypeText = new JLabel("Personeelstype");
        
        personnelNr = new JTextField("", 30);
        personnelName = new JTextField("", 30);
        personnelName.setEditable(false);
        personnelNameLast = new JTextField("", 30);
        personnelNameLast.setEditable(false);
        personnelType= new JTextField("", 30);
        personnelType.setEditable(false);
        
        
        midPanel.add(personnelNrText); midPanel.add( new JLabel( " " ));
        midPanel.add(personnelNr); midPanel.add(searchButton);
        
        midPanel.add(personnelNameText); midPanel.add( new JLabel( " " ));  
        midPanel.add(personnelName); midPanel.add( new JLabel( " " ));   
        
        midPanel.add(personnelNameLastText); midPanel.add( new JLabel( " " ));    
        midPanel.add(personnelNameLast); midPanel.add( new JLabel( " " ));
        
        midPanel.add(personnelTypeText); midPanel.add( new JLabel( " " ));
        midPanel.add(personnelType); midPanel.add( new JLabel( " " ));
        
        midPanel.add( new JLabel( " " )); midPanel.add( new JLabel( " " )); midPanel.add( new JLabel( " " )); midPanel.add(deleteButton);
        
        add(midPanel, BorderLayout.CENTER);
    }
    
    class DeleteFunction implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {   
                int employeeNr = Integer.parseInt(personnelNr.getText());
                manager.removeEmployee(employeeNr);
                personnelNr.setText("");
                personnelName.setText("");
                personnelNameLast.setText("");
                personnelType.setText("");
                JOptionPane.showMessageDialog(null,"Werknemer is verwijderd uit de database");
            }
        }    
    class SearchFunction implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                int employeeNr = Integer.parseInt(personnelNr.getText());
                doFindEmployee(employeeNr);               
            }
        }  
    
    private void doFindEmployee(int employeeNr) {
        
        currentEmployee = manager.findEmployee(employeeNr);
       
        if(currentEmployee == null){
            
            JOptionPane.showMessageDialog(null,"Werknemer is niet gevonden");
        }
        
        if(currentEmployee != null){
            
            //personnelNr.setText(currentEmployee.getEmployeeNumber());
            personnelName.setText(currentEmployee.getForname());
            personnelNameLast.setText(currentEmployee.getSurname());
            personnelType.setText(currentEmployee.getAccountType());
            
        }
        }
          
}
