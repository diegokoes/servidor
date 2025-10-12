<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.daw.jakarta.model.Producto" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Lista de Productos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 2rem 1rem;
            margin: 0;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            padding: 2rem;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
        }

        h2 {
            text-align: center;
            color: #667eea;
            font-size: 2rem;
            margin-bottom: 2rem;
            font-weight: 600;
        }

        .table-responsive {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 8px;
            overflow: hidden;
        }

        thead {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        thead th {
            padding: 1rem;
            text-align: left;
            font-weight: 600;
            font-size: 1rem;
        }

        tbody tr {
            border-bottom: 1px solid #e0e0e0;
            transition: background-color 0.2s ease;
        }

        tbody tr:nth-child(odd) {
            background-color: #f8f9fa;
        }

        tbody tr:nth-child(even) {
            background-color: #ffffff;
        }

        tbody tr:hover {
            background-color: #e8eaf6;
        }

        tbody td {
            padding: 1rem;
            color: #333;
        }

        .alert {
            padding: 1.5rem;
            border-radius: 8px;
            background-color: #fff3cd;
            border: 1px solid #ffc107;
            color: #856404;
            text-align: center;
            font-size: 1.1rem;
        }

        .back-link {
            display: inline-block;
            margin-top: 2rem;
            padding: 0.75rem 2rem;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-weight: 600;
            transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .back-link:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        }
    </style>
</head>
<body>

<div class="container">
    <h2>📋 Productos disponibles</h2>

    <%
        List<Producto> productos = (List<Producto>) request.getAttribute("productos");
        if (productos != null && !productos.isEmpty()) {
    %>
    <div class="table-responsive">
        <table>
            <thead>
            <tr>
                <th>Código</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Fabricante</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Producto producto : productos) {
            %>
            <tr>
                <td><%= producto.getCodigo() %>
                </td>
                <td><%= producto.getNombre() %>
                </td>
                <td><%= producto.getPrecio() %>€</td>
                <td><%= producto.getCodigo_fabricante() %>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
    <%
    } else {
    %>
    <div class="alert">
        ⚠️ No hay productos disponibles
    </div>
    <%
        }
    %>

    <div style="text-align: center;">
        <a class="back-link" href='<%=request.getContextPath()%>/index'>Volver al inicio</a>
    </div>

</div>

</body>
</html>