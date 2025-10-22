<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page
        import="es.daw.jakarta.bibliotecajdbc.model.Autor" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Formulario Autor</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
    />
    <style>
        body {
            background: linear-gradient(135deg, #f0f4f8, #e6ebef);
            font-family: "Segoe UI", sans-serif;
        }

        .form-card {
            border: none;
            border-radius: 1rem;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
            margin-top: 3rem;
        }

        .form-title {
            color: #0d6efd;
            font-weight: 700;
            text-align: center;
            margin-bottom: 1.5rem;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-12 col-md-8 col-lg-6">
            <div class="card form-card">
                <div class="card-body">
                    <h2 class="form-title">Formulario Autor</h2>
            <form
                method="post"
                action="<%= request.getContextPath() %><%= (request.getAttribute("autorEditar") != null) ? "/autores/editar" : "/autores/crear" %>"
            >
                        <div class="mb-3">
                            <label for="autorId" class="form-label"
                            >ID (solo lectura)</label
                            >
                            <% Autor autorEditar = (Autor)
                                    request.getAttribute("autorEditar");
                                String autorIdVal =
                                        (autorEditar != null && autorEditar.getId_autor() != null) ?
                                                autorEditar.getId_autor().toString() : ""; %>
                            <input
                                    type="text"
                                    class="form-control"
                                    id="autorId"
                                    name="autorId"
                                    value="<%= autorIdVal %>"
                                    readonly
                            />
                        </div>
                        <div class="mb-3">
                            <label for="nombre"
                                   class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="nombre"
                                   name="nombre" value="<%= (autorEditar != null &&
                  autorEditar.getNombre()!=null) ? autorEditar.getNombre() : ""
                  %>" required/>
                        </div>
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary">
                                Guardar
                            </button>
                            <a
                                    href="<%= request.getContextPath() %>/autores/listar"
                                    class="btn btn-secondary"
                            >Cancelar</a
                            >
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
