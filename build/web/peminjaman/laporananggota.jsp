<%-- 
    Document   : laporanperanggota
    Created on : Dec 12, 2018, 8:35:34 AM
    Author     : LABP2KOMP24
--%>

<%@page import="model.Anggota"%>
<%@page import="model.Peminjaman"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="box">
    <div class="box-header">
        <h3 class="box-title">Laporan Peminjaman Per-anggota</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body no-padding">
        <form method="POST" action="<%= request.getContextPath()%>/PeminjamanServlet?action=laporananggota">
                <table border="0">
                    <tbody>
                        <tr></tr>
                        <tr>
                            <td>&nbsp;&nbsp;Nama Anggota</td>
                            <td>&nbsp;&nbsp;
                                <select name="kode_anggota">
                                    <%
                                        List<Anggota> anggotaList = (List<Anggota>) request.getAttribute("anggota");
                                        for (Anggota anggota : anggotaList) {
                                            out.print("<option value='"
                                                    + anggota.getKodeAnggota()+ "'>"
                                                    + anggota.getNamaAnggota()+ "</option>");
                                        }
                                    %>
                                    <option></option>
                                </select>
                            </td>
                        </tr>
                        <tr align="center">
                            <td></td>
                            <td><br><input class="button btn-default" type="submit" value="Preview" name="preview" /></td>
                        </tr>
                    </tbody>
                </table>
            </form>
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
