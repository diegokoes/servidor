<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            ${empty cookie.color ? "blue" : cookie.color.value}
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
            padding: 1rem;
        }

        .error-container {
            background: white;
            padding: 3rem;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
            text-align: center;
            max-width: 500px;
            width: 100%;
        }

        .error-icon {
            font-size: 4rem;
            margin-bottom: 1rem;
        }

        .error-title {
            font-size: 2rem;
            color: #e74c3c;
            margin-bottom: 1rem;
            font-weight: 600;
        }

        .error-message {
            font-size: 1.1rem;
            color: #555;
            margin-bottom: 2rem;
            line-height: 1.6;
        }

        .back-link {
            display: inline-block;
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

        .back-link:active {
            transform: translateY(0);
        }
    </style>
</head>

<body>
<div class="error-container">
    <div class="error-icon">⚠️</div>
    <h1 class="error-title">Error</h1>
    <p class="error-message">${error}</p>
    <a class="back-link" href='<%=request.getContextPath()%>/index'>Volver al inicio</a>
</div>
</body>
</html>
