<%
    String pesan = (request.getAttribute("pesan")==null)?"":request.getAttribute("pesan").toString();
    out.print(pesan);
%>

    <a href="<%=request.getContextPath() %>/BukuServlet">List Data Buku</a>
    <div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">Tambahkan Data Buku</h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form role="form" method="POST" action="<%= request.getContextPath()%>/BukuServlet?action=tambah">
        <div class="box-body">
            <div class="form-group">
                <label for="kode_buku">Kode Buku</label>
                <input type="text" name="kode_buku" class="form-control" id="kode_buku" placeholder="Kode Buku">
            </div>
            <div class="form-group">
                <label for="judul">Judul</label>
                <input type="text" name="judul" class="form-control" id="judul" placeholder="Judul">
            </div>
            <div class="form-group">
                <label for="pengarang">Pengarang</label>
                <input type="text" name="pengarang" class="form-control" id="pengarang" placeholder="Pengarang">
            </div>
            <div class="form-group">
                <label for="penerbit">Penerbit</label>
                <input type="text" name="penerbit" class="form-control" id="penerbit" placeholder="Penerbit">
            </div>
            <div class="form-group">
                <label for="tahun_terbit">Tahun Terbit</label>
                <input type="text" name="tahun_terbit" class="form-control" id="tahun_terbit" placeholder="Tahun Terbit">
            </div>
        </div>
        <!-- /.box-body -->

        <div class="box-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-danger">Reset</button>
        </div>
    </form>
</div>