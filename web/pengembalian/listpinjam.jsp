<%@page import="java.util.ArrayList"%>
<%@page import="model.Peminjaman"%>
<%@page import="java.util.List"%>

<a href="<%=request.getContextPath()%>/PeminjamanServlet?action=ftambah">Tambah Data</a>
<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">Data Peminjaman</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body">
        <table class="table table-bordered">
            <thead>
        <tr>
            <th>Kode Anggota</th>
            <th>Kode Buku</th>
            <th>Tanggal Peminjaman</th>
            <th>Tanggal Kembali</th>
            <th>Status</th>
            <th>Aksi</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Peminjaman> listpeminjaman=
                   (ArrayList<Peminjaman>) request.getAttribute("listPeminjaman");
            for(Peminjaman peminjaman:listpeminjaman){
        %>
        <tr>
            <td><%=peminjaman.getKode_anggota() %></td>
            <td><%=peminjaman.getKode_buku() %></td>
            <td><%=peminjaman.getTgl_pinjam() %></td>
            <td><%=peminjaman.getTgl_kembali() %></td>
            <td><%=peminjaman.getStatus() %></td>
            <td>
                <a href="<%=request.getContextPath() %>/PengembalianServlet?action=ftambah&kode_anggota=<%=peminjaman.getKode_anggota() %>&kode_buku=<%=peminjaman.getKode_buku() %>&tgl_pinjam=<%=peminjaman.getTgl_pinjam() %>">Kembalikan</a>
            </td>
        </tr>
        <%
            }
        %>
    </tbody>
        </table>
    </div>
    <!-- /.box-body -->

</div>
<!-- /.box -->