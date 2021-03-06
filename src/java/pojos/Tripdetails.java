package pojos;
// Generated Feb 23, 2018 12:49:42 PM by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * Tripdetails generated by hbm2java
 */
public class Tripdetails  implements java.io.Serializable {


     private Integer idtripdetails;
     private DriverHasVehicledetails driverHasVehicledetails;
     private Date starttime;
     private Date endtime;
     private Date date;
     private Double avgspeed;
     private Date hiretime;
     private Date waitingtime;
     private Double totalkm;
     private Double totalprice;
     private Double totaltime;

    public Tripdetails() {
    }

	
    public Tripdetails(DriverHasVehicledetails driverHasVehicledetails) {
        this.driverHasVehicledetails = driverHasVehicledetails;
    }
    public Tripdetails(DriverHasVehicledetails driverHasVehicledetails, Date starttime, Date endtime, Date date, Double avgspeed, Date hiretime, Date waitingtime, Double totalkm, Double totalprice, Double totaltime) {
       this.driverHasVehicledetails = driverHasVehicledetails;
       this.starttime = starttime;
       this.endtime = endtime;
       this.date = date;
       this.avgspeed = avgspeed;
       this.hiretime = hiretime;
       this.waitingtime = waitingtime;
       this.totalkm = totalkm;
       this.totalprice = totalprice;
       this.totaltime = totaltime;
    }
   
    public Integer getIdtripdetails() {
        return this.idtripdetails;
    }
    
    public void setIdtripdetails(Integer idtripdetails) {
        this.idtripdetails = idtripdetails;
    }
    public DriverHasVehicledetails getDriverHasVehicledetails() {
        return this.driverHasVehicledetails;
    }
    
    public void setDriverHasVehicledetails(DriverHasVehicledetails driverHasVehicledetails) {
        this.driverHasVehicledetails = driverHasVehicledetails;
    }
    public Date getStarttime() {
        return this.starttime;
    }
    
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
    public Date getEndtime() {
        return this.endtime;
    }
    
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public Double getAvgspeed() {
        return this.avgspeed;
    }
    
    public void setAvgspeed(Double avgspeed) {
        this.avgspeed = avgspeed;
    }
    public Date getHiretime() {
        return this.hiretime;
    }
    
    public void setHiretime(Date hiretime) {
        this.hiretime = hiretime;
    }
    public Date getWaitingtime() {
        return this.waitingtime;
    }
    
    public void setWaitingtime(Date waitingtime) {
        this.waitingtime = waitingtime;
    }
    public Double getTotalkm() {
        return this.totalkm;
    }
    
    public void setTotalkm(Double totalkm) {
        this.totalkm = totalkm;
    }
    public Double getTotalprice() {
        return this.totalprice;
    }
    
    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }
    public Double getTotaltime() {
        return this.totaltime;
    }
    
    public void setTotaltime(Double totaltime) {
        this.totaltime = totaltime;
    }




}


