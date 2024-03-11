<%-- 
    Document   : Login
    Created on : Jan 13, 2021, 2:06:52 PM
    Author     : Admin
--%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css" />
        <title>KiA Shop - Be good, Be bad, Be yourself | Change password</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
        <script type="text/javascript" src="http://dev.jquery.com/view/trunk/plugins/validate/jquery.validate.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#formregister").validate({
                    rules: {
                        oldPass: {
                            required: true,
                            maxlength: 20,
                            minlength: 3
                        },
                        pass: {
                            required: true,
                            maxlength: 20,
                            minlength: 3
                        },
                        repass: {
                            required: true,
                            maxlength: 20,
                            minlength: 3,
                            equalTo: "#pass"
                        }
                    }
                });
            });
        </script>
        <style>
            .error{
                color: red;
            }
            #logreg-forms .form-signin input {
                margin-bottom: 2px;
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
            <form action="changePassword" method="post" class="form-signin" id="formregister">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Change password</h1>
                <c:if test="${message != null}">
                    <div style="color: green">
                        ${message}
                    </div>
                </c:if>
                <input name="email" type="email" id="user-name" class="form-control" placeholder="Email" required="" autofocus="" value="${sessionScope.acc.email}">
                <input name="oldPass" type="password" id="oldpass" class="form-control" placeholder="Old Password" required autofocus="">
                <input name="pass" type="password" id="pass" class="form-control" placeholder="New Password" required autofocus="">
                <input name="repass" type="password" id="repass" class="form-control" placeholder="Repeat New Password" required autofocus="">
                <button class="btn btn-warning btn-block" type="submit"><i class="fas fa-user-plus"></i> Change password</button>
                <a href="Login.jsp"><i class="fas fa-angle-left"></i> Back</a>
            </form>
            <br>
        </div>

        <script>
            function toggleResetPswd(e) {
                e.preventDefault();
                $('#logreg-forms .form-signin').toggle(); // display:block or none
                $('#logreg-forms .form-reset').toggle(); // display:block or none
            }

            function toggleSignUp(e) {
                e.preventDefault();
                $('#logreg-forms .form-signin').toggle(); // display:block or none
                $('#logreg-forms .form-signup').toggle(); // display:block or none
            }

            $(() => {
                // Login Register Form
                $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
                $('#logreg-forms #cancel_reset').click(toggleResetPswd);
                $('#logreg-forms #btn-signup').click(toggleSignUp);
                $('#logreg-forms #cancel_signup').click(toggleSignUp);
            })
        </script>
    </body>
</html>
