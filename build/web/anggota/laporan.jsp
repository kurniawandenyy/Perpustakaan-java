<%-- 
    Document   : laporan
    Created on : Dec 12, 2018, 8:35:34 AM
    Author     : LABP2KOMP24
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Anggota"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="box">
    <div class="box-header">
        <h3 class="box-title">Laporan Anggota</h3><br><br>
        <form action="<%=request.getContextPath()%>/AnggotaServlet?action=filterjk" method="POST">
            <label> Jenis Kelamin </label> <select name="jk">
                <option value="L">Laki-laki</option>
                <option value="P">perempuan</option>
            </select>
            <input type="submit" value="Filter" name="filter" />
        </form>
    </div>
    <!-- /.box-header -->
    <div class="box-body no-padding">

        <table class="table table-condensed">
            <thead>
                <tr>
                    <th>Kode</th>
                    <th>Nama Anggota</th>
                    <th>Alamat</th>
                    <th>Jekel</th>
                    <th>Tgl Lahir</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Anggota> listAnggota
                            = (ArrayList<Anggota>) request.getAttribute("listanggota");
                    for (Anggota anggota : listAnggota) {

                %>
                <tr>
                    <td><%=anggota.getKodeAnggota()%></td>
                    <td><%=anggota.getNamaAnggota()%></td>
                    <td><%=anggota.getAlamat()%></td>
                    <td><%=anggota.getJenisKelamin()%></td>
                    <td><%=anggota.getTglLahir()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
    <!-- /.box-body -->
</div>