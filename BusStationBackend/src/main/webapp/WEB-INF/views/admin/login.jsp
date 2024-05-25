<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/admin/login" var="action"/> <!-- Đảm bảo URL này trùng khớp với loginProcessingUrl -->
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        .form-container {
            max-width: 400px;
            margin: 100px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        .form-container h2 {
            text-align: center;
        }
        .form-container label {
            display: block;
            margin-bottom: 8px;
        }
        .form-container input[type="text"], .form-container input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-container button {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
        .form-container button.cancel {
            background-color: #f44336;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Login to Your Account</h2>
    <form id="loginForm" action="${action}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/> <!-- CSRF Token -->
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <button type="submit">Login</button>
        </div>
        <c:if test="${not empty param.error}">
            <p style="color:red;">Invalid username or password.</p>
        </c:if>
    </form>
</div>
</body>
</html>
