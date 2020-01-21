/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author LABP2KOMP24
 */
public class Koneksi {
    private String url="jdbc:mysql://localhost/pj_1601091040";
    private String username ="root";
    private String password="";
    
    public Connection getKoneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        Koneksi k=new Koneksi();
        if(k.getKoneksi()!=null){
            JOptionPane.showMessageDialog(null,"Koneksi OK");
        }else{
            JOptionPane.showMessageDialog(null,"Koneksi Gagal");
        }
    }
}
