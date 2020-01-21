<%@page import="model.Peminjaman"%>
<%@page import="model.Buku"%>
<%@page import="model.Anggota"%>
<%@page import="java.util.List"%>
<%
    String pesan
            = (request.getAttribute("pesan") == null) ? "" : request.getAttribute("pesan").toString();
    out.print(pesan.toString());
    Peminjaman pinjam = (Peminjaman) request.getAttribute("pinjam");
%>

<a href="<%=request.getContextPath()%>/PengembalianServlet?action=listpinjam">List Data</a>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">Form Peminjaman</h3>
    </div>
    <form role="form" method="POST" action="<%= request.getContextPath()%>/PengembalianServlet?action=tambah">
        <div class="box-body">
            <div class="form-group">
                <div class="form-group">
                    <label for="kode_angggota">Kode anggota</label>
                    <input type="text" class="form-control" id="kode_anggota" name="kode_anggota" value="<%=pinjam.getKode_anggota()%>">
                </div>
            </div>
            <div class="form-group">
                <label for="kode_buku">Kode Buku</label>
                <input type="text" class="form-control" id="kode_buku" name="kode_buku" value="<%=pinjam.getKode_buku()%>">
            </div>
            <div class="form-group">
                <label for="tgl_pinjam">Tanggal Pinjam</label>
                <input type="text" class="form-control" id="tgl_pinjam" name="tgl_pinjam" value="<%=pinjam.getTgl_pinjam()%>">
            </div>
            <div class="form-group">
                <label for="tgl_kembali">Tanggal Kembali</label>
                <input type="text" class="form-control" id="tgl_kembali" name="tgl_kembali" value="<%=pinjam.getTgl_kembali()%>">
            </div>
            <div class="form-group">
                <label for="tgl_dikembalikan">Tanggal Dikembalikan</label>
                <input type="text" class="form-control" id="tgl_dikembalikan" name="tgl_dikembalikan" value="<%=request.getAttribute("tglsekarang")%>">
            </div>
            <div class="form-group">
                <label for="terlambat">Terlambat</label>
                <input type="text" class="form-control" id="terlambat" name="terlambat" value="<%=request.getAttribute("terlambat")%>">
            </div>
            <div class="form-group">
                <label for="denda">Denda</label>
                <input type="text" class="form-control" id="denda" name="denda" value="<%=request.getAttribute("denda")%>">
            </div>
        </div>
        <!-- /.box-body -->
        <div class="box-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</div>