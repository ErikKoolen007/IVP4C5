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
public class PlanningUI extends JFrame {
    
    private JButton addPersonnelButton;
    private JButton addPlanningButton;
    
    //Creates new form PlanningUI
    public PlanningUI(){
        
        initComponents();
        setupFramePanel();
    }
    
    private void setupFramePanel(){
        
        setTitle("Hoofdscherm planning");
        
        JPanel contentPane = (JPanel)getContentPane();
        contentPane.setLayout(new GridLayout(3,2));
        
        //rij 1
        contentPane.add(new JLabel("Wijzig het personeelsbestand"));
        addPersonnelButton = new JButton("Personeel");
        contentPane.add(addPersonnelButton);
        
        //rij 2 ter opvulling
        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));
        
        //rij3
        contentPane.add(new JLabel("Toevoeging en wijziging planning per werknemer "));        
        addPlanningButton = new JButton("Maak werknemer planning");
        contentPane.add(addPlanningButton);
        
        //Event handlers
        
        //add new frame for adding personnel
        addPersonnelButton.addActionListener ((ActionEvent e) -> {
            JFrame personnelFrame = new JFrame("Personeel");
            personnelFrame.setSize(600,200);
            personnelFrame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
            personnelFrame.setContentPane(new PersonnelMain());
            personnelFrame.setVisible(true);
            
            //center screen
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            personnelFrame.setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        });
        
        //add new frame for adding and changing a schedule per employee
        addPlanningButton.addActionListener((ActionEvent e) ->{
            JFrame addPlanningFrame = new JFrame("Toevoeging en wijziging planning per werknemer");
            addPlanningFrame.setSize(800,400);
            addPlanningFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addPlanningFrame.setContentPane(new PlanningPanel());
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
    }
    
    
     private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }
}
