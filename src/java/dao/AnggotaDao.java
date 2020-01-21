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
import model.Anggota;

/**
 *
 * @author LABP2KOMP24
 */
public class AnggotaDao {
    Connection con;
    
    public AnggotaDao(){
        Koneksi k=new Koneksi();
        con=k.getKoneksi();
        if(con==null){
            JOptionPane.showMessageDialog(null, "Koneksi Gagal");
        }
    }
    public void insert(Anggota anggota) throws SQLException{
        String sql="insert into anggota values(?,?,?,?,?)";
        PreparedStatement ps =con.prepareStatement(sql);
        ps.setString(1, anggota.getKodeAnggota());
        ps.setString(2, anggota.getNamaAnggota());
        ps.setString(3, anggota.getAlamat());
        ps.setString(4, anggota.getJenisKelamin());
        ps.setString(5, anggota.getTglLahir());
        ps.executeUpdate();
    }
    
    public void update(Anggota anggota) throws SQLException{
        String sql="update anggota set NamaAnggota=?, Alamat=?, JenisKelamin=?, TglLahir=? where KodeAnggota=?";
        PreparedStatement ps =con.prepareStatement(sql); 
        ps.setString(1, anggota.getNamaAnggota());
        ps.setString(2, anggota.getAlamat());
        ps.setString(3, anggota.getJenisKelamin());
        ps.setString(4, anggota.getTglLahir());
        ps.setString(5, anggota.getKodeAnggota());
        ps.executeUpdate();
    }
    
    public void delete(String kode) throws SQLException{
        String sql="delete from anggota where KodeAnggota=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, kode);
        ps.executeUpdate();
    }
    
    public List<Anggota> getFilterJk(String jk) throws SQLException{
        String sql="select * from Anggota where JenisKelamin =?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, jk);
        ResultSet rs = ps.executeQuery();
        List<Anggota> list = new ArrayList<>();
        Anggota anggota= null;
        while(rs.next()){
            anggota=new Anggota();
            anggota.setKodeAnggota(rs.getString(1));
            anggota.setNamaAnggota(rs.getString(2));
            anggota.setAlamat(rs.getString(3));
            anggota.setJenisKelamin(rs.getString(4));
            anggota.setTglLahir(rs.getString(5));
            list.add(anggota);
        }
        return list;
    }
    
    public List<Anggota> getAllAnggota() throws SQLException{
        String sql="select * from Anggota";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Anggota> list = new ArrayList<>();
        Anggota anggota= null;
        while(rs.next()){
            anggota=new Anggota();
            anggota.setKodeAnggota(rs.getString(1));
            anggota.setNamaAnggota(rs.getString(2));
            anggota.setAlamat(rs.getString(3));
            anggota.setJenisKelamin(rs.getString(4));
            anggota.setTglLahir(rs.getString(5));
            list.add(anggota);
        }
        return list;
    }
    
    public Anggota getAnggota(String kode) throws SQLException{
        String sql="select * from anggota where KodeAnggota=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode);
        ResultSet rs = ps.executeQuery();
        Anggota anggota=null;
        if(rs.next()){
            anggota=new Anggota();
            anggota.setKodeAnggota(rs.getString(1));
            anggota.setNamaAnggota(rs.getString(2));
            anggota.setAlamat(rs.getString(3));
            anggota.setJenisKelamin(rs.getString(4));
            anggota.setTglLahir(rs.getString(5));
        }
        return anggota;
    }
}
