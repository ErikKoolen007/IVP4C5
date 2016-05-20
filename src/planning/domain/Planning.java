/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning.domain;

import java.util.ArrayList;

/**
 *
 * @author Erik
 */
public class Planning {
    
    private ArrayList dates = new ArrayList();
    private ArrayList daytimes = new ArrayList();
    
    public Planning(ArrayList dates, ArrayList daytimes){
        this.dates = dates;
        this.daytimes = daytimes;
    }
    
    public ArrayList getDates(){
        return dates;
    }
    
    public ArrayList getDaytimes(){
        return daytimes;
    }
    
    public void addDates(String date){
        dates.add(date);
    }
    
    public void addDaytimes(String daytime){
        daytimes.add(daytime);
    }
}
