<%-- 
    Document   : laporanbuku
    Created on : Dec 18, 2018, 8:28:22 PM
    Author     : User
--%>

<%@page import="model.Pengembalian"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Laporan Pengembalian Per Buku</title>
    </head>
    <body>
        <div align="center">
            <h1>Laporan Pengembalian Per Buku</h1>
                <table border="1" width="800">
                    <thead>
                        <tr>
                            <th>Kode Anggota</th>
                            <th>Kode Buku</th>
                            <th>Tanggal Peminjaman</th>
                            <th>Tanggal Kembali</th>
                            <th>Tanggal Dikembalikan</th>
                            <th>Terlambat</th>
                            <th>Denda</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Pengembalian> listpengembalian
                                    = (ArrayList<Pengembalian>) request.getAttribute("listPengembalian");
                            for (Pengembalian pengembalian : listpengembalian) {
                        %>
                        <tr>
                            <td><%=pengembalian.getKode_anggota()%></td>
                            <td><%=pengembalian.getKode_buku()%></td>
                            <td><%=pengembalian.getTgl_pinjam()%></td>
                            <td><%=pengembalian.getTgl_kembali()%></td>
                            <td><%=pengembalian.getTgl_dikembalikan() %></td>
                            <td><%=pengembalian.getTerlambat() %></td>
                            <td><%=pengembalian.getDenda() %></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
        </div>
    </body>
</html>
