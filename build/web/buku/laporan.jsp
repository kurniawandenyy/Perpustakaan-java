<%-- 
    Document   : laporan
    Created on : Dec 12, 2018, 8:35:34 AM
    Author     : LABP2KOMP24
--%>

<%@page import="model.Peminjaman"%>
<%@page import="model.Anggota"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Buku"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="box">
    <div class="box-header">
        <h3 class="box-title">Laporan Buku</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body no-padding">

        <table class="table table-condensed">
           <thead>
                    <tr>
                        <th>Kode Buku</th>
                        <th>Judul</th>
                        <th>Pengarang</th>
                        <th>Penerbit</th>
                        <th>Tahun terbit</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Buku> listbuku
                                = (ArrayList<Buku>) request.getAttribute("listbuku");
                        for (Buku buku : listbuku) {

                    %>
                    <tr>
                        <td><%=buku.getKode()%></td>
                        <td><%=buku.getJudul()%></td>
                        <td><%=buku.getPengarang()%></td>
                        <td><%=buku.getPenerbit()%></td>
                        <td><%=buku.getTahun()%></td>

                    </tr>
                    <% }%>
                </tbody>
        </table>
    </div>
    <!-- /.box-body -->
</div>
