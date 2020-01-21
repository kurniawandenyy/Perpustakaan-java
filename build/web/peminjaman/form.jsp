<%@page import="model.Buku"%>
<%@page import="java.util.List"%>
<%@page import="model.Anggota"%>
<%
    String pesan = (request.getAttribute("pesan") == null) ? "" : request.getAttribute("pesan").toString();
    out.print(pesan);
%>              


<a href="<%=request.getContextPath() %>/PeminjamanServlet">List Data Peminjaman</a>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">Tambahkan Data Peminjaman</h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form role="form" method="POST" action="<%= request.getContextPath()%>/PeminjamanServlet?action=tambah">
        <div class="box-body">
            <div class="form-group">
                <label for="kode_anggota">Nama Anggota</label>
                <select name="kode_anggota" class="form-control" id="kode_anggota">
                    <%
                        List<Anggota> anggotaList =(List<Anggota>) request.getAttribute("anggota");
                        for(Anggota anggota:anggotaList){
                            out.print("<option value='"+anggota.getKodeAnggota()+"'>"+anggota.getNamaAnggota()+"</option>");
                        }
                    %>

                </select>
            </div>
            <div class="form-group">
                <label for="kode_buku">Judul Buku</label>
                <select name="kode_buku" class="form-control" id="kode_buku">
                    <%
                        List<Buku> bukuList =(List<Buku>) request.getAttribute("buku");
                        for(Buku buku:bukuList){
                            out.print("<option value='"+buku.getKode()+"'>"+buku.getJudul()+"</option>");
                        }
                    %>

                </select>
            </div>
            <div class="form-group">
                <label for="tgl_pinjam">Tanggal Pinjam</label>
                <input type="text" name="tgl_pinjam" class="form-control" id="tgl_pinjam" placeholder="Tanggal Pinjam">
            </div>
            <div class="form-group">
                <label for="tgl_kembali">Tanggal Kembali</label>
                <input type="text" name="tgl_kembali" class="form-control" id="tgl_kembali" placeholder="Tanggal Kembali">
            </div>
            <div class="form-group">
                <label for="status">Status</label>
                <input type="text" name="status" class="form-control" id="status" placeholder="Status">
            </div>
        </div>
        <!-- /.box-body -->

        <div class="box-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-danger">Reset</button>
        </div>
    </form>
</div>