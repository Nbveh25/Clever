<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
    <title>Login Form</title>
    <style>
        .login-form {
            width: 400px;
            margin-top: 200px;
            margin-left: 550px;
            padding: 20px;
            border: 2px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        .login-heading {
            text-align: center;
        }
        .input-field {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .submit-button {
            background-color: #04AA6D;
            color: white;
            border: none;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border-radius: 10px;
            margin-left: 145px;
        }
        .text-button {
            font-size: 14px;
            background: none;
            border: none;
            margin-left: none;
        }
    </style>
</head>
<body style="background-color:powderblue;">
    <div class="login-form">
        <h2 class="login-heading">Log in</h2>
        <form action="login-servlet" method="post">
            <input type="text" name="user_email" placeholder="Input your email" class="input-field" required><br><br>
            <input type="password" name="user_pass" placeholder="Input your password" class="input-field" required><br>
            <text>You don't have an account?</text>
            <a href="Registration.jsp">Create an account?</a><br><br>
            <input type="submit" value="Submit" class="submit-button">
        </form>
    </div>
</body>
</html>