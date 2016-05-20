/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Erik
 */
public class PersonnelMain extends JPanel {
    
    private JButton addPersonnelAddButton;
    private JButton addPersonnelDeleteButton;
    JPanel contentPane;
    
    //Creates new form PlanningUI
    public PersonnelMain(){
        setLayout( new BorderLayout());        
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(3,2));
        
        //rij 1
        contentPane.add(new JLabel("Voeg nieuwe personeelsleden toe"));
        addPersonnelAddButton = new JButton("Voeg personeel toe");
        contentPane.add(addPersonnelAddButton);        
        
        contentPane.add(new JLabel(""));contentPane.add(new JLabel(""));
        
        //rij2
        contentPane.add(new JLabel("Verwijder personeelsleden uit het systeem"));
        addPersonnelDeleteButton = new JButton("Verwijder personeel");
        contentPane.add(addPersonnelDeleteButton);
        
        //Event handlers
        
        //add new frame for adding personnel
        addPersonnelAddButton.addActionListener ((ActionEvent e) -> {
            JFrame personnelFrame = new JFrame("Voeg personeel toe");
            personnelFrame.setSize(600,200);
            personnelFrame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
            personnelFrame.setContentPane(new PersonnelAddPanel());
            personnelFrame.setVisible(true);
            
            //center screen
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            personnelFrame.setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        });
        
        //add new frame for deleting personnel
        addPersonnelDeleteButton.addActionListener((ActionEvent e) ->{
            JFrame addPlanningFrame = new JFrame("Verwijder personeel");
            addPlanningFrame.setSize(600,200);
            addPlanningFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addPlanningFrame.setContentPane(new PersonnelDeletePanel());
            addPlanningFrame.setVisible(true); 
            
            //center screen
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            addPlanningFrame.setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        });
        
        
        //set the size of the window
        setSize(600,200);
        
        //center screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        
        
        add(contentPane, BorderLayout.CENTER);
        
    }
    
    
}
