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
import model.Peminjaman;

/**
 *
 * @author LABP2KOMP24
 */
public class PeminjamanDao {
    Connection con;
    
    public PeminjamanDao(){
        Koneksi k=new Koneksi();
        con=k.getKoneksi();
        if(con==null){
            JOptionPane.showMessageDialog(null, "Koneksi Gagal");
        }
    }
    
    public void insert(Peminjaman peminjaman) throws SQLException{
        String sql="insert into peminjaman values(?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,peminjaman.getKode_anggota());
        ps.setString(2, peminjaman.getKode_buku());
        ps.setString(3, peminjaman.getTgl_pinjam());
        ps.setString(4, peminjaman.getTgl_kembali());
        ps.setString(5, peminjaman.getStatus());
        ps.executeUpdate();
    }
    
    public void update(Peminjaman peminjaman) throws SQLException{
        String sql="update peminjaman set tgl_kembali=?, status=? where kode_anggota=? and kode_buku=? and tgl_pinjam=?";
        PreparedStatement ps =con.prepareStatement(sql); 
        ps.setString(1, peminjaman.getTgl_kembali());
        ps.setString(2, peminjaman.getStatus());
        ps.setString(3, peminjaman.getKode_anggota());
        ps.setString(4, peminjaman.getKode_buku());
        ps.setString(5, peminjaman.getTgl_pinjam());
        ps.executeUpdate();
    }
    
    public void delete(String kode_anggota,String kode_buku,String tgl_pinjam) throws SQLException{
        String sql="delete from peminjaman where kode_anggota=? and kode_buku=? and tgl_pinjam=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, kode_anggota);
        ps.setString(2, kode_buku);
        ps.setString(3, tgl_pinjam);
        ps.executeUpdate();
    }
    
    public List<Peminjaman> getAllPeminjaman() throws SQLException{
        String sql="select * from peminjaman";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Peminjaman> list = new ArrayList<>();
        Peminjaman peminjaman= null;
        while(rs.next()){
            peminjaman=new Peminjaman();
            peminjaman.setKode_anggota(rs.getString(1));
            peminjaman.setKode_buku(rs.getString(2));
            peminjaman.setTgl_pinjam(rs.getString(3));
            peminjaman.setTgl_kembali(rs.getString(4));
            peminjaman.setStatus(rs.getString(5));
            list.add(peminjaman);
        }
        return list;
    }
    
    public List<Peminjaman> getAllPeminjamanBulan(String bln, String thn) throws SQLException{
        String sql = "SELECT * FROM peminjaman WHERE MONTH(tgl_pinjam)=? AND YEAR(tgl_pinjam)=? ORDER BY tgl_pinjam ASC";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, bln);
        ps.setString(2, thn);
        ResultSet rs = ps.executeQuery();
        List<Peminjaman> list = new ArrayList<>();
        Peminjaman peminjaman = null;
        while(rs.next()){
            peminjaman = new Peminjaman();
            peminjaman.setKode_anggota(rs.getString(1));
            peminjaman.setKode_buku(rs.getString(2));
            peminjaman.setTgl_pinjam(rs.getString(3));
            peminjaman.setTgl_kembali(rs.getString(4));
            peminjaman.setStatus(rs.getString(5));
            list.add(peminjaman);
        }
        return list;
    }
    public List<Peminjaman> getAllPeminjamanAnggota(String kode_anggota) throws SQLException{
        String sql="select * from peminjaman where kode_anggota=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode_anggota);
        ResultSet rs = ps.executeQuery();
        List<Peminjaman> list = new ArrayList<>();
        Peminjaman peminjaman = null;
        while(rs.next()){
            peminjaman = new Peminjaman();
            peminjaman.setKode_anggota(rs.getString(1));
            peminjaman.setKode_buku(rs.getString(2));
            peminjaman.setTgl_pinjam(rs.getString(3));
            peminjaman.setTgl_kembali(rs.getString(4));
            peminjaman.setStatus(rs.getString(5));
            list.add(peminjaman);
        }
        return list;
    }
    
    public Peminjaman getPeminjaman(String kode_anggota,String kode_buku,String tgl_pinjam) throws SQLException{
        String sql="select * from peminjaman where kode_anggota=? and kode_buku=? and tgl_pinjam=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode_anggota);
        ps.setString(2, kode_buku);
        ps.setString(3, tgl_pinjam);
        ResultSet rs = ps.executeQuery();
        Peminjaman peminjaman=null;
        if(rs.next()){
            peminjaman=new Peminjaman();
            peminjaman.setKode_anggota(rs.getString(1));
            peminjaman.setKode_buku(rs.getString(2));
            peminjaman.setTgl_pinjam(rs.getString(3));
            peminjaman.setTgl_kembali(rs.getString(4));
            peminjaman.setStatus(rs.getString(5));
        }
        return peminjaman;
    }
}
