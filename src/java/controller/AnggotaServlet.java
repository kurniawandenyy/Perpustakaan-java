/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AnggotaDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Anggota;

/**
 *
 * @author LABP2KOMP24
 */
@WebServlet(name = "AnggotaServlet", urlPatterns = {"/AnggotaServlet"})
public class AnggotaServlet extends HttpServlet {

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
        String forward = "index.jsp";
        String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");

        /* TODO output your page here. You may use following sample code. */
        if (action.equalsIgnoreCase("tambah")) {
            Anggota anggota = new Anggota(); //ambil data dari form
            anggota.setKodeAnggota(request.getParameter("KodeAnggota"));
            anggota.setNamaAnggota(request.getParameter("NamaAnggota"));
            anggota.setAlamat(request.getParameter("Alamat"));
            anggota.setJenisKelamin(request.getParameter("JenisKelamin"));
            anggota.setTglLahir(request.getParameter("TglLahir"));
            try {
                insert(anggota);
                request.setAttribute("pesan", "Entri Data Ok");
            } catch (Exception ex) {
                request.setAttribute("pesan", "Error :" + ex.getMessage());
            }
            request.setAttribute("content", "/anggota/form.jsp");
                //forward = "/anggota/form.jsp";

        } else if (action.equalsIgnoreCase("fedit")) {
            String KodeAnggota = request.getParameter("KodeAnggota");
            Anggota anggota;
            try {
                anggota = getAnggota(KodeAnggota);
                request.setAttribute("anggota", anggota);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("content", "/anggota/formedit.jsp");
            //forward = "/anggota/formedit.jsp";
        } else if (action.equalsIgnoreCase("edit")) {
            Anggota anggota = new Anggota(); //ambil data dari form
            anggota.setKodeAnggota(request.getParameter("KodeAnggota"));
            anggota.setNamaAnggota(request.getParameter("NamaAnggota"));
            anggota.setAlamat(request.getParameter("Alamat"));
            anggota.setJenisKelamin(request.getParameter("JenisKelamin"));
            anggota.setTglLahir(request.getParameter("TglLahir"));
            try {
                update(anggota);
                request.setAttribute("pesan", "Update Data Ok");
                request.setAttribute("anggota", anggota);
            } catch (SQLException ex) {
                request.setAttribute("pesan", "Error :" + ex.getMessage());
            }
            request.setAttribute("content", "/anggota/formedit.jsp");
            //forward = "/anggota/formedit.jsp";
        } else if (action.equalsIgnoreCase("delete")) {
            String KodeAnggota = request.getParameter("KodeAnggota");
            try {
                delete(KodeAnggota);
                List<Anggota> listAnggota = getAllAnggota();
                request.setAttribute("listAnggota", listAnggota);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("content", "/anggota/list.jsp");
            //forward="/anggota/list.jsp";
        } else if (action.equalsIgnoreCase("laporan")) {
            try {
                List<Anggota> list = getAllAnggota();
                request.setAttribute("listanggota", list);
                request.setAttribute("content", "/anggota/laporan.jsp");
                //forward="/anggota/laporan.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(action.equalsIgnoreCase("filterjk")){
            String jk=request.getParameter("jk");
            List<Anggota> list;
            try {
                list = getFilterJk(jk);
                request.setAttribute("listanggota", list);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("content", "/anggota/laporan.jsp");
            //forward="/anggota/laporan.jsp";
    }else {
            try {
                List<Anggota> listAnggota = getAllAnggota();
                request.setAttribute("listAnggota", listAnggota);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("content", "/anggota/list.jsp");
            //forward = "/anggota/list.jsp";  
        }
        RequestDispatcher rd = request.getRequestDispatcher(forward);
        rd.forward(request, response);

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

    void insert(Anggota anggota) throws SQLException {
        AnggotaDao anggotaDao = new AnggotaDao();
        anggotaDao.insert(anggota);
    }

    List<Anggota> getAllAnggota() throws SQLException {
        AnggotaDao anggotaDao = new AnggotaDao();
        return anggotaDao.getAllAnggota();
    }

    Anggota getAnggota(String kode) throws SQLException {
        AnggotaDao anggotaDao = new AnggotaDao();
        return anggotaDao.getAnggota(kode);
    }

    void update(Anggota anggota) throws SQLException {
        AnggotaDao anggotaDao = new AnggotaDao();
        anggotaDao.update(anggota);
    }

    void delete(String kode) throws SQLException {
        AnggotaDao anggotaDao = new AnggotaDao();
        anggotaDao.delete(kode);
    }

    List<Anggota> getFilterJk(String jk) throws SQLException {
        AnggotaDao anggotaDao = new AnggotaDao();
        return anggotaDao.getFilterJk(jk);
    }
        
}
