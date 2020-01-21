<%@page import="model.Anggota"%>
<%
    Anggota anggota=(Anggota)request.getAttribute("anggota");
    String pesan = (request.getAttribute("pesan")==null)?"":request.getAttribute("pesan").toString();
    out.print(pesan);
%>


            
            <a href="<%=request.getContextPath()%>/AnggotaServlet">List Data Anggota</a>

<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">Edit Data Anggota</h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form method="POST" action="<%= request.getContextPath() %>/AnggotaServlet?action=edit">
        <div class="box-body">
            <div class="form-group">
                <label for="NamaAnggota">Kode Anggota</label>
                <input type="text" name="KodeAnggota" class="form-control" id="KodeAnggota" placeholder="Kode Anggota" value="<%=anggota.getKodeAnggota()%>" readonly="true">
            </div>
            <div class="form-group">
                <label for="KodeAnggota">Nama Anggota</label>
                <input type="text" name="NamaAnggota" value="<%=anggota.getNamaAnggota()%>" class="form-control" id="NamaAnggota" placeholder="Nama Anggota">
            </div>
            <div class="form-group">
                <label for="Alamat">Alamat</label>
                <input type="text" name="Alamat" value="<%=anggota.getAlamat()%>" class="form-control" id="Alamat" placeholder="Alamat">
            </div>
            <div class="form-group">
                <label for="JenisKelamin">Jenis Kelamin</label>
                <input type="text" name="JenisKelamin" value="<%=anggota.getJenisKelamin() %>" class="form-control" id="JenisKelamin" placeholder="Jenis Kelamin">
            </div>
            <div class="form-group">
                <label for="TglLahir">Tanggal Lahir</label>
                <input type="text" name="TglLahir" value="<%=anggota.getTglLahir() %>" class="form-control" id="TglLahir" placeholder="Tanggal Lahir">
            </div>
        </div>
        <!-- /.box-body -->

        <div class="box-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-danger">Reset</button>
        </div>
    </form>
</div>