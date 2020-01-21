<%@page import="java.util.ArrayList"%>
<%@page import="model.Peminjaman"%>
<%@page import="java.util.List"%>
<h1>Data Peminjaman</h1>

<a href="<%=request.getContextPath()%>/PeminjamanServlet?action=ftambah"> +Tambah Data Peminjaman</a>
<div class="box">
    <div class="box-header">
        <h3 class="box-title">Data Peminjaman</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body no-padding">
        <table class="table table-condensed">
            <thead>
                <tr>
                    <th>Kode Anggota</th>
                    <th>Kode Buku</th>
                    <th>Tanggal Pinjam</th>
                    <th>Tanggal Kembali</th>
                    <th>Status</th>
                    <th>Aksi</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Peminjaman> listPeminjaman
                            = (ArrayList<Peminjaman>) request.getAttribute("listPeminjaman");
                    for (Peminjaman peminjaman : listPeminjaman) {

                %>
                <tr>
                    <td><%=peminjaman.getKode_anggota()%></td>
                    <td><%=peminjaman.getKode_buku()%></td>
                    <td><%=peminjaman.getTgl_pinjam()%></td>
                    <td><%=peminjaman.getTgl_kembali()%></td>
                    <td><%=peminjaman.getStatus()%></td>
                    <td>
                <a href="<%=request.getContextPath()%>/PeminjamanServlet?action=fedit&kode_anggota=<%=peminjaman.getKode_anggota()%>&kode_buku=<%=peminjaman.getKode_buku()%>&tgl_pinjam=<%=peminjaman.getTgl_pinjam()%>">Edit | </a>
                <a href="<%=request.getContextPath()%>/PeminjamanServlet?action=delete&kode_anggota=<%=peminjaman.getKode_anggota()%>&kode_buku=<%=peminjaman.getKode_buku()%>&tgl_pinjam=<%=peminjaman.getTgl_pinjam()%>">Delete</a>
            </td>
                </tr>
                <% }%>
            </tbody>  
        </table>
    </div>
    <!-- /.box-body -->
</div>