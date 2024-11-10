<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../css/permission.css">
  <title>Выдача прав</title>
</head>
<body>
<div class="container">
  <h1>Выдача прав</h1>
  <form id="grant-permission-form">
    <label for="permissions">Выберите права:</label>
    <select id="permissions" required>
      <option value="" disabled selected>-- Выберите права --</option>
      <option value="moderator">Модератор</option>
    </select>

    <button type="submit" class="grant-permission-button">Выдать права</button>
  </form>

  <div id="message" class="message"></div>
</div>

<script>
  document.getElementById('grant-permission-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const permission = document.getElementById('permissions').value;

    // Логика выдачи прав
    if (permission) {
      document.getElementById('message').innerText = `Права "${permission}" успешно выданы пользователю.`;
      document.getElementById('grant-permission-form').reset(); // Сброс формы
    } else {
      document.getElementById('message').innerText = 'Пожалуйста, выберите права.';
    }
  });
</script>
</body>
</html>