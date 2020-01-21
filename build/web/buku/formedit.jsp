
<%@page import="model.Buku"%>
<%
    Buku buku=(Buku)request.getAttribute("buku");
    String pesan = (request.getAttribute("pesan")==null)?"":request.getAttribute("pesan").toString();
    out.print(pesan);
%>
            
<a href="<%=request.getContextPath()%>/BukuServlet">List Data Buku</a>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">Edit Data Buku</h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form method="POST" action="<%= request.getContextPath() %>/BukuServlet?action=edit">
        <div class="box-body">
            <div class="form-group">
                <label for="kode_buku">Kode Buku</label>
                <input type="text" name="kode_buku" class="form-control" id="kode_buku" placeholder="Kode Buku" value="<%=buku.getKode()%>" readonly="true">
            </div>
            <div class="form-group">
                <label for="judul">Judul</label>
                <input type="text" name="judul" value="<%=buku.getJudul()%>" class="form-control" id="judul" placeholder="Judul">
            </div>
            <div class="form-group">
                <label for="pengarang">Pengarang</label>
                <input type="text" name="pengarang" value="<%=buku.getPengarang()%>" class="form-control" id="pengarang" placeholder="Pengarang">
            </div>
            <div class="form-group">
                <label for="penerbit">Penerbit</label>
                <input type="text" name="penerbit" value="<%=buku.getPenerbit()%>" class="form-control" id="penerbit" placeholder="Penerbit">
            </div>
            <div class="form-group">
                <label for="tahun_terbit">Tahun Terbit</label>
                <input type="text" name="tahun_terbit" value="<%=buku.getTahun()%>" class="form-control" id="tahun_terbit" placeholder="Tahun Terbit">
            </div>
        </div>
        <!-- /.box-body -->

        <div class="box-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-danger">Reset</button>
        </div>
    </form>
</div>