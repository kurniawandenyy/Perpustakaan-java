<%-- 
    Document   : laporanperthn
    Created on : Dec 12, 2018, 9:46:33 AM
    Author     : Denykurniawan
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Buku"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="box">
    <div class="box-header">
        <h3 class="box-title">Laporan Buku Per-Tahun Terbit</h3><br><br>
        <form action="<%= request.getContextPath()%>/BukuServlet?action=laporanperthn" method="POST">
            <label> Tahun Terbit </label> 
            <select name="tahun_terbit">
                <%
                    List<Buku> bukuList = (List<Buku>) request.getAttribute("buku");
                    for (Buku buku : bukuList) {
                        out.print("<option value='"
                                + buku.getTahun() + "'>"
                                + buku.getTahun() + "</option>");
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
