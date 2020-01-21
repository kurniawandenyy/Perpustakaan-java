<%-- 
    Document   : laporanperpengarang
    Created on : Dec 12, 2018, 8:35:34 AM
    Author     : LABP2KOMP24
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Buku"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="box">
    <div class="box-header">
        <h3 class="box-title">Laporan Buku Per-Pengarang</h3><br><br>
        <form action="<%= request.getContextPath()%>/BukuServlet?action=laporanperpengarang" method="POST">
            <label> Pengarang </label> 
            <select name="pengarang">
                <%
                    List<Buku> bukuListpengarang = (List<Buku>) request.getAttribute("bukupengarang");
                    for (Buku bukupengarang : bukuListpengarang) {
                        out.print("<option value='"
                                + bukupengarang.getPengarang() + "'>"
                                + bukupengarang.getPengarang() + "</option>");
                    }
                %>
                <option></option>
            </select>
            <input type="submit" value="Filter" name="filter" />
        </form>
    </div>
    <!-- /.box-header -->
    <div class="box-body no-padding">

        <table class="table table-condensed">
           <thead>
                        <tr>
                            <th>Kode Buku</th>
                            <th>Judul</th>
                            <th>Pengarang</th>
                            <th>Penerbit</th>
                            <th>Tahun Terbit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Buku> listbuku
                                    = (ArrayList<Buku>) request.getAttribute("listbuku");
                            for (Buku buku : listbuku) {
                        %>
                        <tr>
                            <td><%=buku.getKode()%></td>
                            <td><%=buku.getJudul()%></td>
                            <td><%=buku.getPengarang()%></td>
                            <td><%=buku.getPenerbit()%></td>
                            <td><%=buku.getTahun()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
        </table>
    </div>
    <!-- /.box-body -->
</div>
