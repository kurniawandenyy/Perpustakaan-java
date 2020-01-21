<%-- 
    Document   : laporan
    Created on : Dec 18, 2018, 7:32:12 PM
    Author     : User
--%>

<%@page import="model.Buku"%>
<%@page import="model.Anggota"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Pengembalian"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="box">
    <div class="box-header">
        <h3 class="box-title">Laporan Pengembalian</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body no-padding">

        <form method="POST" action="<%= request.getContextPath()%>/PengembalianServlet?action=laporanperbulan">
                <br>
                Laporan Pengembalian Per Bulan
                <table border="0">
                    <tbody>
                        <tr></tr>
                        <tr>
                            <td>Bulan</td>
                            <td>
                                <select name="bulan">
                                    <option value=1>Januari</option>
                                    <option value=2>Februari</option>
                                    <option value=3>Maret</option>
                                    <option value=4>April</option>
                                    <option value=5>Mei</option>
                                    <option value=6>Juni</option>
                                    <option value=7>Juli</option>
                                    <option value=8>Agustus</option>
                                    <option value=9>September</option>
                                    <option value=10>Oktober</option>
                                    <option value=11>November</option>
                                    <option value=12>Desember</option>
                                </select>

                            </td>
                        </tr>
                        <tr>
                            <td>Tahun</td>
                            <td>
                                <select name="tahun">
                                    <option value=2017>2017</option>
                                    <option value=2018>2018</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Preview" name="preview" /></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <form method="POST" action="<%= request.getContextPath()%>/PengembalianServlet?action=laporananggota">
                <br>
                Laporan Pengembalian Per Anggota
                <table border="0">
                    <tbody>
                        <tr></tr>
                        <tr>
                            <td>Nama Anggota</td>
                            <td>
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
                        <tr></tr>
                        <tr align="center">
                            <td><input type="submit" value="Preview" name="preview" /></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <form method="POST" action="<%= request.getContextPath()%>/PengembalianServlet?action=laporanbuku">
                <br>
                Laporan Pengembalian Per Buku
                <table border="0">
                    <tbody>
                        <tr></tr>
                        <tr>
                            <td>Judul Buku</td>
                            <td>
                                <select name="kode_buku">
                                    <%
                                        List<Buku> bukuList = (List<Buku>) request.getAttribute("buku");
                                        for (Buku buku : bukuList) {
                                            out.print("<option value='"
                                                    + buku.getKode()+ "'>"
                                                    + buku.getJudul() + "</option>");
                                        }
                                    %>
                                    <option></option>
                                </select>
                            </td>
                        </tr>
                        <tr></tr>
                        <tr align="center">
                            <td><input type="submit" value="Preview" name="preview" /></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <label>Laporan Pengembalian</label>
        <table class="table table-condensed">
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
                        <td><%=pengembalian.getTgl_dikembalikan()%></td>
                        <td><%=pengembalian.getTerlambat()%></td>
                        <td><%=pengembalian.getDenda()%></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
        </table>
    </div>
    <!-- /.box-body -->
</div>
