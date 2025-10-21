<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.daw.jakarta.jdbcapp.model.Fabricante" %>
<%@ page import="es.daw.jakarta.jdbcapp.model.Producto" %>
<%@ page import="es.daw.jakarta.jdbcapp.util.Utils" %>
<%@ page import="java.util.List" %>
<%
    String preferencia = (String) request.getAttribute("preferencia");
    if (preferencia == null) {
        preferencia = "no";
    }
    System.out.println(preferencia);
    %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Fabricantes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .table-custom tbody tr:nth-child(odd) {
            background-color: #f8f9fa;
        }
        .table-custom tbody tr:nth-child(even) {
            background-color: #e9f2fa;
        }
        .table-custom tbody tr:hover {
            background-color: #d0e7f9;
        }
    </style>
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-primary mb-0">🏭 Fabricantes registrados</h2>

        <form action="<%= request.getContextPath() %>/preferencias" method="post" class="text-center mb-4">
            <label for="mostrar" class="form-label fw-bold">Mostrar productos por fabricante:</label>
            <select name="mostrar" id="mostrar" class="form-select d-inline-block w-auto">
                <option value="si"
                        <%=preferencia.equals("si") ? "selected": ""%>>Sí
                </option>
                <option value="no"
                        <%=preferencia.equals("no") ? "selected" : ""%>>No
                </option>
            </select>
            <button type="submit" class="btn btn-outline-primary ms-2">Guardar</button>
        </form>

        <a href="<%= request.getContextPath() %>/fabricantes/crear" class="btn btn-success">➕ Añadir fabricante</a>
    </div>

    <%
        List<Fabricante> fabricantes = (List<Fabricante>) request.getAttribute("fabricantes");
        if (fabricantes != null && !fabricantes.isEmpty()) {
    %>
    <div class="table-responsive">
        <table class="table table-bordered table-custom">
            <thead class="table-dark">
            <tr>
                <th scope="col">Código</th>
                <th scope="col">Nombre</th>
                <% if ("si".equals(preferencia)) { %>
                <th scope="col">Productos</th>
                <% } %>
                <th scope="col">Editar</th>
                <th scope="col">Borrar</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Fabricante f : fabricantes) {
                    // compute prods for this fabricante (always compute so we can check delete availability)
                    List<String> prods = Utils.obtenerProductosPorFabricante(
                            (List<Producto>) request.getAttribute("productos"), f.getCodigo());
            %>
            <tr>
                <td><%= f.getCodigo() %></td>
                <td><%= f.getNombre() %></td>
                <% if ("si".equals(preferencia)) { %>
                <td>
                    <% if (prods != null && !prods.isEmpty()) { %>
                    <ul class="mb-0">
                        <% for (String pn : prods) { %>
                        <li><%= pn %></li>
                        <% } %>
                    </ul>
                    <% } else { %>
                    <em>No hay productos</em>
                    <% } %>
                </td>
                <% } %>
                <td>
                    <form action="<%= request.getContextPath() %>/fabricantes/editar" method="get">
                        <input type="hidden" name="codigo" value="<%= f.getCodigo() %>">
                        <button type="submit" class="btn btn-warning btn-sm">✏️</button>
                    </form>
                </td>
                <% if (prods == null || prods.isEmpty()) { %>
                <td>
                    <form action="<%= request.getContextPath() %>/fabricantes/borrar" method="post" onsubmit="return confirm('¿Seguro que deseas borrar este fabricante?');">
                        <input type="hidden" name="codigo" value="<%= f.getCodigo() %>">
                        <button type="submit" class="btn btn-danger btn-sm">🗑️</button>
                    </form>
                </td>
                <% } else { %>
                <td>No puedes borrarlo</td>
                <% } %>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <% } else { %>
    <div class="alert alert-warning" role="alert">
        ⚠️ No hay fabricantes registrados
    </div>
    <% } %>

    <div class="mt-4">
        <a href="<%= request.getContextPath() %>/" class="btn btn-secondary">⬅️ Volver al inicio</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
