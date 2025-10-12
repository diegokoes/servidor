<%@ page import="es.daw.jakarta.model.Fabricante" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Conexión a base de datos</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-size: 14px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            height: 100vh;
            padding: 1rem;
            margin: 0;
            display: flex;
            flex-direction: column;
            overflow: hidden;
        }

        h1 {
            text-align: center;
            color: white;
            font-size: 1.8rem;
            margin: 0 0 1rem 0;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }

        .forms-container {
            display: flex;
            gap: 1rem;
            flex: 1;
            overflow-y: auto;
            max-width: 1400px;
            width: 100%;
            margin: 0 auto;
        }

        form {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .fieldset-principal {
            padding: 1rem;
            margin: 0 0 0.75rem 0;
            border: none;
            border-radius: 8px;
            background: white;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
        }

        .fieldset-principal > legend {
            padding: 0.5rem 1rem;
            border: none;
            border-radius: 6px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            font-size: 1rem;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        legend {
            font-weight: 600;
        }

        label[for] {
            margin-top: 0.5rem;
            margin-bottom: 0.25rem;
            font-weight: 600;
            display: block;
            color: #333;
            font-size: 0.85rem;
        }

        input[type="text"],
        select {
            width: 100%;
            padding: 0.5rem 0.75rem;
            margin-top: 0.25rem;
            border: 2px solid #e0e0e0;
            border-radius: 6px;
            font-size: 0.9rem;
            transition: all 0.3s ease;
            font-family: inherit;
        }

        input[type="text"]:focus,
        select:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }

        select {
            cursor: pointer;
            background-color: white;
        }

        label input[type="checkbox"],
        label input[type="radio"] {
            display: inline;
            font-weight: normal;
            width: auto;
            margin-right: 0.5rem;
            cursor: pointer;
            transform: scale(1.2);
        }

        .radio-group label {
            display: flex;
            align-items: center;
            padding: 0.5rem;
            margin-top: 0.25rem;
            border-radius: 6px;
            transition: background-color 0.2s ease;
            cursor: pointer;
            font-weight: normal;
        }

        .radio-group label:hover {
            background-color: #f5f5f5;
        }

        .obligatorio:after {
            content: ' (obligatorio)';
            color: #e74c3c;
            font-size: 0.75rem;
        }

        button,
        input[type="submit"] {
            padding: 0.6rem 1.5rem;
            border: none;
            border-radius: 6px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            font-weight: 600;
            font-size: 0.9rem;
            color: white;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 100%;
            margin-top: 0.5rem;
        }

        button:hover,
        input[type="submit"]:hover {
            transform: translateY(-1px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
        }

        button:active,
        input[type="submit"]:active {
            transform: translateY(0);
        }

        .single-submit {
            text-align: center;
        }
    </style>

</head>
<body>
<h1>PRODUCTOS JDBC DAO</h1>
<div class="forms-container">
    <form action="productos/listar" method="GET" target="_self">
        <fieldset class="fieldset-principal">
            <legend>Listado de productos</legend>
            <div class="single-submit">
                <input type="submit" value="Ver productos"/>
            </div>
        </fieldset>
    </form>

    <form action="productos/modificar" method="POST" target="_self">
        <fieldset class="fieldset-principal">
            <legend>Datos del producto</legend>
            <label for="codigo">
                Código:
                <input type="text" name="codigo" id="codigo" placeholder="Introduce el codigo del producto"/>
            </label>
            <label for="nombre">
                Nombre:
                <input type="text" name="nombre" id="nombre" placeholder="Introduce el nombre del producto"/>
            </label>

            <label for="precio">
                Precio:
                <input type="text" name="precio" id="precio" placeholder="Introduce el precio"/>
            </label>

            <label for="departamento">
                Fabricantes:
            </label>
            <select name="codigo_fabricante" id="departamento">
                <%
                    List<Fabricante> fabricantes = (List<Fabricante>) request.getAttribute("fabricantes");
                    if (fabricantes != null) {
                        for (Fabricante fab : fabricantes) {
                %>
                <option value="<%= fab.getCodigo() %>"><%= fab.getNombre() %>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </fieldset>

        <fieldset class="fieldset-principal">
            <legend>Operaciones</legend>
            <div class="radio-group">
                <label for="operacion1">
                    <input type="radio" value="insert" id="operacion1" name="operacion" required/>
                    Insertar nuevo producto con todos los campos
                </label>
                <label for="operacion2">
                    <input type="radio" name="operacion" id="operacion2" value="update" required/>
                    Actualizar nombre del producto
                </label>
                <label for="operacion3">
                    <input type="radio" name="operacion" id="operacion3" value="delete" required/>
                    Borrar por código de producto
                </label>
            </div>

            <label for="codigo">
                Código del producto (actualizar/borrar):
                <input type="text" name="codigo" id="codigo" placeholder="Introduce el código del producto"/>
            </label>
        </fieldset>

        <button name="enviar" type="submit">Modificar productos</button>
    </form>
</div>
</body>
</html>