<%
    String pesan = (request.getAttribute("pesan") == null) ? "" : request.getAttribute("pesan").toString();
    //out.print(pesan);
    if(!pesan.equals("")){
%>

 <div class="box box-success">
            <div class="box-header with-border">
              <h3 class="box-title">Pesan</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
              <!-- /.box-tools -->
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <%= pesan %>
            </div>
            <!-- /.box-body -->
          </div>
 <%
    }
 %>
<a href="<%=request.getContextPath()%>/AnggotaServlet">List Data Anggota</a>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">Tambahkan Data Anggota</h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form role="form" method="POST" action="<%= request.getContextPath()%>/AnggotaServlet?action=tambah">
        <div class="box-body">
            <div class="form-group">
                <label for="KodeAnggota">Kode Anggota</label>
                <input type="text" name="KodeAnggota" class="form-control" id="KodeAnggota" placeholder="Kode Anggota">
            </div>
            <div class="form-group">
                <label for="NamaAnggota">Nama Anggota</label>
                <input type="text" name="NamaAnggota" class="form-control" id="NamaAnggota" placeholder="Nama Anggota">
            </div>
            <div class="form-group">
                <label for="Alamat">Alamat</label>
                <input type="text" name="Alamat" class="form-control" id="Alamat" placeholder="Alamat">
            </div>
            <div class="form-group">
                <label for="JenisKelamin">Jenis Kelamin</label>
                <input type="text" name="JenisKelamin" class="form-control" id="JenisKelamin" placeholder="Jenis Kelamin">
            </div>
            <div class="form-group">
                <label for="TglLahir">Tanggal Lahir</label>
                <input type="text" name="TglLahir" class="form-control" id="TglLahir" placeholder="Tanggal Lahir">
            </div>
        </div>
        <!-- /.box-body -->

        <div class="box-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-danger">Reset</button>
        </div>
    </form>
</div>