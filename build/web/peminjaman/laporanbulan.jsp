<%-- 
    Document   : laporanbulan
    Created on : Dec 12, 2018, 8:35:34 AM
    Author     : LABP2KOMP24
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Peminjaman"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="box">
    <div class="box-header">
        <h3 class="box-title">Laporan Peminjaman Per-bulan</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body no-padding">
        <form method="POST" action="<%= request.getContextPath()%>/PeminjamanServlet?action=laporanbulan">
            <table border="0">
                <tbody>
                    <tr>
                        <td><label>Bulan</label>
                            <select name="bln">
                                <option value="01">Januari</option>
                                <option value="02">Februari</option>
                                <option value="03">Maret</option>
                                <option value="04">April</option>
                                <option value="05">Mei</option>
                                <option value="06">Juni</option>
                                <option value="07">Juli</option>
                                <option value="08">Agustus</option>
                                <option value="09">September</option>
                                <option value="10">Oktober</option>
                                <option value="11">November</option>
                                <option value="12">Desember</option>
                            </select></td>
                            <td><label>Tahun</label>
                            <select name="thn">
                                 <option value="2016">2016</option>
                                <option value="2017">2017</option>
                                <option value="2018">2018</option>
                                 <option value="2019">2019</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input class="button btn-default" type="submit" value="Preview" name="preview" /></td>
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