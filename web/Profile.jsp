<%-- 
    Document   : 
    Created on :
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Account"%>
<%@page import="dao.DAO"%>
<%@page import="jakarta.servlet.http.HttpSession" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>JSP Page</title>-->
        <meta charset="UTF-8">
        <meta name="description" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Title  -->
        <title>Profile</title>

        <!-- Favicon  -->
        <link rel="icon" href="img/core-img/favicon2.ico">

        <!-- Core Style CSS -->
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
        <script type="text/javascript" src="http://dev.jquery.com/view/trunk/plugins/validate/jquery.validate.js"></script>
        <link rel="stylesheet" href="css/core-style.css">
        <!-- <link rel="stylesheet" href="style.css"> -->

    </head>
    <body>
        <!-- Search Wrapper Area Start -->
        <jsp:include page="common/search.jsp"></jsp:include>
            <!-- Search Wrapper Area End -->

            <!-- ##### Main Content Wrapper Start ##### -->
        <jsp:include page="common/header.jsp"></jsp:include>
            <!-- Header Area End -->

            <div class="cart-table-area section-padding-100">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12 col-lg-8">
                            <div class="checkout_details_area mt-50 clearfix">

                                <div class="cart-title">
                                    <h2>Profile</h2>
                                </div>
                            <c:if test="${message != null}">
                                <div style="color: green">
                                    ${message}
                                </div>
                            </c:if>
                            <form action="profile" method="post">
                                <div class="content" style="display: flex">
                                    <div class="left" style="margin-right: 30px">
                                        <img src="img/profile.jpg" alt="alt" width="300px"/>
                                    </div>
                                    <div class="right">
                                        <div>
                                            <div style="display: flex">
                                                <div style="width: 100px">Full Name: </div>
                                                <div>
                                                    <input type="hidden" name="id" required="" autofocus="" value="${acc.id}"/>
                                                    <input type="text" name="fullName" required="" autofocus="" value="${acc.fullname}"/>

                                                </div>
                                            </div>
                                            <div>
                                                <div style="display: flex">
                                                    <div style="width: 100px">Email: </div>
                                                    <div>
                                                        <input type="email" name="email" required="" autofocus="" value="${acc.email}" readonly=""/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div>
                                                <div style="display: flex">
                                                    <div style="width: 100px">Phone: </div>
                                                   <div>
    <input type="text" name="phone" pattern="0\d{9,10}" autofocus="" value="${acc.phone == 0 ? '' : acc.phone}" title="Please enter a valid phone number starting with 0 and followed by 9 or 10 digits"/>
</div>

                                                </div>
                                                <div>
                                                    <div style="display: flex">
                                                        <div style="width: 100px">Address: </div>
                                                        <div><input type="text" name="address" autofocus="" value="${acc.address}"/></div>
                                                    </div>
                                                </div>
                                               
                                                </div>
                                                <div style="margin-top: 20px">
                                                    <button type="submit">Update</button>
                                                    <a href="ChangePassword.jsp" style="color: #fbb710"><label class="form-check-label" for="exampleCheck1"><i class="fas fa-question-circle"></i> Change password?</label></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>                  
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ##### Main Content Wrapper End ##### -->

            <!-- ##### Newsletter Area Start ##### -->

            <!-- ##### Newsletter Area End ##### -->

            <!-- ##### Footer Area Start ##### -->
        <jsp:include page="common/footer.jsp"></jsp:include>
        <!-- ##### Footer Area End ##### -->

        <!-- ##### jQuery (Necessary for All JavaScript Plugins) ##### -->
        <script src="js/jquery/jquery-2.2.4.min.js"></script>
        <!-- Popper js -->
        <script src="js/popper.min.js"></script>
        <!-- Bootstrap js -->
        <script src="js/bootstrap.min.js"></script>
        <!-- Plugins js -->
        <script src="js/plugins.js"></script>
        <!-- Active js -->
        <script src="js/active.js"></script>
    </body>
</html>
