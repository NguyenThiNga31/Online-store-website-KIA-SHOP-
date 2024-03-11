<%-- 
    Document   : ManagerProduct
    Created on : October 24, 2023, 2:08:03 PM
    Author     : Admin
--%>

<%@page import="entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="dao.DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Title Page-->
        <title>Manager Product</title>

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
        <script type="text/javascript" src="http://dev.jquery.com/view/trunk/plugins/validate/jquery.validate.js"></script>
        <!-- Main CSS-->
        <link href="css/manager.css" rel="stylesheet" media="all">
        <style>
            .error-message{
                color: red;
            }
            img {
                width: 100px;
                height: 100px;
            }
        </style>
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
                                    <h2 class="title-1"><a href="ShopControl">Shop</a> / Manager Product</h2>
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
                                                <%
                                                    DAO dao = new DAO();
                                                    int viewed = dao.getViewed();
                                                %>
                                                <h2>${countTheNumberSold}</h2>
                                                <span>The Number Sold</span>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            <canvas id="widgetChart1"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c2">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-shopping-cart"></i>
                                            </div>
                                            <div class="text">
                                                <h2>${calculateTotalStockQuantity}</h2>
                                                <span>Total Stock Quantity</span>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            <canvas id="widgetChart2"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c3">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-calendar-note"></i>
                                            </div>
                                            <div class="text">
                                                <h2>${numberP}</h2>
                                                <span>Type Product</span>
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
                                                <h2>$${totalPrice}</h2>
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
            <!-- END MAIN CONTENT-->




            <!-- list------------------------------------------------------------------- -->
            <div class="container">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-3">
                                <h2>Manage <b>Product</b></h2>
                            </div>
                            <div class="col-sm-3">
                                <form id="searchForm" action="ManagerControl" method="get">
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
                            <div class="col-sm-3">
                                <div class="btn-group" role="group">
                                    <a href="ManagerControl?sortOption=AZ" class="btn btn-primary" style="margin-left:10px;">Sort A-Z</a>
                                    <a href="ManagerControl?sortOption=ZA" class="btn btn-primary">Sort Z-A</a>
                                    <a href="ManagerControl?sortOption=createdAt" class="btn btn-primary" style="margin-right:10px;">Sort by Created At</a>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                                <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="selectAll">
                                        <label for="selectAll"></label>
                                    </span>
                                </th>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Image</th>
                                <th>Price</th>
                                <th>title</th>
                                <th>Description</th>
                                <th>Amount</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listP}" var="o"> 
                                <tr>
                                    <td>
                                        <span class="custom-checkbox">
                                            <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                            <label for="checkbox1"></label>
                                        </span>
                                    </td>
                                    <td>${o.id}</td>
                                    <td>${o.name}</td>
                                    <td>
                                        <img src="${o.image}">
                                    </td>
                                    <td>${o.price} $</td>
                                    <td>${o.title} </td>
                                    <td>${o.description} </td>
                                    <td>${o.amount} </td>
                                    <td>
                                        <!--                                        <a href="#editEmployeeModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>-->
                                        <a onclick="editProduct(${o.id})" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="delete?deletePID=${o.id}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach> 
                        </tbody>

                    </table>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                        <ul class="pagination">
                            <li class="page-item disabled"><a href="#">Previous</a></li>
                            <li class="page-item"><a href="#" class="page-link">1</a></li>
                            <li class="page-item"><a href="#" class="page-link">2</a></li>
                            <li class="page-item active"><a href="#" class="page-link">3</a></li>
                            <li class="page-item"><a href="#" class="page-link">4</a></li>
                            <li class="page-item"><a href="#" class="page-link">5</a></li>
                            <li class="page-item"><a href="#" class="page-link">Next</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- Add Modal HTML -->
            <div id="addEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="addProduct" method="post" id="addProductForm">
                            <div class="modal-header">
                                <h4 class="modal-title">Add Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input name="name" type="text" class="form-control" >
                                    <span id="nameError" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>Image</label>
                                    <input name="image" type="text" class="form-control" >
                                    <span id="imageError" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>SubImage1</label>
                                    <input name="subImage1" type="text" class="form-control" >
                                    <span id="subImage1Error" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>SubImage2</label>
                                    <input name="subImage2" type="text" class="form-control" >
                                    <span id="subImage2Error" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>SubImage3</label>
                                    <input name="subImage3" type="text" class="form-control" >
                                    <span id="subImage3Error" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>SubImage4</label>
                                    <input name="subImage4" type="text" class="form-control" >
                                    <span id="subImage4Error" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input name="price" type="number" class="form-control" min="1" max="10000" >
                                    <span id="priceError" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>Title</label>
                                    <textarea name="title" class="form-control" ></textarea>
                                    <span id="titleError" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="description" class="form-control" ></textarea>
                                    <span id="descriptionError" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>Size Value </label>
                                    <input value="37" name="sizevalue1" type="text" class="form-control" readonly>
                                </div>
                                <div class="form-group">
                                    <label> Quantity </label>
                                    <input name="quantity1" type="number" class="form-control" min="0" max="100">
                                    <span id="quantity1Error" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>Size Value </label>
                                    <input value="38" name="sizevalue2" type="text" class="form-control" readonly>
                                </div>
                                <div class="form-group">
                                    <label> Quantity </label>
                                    <input name="quantity2" type="number" class="form-control" min="0" max="100">
                                    <span id="quantity2Error" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>Size Value </label>
                                    <input value="39" name="sizevalue3" type="text" class="form-control" readonly>
                                </div>
                                <div class="form-group">
                                    <label> Quantity </label>
                                    <input name="quantity3" type="number" class="form-control" min="0" max="100">
                                    <span id="quantity3Error" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>Size Value </label>
                                    <input value="40" name="sizevalue4" type="text" class="form-control" readonly>
                                </div>
                                <div class="form-group">
                                    <label> Quantity </label>
                                    <input name="quantity4" type="number" class="form-control" min="0" max="100">
                                    <span id="quantity4Error" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>Size Value </label>
                                    <input value="41" name="sizevalue5" type="text" class="form-control" readonly>
                                </div>
                                <div class="form-group">
                                    <label> Quantity </label>
                                    <input name="quantity5" type="number" class="form-control" min="0" max="100">
                                    <span id="quantity5Error" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>Size Value </label>
                                    <input value="42" name="sizevalue6" type="text" class="form-control" readonly>
                                </div>
                                <div class="form-group">
                                    <label> Quantity </label>
                                    <input name="quantity6" type="number" class="form-control" min="0" max="100">
                                    <span id="quantity6Error" class="error-message"></span>
                                </div>
                                <div class="form-group">
                                    <label>Size Value </label>
                                    <input value="43" name="sizevalue7" type="text" class="form-control" readonly>
                                </div>
                                <div class="form-group">
                                    <label> Quantity </label>
                                    <input name="quantity7" type="number" class="form-control" min="0" max="100">
                                    <span id="quantity7Error" class="error-message"></span>
                                </div>
                                <%
                                    List<Category> listC = dao.getAllCategory();
                                %>
                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="category" class="form-select" aria-label="Default select example">
                                        <c:forEach items="<%= listC%>" var="o">
                                            <option value="${o.id}">${o.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <c:set var="product" value="${dao.getProductByID(id)}"/>
            <c:set var="listC" value="${dao.getAllCategory()}"/>
            <c:set var="select" value=""/>
            <c:forEach var="category" items="${listC}">
                <c:choose>
                    <c:when test="${product.cateID ne category.id}">
                        <c:set var="selectOption" value='<option value="${category.id}">${category.name}</option>' />
                    </c:when>
                    <c:otherwise>
                        <c:set var="selectOption" value='<option value="${category.id}" selected>${category.name}</option>' />
                    </c:otherwise>
                </c:choose>
                <c:set var="select" value="${select}${selectOption}\n"/>
            </c:forEach>
            <!-- Edit Modal HTML -->
            <div id="editEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form>
                            <div class="modal-header">
                                <h4 class="modal-title">Edit Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input name="name" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Image</label>
                                    <input name="image" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>SubImage1</label>
                                    <input name="subImage1" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>SubImage2</label>
                                    <input name="subImage2" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>SubImage3</label>
                                    <input name="subImage3" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>SubImage4</label>
                                    <input name="subImage4" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input name="price" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Title</label>
                                    <textarea name="title" class="form-control" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="description" class="form-control" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Amount</label>
                                    <input name="amount" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="category" class="form-select" aria-label="Default select example">
                                        <c:forEach items="<%= listC%>" var="o">
                                            <option value="${o.id}">${o.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-info" value="Save">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Delete Modal HTML -->
            <div id="deleteEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form>
                            <div class="modal-header">
                                <h4 class="modal-title">Delete Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <p>Are you sure you want to delete these Records?</p>
                                <p class="text-warning"><small>This action cannot be undone.</small></p>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-danger" value="Delete">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <script src="js/manager.js" type="text/javascript"></script>

            <script>
                                            function editProduct(param) {
                                                var id = param;
                                                console.log(id);
                                                $.ajax({
                                                    url: "/Project_KiaShop/UpdateProductControl",
                                                    type: "get", //send it throung get method
                                                    data: {
                                                        id: id
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("editEmployeeModal");
                                                        row.innerHTML = data;
                                                        $("#editEmployeeModal").modal('show');

                                                    },
                                                    error: function (xhr) {
                                                        //do something to handle error
                                                    }
                                                });
                                            }



                                            document.getElementById("addProductForm").addEventListener("submit", function (event) {
                                                // Reset previous error messages
                                                var errorElements = document.getElementsByClassName("error-message");
                                                for (var i = 0; i < errorElements.length; i++) {
                                                    errorElements[i].innerHTML = "";
                                                }

                                                var name = document.getElementsByName("name")[0].value.trim();
                                                var image = document.getElementsByName("image")[0].value.trim();
                                                var subImage1 = document.getElementsByName("subImage1")[0].value.trim();
                                                var subImage2 = document.getElementsByName("subImage2")[0].value.trim();
                                                var subImage3 = document.getElementsByName("subImage3")[0].value.trim();
                                                var subImage4 = document.getElementsByName("subImage4")[0].value.trim();
                                                var price = document.getElementsByName("price")[0].value.trim();
                                                var title = document.getElementsByName("title")[0].value.trim();
                                                var description = document.getElementsByName("description")[0].value.trim();
                                                var quantity1 = document.getElementsByName("quantity1")[0].value.trim();
                                                var quantity2 = document.getElementsByName("quantity2")[0].value.trim();
                                                var quantity3 = document.getElementsByName("quantity3")[0].value.trim();
                                                var quantity4 = document.getElementsByName("quantity4")[0].value.trim();
                                                var quantity5 = document.getElementsByName("quantity5")[0].value.trim();
                                                var quantity6 = document.getElementsByName("quantity6")[0].value.trim();
                                                var quantity7 = document.getElementsByName("quantity7")[0].value.trim();

                                                // Repeat the pattern for other form fields

                                                var isValid = true;

                                                if (name === "") {
                                                    displayError("name", "Please enter a Name.");
                                                    isValid = false;
                                                }
                                                if (image === "") {
                                                    displayError("image", "Please enter link image.");
                                                    isValid = false;
                                                }
                                                if (subImage1 === "") {
                                                    displayError("subImage1", "Please enter link image 1.");
                                                    isValid = false;
                                                }
                                                if (subImage2 === "") {
                                                    displayError("subImage2", "Please enter link image 2.");
                                                    isValid = false;
                                                }
                                                if (subImage3 === "") {
                                                    displayError("subImage3", "Please enter link image 3.");
                                                    isValid = false;
                                                }
                                                if (subImage4 === "") {
                                                    displayError("subImage4", "Please enter link image 4.");
                                                    isValid = false;
                                                }
                                                if (price === "" || price === 0) {
                                                    displayError("price", "Please enter a Price.");
                                                    isValid = false;
                                                }
                                                if (title === "") {
                                                    displayError("title", "Please enter a Title.");
                                                    isValid = false;
                                                }
                                                if (description === "") {
                                                    displayError("description", "Please enter a Description.");
                                                    isValid = false;
                                                }
                                                if (quantity1 === "") {
                                                    displayError("quantity1", "Please enter a Quantity size 37.");
                                                    isValid = false;
                                                }
                                                if (quantity2 === "") {
                                                    displayError("quantity2", "Please enter a Quantity size 38.");
                                                    isValid = false;
                                                }
                                                if (quantity3 === "") {
                                                    displayError("quantity3", "Please enter a Quantity size 39.");
                                                    isValid = false;
                                                }
                                                if (quantity4 === "") {
                                                    displayError("quantity4", "Please enter a Quantity size 40.");
                                                    isValid = false;
                                                }
                                                if (quantity5 === "") {
                                                    displayError("quantity5", "Please enter a Quantity size 41.");
                                                    isValid = false;
                                                }
                                                if (quantity6 === "") {
                                                    displayError("quantity6", "Please enter a Quantity size 42.");
                                                    isValid = false;
                                                }
                                                if (quantity7 === "") {
                                                    displayError("quantity7", "Please enter a Quantity size 43.");
                                                    isValid = false;
                                                }
                                                // Repeat the pattern for other form fields
                                                if (!isValid) {
                                                    event.preventDefault(); // Prevent the form from being submitted
                                                }
                                            });
                                            function displayError(fieldName, errorMessage) {
                                                var errorElement = document.getElementById(fieldName + "Error");
                                                errorElement.innerHTML = errorMessage;
                                            }
            </script>
    </body>
</html>
