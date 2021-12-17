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
import org.hibernate.criterion.Restrictions;
import pojos.DriverHasVehicledetails;

/**
 *
 * @author Supun Madushanka
 */
@WebServlet(name = "getvehicledetailsformme", urlPatterns = {"/getvehicledetailsformme"})
public class getvehicledetailsformme extends HttpServlet {

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
            String drivernic = obin.readObject().toString();
            obin.close();

            Criteria driverCriteria = session.createCriteria(pojos.Driver.class);
            driverCriteria.add(Restrictions.eq("nic", drivernic));

            List<pojos.Driver> drivers = driverCriteria.list();
            Set dataset = new HashSet();
            if (!drivers.isEmpty()) {
                pojos.Driver driver = drivers.get(0);
                Criteria drivervehiclesdetailsCriteria = session.createCriteria(pojos.DriverHasVehicledetails.class);
                drivervehiclesdetailsCriteria.add(Restrictions.eq("driver", driver));

                List<pojos.DriverHasVehicledetails> dhvs = drivervehiclesdetailsCriteria.list();

                if (!dhvs.isEmpty()) {
                    for (DriverHasVehicledetails driverHasVehicledetails : dhvs) {
                        dataset.add(driverHasVehicledetails.getVehicledetails().getVehicleregno()+" ("+driverHasVehicledetails.getVehicledetails().getVehicle().getVehicle()+")");
                          
                    }
                }
            }

            ObjectOutputStream obout = new ObjectOutputStream(response.getOutputStream());
            obout.writeObject(dataset);
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
