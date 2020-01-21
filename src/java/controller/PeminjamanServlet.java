/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AnggotaDao;
import dao.BukuDao;
import dao.PeminjamanDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
import model.Buku;
import model.Peminjaman;

/**
 *
 * @author LABP2KOMP24
 */
@WebServlet(name = "PeminjamanServlet", urlPatterns = {"/PeminjamanServlet"})
public class PeminjamanServlet extends HttpServlet {

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
            Peminjaman peminjaman = new Peminjaman();
            peminjaman.setKode_anggota(request.getParameter("kode_anggota"));
            peminjaman.setKode_buku(request.getParameter("kode_buku"));
            peminjaman.setTgl_pinjam(request.getParameter("tgl_pinjam"));
            peminjaman.setTgl_kembali(request.getParameter("tgl_kembali"));
            peminjaman.setStatus(request.getParameter("status"));
            try {
                insert(peminjaman);
                request.setAttribute("anggota", getAllAnggota());
                request.setAttribute("buku", getAllBuku());
                request.setAttribute("pesan", "Entri Data Ok");
                request.setAttribute("content", "/peminjaman/form.jsp");
                //forward = "/peminjaman/form.jsp";
            } catch (SQLException ex) {
                request.setAttribute("error", ex.getMessage());
                forward="error.jsp";
                Logger.getLogger(PeminjamanServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if(action.equalsIgnoreCase("ftambah")){
            request.setAttribute("anggota", getAllAnggota());
            request.setAttribute("buku", getAllBuku());
            request.setAttribute("content", "/peminjaman/form.jsp");
            //forward = "/peminjaman/form.jsp";
        } else if (action.equalsIgnoreCase("fedit")) {
            String kode_anggota = request.getParameter("kode_anggota");
            String kode_buku = request.getParameter("kode_buku");
            String tgl_pinjam = request.getParameter("tgl_pinjam");
            Peminjaman peminjaman;
            try {
                peminjaman = getPeminjaman(kode_anggota,kode_buku,tgl_pinjam);
                request.setAttribute("anggota", getAllAnggota());
                request.setAttribute("buku", getAllBuku());
                request.setAttribute("peminjaman", peminjaman);
            } catch (SQLException ex) {
                Logger.getLogger(PeminjamanServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("content", "/peminjaman/formedit.jsp");
            //forward = "/peminjaman/formedit.jsp";
            
        } else if (action.equalsIgnoreCase("edit")) {
            Peminjaman peminjaman = new Peminjaman(); //ambil data dari form
            peminjaman.setKode_anggota(request.getParameter("kode_anggota"));
            peminjaman.setKode_buku(request.getParameter("kode_buku"));
            peminjaman.setTgl_pinjam(request.getParameter("tgl_pinjam"));
            peminjaman.setTgl_kembali(request.getParameter("tgl_kembali"));
            peminjaman.setStatus(request.getParameter("status"));
            try {
                update(peminjaman);
                request.setAttribute("anggota", getAllAnggota());
                request.setAttribute("buku", getAllBuku());
                request.setAttribute("pesan", "Update Data Ok");
                request.setAttribute("peminjaman", peminjaman);
            } catch (SQLException ex) {
                request.setAttribute("pesan", "Error :" + ex.getMessage());
            }
            request.setAttribute("content", "/peminjaman/formedit.jsp");
            //forward = "/peminjaman/formedit.jsp";
        } else if (action.equalsIgnoreCase("delete")) {
            String kode_anggota = request.getParameter("kode_anggota");
            String kode_buku = request.getParameter("kode_buku");
            String tgl_pinjam = request.getParameter("tgl_pinjam");
            try {
                delete(kode_anggota,kode_buku,tgl_pinjam);
                List<Peminjaman> listPeminjaman = getAllPeminjaman();
                request.setAttribute("listPeminjaman", listPeminjaman);
            } catch (SQLException ex) {
                Logger.getLogger(PeminjamanServlet.class.getName()).log(Level.SEVERE, null, ex);
            }  
            request.setAttribute("content", "/peminjaman/list.jsp");
            //forward = "/peminjaman/list.jsp";
        }else if (action.equalsIgnoreCase("laporan")) {
            try {
                List<Peminjaman> listPeminjaman = getAllPeminjaman();
                request.setAttribute("anggota", getAllAnggota());
                request.setAttribute("buku", getAllBuku());
                request.setAttribute("listPeminjaman", listPeminjaman);
                request.setAttribute("content", "/peminjaman/laporan.jsp");
                //forward = "/peminjaman/laporan.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(PeminjamanServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("laporanbulan")) {
            try {
                String bln = request.getParameter("bln");
                String thn = request.getParameter("thn");
                List<Peminjaman> list = getAllPeminjamanBulan(bln,thn);
                request.setAttribute("anggota", getAllAnggota());
                request.setAttribute("buku", getAllBuku());
                request.setAttribute("listPeminjaman", list);
                request.setAttribute("content", "/peminjaman/laporanbulan.jsp");
                //forward = "/peminjaman/laporanbulan.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(PeminjamanServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (action.equalsIgnoreCase("laporananggota")) {
            String kode_anggota = request.getParameter("kode_anggota");
            try {
                List<Peminjaman> listPeminjaman = getAllPeminjamanAnggota(kode_anggota);
                request.setAttribute("listPeminjaman", listPeminjaman);
                request.setAttribute("anggota", getAllAnggota());
                request.setAttribute("content", "/peminjaman/laporananggota.jsp");
                //forward = "/peminjaman/laporananggota.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(AnggotaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { 
            try {
                List<Peminjaman> listPeminjaman=getAllPeminjaman();
                request.setAttribute("listPeminjaman", listPeminjaman);
            } catch (SQLException ex) {
                Logger.getLogger(PeminjamanServlet.class.getName()).log(Level.SEVERE, null, ex);
            }    
            request.setAttribute("content", "/peminjaman/list.jsp");
            //forward = "/peminjaman/list.jsp";
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

    void insert(Peminjaman peminjaman) throws SQLException{
        PeminjamanDao peminjamanDao=new PeminjamanDao();
        peminjamanDao.insert(peminjaman);
    }
    
    List<Peminjaman> getAllPeminjaman() throws SQLException{
        PeminjamanDao peminjamanDao =new PeminjamanDao();
        return peminjamanDao.getAllPeminjaman();
    }
    
    List<Anggota> getAllAnggota(){
       AnggotaDao anggotaDao =new AnggotaDao();    
        try {
            List<Anggota> list;
            list = anggotaDao.getAllAnggota();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
    }
    
    List<Buku> getAllBuku(){
        BukuDao bukuDao =new BukuDao();    
        try {
            List<Buku> list;
            list = bukuDao.getAllBuku();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
    }
    
    Peminjaman getPeminjaman(String kode_anggota,String kode_buku,String tgl_pinjam) throws SQLException{
        PeminjamanDao peminjamanDao = new PeminjamanDao();
        return peminjamanDao.getPeminjaman(kode_anggota,kode_buku,tgl_pinjam);
    }
    
    void update(Peminjaman peminjaman) throws SQLException{
        PeminjamanDao peminjamanDao = new PeminjamanDao();
        peminjamanDao.update(peminjaman);
    }
    
    List<Peminjaman> getAllPeminjamanBulan(String bln, String thn) throws SQLException {
        PeminjamanDao peminjamanDao = new PeminjamanDao();
        return peminjamanDao.getAllPeminjamanBulan(bln, thn);
    }
    
    List<Peminjaman> getAllPeminjamanAnggota(String kode_anggota) throws SQLException {
        PeminjamanDao peminjamanDao = new PeminjamanDao();
        return peminjamanDao.getAllPeminjamanAnggota(kode_anggota);
    }
    
    void delete(String kode_anggota,String kode_buku,String tgl_pinjam) throws SQLException{
        PeminjamanDao peminjamanDao = new PeminjamanDao();
        peminjamanDao.delete(kode_anggota,kode_buku,tgl_pinjam);
    }
}
