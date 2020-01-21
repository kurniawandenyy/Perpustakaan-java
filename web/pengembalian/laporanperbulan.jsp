<%-- 
    Document   : laporanperbulan
    Created on : Dec 19, 2018, 9:40:01 AM
    Author     : LABP2KOMP5
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Laporan Pengembalian Per Bulan</title>
    </head>
    <body>
        <div align="center">
            <h3>Laporan Pengembalian Per Bulan</h3>
            <h3>Bulan : <%=request.getAttribute("bulan")%>
                Tahun : <%=request.getAttribute("tahun")%>
            </h3>
            <table border="1" width="800">
                <thead>
                    <tr>
                        <th>Kode Anggota</th>
                        <th>Nama Anggota</th>
                        <th>Judul Buku</th>
                        <th>Tanggal Pinjam</th>
                        <th>Tanggal Kembali</th>
                        <th>Tanggal Dikembalikan</th>
                        <th>Terlambat</th>
                        <th>Denda</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ResultSet rs = (ResultSet) request.getAttribute("data");
                        while (rs.next()) {
                    %>
                    <tr>
                        <td><%=rs.getString(1)%></td>
                        <td><%=rs.getString(2)%></td>
                        <td><%=rs.getString(3)%></td>
                        <td><%=rs.getString(4)%></td>
                        <td><%=rs.getString(5)%></td>
                        <td><%=rs.getString(6)%></td>
                        <td><%=rs.getString(7)%></td>
                        <td><%=rs.getString(8)%></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
