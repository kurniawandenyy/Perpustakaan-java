/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BukuDao;
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
import model.Buku;
import org.omg.CORBA.AnySeqHelper;

/**
 *
 * @author LABP2KOMP24
 */
@WebServlet(name = "BukuServlet", urlPatterns = {"/BukuServlet"})
public class BukuServlet extends HttpServlet {

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
        String forward="index.jsp";
        String action=(request.getParameter("action")==null)?"":request.getParameter("action");
        
        if (action.equalsIgnoreCase("tambah")) {
            Buku buku = new Buku();
            buku.setKode(request.getParameter("kode_buku"));
            buku.setJudul(request.getParameter("judul"));
            buku.setPengarang(request.getParameter("pengarang"));
            buku.setPenerbit(request.getParameter("penerbit"));
            buku.setTahun(request.getParameter("tahun_terbit"));
            try {
                insert(buku);
                request.setAttribute("pesan", "Entri Data Ok");
            } catch (SQLException ex) {
                Logger.getLogger(BukuServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("content", "/buku/form.jsp");
            //forward = "/buku/form.jsp";
        } else if (action.equalsIgnoreCase("fedit")) {
            String kode_buku = request.getParameter("kode_buku");
            Buku buku;
            try {
                buku = getBuku(kode_buku);
                request.setAttribute("buku", buku);
            } catch (SQLException ex) {
                Logger.getLogger(BukuServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("content", "/buku/formedit.jsp");
            //forward = "/buku/formedit.jsp";
        } else if (action.equalsIgnoreCase("edit")) {
            Buku buku = new Buku(); //ambil data dari form
            buku.setKode(request.getParameter("kode_buku"));
            buku.setJudul(request.getParameter("judul"));
            buku.setPengarang(request.getParameter("pengarang"));
            buku.setPenerbit(request.getParameter("penerbit"));
            buku.setTahun(request.getParameter("tahun_terbit"));
            try {
                update(buku);
                request.setAttribute("pesan", "Update Data Ok");
                request.setAttribute("buku", buku);
            } catch (SQLException ex) {
                request.setAttribute("pesan", "Error :" + ex.getMessage());
            }
            request.setAttribute("content", "/buku/formedit.jsp");
            //forward = "/buku/formedit.jsp";
        } else if (action.equalsIgnoreCase("delete")) {
            String kode_buku = request.getParameter("kode_buku");
            try {
                delete(kode_buku);
                List<Buku> listBuku = getAllBuku();
                request.setAttribute("listBuku", listBuku);
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("content", "/buku/list.jsp");
            //forward = "/buku/list.jsp";
        }else if (action.equalsIgnoreCase("laporan")) {
            try {
                List<Buku> list = getAllBuku();
                request.setAttribute("listbuku", list);
                request.setAttribute("buku", getAllBuku());
                request.setAttribute("bukupengarang", getAllBuku());
                request.setAttribute("content", "/buku/laporan.jsp");
                //forward = "/buku/laporan.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("laporanperthn")) {
            String tahun_terbit = request.getParameter("tahun_terbit");
            try {
                List<Buku> list = getAllBukuTahunTerbit(tahun_terbit);
                request.setAttribute("listbuku", list);
                request.setAttribute("buku", getAllBuku());
                request.setAttribute("content", "/buku/laporanperthn.jsp");
                //forward = "/buku/laporanperthn.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(BukuServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("laporanperpengarang")) {
            String pengarang=request.getParameter("pengarang");
            try {
                List<Buku> list = getAllBukuPengarang(pengarang);
                request.setAttribute("listbuku", list);
                request.setAttribute("bukupengarang", getAllBuku());
                request.setAttribute("content", "/buku/laporanperpengarang.jsp");
                //forward = "/buku/laporanperpengarang.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            
            try {
                List<Buku> listBuku=getAllBuku();
                request.setAttribute("listBuku", listBuku);
            } catch (SQLException ex) {
                Logger.getLogger(BukuServlet.class.getName()).log(Level.SEVERE, null, ex);
            }      
            request.setAttribute("content", "/buku/list.jsp");
            //forward = "/buku/list.jsp";
        }
        RequestDispatcher rd=request.getRequestDispatcher(forward);
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

    void insert (Buku buku) throws SQLException{
        BukuDao bukuDao=new BukuDao();
        bukuDao.insert(buku);
    }
    
    List<Buku> getAllBuku() throws SQLException{
        BukuDao bukuDao =new BukuDao();
        return bukuDao.getAllBuku();
    }
    
    Buku getBuku(String kode) throws SQLException{
        BukuDao bukuDao = new BukuDao();
        return bukuDao.getBuku(kode);
    }
    
    void update(Buku buku) throws SQLException{
        BukuDao bukuDao = new BukuDao();
        bukuDao.update(buku);
    }
    
    void delete(String kode) throws SQLException{
        BukuDao bukuDao = new BukuDao();
        bukuDao.delete(kode);
    }
    List<Buku> getAllBukuTahunTerbit(String tahun_terbit) throws SQLException {
        BukuDao bukuDao = new BukuDao();
        return bukuDao.getAllBukuTahunTerbit(tahun_terbit);
    }

    List<Buku> getAllBukuPengarang(String pengarang) throws SQLException {
        BukuDao bukuDao = new BukuDao();
        return bukuDao.getAllBukuPengarang(pengarang);
    }
}
