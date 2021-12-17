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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojos.Tripdetails;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "SaveTripDetails", urlPatterns = {"/SaveTripDetails"})
public class SaveTripDetails extends HttpServlet {

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

        try {
            ObjectInputStream obin = new ObjectInputStream(request.getInputStream());
            HashMap hm = (HashMap) obin.readObject();
            obin.close();

            pojos.Tripdetails tripdetails = new Tripdetails();

            Criteria vehiCriteria = session.createCriteria(pojos.Vehicledetails.class);
            vehiCriteria.add(Restrictions.eq("vehicleregno", hm.get("vehicleno").toString()));

            List<pojos.Vehicledetails> vehicledetailses = vehiCriteria.list();

            pojos.Vehicledetails vehicledetails = null;
            if (!vehicledetailses.isEmpty()) {
                vehicledetails = vehicledetailses.get(0);
            }

            Criteria driverCriteria = session.createCriteria(pojos.Driver.class);
            driverCriteria.add(Restrictions.eq("nic", hm.get("driver").toString()));

            List<pojos.Driver> drivers = driverCriteria.list();

            pojos.Driver driver = null;
            if (!drivers.isEmpty()) {
                driver = drivers.get(0);
            }

            Criteria vehidetailsCriteria = session.createCriteria(pojos.DriverHasVehicledetails.class);
            vehidetailsCriteria.add(Restrictions.eq("driver", driver));
            vehidetailsCriteria.add(Restrictions.eq("vehicledetails", vehicledetails));

            List<pojos.DriverHasVehicledetails> driverHasVehicledetailses = vehidetailsCriteria.list();

            pojos.DriverHasVehicledetails driverHasVehicledetails = null;
            if (!driverHasVehicledetailses.isEmpty()) {
                driverHasVehicledetails = driverHasVehicledetailses.get(0);
            }
            
            tripdetails.setDriverHasVehicledetails(driverHasVehicledetails);
            tripdetails.setAvgspeed(Double.parseDouble(hm.get("avgspeed").toString()));
            tripdetails.setDate((Date) hm.get("date"));
            tripdetails.setEndtime((Date) hm.get("endtime"));
            tripdetails.setHiretime((Date) hm.get("hiretime"));
            tripdetails.setStarttime((Date) hm.get("starttime"));
            tripdetails.setWaitingtime((Date) hm.get("waitingtime"));
            tripdetails.setTotalkm(Double.parseDouble(hm.get("totkm").toString()));
            tripdetails.setTotalprice(Double.parseDouble(hm.get("totprice").toString()));
            tripdetails.setTotaltime(Double.parseDouble(hm.get("totaltime").toString()));
            
            session.save(tripdetails);
            
            Transaction t=session.beginTransaction();
            t.commit();
                 
            ObjectOutputStream obout = new ObjectOutputStream(response.getOutputStream());
            obout.writeObject("done");
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
