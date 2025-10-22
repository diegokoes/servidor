<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="es.daw.jakarta.bibliotecajdbc.model.Libro" %>
<%@ page import="es.daw.jakarta.bibliotecajdbc.model.Autor" %>
<%@ page import="es.daw.jakarta.bibliotecajdbc.utils.Utils" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Biblioteca JDBC - Libros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body { background: linear-gradient(135deg, #f0f4f8, #e6ebef); font-family: 'Segoe UI', sans-serif; }
        .hero { text-align: center; padding: 2.5rem 1rem 1.5rem; }
        .hero h1 { font-weight: 700; color: #0d6efd; }
        .hero .lead { color: #6c757d; }
        .card { border: none; border-radius: 1rem; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        thead th { background: #0d6efd; color: #fff; border-color: #0b5ed7 !important; }
        .btn-edit { --bs-btn-color:#fff; --bs-btn-bg:#0d6efd; --bs-btn-border-color:#0d6efd; --bs-btn-hover-bg:#0b5ed7; --bs-btn-hover-border-color:#0a58ca; }
        .btn-delete { --bs-btn-color:#fff; --bs-btn-bg:#dc3545; --bs-btn-border-color:#dc3545; --bs-btn-hover-bg:#bb2d3b; --bs-btn-hover-border-color:#b02a37; }
        .empty { color:#6c757d; }
    </style>
</head>
<body>
<div class="container mt-5">
    <section class="hero">
        <h1>📖 Lista de Libros</h1>
        <p class="lead">Administra y revisa los libros</p>
    </section>

    <div class="row mb-3 justify-content-end">
        <div class="col-auto">
            <a href="<%= request.getContextPath() %>/libros/crear" class="btn btn-success">
                <i class="bi bi-plus-lg"></i> Crear Libro
            </a>
        </div>
    </div>

    <%
        List<Libro> libros = (List<Libro>) request.getAttribute("libros");
        List<Autor> autores = (List<Autor>) request.getAttribute("autores");
    %>
    <div class="row justify-content-center">
        <div class="col-12 col-md-10 col-lg-8">
            <div class="card">
                <div class="card-body p-0">
                    <% if (libros != null && !libros.isEmpty()) { %>
                    <div class="table-responsive">
                        <table class="table table-striped table-hover align-middle mb-0">
                            <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Título</th>
                                <th scope="col">Autor</th>
                                <th scope="col">Fecha de Publicación</th>
                                <th scope="col" class="text-center">Editar</th>
                                <th scope="col" class="text-center">Borrar</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% for (Libro libro : libros) { %>
                            <tr>
                                <td><%= libro.getId_libro() %></td>
                                <td><%= libro.getTitulo() %></td>
                                <td><%= Utils.obtenerNombreAutor(autores, libro.getId_autor()) %></td>
                                <td>
                                    <%
                                        String fechaIso = "";
                                        if (libro.getFechaPublicacion() != null) {
                                            SimpleDateFormat iso = new SimpleDateFormat("yyyy-MM-dd");
                                            fechaIso = iso.format(libro.getFechaPublicacion());
                                        }
                                    %>
                                    <%= fechaIso.isEmpty() ? "—" : fechaIso %>
                                </td>
                                <td class="text-center">
                                    <a class="btn btn-sm btn-edit" href="<%=request.getContextPath()%>/libros/editar?id=<%= libro.getId_libro() %>&titulo=<%= libro.getTitulo() %>&autor=<%= libro.getId_autor() %>&fechaPublicacion=<%= fechaIso %>">Editar</a>
                                </td>
                                <td class="text-center">
                                    <a class="btn btn-sm btn-delete" href="<%=request.getContextPath()%>/libros/borrar?id=<%= libro.getId_libro() %>" onclick="return confirm('¿Seguro que quieres borrar este libro?');">Borrar</a>
                                </td>
                            </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                    <% } else { %>
                    <div class="p-4 text-center empty">No hay libros disponibles.</div>
                    <% } %>
                </div>
            </div>
            <div class="text-center mt-4">
                <a class="btn btn-secondary" href="<%= request.getContextPath() %>">Volver</a>
            </div>
        </div>
    </div>

    <footer class="text-center mt-5">
        <hr>
        <p class="small text-muted">Listado de libros • Servlets + JSP + JDBC</p>
    </footer>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
