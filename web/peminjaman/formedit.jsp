<%@page import="model.Buku"%>
<%@page import="java.util.List"%>
<%@page import="model.Anggota"%>
<%@page import="model.Peminjaman"%>
<%
    Peminjaman peminjaman = (Peminjaman) request.getAttribute("peminjaman");
    String pesan = (request.getAttribute("pesan") == null) ? "" : request.getAttribute("pesan").toString();
    out.print(pesan);
%>

<a href="<%=request.getContextPath()%>/PeminjamanServlet">List Data Peminjaman</a>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">Edit Data Peminjaman</h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form method="POST" action="<%= request.getContextPath()%>/PeminjamanServlet?action=edit">
        <div class="box-body">
            <div class="form-group">
                <label for="kode_anggota">Nama Anggota</label>
                <select name="kode_anggota" id="kode_anggota" class="form-control">
                    <%
                        List<Anggota> anggotaList = (List<Anggota>) request.getAttribute("anggota");
                        for (Anggota anggota : anggotaList) {
                            if (anggota.getKodeAnggota().equals(peminjaman.getKode_anggota())) {
                                out.print("<option value='" + anggota.getKodeAnggota() + "' selected>" + anggota.getNamaAnggota() + "</option>");
                            } else {
                                out.print("<option value='" + anggota.getKodeAnggota() + "'>" + anggota.getNamaAnggota() + "</option>");
                            }
                        }
                    %>

                </select>
            </div>
            <div class="form-group">
                <label for="kode_buku">Judul</label>
                <select name="kode_buku" id="kode_buku" class="form-control">
                    <%
                        List<Buku> bukuList = (List<Buku>) request.getAttribute("buku");
                        for (Buku buku : bukuList) {
                            if (buku.getKode().equals(peminjaman.getKode_buku())) {
                                out.print("<option value='" + buku.getKode() + "' selected>" + buku.getJudul() + "</option>");
                            } else {
                                out.print("<option value='" + buku.getKode() + "'>" + buku.getJudul() + "</option>");
                            }
                        }
                    %>

                </select>
            </div>
            <div class="form-group">
                <label for="tgl_pinjam">Tanggal Pinjam</label>
                <input type="text" name="tgl_pinjam" value="<%=peminjaman.getTgl_pinjam()%>" class="form-control" id="tgl_pinjam" placeholder="Tanggal Pinjam">
            </div>
            <div class="form-group">
                <label for="tgl_kembali">Tanggal Kembali</label>
                <input type="text" name="tgl_kembali" value="<%=peminjaman.getTgl_kembali()%>" class="form-control" id="tgl_kembali" placeholder="Tanggal Kembali">
            </div>
            <div class="form-group">
                <label for="status">Status</label>
                <input type="text" name="status" value="<%=peminjaman.getStatus()%>" class="form-control" id="status" placeholder="Status">
            </div>
        </div>
        <!-- /.box-body -->

        <div class="box-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-danger">Reset</button>
        </div>
    </form>
</div>