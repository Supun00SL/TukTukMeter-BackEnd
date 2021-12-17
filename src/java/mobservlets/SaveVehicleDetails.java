/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobservlets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojos.DriverHasVehicledetails;
import pojos.Vehicledetails;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "SaveVehicleDetails", urlPatterns = {"/SaveVehicleDetails"})
public class SaveVehicleDetails extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Session session = connection.sessionpoolmanager.getSessionFactory().openSession();
        String Stat="";
        try {
            ObjectInputStream obin = new ObjectInputStream(request.getInputStream());
            HashMap hashMap = (HashMap) obin.readObject();
            obin.close();

            Criteria driverCriteria = session.createCriteria(pojos.Driver.class);
            driverCriteria.add(Restrictions.eq("nic", hashMap.get("driver").toString()));

            List<pojos.Driver> drivers = driverCriteria.list();
            pojos.Driver driver = null;
            if (!drivers.isEmpty()) {
                driver = drivers.get(0);
            }

            Criteria vehidetailsCriteria = session.createCriteria(pojos.Vehicledetails.class);
            vehidetailsCriteria.add(Restrictions.eq("vehicleregno", hashMap.get("vehino").toString()));

            List<pojos.Vehicledetails> vs = vehidetailsCriteria.list();

            if (vs.isEmpty()) {
                //save vehicle details
                pojos.Vehicledetails vehicledetails = new Vehicledetails();

                Criteria vehCriteria = session.createCriteria(pojos.Vehicle.class);
                vehCriteria.add(Restrictions.eq("vehicle", hashMap.get("vehi").toString()));

                if (!vehCriteria.list().isEmpty()) {
                    vehicledetails.setVehicle((pojos.Vehicle) vehCriteria.list().get(0));
                    vehicledetails.setVehicleregno(hashMap.get("vehino").toString());                    
                    session.save(vehicledetails);
                    
                    //assign vehicle to you here
                    
                    pojos.DriverHasVehicledetails dhv=new DriverHasVehicledetails();
                    dhv.setDriver(driver);
                    dhv.setVehicledetails(vehicledetails);
                    session.save(dhv);
                                        
                }
                Stat="Successfully Added !";

            } else {
                //vehicle Already Added ???
                pojos.Vehicledetails vehicledetails = vs.get(0);

                Criteria drivervehdCriteria = session.createCriteria(pojos.DriverHasVehicledetails.class);
                drivervehdCriteria.add(Restrictions.eq("vehicledetails", vehicledetails));
                drivervehdCriteria.add(Restrictions.eq("driver", driver));

                List<pojos.DriverHasVehicledetails> dhvs = drivervehdCriteria.list();

                if (dhvs.isEmpty()) {
                    //just assign vehicle to you here
                    pojos.DriverHasVehicledetails dhv=new DriverHasVehicledetails();
                    dhv.setDriver(driver);
                    dhv.setVehicledetails(vehicledetails);
                    session.save(dhv);
                    Stat="Successfully Added !";
                } else {
                    //already added this vehice to your Account
                    Stat="Already added this vehice to your Account !";
                }
            }
            Transaction t=session.beginTransaction();
            t.commit();

            ObjectOutputStream obout = new ObjectOutputStream(response.getOutputStream());
            obout.writeObject(Stat);
            obout.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
