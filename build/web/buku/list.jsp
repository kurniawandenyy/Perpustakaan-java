<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Buku"%>

<a href="<%=request.getContextPath()%>/BukuServlet?action=tambah"> +Tambah Data Buku</a>
<div class="box">
    <div class="box-header">
        <h3 class="box-title">Data Buku</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body no-padding">
        <table class="table table-condensed">
            <thead>
                <tr>
                    <th>Kode Buku</th>
                    <th>Judul Buku</th>
                    <th>Pengarang</th>
                    <th>Penerbit</th>
                    <th>Tahun Terbit</th>
                    <th>Aksi</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Buku> listBuku
                            = (ArrayList<Buku>) request.getAttribute("listBuku");
                    for (Buku buku : listBuku) {

                %>
                <tr>
                    <td><%=buku.getKode()%></td>
                    <td><%=buku.getJudul()%></td>
                    <td><%=buku.getPengarang()%></td>
                    <td><%=buku.getPenerbit()%></td>
                    <td><%=buku.getTahun()%></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/BukuServlet?action=fedit&kode_buku=<%=buku.getKode()%>">Edit | </a>
                        <a href="<%=request.getContextPath()%>/BukuServlet?action=delete&kode_buku=<%=buku.getKode()%>">Delete</a>
                    </td>
                </tr>
                <% }%>
            </tbody>  
        </table>
    </div>
    <!-- /.box-body -->
</div>