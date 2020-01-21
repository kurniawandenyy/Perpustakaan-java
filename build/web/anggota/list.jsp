<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Anggota"%>

<a href="<%=request.getContextPath()%>/AnggotaServlet?action=tambah"> +Tambah Data Anggota</a>
<div class="box">
    <div class="box-header">
        <h3 class="box-title">Data Anggota</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body no-padding">
        <table class="table table-condensed">
            <thead>
                <tr>
                    <th>Kode Anggota</th>
                    <th>Nama Anggota</th>
                    <th>Alamat</th>
                    <th>Jenis Kelamin</th>
                    <th>Tanggal Lahir</th>
                    <th>Aksi</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Anggota> listAnggota
                            = (ArrayList<Anggota>) request.getAttribute("listAnggota");
                    for (Anggota anggota : listAnggota) {

                %>
                <tr>
                    <td><%=anggota.getKodeAnggota()%></td>
                    <td><%=anggota.getNamaAnggota()%></td>
                    <td><%=anggota.getAlamat()%></td>
                    <td><%=anggota.getJenisKelamin()%></td>
                    <td><%=anggota.getTglLahir()%></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/AnggotaServlet?action=fedit&KodeAnggota=<%=anggota.getKodeAnggota()%>">Edit | </a>
                        <a href="<%=request.getContextPath()%>/AnggotaServlet?action=delete&KodeAnggota=<%=anggota.getKodeAnggota()%>">Delete</a>
                    </td>
                </tr>
                <% }%>
            </tbody>  
        </table>
    </div>
    <!-- /.box-body -->
</div>
