
<%-- 
    Document   : Shop
    Created on : October 24, 2023, 2:10:09 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="entity.Account" %>
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
        <title>KiA Shop - Be good, Be bad, Be yourself | Shop</title>

        <!-- Favicon  -->
        <link rel="icon" href="img/core-img/favicon2.ico">

        <!-- Core Style CSS -->
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="css/core-style.css">
        <link rel="stylesheet" href="style.css"> 
        <style>
            .searchbar{
                margin-bottom: auto;
                margin-top: auto;
                height: 50px;
                background-color: #f5f7fa;
                border-radius: 30px;
                padding: 8px;
            }

            .search_input{
                color: black;
                border: 0;
                outline: 0;
                background: none;
                width: 0;
                caret-color:transparent;
                line-height: 40px;
                transition: width 0.4s linear;
            }

            .searchbar > .search_input{
                padding: 0 10px;
                width: 360px;
                caret-color:red;
                transition: width 0.4s linear;
            }

            .searchbar > .search_icon{
                background: #f5f7fa;
                color: #e74c3c;
            }

            .search_icon{
                height: 40px;
                width: 40px;
                float: right;
                display: flex;
                justify-content: center;
                align-items: center;
                border-radius: 50%;
                color:white;
                text-decoration:none;
            }


        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <!-- Search Wrapper Area Start -->
        <jsp:include page="common/search.jsp"></jsp:include>
            <!-- Search Wrapper Area End -->

            <!-- ##### Main Content Wrapper Start ##### -->
        <jsp:include page="common/header.jsp"></jsp:include>
            <!-- Header Area End -->

            <div class="shop_sidebar_area">
                <div class="product-topbar d-xl-flex align-items-end justify-content-between">
                    <!-- Total Products -->
                    <div class="total-products">
                        <p>View All Products</p>
                        <div class="view d-flex">
                            <a href="ShopControl?showAllProducts=true"><i class="fa fa-th-large" aria-hidden="true"></i></a>
                        </div>
                    </div>
                </div>
                <!-- ##### Single Widget ##### -->
                <div class="widget catagory mb-50">
                    <!-- Widget Title -->
                    <h6 class="widget-title mb-30">Catagories</h6>

                    <!--  Catagories  -->
                    <div class="catagories-menu">
                        <ul id="categoryMenu">
                        <c:forEach items="${listC}" var="o">
                            <li class="${tag == o.id ? "active":""}">
                                <a href="ShopControl?pageIndex=1&cID=${o.id}">${o.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <!-- ##### Single Widget ##### -->
            <div class="widget brands mb-50">
                <!-- Widget Title -->
                <h6 class="widget-title mb-30">Price</h6>
                <div class="widget-desc">
                    <div class="catagories-menu">
                        <ul>
                            <c:forEach items="${listC}" var="catory">
                                <c:if test="${!empty cID && catory.id eq cID}">
                                    <li>
                                        <a href="SortpriceControl?pageIndex=1&cID=${catory.id}&price=0-100" class="form-check-label" for="amado" id="amado" name="price">Under $100</a>
                                    </li>
                                    <li>
                                        <a href="SortpriceControl?pageIndex=1&cID=${catory.id}&price=100-300" class="form-check-label" for="amado" id="amado" name="price">$100 - $300</a>
                                    </li>
                                    <li>
                                        <a href="SortpriceControl?pageIndex=1&cID=${catory.id}&price=300-500" class="form-check-label" for="amado" id="amado" name="price">$300 - $500</a>
                                    </li>
                                    <li>
                                        <a href="SortpriceControl?pageIndex=1&cID=${catory.id}&price=500-1000" class="form-check-label" for="amado" id="amado" name="price">$500 - $1000</a>
                                    </li>
                                    <li>
                                        <a href="SortpriceControl?pageIndex=1&cID=${catory.id}&price=1000-3000" class="form-check-label" for="amado" id="amado" name="price">$1000 +</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
                            <c:if test="${empty cID }">
                                <li>
                                    <a href="SortpriceControl?pageIndex=1&cID=&price=0-100" class="form-check-label" for="amado" id="amado" name="price">Under $100</a>
                                </li>
                                <li>
                                    <a href="SortpriceControl?pageIndex=1&cID=&price=100-300" class="form-check-label" for="amado" id="amado" name="price">$100 - $300</a>
                                </li>
                                <li>
                                    <a href="SortpriceControl?pageIndex=1&cID=&price=300-500" class="form-check-label" for="amado" id="amado" name="price">$300 - $500</a>
                                </li>
                                <li>
                                    <a href="SortpriceControl?pageIndex=1&cID=&price=500-1000" class="form-check-label" for="amado" id="amado" name="price">$500 - $1000</a>
                                </li>
                                <li>
                                    <a href="SortpriceControl?pageIndex=1&cID=&price=1000-3000" class="form-check-label" for="amado" id="amado" name="price">$1000 +</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>


        <div class="amado_product_area section-padding-100">
            <%
                                                  Account acc = (Account) session.getAttribute("acc");
                                                  String email = "";
                                                  if (acc != null) {
                                                  email = acc.getEmail();
                                                                   }
            %>

            <%-- Hiển thị tên người dùng nếu đã đăng nhập --%>
            <div class="username" style="margin-left: 65%">
                <% if (!email.isEmpty()) { %>
                <p>Hello, <%= email %>!</p>
                <% } else { %>
                <p>Please log in to access this page.</p>
                <% } %>
            </div>
            <div class="container-fluid">

                <div class="row">
                    <div class="col-12">
                        <div class="product-topbar d-xl-flex align-items-end justify-content-between">
                            <!-- Search Form -->
                            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                            <form id="searchForm" action="SearchControl" method="get">
                                <input name="txt" type="text" placeholder="Search by name" value="${txtSearch}">
                                <select name="cID" ${showAllProducts ? 'disabled' : ''}>
                                    <c:choose>
                                        <c:when test="${empty cID || showAllProducts}">
                                            <option selected disabled hidden>All Products</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option disabled hidden>All Products</option>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach var="category" items="${listC}">
                                        <c:if test="${!empty cID && category.id eq cID}">
                                            <option value="${category.id}"selected>${category.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <button type="submit">Search</button>
                            </form>
                            <!-- Hiển thị thông báo kết quả -->
                            <c:if test="${not empty resultCount}">
                                <p>Found ${resultCount} product(s).</p>
                            </c:if>
                            <c:if test="${not empty resultCount}">
                                <p>${errorMessage}</p>
                            </c:if>
                            <!-- Hiển thị thông báo lỗi nếu có -->
                            <c:if test="${empty resultCount}">
                                <p>${message}</p>
                            </c:if>
                            <!-- Sorting -->
                            <div class="product-sorting d-flex">
                                <div class="sort-by-date d-flex align-items-center mr-15">
                                    <p>Sort</p>
                                    <form action="Sort" method="get">
                                        <label for="sortOption">Sort by:</label>
                                        <select name="sortOption" id="sortOption">
                                            <option value="AZ">A-Z</option>
                                            <option value="ZA">Z-A</option>
                                            <option value="asc">Price (Low to High)</option>
                                            <option value="des">Price (High to Low)</option>
                                            <option value="new">Newest Product</option>
                                        </select>
                                        <button type="submit" class="btn btn-primary">Sort</button>
                                    </form>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>
                <!-- Đoạn này để hiển thị danh sách sản phẩm -->
                <div id="content" class="row">
                    <c:forEach items="${listP}" var="o">
                        <!-- Single Product Area -->
                        <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                            <div class="single-product-wrapper">
                                <!-- Product Image -->
                                <div class="product-img">
                                    <img src="${o.image}" alt="">
                                    <!-- Hover Thumb -->
                                    <!--<img class="hover-img" src="img/product-img/product5.jpg" alt="">-->
                                </div>

                                <!-- Product Description -->
                                <div class="product-description d-flex align-items-center justify-content-between">
                                    <!-- Product Meta Data -->
                                    <div class="product-meta-data">
                                        <div class="line"></div>
                                        <p class="product-price">$${o.price}</p>
                                        <a href="productDetail?productID=${o.id}">
                                            <h6>${o.name}</h6>
                                        </a>
                                    </div>
                                    <!-- Ratings & Cart -->
                                    <div class="ratings-cart text-right">
                                        <div class="ratings">
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                        </div>
                                        <div class="cart">
                                            <c:if test="${o.amount != 0}">
                                                <a href="cart?id=${o.id}&action=add" data-toggle="tooltip" data-placement="left" title="Add to Cart">
                                                    <img src="img/core-img/cart.png" alt="">
                                                </a>
                                                <a href="Shop.jsp" style='font-size:16px;' title="Add to favourite">&#129505;</a>
                                            </c:if>
                                            <c:if test="${o.amount == 0}">
                                                <a href="ShopControl" data-toggle="tooltip" data-placement="left">Sold out</a>
                                                <a href="FavouriteControl" style='font-size:16px;' title="Add to favourite">&#129505;</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="row">
                    <div class="col-12">
                        <!-- Pagination -->
                        <nav aria-label="navigation">
                            <ul class="pagination justify-content-end mt-50" id="paging">
                                <c:forEach begin="1" end="${totalPage}" var="i">
                                    <c:url var="pageURL" value="">
                                        <c:param name="pageIndex" value="${i}" />
                                        <!-- Kiểm tra showAllProducts để thêm tham số cID khi cần -->
                                        <c:if test="${!showAllProducts}">
                                            <c:param name="cID" value="${cID}" />
                                            <c:param name="price" value="${price}" />
                                        </c:if>
                                        <c:if test="${showAllProducts==true}">
                                            <c:param name="showAllProducts" value="${showAllProducts}" />
                                        </c:if>
                                    </c:url>
                                    <li class="page-item ${pageIndex == i ? 'active' : ''}">
                                        <a class="page-link" href="${pageURL}">
                                            ${i}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" id="categoryID" value="${cID}"/>
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
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <!-- Thêm script để thực hiện chức năng tìm kiếm -->

    <script>
        function searchByName() {
            var txtSearch = document.getElementById("txtSearch").value;
            var cID = document.getElementById("categoryID").value;
            var sort = document.getElementById("sortByPrice").value;
            console.log(txtSearch);
            $.ajax({
                url: "/Project_KiaShop/search",
                type: "get", //send it throung get method
                data: {
                    txt: txtSearch,
                    cID: cID,
                    sort: sort
                },
                success: function (data) {
                    var row = document.getElementById("content");
                    row.innerHTML = data.product;
                    var row2 = document.getElementById("paging");
                    row2.innerHTML = data.paging;
                    var row3 = document.getElementById("categoryMenu");
                    row3.innerHTML = data.categoryMenu;
                    var row4 = document.getElementById("formSort");
                    row4.innerHTML = data.sortJson;
                },
                error: function (xhr) {
                    //do something to handle error
                }
            });
        }
    </script>
</body>
</html>