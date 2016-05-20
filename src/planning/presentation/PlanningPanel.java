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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import planning.domain.Employee;
import planning.domain.Planning;
/**
 *
 * @author Erik
 */
public class PlanningPanel extends JPanel{
    
    private JTextField employeeNrField;
    private JTextField textFieldDate;
    private JTextField textFieldRowNr;
    
    private JButton searchEmployeeButton;
    private JButton addDayButton;
    private JButton confirmButton;
    private JButton deleteRowButton;
    
    private JTextArea employeeResultArea;
    
    //necessary things for table
    String[] columns = {"Rijnummer","Datum","Dagdeel"};
    DefaultTableModel model = new DefaultTableModel(columns,0);
    JTable table = new JTable(model);
    private final TableColumnModel tcm = table.getColumnModel();
    
    //dropdown menu
    String[] dayPart = {"ochtend","middag","avond"};
    JComboBox dayPartList = new JComboBox(dayPart);
        
    
    //The PlanningManager to delegate the real work to.
    private final PlanningManager manager;
    
    private Employee currentEmployee;
    private Planning planning;
    
    public PlanningPanel(){
    
        manager = new PlanningManager();
        currentEmployee = null;
        //borderlayout
        setLayout(new BorderLayout(5,5));
        
        //setup of west area
        JPanel employeeInfoPanel = createEmployeeInfoPanel();
        
        //setup of center area
        JPanel schedulePanel = createSchedulePanel();
        
        add(employeeInfoPanel, BorderLayout.WEST);
        add(schedulePanel, BorderLayout.CENTER);
        
        //add Action Listeners
        searchEmployeeButton.addActionListener ((ActionEvent e) -> {
            //check whether the field is empty
            //if no:
            if(!(employeeNrField.getText().isEmpty())){
                //go find the employee
                int employeeNr = Integer.parseInt(employeeNrField.getText());
                doFindEmployee(employeeNr);
            }
            //if yes:
            else{
                //let the user know he/she needs to fill in a number
                JOptionPane.showMessageDialog(null,"Vul een nummer in");
            }
        });
        
        addDayButton.addActionListener((ActionEvent e) ->{
            
            if(currentEmployee == null){
                JOptionPane.showMessageDialog(null,"Vul een Werknemersnummer in");
                //clear the (already) made entry
                textFieldDate.setText("");
                dayPartList.setSelectedIndex(0);
            }
            else{
                String addDate = textFieldDate.getText();  
                
                if(addDate.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")){  
                
                    int rijnummer = model.getRowCount();
                    String addDaytime = String.valueOf(dayPartList.getSelectedItem());
                    model.addRow(new Object[]{rijnummer,addDate,addDaytime});
            
                    //clear entry after input
                    textFieldDate.setText("");
                    dayPartList.setSelectedIndex(0);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Voer een datum in volgens: yyyy-mm-dd");
                    //clear the (already) made entry
                    textFieldDate.setText("");
                    dayPartList.setSelectedIndex(0);
                }
            }
        });
        
        
    }
    
    private JPanel createEmployeeInfoPanel(){
       
        JPanel employeeInfoPanel = new JPanel();
        employeeInfoPanel.setLayout(new BorderLayout(5,5));
       
        //setup of north area
        JPanel employeeNrPanel = new JPanel();
        employeeNrPanel.setLayout(new BoxLayout(employeeNrPanel, BoxLayout.X_AXIS));
      
        employeeNrPanel.add(new JLabel("Werknemer"));
      
        employeeNrField = new JTextField(10);
        employeeNrPanel.add(employeeNrField);
      
        searchEmployeeButton = new JButton("Zoek");
        employeeNrPanel.add(searchEmployeeButton);
      
        //setup of center area
        JPanel employeeResultPanel = new JPanel();
        employeeResultPanel.setLayout(new GridLayout());
      
        employeeResultArea =  new JTextArea();
        employeeResultPanel.add(employeeResultArea);
        employeeResultArea.setText("");
        employeeResultArea.setEditable(false);
      
        employeeInfoPanel.add(employeeNrPanel, BorderLayout.NORTH);
        employeeInfoPanel.add(employeeResultPanel, BorderLayout.CENTER);
       
        return employeeInfoPanel;
    }
    
    private JPanel createSchedulePanel(){
        
        JPanel schedulePanel = new JPanel();
        schedulePanel.setLayout(new BorderLayout(5,5));
        
        //setup of north area
        JPanel addDatePanel = new JPanel();
        addDatePanel.setLayout(new BoxLayout(addDatePanel, BoxLayout.X_AXIS));
        
        addDatePanel.add(new JLabel("Voer datum(yyyy-mm-dd) en dagdeel in"));
        
        textFieldDate = new JTextField(10);
        addDatePanel.add(textFieldDate);
        
        //dropdown menu
        dayPartList.setSelectedIndex(0);
        addDatePanel.add(dayPartList);
        
        addDayButton = new JButton("Voeg toe");
        addDatePanel.add(addDayButton);
        
        //setup of center area
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout());
        tablePanel.add(table);
        
        //add scrollpane
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane);
        
        //setup of south area
        JPanel deleteOrConfirmPanel = new JPanel();
        deleteOrConfirmPanel.setLayout(new BoxLayout(deleteOrConfirmPanel, BoxLayout.X_AXIS));
        
        deleteOrConfirmPanel.add(new JLabel("Verwijder rijnummer"));
        
        textFieldRowNr = new JTextField(10);
        deleteOrConfirmPanel.add(textFieldRowNr);
        
        deleteRowButton = new JButton("Verwijder");
        deleteOrConfirmPanel.add(deleteRowButton);
        
        confirmButton = new JButton("Bevestig");
        deleteOrConfirmPanel.add(confirmButton);
        
        schedulePanel.add(addDatePanel, BorderLayout.NORTH);
        schedulePanel.add(tablePanel, BorderLayout.CENTER);
        schedulePanel.add(deleteOrConfirmPanel, BorderLayout.SOUTH);
        
        
        return schedulePanel;
    }

    private void doFindEmployee(int employeeNr) {
        
        String employeeInfo = "";
        currentEmployee = manager.findEmployee(employeeNr);
        planning = manager.findEmployeePlanning(employeeNr);
       
        if(currentEmployee == null){
            
            JOptionPane.showMessageDialog(null,"Werknemer is niet gevonden");
        }
        
        if(currentEmployee != null){
            
            employeeInfo = "Personeelsnummer: " + currentEmployee.getEmployeeNumber() +
            "\n" + "\n" + 
            "Voornaam: " + currentEmployee.getForname() + 
            "\n" + "\n" +
            "Achternaam: " + currentEmployee.getSurname() +
            "\n" + "\n" +
            "Personeelstype: " + currentEmployee.getAccountType();
            
            while(model.getRowCount() >0){
                model.removeRow(0);
            }
            
            for(int i = 0; i != planning.getDates().size(); i++){
                
                int rijnummer = model.getRowCount();
                model.addRow(new Object[]{rijnummer,planning.getDates().get(i),planning.getDaytimes().get(i)});
            }
        }
        
        employeeResultArea.setText(employeeInfo);
    }
}
