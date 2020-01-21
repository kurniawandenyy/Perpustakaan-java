/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Koneksi;
import model.Pengembalian;

/**
 *
 * @author LABP2KOMP24
 */
public class PengembalianDao {

    Connection con;

    public PengembalianDao() {
        Koneksi k = new Koneksi();
        con = k.getKoneksi();
        if (con == null) {
            JOptionPane.showMessageDialog(null, "Koneksi GAGAL");
        }
    }

    public void insert(Pengembalian pengembalian) throws SQLException {
        String sql = "insert into pengembalian values(?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pengembalian.getKode_anggota());
        ps.setString(2, pengembalian.getKode_buku());
        ps.setString(3, pengembalian.getTgl_pinjam());
        ps.setString(4, pengembalian.getTgl_kembali());
        ps.setString(5, pengembalian.getTgl_dikembalikan());
        ps.setInt(6, pengembalian.getTerlambat());
        ps.setFloat(7, pengembalian.getDenda());
        ps.executeUpdate();
    }

    public void updatePeminjaman(Pengembalian pengembalian) throws SQLException {
        String sql = "update peminjaman set status=? where kode_anggota=? and kode_buku=? and tgl_pinjam=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, 1);
        ps.setString(2, pengembalian.getKode_anggota());
        ps.setString(3, pengembalian.getKode_buku());
        ps.setString(4, pengembalian.getTgl_pinjam());
        ps.executeUpdate();
    }

    public List<Pengembalian> getAllPengembalian() throws SQLException {
        String sql = "select * from pengembalian";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Pengembalian> list = new ArrayList<>();
        Pengembalian pengembalian = null;
        while (rs.next()) {
            pengembalian = new Pengembalian();
            pengembalian.setKode_anggota(rs.getString(1));
            pengembalian.setKode_buku(rs.getString(2));
            pengembalian.setTgl_pinjam(rs.getString(3));
            pengembalian.setTgl_kembali(rs.getString(4));
            pengembalian.setTgl_dikembalikan(rs.getString(5));
            pengembalian.setTerlambat(rs.getInt(6));
            pengembalian.setDenda(rs.getFloat(7));
            list.add(pengembalian);
        }
        return list;
    }
    public List<Pengembalian> getAllPengembalianAnggota(String kode_anggota) throws SQLException {
        String sql = "select * from pengembalian where kode_anggota=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode_anggota);
        ResultSet rs = ps.executeQuery();
        List<Pengembalian> list = new ArrayList<>();
        Pengembalian pengembalian = null;
        while (rs.next()) {
            pengembalian = new Pengembalian();
            pengembalian.setKode_anggota(rs.getString(1));
            pengembalian.setKode_buku(rs.getString(2));
            pengembalian.setTgl_pinjam(rs.getString(3));
            pengembalian.setTgl_kembali(rs.getString(4));
            pengembalian.setTgl_dikembalikan(rs.getString(5));
            pengembalian.setTerlambat(rs.getInt(6));
            pengembalian.setDenda(rs.getFloat(7));
            list.add(pengembalian);
        }
        return list;
    }

    public List<Pengembalian> getAllPengembalianBuku(String kode_buku) throws SQLException {
        String sql = "select * from pengembalian where kode_buku=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode_buku);
        ResultSet rs = ps.executeQuery();
        List<Pengembalian> list = new ArrayList<>();
        Pengembalian pengembalian = null;
        while (rs.next()) {
            pengembalian = new Pengembalian();
            pengembalian.setKode_anggota(rs.getString(1));
            pengembalian.setKode_buku(rs.getString(2));
            pengembalian.setTgl_pinjam(rs.getString(3));
            pengembalian.setTgl_kembali(rs.getString(4));
            pengembalian.setTgl_dikembalikan(rs.getString(5));
            pengembalian.setTerlambat(rs.getInt(6));
            pengembalian.setDenda(rs.getFloat(7));
            list.add(pengembalian);
        }
        return list;
    }

    public int getTerlambat(String tgl1, String tgl2) throws SQLException {
        String sql = "select datediff(?, ?) as hasil";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, tgl1);
        ps.setString(2, tgl2);
        ResultSet rs = ps.executeQuery();
        int terlambat = 0;
        if (rs.next()) {
            terlambat = rs.getInt(1);
        }
        if (terlambat < 0) {
            terlambat = 0;
        }
        return terlambat;
    }

    public ResultSet getPengembalianPerBulan(int bulan, int tahun) throws SQLException {
        String sql = "select p.kode_anggota,a.nama_anggota,b.judul,p.tgl_pinjam, p.tgl_kembali,p.tgl_dikembalikan,p.terlambat,p.denda \n"
                + "from pengembalian p,anggota a,buku b \n"
                + "where p.kode_anggota=a.kode_anggota \n"
                + "and p.kode_buku=b.kode_buku \n"
                + "and month(tgl_dikembalikan)=? \n"
                + "and year(tgl_dikembalikan)=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, bulan);
        ps.setInt(2, tahun);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

}
