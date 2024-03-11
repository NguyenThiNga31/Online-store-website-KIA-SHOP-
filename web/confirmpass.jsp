<%-- 
    Document   : confirmpass
    Created on : Jan 22, 2024, 2:59:09 PM
    Author     : MSI GTX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

        <title>KiA Shop - Be good, Be bad, Be yourself | Login</title>
        <style>
            .error {
                color: #fff;
                font-size: 16px;
                display: block;
                margin-top: 5px;
            }
        </style>
        <!-- Favicon  -->
        <link rel="icon" href="img/core-img/favicon2.ico">
    </head>
    <body>
        <div class="wel">
            <h1>Welcome to KiA Shop</h1>
            <p>Be good, Be bad, Be yourself</p>
        </div>
        <div id="logreg-forms">
            <form action="confirmpass" method="post" class="form-signup" id="formforgot"style="display: block;" >
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Forgot Password</h1>
                <input name="password" type="password" id="password" class="form-control" placeholder="Password">
                <span class="error" id="password-error"></span>
                <input name="repassword" type="password" id="repassword" class="form-control" placeholder="Confirm Password">
                <span class="error" id="repassword-error"></span>
                <span class="error" id="confirmpass-error"></span>
                <button class="btn btn-warning btn-block" name="btnpass" id="btnpass" value="Confirmpass" type="submit"> Submit</button>
                <a id="backforgot" href="javascript:void(0);" onclick="goBack()"><i class="fas fa-angle-left"></i> Back</a>
            </form>

            <br>

        </div>

        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
                    $("#backforgot").click(function (event) {
                        event.preventDefault();
                        window.history.back();
                    });

                    $(document).ready(function () {
                        $("#password, #repassword").on("input", function () {
                            checkPasswordMatch();
                        });
                        $("#btnpass").click(function (event) {
                            event.preventDefault(); // Prevent form submission
                            performAjaxRequestForgot();
                        });
                    });

                    function checkPasswordMatch() {
                        var password = $("#password").val().trim();
                        var repassword = $("#repassword").val().trim();
                        $("#confirmpass-error, #password-error, #repassword-error").text("");

                        if (password !== repassword) {
                            $("#confirmpass-error").text("Passwords do not match.").css("color", "red");
                        } else {
                            $("#confirmpass-error").text("");
                        }
                    }

                    function performAjaxRequestForgot() {
                        var password = $("#password").val().trim();
                        var repassword = $("#repassword").val().trim();
                        var hasErrorse = false;
                        $("#confirmpass-error, #password-error, #repassword-error").text("");

                        // Check if email is empty or missing '@'
                        if (password === "") {
                            $("#password-error").text("Please enter password.").css("color", "red");
                            hasErrorse = true;
                        } else {
                            $("#password-error").text("");
                        }
                        if (repassword === "") {
                            $("#repassword-error").text("Please enter password.").css("color", "red");
                            hasErrorse = true;
                        } else {
                            $("#repassword-error").text("");
                        }

                        // If there is an error, stop further processing
                        if (hasErrorse) {
                            return;
                        }

                        $.ajax({
                            type: "POST",
                            url: "confirmpass",
                            data: {btnpass: "Confirmpass", password: password, repassword: repassword},
                            success: function (response) {
                                if (response === "SUCCESS") {
                                    Swal.fire({
//                                title: 'Forgot Success',
                                        text: 'Check email to verify pin code',
                                        icon: 'success',
                                        confirmButtonText: 'OK'
                                    }).then(() => {
                                        // Redirect to another page after clicking OK
                                        window.location.href = 'pin.jsp'; // Change this to the desired URL
                                    });
                                } else {
                                    // Display SweetAlert for failure
                                    Swal.fire({
                                        title: 'Forgot Failed',
                                        text: 'Forgot failed, please check again.',
                                        icon: 'error',
                                        confirmButtonText: 'OK'
                                    });
                                }
                            },
                            error: function () {
                                // Display SweetAlert for error
                                Swal.fire({
                                    title: 'System Error',
                                    text: 'The system is failing',
                                    icon: 'error',
                                    confirmButtonText: 'OK'
                                });
                            }
                        });
                    }
        </script>
    </body>
</html>
