package pojos;
// Generated Feb 23, 2018 12:49:42 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Bloodgroup generated by hbm2java
 */
public class Bloodgroup  implements java.io.Serializable {


     private Integer idbloodgroup;
     private String bloodgroup;
     private Set<Driver> drivers = new HashSet<Driver>(0);

    public Bloodgroup() {
    }

    public Bloodgroup(String bloodgroup, Set<Driver> drivers) {
       this.bloodgroup = bloodgroup;
       this.drivers = drivers;
    }
   
    public Integer getIdbloodgroup() {
        return this.idbloodgroup;
    }
    
    public void setIdbloodgroup(Integer idbloodgroup) {
        this.idbloodgroup = idbloodgroup;
    }
    public String getBloodgroup() {
        return this.bloodgroup;
    }
    
    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }
    public Set<Driver> getDrivers() {
        return this.drivers;
    }
    
    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }




}

