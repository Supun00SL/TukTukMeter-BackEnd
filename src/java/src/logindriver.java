/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Supun Madushanka
 */
public class logindriver {

    public HashMap login(HashMap hashMap) {
        Session session = connection.sessionpoolmanager.getSessionFactory().openSession();
        HashMap hm=new HashMap();
        try {
            Criteria loginCriteria=session.createCriteria(pojos.Driver.class);
            loginCriteria.add(Restrictions.eq("nic", hashMap.get("nic").toString()));
            loginCriteria.add(Restrictions.eq("password", hashMap.get("pass").toString()));
        
            List<pojos.Driver> drivers=loginCriteria.list();
            
            if(!drivers.isEmpty()){
               pojos.Driver driver=drivers.get(0);
               hm.put("stat", "true");
               hm.put("nic", driver.getNic());
               hm.put("name", driver.getFname()+" "+driver.getLname());
               hm.put("contact", driver.getContactno());
               hm.put("emergency", driver.getEmergencyno());
               hm.put("licean", driver.getLicenseno());
               hm.put("blood", driver.getBloodgroup().getBloodgroup());
               
            }else{
               hm.put("stat", "false");               
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
             session.close();
             return hm;
        }
    }
}
