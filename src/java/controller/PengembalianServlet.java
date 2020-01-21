/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AnggotaDao;
import dao.BukuDao;
import dao.PeminjamanDao;
import dao.PengembalianDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import model.Pengembalian;

/**
 *
 * @author LABP2KOMP24
 */
@WebServlet(name = "PengembalianServlet", urlPatterns = {"/PengembalianServlet"})
public class PengembalianServlet extends HttpServlet {

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
        if (action.equalsIgnoreCase("ftambah")) {
            String kode_anggota = request.getParameter("kode_anggota");
            String kode_buku = request.getParameter("kode_buku");
            String tgl_pinjam = request.getParameter("tgl_pinjam");
            try {
                Peminjaman pinjam = getPeminjaman(kode_anggota, kode_buku, tgl_pinjam);
                String tglsekarang = getTanggalSekarang();
                int terlambat = getTerlambat(tglsekarang, pinjam.getTgl_kembali());
                float denda = 100 * terlambat;
                request.setAttribute("pinjam", pinjam);
                request.setAttribute("tglsekarang", tglsekarang);
                request.setAttribute("terlambat", terlambat);
                request.setAttribute("denda", denda);
                request.setAttribute("content", "/pengembalian/form.jsp");
                //forward = "/pengembalian/form.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(PengembalianServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("tambah")) {
            Pengembalian pengembalian = new Pengembalian();
            pengembalian.setKode_anggota(request.getParameter("kode_anggota"));
            pengembalian.setKode_buku(request.getParameter("kode_buku"));
            pengembalian.setTgl_pinjam(request.getParameter("tgl_pinjam"));
            pengembalian.setTgl_kembali(request.getParameter("tgl_kembali"));
            pengembalian.setTgl_dikembalikan(request.getParameter("tgl_dikembalikan"));
            pengembalian.setTerlambat(Integer.parseInt(request.getParameter("terlambat")));
            pengembalian.setDenda(Float.parseFloat(request.getParameter("denda")));
            //pengembalian.setStatus(request.getParameter("status"));
            try {
                insert(pengembalian);
                updatePeminjaman(pengembalian);
                request.setAttribute("pesan", "Entri Data OK");
            } catch (Exception ex) {
                request.setAttribute("error", ex.getMessage());
                forward = "error.jsp";
                Logger.getLogger(PeminjamanServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("content", "/pengembalian/form.jsp");
            //forward = "/anggota/form.jsp";

        } else if (action.equalsIgnoreCase("listpinjam")) {
            try {
                List<Peminjaman> listPeminjaman = getAllPeminjaman();
                request.setAttribute("listPeminjaman", listPeminjaman);
            } catch (SQLException ex) {
                Logger.getLogger(PengembalianServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("content", "/pengembalian/listpinjam.jsp");
            //forward = "/pengembalian/listpinjam.jsp";
        } else if (action.equalsIgnoreCase("laporan")) {
            try {
                List<Pengembalian> listPengembalian = getAllPengembalian();
                request.setAttribute("anggota", getAllAnggota());
                request.setAttribute("buku", getAllBuku());
                request.setAttribute("listPengembalian", listPengembalian);
                request.setAttribute("content", "/pengembalian/laporan.jsp");
                //forward = "/pengembalian/laporan.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(PengembalianServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  else if (action.equalsIgnoreCase("laporanperbulan")) {
            int bulan = Integer.parseInt(request.getParameter("bulan"));
            int tahun = Integer.parseInt(request.getParameter("tahun"));
            try {
                ResultSet rs = getLaporanPerBulan(bulan, tahun);
                request.setAttribute("bulan", bulan);
                request.setAttribute("tahun", tahun);
                request.setAttribute("data", rs);
                forward = "/pengembalian/laporanperbulan.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(PengembalianServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (action.equalsIgnoreCase("laporananggota")) {
            String kode_anggota = request.getParameter("kode_anggota");
            try {
                List<Pengembalian> listPengembalian = getAllPengembalianAnggota(kode_anggota);
                request.setAttribute("listPengembalian", listPengembalian);
                request.setAttribute("anggota", getAllAnggota());
                forward = "/pengembalian/laporananggota.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(PengembalianServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("laporanbuku")) {
            String kode_buku = request.getParameter("kode_buku");
            try {
                List<Pengembalian> listPengembalian = getAllPengembalianBuku(kode_buku);
                request.setAttribute("listPengembalian", listPengembalian);
                request.setAttribute("buku", getAllBuku());
                forward = "/pengembalian/laporanbuku.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(PengembalianServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                List<Peminjaman> listPeminjaman = getAllPeminjaman();
                request.setAttribute("listPeminjaman", listPeminjaman);
            } catch (SQLException ex) {
                Logger.getLogger(PengembalianServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("content", "/pengembalian/listpinjam.jsp");
            //forward = "/pengembalian/listpinjam.jsp";
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

    List<Peminjaman> getAllPeminjaman() throws SQLException {
        PeminjamanDao peminjamanDao = new PeminjamanDao();
        return peminjamanDao.getAllPeminjaman();
    }

    List<Pengembalian> getAllPengembalian() throws SQLException {
        PengembalianDao pengembalianDao = new PengembalianDao();
        return pengembalianDao.getAllPengembalian();
    }

    List<Pengembalian> getAllPengembalianAnggota(String kode_anggota) throws SQLException {
        PengembalianDao pengembalianDao = new PengembalianDao();
        return pengembalianDao.getAllPengembalianAnggota(kode_anggota);
    }

    List<Pengembalian> getAllPengembalianBuku(String kode_buku) throws SQLException {
        PengembalianDao pengembalianDao = new PengembalianDao();
        return pengembalianDao.getAllPengembalianBuku(kode_buku);
    }

    void insert(Pengembalian pengembalian) throws SQLException {
        PengembalianDao pengembalianDao = new PengembalianDao();
        pengembalianDao.insert(pengembalian);
    }

    void updatePeminjaman(Pengembalian pengembalian) throws SQLException {
        PengembalianDao pengembalianDao = new PengembalianDao();
        pengembalianDao.updatePeminjaman(pengembalian);
    }

    List<Anggota> getAllAnggota() {
        try {
            AnggotaDao anggotaDao = new AnggotaDao();
            List<Anggota> list = anggotaDao.getAllAnggota();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    List<Buku> getAllBuku() {
        try {
            BukuDao bukuDao = new BukuDao();
            List<Buku> list = bukuDao.getAllBuku();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    Peminjaman getPeminjaman(String kode_anggota, String kode_buku, String tgl_pinjam) throws SQLException {
        PeminjamanDao peminjamanDao = new PeminjamanDao();
        return peminjamanDao.getPeminjaman(kode_anggota, kode_buku, tgl_pinjam);
    }

    String getTanggalSekarang() {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        return d.format(new Date());//date java util
    }

    int getTerlambat(String tgl1, String tgl2) throws SQLException {
        PengembalianDao pengembalianDao = new PengembalianDao();
        return pengembalianDao.getTerlambat(tgl1, tgl2);
    }

    ResultSet getLaporanPerBulan(int bulan, int tahun) throws SQLException {
        PengembalianDao pengembalianDao = new PengembalianDao();
        ResultSet rs = pengembalianDao.getPengembalianPerBulan(bulan, tahun);
        return rs;
    }
}
