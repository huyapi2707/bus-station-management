<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create New Company</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <style>
        .form-container {
            max-width: 600px;
            margin: auto;
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
        .form-container input[type="text"], .form-container input[type="email"], .form-container input[type="file"], .form-container select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-container button {
            display: inline-block;
            padding: 10px 20px;
            margin-right: 10px;
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
    <h2>Create New Company</h2>
    <form id="newCompanyForm">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="isVerified">Is Verified:</label>
        <input type="checkbox" id="isVerified" name="isVerified">

        <label for="isActive">Is Active:</label>
        <input type="checkbox" id="isActive" name="isActive">

        <label for="isCargoTransport">Is Cargo Transport:</label>
        <input type="checkbox" id="isCargoTransport" name="isCargoTransport">

        <label for="avatar">Avatar:</label>
        <input type="file" id="avatar" name="avatar" required>

        <label for="managerId">Manager ID:</label>
        <select id="managerId" name="managerId" required>
            <option value="">Select a manager</option>
        </select>

        <button type="button" id="saveBtn">Save</button>
        <a class="btn btn-primary" href="<c:url value="/admin/companies"/>">Cancel</a>

    </form>
</div>

<script>
    $(document).ready(function() {
        // Load users with role 3 (managers)
        axios.get('http://localhost:8080/busstation/admin/users/role/3')
            .then(function(response) {
                const managers = response.data;
                const managerSelect = $('#managerId');
                managers.forEach(manager => {
                    managerSelect.append(new Option(manager.id + ' - ' + manager.firstname + ' ' + manager.lastname, manager.id));
                });
            })
            .catch(function(error) {
                console.error('Failed to load managers:', error);
            });

        // Save new company
        $('#saveBtn').on('click', function() {
            const formData = new FormData();
            const companyData = {
                name: $('#name').val(),
                phone: $('#phone').val(),
                email: $('#email').val(),
                isVerified: $('#isVerified').is(':checked'),
                isActive: $('#isActive').is(':checked'),
                isCargoTransport: $('#isCargoTransport').is(':checked'),
                managerId: $('#managerId').val()
            };

            formData.append('company', new Blob([JSON.stringify(companyData)], { type: "application/json" }));
            formData.append('avatar', document.getElementById('avatar').files[0]);

            axios.post('http://localhost:8080/busstation/api/v1/transportation_company/add', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
                .then(function() {
                    window.location.href = 'http://localhost:8080/busstation/admin/companies';
                })
                .catch(function(error) {
                    console.error("Failed to create new company:", error);
                    alert("Failed to create new company");
                });
        });
    });
</script>
</body>
</html>
