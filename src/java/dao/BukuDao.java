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
import model.Buku;
import model.Koneksi;

/**
 *
 * @author LABP2KOMP24
 */
public class BukuDao {
    Connection con;
    
    public BukuDao(){
        Koneksi k=new Koneksi();
        con=k.getKoneksi();
        if(con==null){
            JOptionPane.showMessageDialog(null, "Koneksi Gagal");
        }
    }
    
    public void insert(Buku buku) throws SQLException{
        String sql="insert into buku values(?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,buku.getKode());
        ps.setString(2, buku.getJudul());
        ps.setString(3, buku.getPengarang());
        ps.setString(4, buku.getPenerbit());
        ps.setString(5, buku.getTahun());
        ps.executeUpdate();
    }
    
    public void update(Buku buku) throws SQLException{
        String sql="update buku set judul=?, pengarang=?, penerbit=?, tahun_terbit=? where kode_buku=?";
        PreparedStatement ps =con.prepareStatement(sql); 
        ps.setString(1, buku.getJudul());
        ps.setString(2, buku.getPengarang());
        ps.setString(3, buku.getPenerbit());
        ps.setString(4, buku.getTahun());
        ps.setString(5, buku.getKode());
        ps.executeUpdate();
    }
    
    public void delete(String kode) throws SQLException{
        String sql="delete from buku where kode_buku=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, kode);
        ps.executeUpdate();
    }
    
    public List<Buku> getAllBuku() throws SQLException{
        String sql="select * from buku";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Buku> list = new ArrayList<>();
        Buku buku= null;
        while(rs.next()){
            buku=new Buku();
            buku.setKode(rs.getString(1));
            buku.setJudul(rs.getString(2));
            buku.setPengarang(rs.getString(3));
            buku.setPenerbit(rs.getString(4));
            buku.setTahun(rs.getString(5));
            list.add(buku);
        }
        return list;
    }
    
    public List<Buku> getAllBukuTahunTerbit(String tahun_terbit) throws SQLException{
        String sql="select * from buku where tahun_terbit=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, tahun_terbit);
        ResultSet rs = ps.executeQuery();
        List<Buku> list = new ArrayList<>();
        Buku buku = null;
        while(rs.next()){
            buku = new Buku();
            buku.setKode(rs.getString(1));
            buku.setJudul(rs.getString(2));
            buku.setPengarang(rs.getString(3));
            buku.setPenerbit(rs.getString(4));
            buku.setTahun(rs.getString(5));
            list.add(buku);
        }
        return list;
    }
    
    public List<Buku> getAllBukuPengarang(String pengarang) throws SQLException{
        String sql="select * from buku where pengarang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pengarang);
        ResultSet rs = ps.executeQuery();
        List<Buku> list = new ArrayList<>();
        Buku buku = null;
        while(rs.next()){
            buku = new Buku();
            buku.setKode(rs.getString(1));
            buku.setJudul(rs.getString(2));
            buku.setPengarang(rs.getString(3));
            buku.setPenerbit(rs.getString(4));
            buku.setTahun(rs.getString(5));
            list.add(buku);
        }
        return list;
    }
    
    public Buku getBuku(String kode) throws SQLException{
        String sql="select * from buku where kode_buku=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode);
        ResultSet rs = ps.executeQuery();
        Buku buku=null;
        if(rs.next()){
            buku=new Buku();
            buku.setKode(rs.getString(1));
            buku.setJudul(rs.getString(2));
            buku.setPengarang(rs.getString(3));
            buku.setPenerbit(rs.getString(4));
            buku.setTahun(rs.getString(5));
        }
        return buku;
    }
    
}
