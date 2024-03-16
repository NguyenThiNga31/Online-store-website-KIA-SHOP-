<%-- 
Document   : ManageOrder
Created on : Feb 29, 2024, 10:09:26 AM
Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Manage Order</title>

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
        <script type="text/javascript" src="http://dev.jquery.com/view/trunk/plugins/validate/jquery.validate.js"></script>
        <link href="css/manager.css" rel="stylesheet" media="all">
    </head>
    <body>

        <div class="page-wrapper">
            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="overview-wrap">
                                    <h2 class="title-1"><a href="ShopControl?showAllProducts=true">Shop</a> / Manager Order</h2>
                                </div>
                            </div>
                        </div>
                        <div class="row m-t-25">

                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c1">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-account-o"></i>
                                            </div>
                                            <div class="text">    
                                                <h2>${totalOrders}</h2>
                                                <span>Total Orders</span>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            <canvas id="widgetChart1"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c3">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-shopping-cart"></i>
                                            </div>
                                            <div class="text">
                                                <h2>${waitingOrder}</h2>
                                                <span>Waiting Order</span>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            <canvas id="widgetChart2"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c2">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-calendar-note"></i>
                                            </div>
                                            <div class="text">
                                                <h2>${acceptedOrder}</h2>
                                                <span>Accepted Order</span>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            <canvas id="widgetChart3"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c4">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-money"></i>
                                            </div>
                                            <div class="text">
                                                <h2>${totalE}</h2>
                                                <span>Total earnings</span>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            <canvas id="widgetChart4"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--        table manage order-->
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-4">
                            <h2>Manage <b>Orders</b></h2>
                        </div>
                        <div class="col-sm-4">
                            <form id="searchForm" action="ManageOrderControl" method="get">
                                <div class="form-group">
                                    <input name="searchInput" type="text" class="form-control" placeholder="Key word..." value="${txtSearch}">
                                </div>
                                <div class="form-group">
                                    <select name="searchOption" class="form-control">
                                        <option value="byName" ${searchOption == 'byName' ? 'selected' : ''}>Search by name</option>
                                        <option value="byPhoneNumber" ${searchOption == 'byPhoneNumber' ? 'selected' : ''}>Search by phone number</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Search</button>
                            </form>
                        </div>
                        <div class="col-sm-4">
                            <div class="btn-group" role="group">
                                <a href="ManageOrderControl?sortOption=Accepted" class="btn btn-primary" style="margin-left:10px;">Accepted Orders</a>
                                <a href="ManageOrderControl?sortOption=Waiting" class="btn btn-primary">Waiting Orders</a>
                                <a href="ManageOrderControl" class="btn btn-primary" style="margin-left:20px; margin-top: 10px" >All Orders</a>
                            </div>
                        </div>
                    </div>
                </div>

                <!--        table list order-->
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Date</th>
                            <th>Account ID</th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th>Name</th>
                            <th>Total Price</th>
                            <th>Status</th>
                            <th>Customer Cancel Reason</th>
                            <th>Action</th>
                            <th>Detail</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="order" items="${listO}">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.date}</td>
                                <td>${order.accountID}</td>
                                <td>${order.address}</td>
                                <td>${order.sdt}</td>
                                <td>${order.name}</td>
                                <td>${order.totalPrice}</td>
                                <td>${order.status}</td>
                                <td>${order.reason}</td>
                                <td>
                                    <c:if test="${order.status eq 'Waiting'}">
                                        <a href="changestatus?acceptID=${order.id}" class="active">Accept Order</a>
                                        <a href="changestatus?cancelID=${order.id}" class="delete">Cancel Order</a>
                                    </c:if>
                                </td>
                                <td style="text-align: center">
                                    <a href="OrderDetailAdminControl?orderId=${order.id}"><i class="material-icons" data-toggle="tooltip" title="Detail">&#xe24c;</i>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="clearfix">
                    <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                    <ul class="pagination">
                        <li class="page-item disabled"><a href="#">Previous</a></li>
                        <li class="page-item active"><a href="#" class="page-link">1</a></li>
                        <li class="page-item"><a href="#" class="page-link">2</a></li>
                        <li class="page-item "><a href="#" class="page-link">3</a></li>
                        <li class="page-item"><a href="#" class="page-link">4</a></li>
                        <li class="page-item"><a href="#" class="page-link">5</a></li>
                        <li class="page-item"><a href="#" class="page-link">Next</a></li>
                    </ul>
                </div>
            </div>
        </div>


    </body>
</html>
