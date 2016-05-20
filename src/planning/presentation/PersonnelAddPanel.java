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
public class PersonnelAddPanel extends JPanel {
    JPanel midPanel;
    private JTextField personnelNr, personnelName, personnelNameLast, personnelType;
    private JLabel personnelNrText, personnelNameText, personnelNameLastText, personnelTypeText;
    private JButton addButton;
    
    private final PlanningManager manager;
    private Employee currentEmployee;
    
    public PersonnelAddPanel(){   
        manager = new PlanningManager();
        currentEmployee = null;
        setLayout( new BorderLayout());
        Border border = BorderFactory.createEmptyBorder( 10, 10, 10, 10);
        setBorder( border );
        
        midPanel = new JPanel();
        midPanel.setLayout( new GridLayout( 5, 4, 10, 10 ));
        
        addButton = new JButton("Bevestig");
        ButtonFunction1 bf1 = new ButtonFunction1();
        addButton.addActionListener( bf1 );
        
        personnelNrText = new JLabel("Personeelsnummer");
        personnelNameText = new JLabel("Voornaam");
        personnelNameLastText = new JLabel("Achternaam");
        personnelTypeText = new JLabel("Personeelstype");
        
        personnelNr = new JTextField("", 30);
        personnelNr.setEditable(false);
        personnelName = new JTextField("", 30);
        personnelNameLast = new JTextField("", 30);
        personnelType= new JTextField("", 30);
        
        
        midPanel.add(personnelNrText); midPanel.add( new JLabel( " " ));
        midPanel.add(personnelNr); midPanel.add( new JLabel( " " ));
        
        midPanel.add(personnelNameText); midPanel.add( new JLabel( " " ));  
        midPanel.add(personnelName); midPanel.add( new JLabel( " " ));   
        
        midPanel.add(personnelNameLastText); midPanel.add( new JLabel( " " ));    
        midPanel.add(personnelNameLast); midPanel.add( new JLabel( " " ));
        
        midPanel.add(personnelTypeText); midPanel.add( new JLabel( " " ));
        midPanel.add(personnelType); midPanel.add( new JLabel( " " ));
        
        midPanel.add( new JLabel( " " )); midPanel.add( new JLabel( " " )); midPanel.add( new JLabel( " " )); midPanel.add(addButton);
        
        add(midPanel, BorderLayout.CENTER);
        
    }
    
   
    class ButtonFunction1 implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {   
                if((personnelName.getText().isEmpty())){
                    JOptionPane.showMessageDialog(null,"Vul een voornaam in");
                }
                if((personnelNameLast.getText().isEmpty())){
                    JOptionPane.showMessageDialog(null,"Vul een achternaam in");
                }
                if((personnelType.getText().isEmpty())){
                    JOptionPane.showMessageDialog(null,"Vul 'keuken' of 'bediening' in");
                }
                //als ze vol zijn
                else{
                    manager.addEmployee(personnelName.getText(), personnelNameLast.getText(), personnelType.getText());                    
                    personnelNr.setText("");//miss doen date ie nog eens de hoogste opnnieuw zoekt en auto invult, miss dan +2 als de nieuwe werkenemer niet is verwerkt
                    personnelName.setText("");
                    personnelNameLast.setText("");
                    personnelType.setText("");
                    JOptionPane.showMessageDialog(null,"Werknemer is toegevoegd aan de database");
                    }                
            }
        }
    
    /*private void doAddEmployee(int employeeNr) {
        
        currentEmployee = manager.addEmployee(employeeNr);
       
        if(currentEmployee == null){
            
            JOptionPane.showMessageDialog(null,"Werknemer is niet gevonden");
        }
        
        if(currentEmployee != null){
            
            //personnelNr.setText(currentEmployee.getEmployeeNumber());
            personnelName.setText(currentEmployee.getForname());
            personnelNameLast.setText(currentEmployee.getSurname());
            personnelType.setText(currentEmployee.getAccountType());
            
        }
        } */
    
}
    

