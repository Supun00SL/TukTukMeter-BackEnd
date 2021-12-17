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
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import pojos.Bloodgroup;
import pojos.Driver;

/**
 *
 * @author Supun Madushanka
 */
public class mobileregistrationcontroller {

    public pojos.Bloodgroup SaveAndReturnBloodgroup(String Blood) {
        Session session = connection.sessionpoolmanager.getSessionFactory().openSession();
        pojos.Bloodgroup bloodgroup = null;
        try {
            Criteria bloodCriteria = session.createCriteria(pojos.Bloodgroup.class);
            bloodCriteria.add(Restrictions.eq("bloodgroup", Blood));

            List<pojos.Bloodgroup> bloodgroups = bloodCriteria.list();

            if (bloodgroups.isEmpty()) {
                //save
                bloodgroup = new Bloodgroup();
                bloodgroup.setBloodgroup(Blood);
                session.save(bloodgroup);
                Transaction t = session.beginTransaction();
                t.commit();
            } else {
                //jst return
                bloodgroup = bloodgroups.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return bloodgroup;
        }
    }

    public boolean SaveDriver(HashMap hashMap) {
        Session session = connection.sessionpoolmanager.getSessionFactory().openSession();
        boolean flag=false;
        try {
            Criteria driverCriteria = session.createCriteria(pojos.Driver.class);

            Criterion nicCriterion = Restrictions.eq("nic", hashMap.get("nic"));
            Criterion licenCriterion = Restrictions.eq("licenseno", hashMap.get("licen"));

            LogicalExpression orExpression = Restrictions.or(licenCriterion, nicCriterion);

            driverCriteria.add(orExpression);

            List<pojos.Driver> drivers = driverCriteria.list();

            if (drivers.isEmpty()) {
                //save
                pojos.Driver driver = new Driver();
                driver.setBloodgroup((pojos.Bloodgroup) session.load(pojos.Bloodgroup.class, SaveAndReturnBloodgroup(hashMap.get("blood").toString()).getIdbloodgroup()));
                driver.setContactno(hashMap.get("contact").toString());
                driver.setEmergencyno(hashMap.get("emer").toString());
                driver.setFname(hashMap.get("fname").toString());
                driver.setLicenseno(hashMap.get("licen").toString());
                driver.setLname(hashMap.get("lname").toString());
                driver.setNic(hashMap.get("nic").toString());
                
                session.save(driver);
                Transaction t = session.beginTransaction();
                t.commit();
                
                flag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return flag;
        }
    }
}
