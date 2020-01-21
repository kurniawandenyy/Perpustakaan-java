<%-- 
    Document   : laporan
    Created on : Dec 12, 2018, 8:35:34 AM
    Author     : LABP2KOMP24
--%>

<%@page import="model.Buku"%>
<%@page import="model.Anggota"%>
<%@page import="model.Peminjaman"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="box">
    <div class="box-header">
        <h3 class="box-title">Laporan Peminjaman</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body no-padding">

        <table class="table table-condensed">
            <thead>
                <tr>
                    <th>Kode Anggota</th>
                    <th>Kode Buku</th>
                    <th>Tanggal Peminjaman</th>
                    <th>Tanggal Kembali</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Peminjaman> listpeminjaman
                            = (ArrayList<Peminjaman>) request.getAttribute("listPeminjaman");
                    for (Peminjaman peminjaman : listpeminjaman) {
                %>
                <tr>
                    <td><%=peminjaman.getKode_anggota()%></td>
                    <td><%=peminjaman.getKode_buku()%></td>
                    <td><%=peminjaman.getTgl_pinjam()%></td>
                    <td><%=peminjaman.getTgl_kembali()%></td>
                    <td><%=peminjaman.getStatus()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
    <!-- /.box-body -->
</div>
