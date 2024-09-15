<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Form</title>
    <style>
        .registration-form {
            width: 400px;
            margin-top: 200px;
            margin-left: 550px;
            padding: 20px;
            border: 2px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        .registration-heading {
            text-align: center;
        }
        .input-field {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .submit-button {
            background-color: #04AA6D;
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border-radius: 10px;
            margin-left: 145px;
        }
    </style>
</head>
<body style="background-color:powderblue;">
    <div class="registration-form">
        <h2 class="registration-heading">Sign Up</h2>
        <form action="registered-servlet" method="post">
            <input type="text" name="user_email" placeholder="Input your email" class="input-field" required><br><br>
            <input type="password" name="user_pass" placeholder="Input your password" class="input-field" required><br><br>
            <input type="password" name="user_repass" placeholder="Repeat your password" class="input-field" required><br><br>
            <input type="submit" value="Submit" class="submit-button">
        </form>
    </div>
</body>
</html>