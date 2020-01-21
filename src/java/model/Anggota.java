/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author LABP2KOMP24
 */
public class Anggota {
    private String KodeAnggota;
    private String NamaAnggota;
    private String Alamat;
    private String JenisKelamin;
    private String TglLahir;

    public String getKodeAnggota() {
        return KodeAnggota;
    }

    public void setKodeAnggota(String KodeAnggota) {
        this.KodeAnggota = KodeAnggota;
    }

    public String getNamaAnggota() {
        return NamaAnggota;
    }

    public void setNamaAnggota(String NamaAnggota) {
        this.NamaAnggota = NamaAnggota;
    }

    public String getJenisKelamin() {
        return JenisKelamin;
    }

    public void setJenisKelamin(String JenisKelamin) {
        this.JenisKelamin = JenisKelamin;
    }

    public String getTglLahir() {
        return TglLahir;
    }

    public void setTglLahir(String TglLahir) {
        this.TglLahir = TglLahir;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }
    
}
