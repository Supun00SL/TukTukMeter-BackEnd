package pojos;
// Generated Feb 23, 2018 12:49:42 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Vehicle generated by hbm2java
 */
public class Vehicle  implements java.io.Serializable {


     private Integer idvehicle;
     private String vehicle;
     private Set<Feetype> feetypes = new HashSet<Feetype>(0);
     private Set<Vehicledetails> vehicledetailses = new HashSet<Vehicledetails>(0);

    public Vehicle() {
    }

    public Vehicle(String vehicle, Set<Feetype> feetypes, Set<Vehicledetails> vehicledetailses) {
       this.vehicle = vehicle;
       this.feetypes = feetypes;
       this.vehicledetailses = vehicledetailses;
    }
   
    public Integer getIdvehicle() {
        return this.idvehicle;
    }
    
    public void setIdvehicle(Integer idvehicle) {
        this.idvehicle = idvehicle;
    }
    public String getVehicle() {
        return this.vehicle;
    }
    
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
    public Set<Feetype> getFeetypes() {
        return this.feetypes;
    }
    
    public void setFeetypes(Set<Feetype> feetypes) {
        this.feetypes = feetypes;
    }
    public Set<Vehicledetails> getVehicledetailses() {
        return this.vehicledetailses;
    }
    
    public void setVehicledetailses(Set<Vehicledetails> vehicledetailses) {
        this.vehicledetailses = vehicledetailses;
    }




}


