<%-- 
    Document   : ProductDetail
    Created on : October 24, 2023, 2:09:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.SizeDetail"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="java.util.List" %>
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
        <title>KiA Shop - Be bad,be good, be yourself | Product Details</title>

        <!-- Favicon  -->
        <link rel="icon" href="img/core-img/favicon2.ico">

        <!-- Core Style CSS -->
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="css/core-style.css">
        <style>
            .container {
                display: flex;
                flex-wrap: wrap;
                /*                justify-content: center;  Canh giữa các ô vuông theo chiều ngang */
            }
            .square {
                width: 50px;
                height: 50px;
                border: 1px solid black;
                margin: 5px;
                display: flex;
                justify-content: center; /* Canh giữa nội dung theo chiều ngang */
                align-items: center; /* Canh giữa nội dung theo chiều dọc */
                text-decoration: none;
                color: black;
                font-weight: bold;
                line-height: 50px; /* Chiều cao của ô vuông */
                text-align: center; /* Canh giữa văn bản theo chiều ngang */
            }
        </style>
        <!-- <link rel="stylesheet" href="style.css"> -->
    </head>
    <body>
        <!-- Search Wrapper Area Start -->
        <jsp:include page="common/search.jsp"></jsp:include>
            <!-- Search Wrapper Area End -->

            <!-- ##### Main Content Wrapper Start ##### -->
        <jsp:include page="common/header.jsp"></jsp:include>
            <!-- Header Area End -->

            <!-- Product Details Area Start -->
            <div class="single-product-area section-padding-100 clearfix">
                <div class="container-fluid">

                    <div class="row">
                        <div class="col-12">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb mt-50">
                                    <li class="breadcrumb-item"><a href="ShopControl?showAllProducts=true">Shop</a></li>
                                    <li class="breadcrumb-item"><a href="ShopControl?pageIndex=1&cID=${o.cid}">${cateName}</a></li>
                                <li class="breadcrumb-item active" aria-current="page">${detail.name}</li>
                            </ol>
                        </nav>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12 col-lg-7">
                        <div class="single_product_thumb">
                            <div id="product_details_slider" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">
                                    <li class="active" data-target="#product_details_slider" data-slide-to="0" style="background-image: url(${image0}); background-repeat: no-repeat; background-size: 100% 100%;">
                                    </li>
                                    <li data-target="#product_details_slider" data-slide-to="1" style="background-image: url(${image1}); background-repeat: no-repeat; background-size: 100% 100%;">
                                    </li>
                                    <li data-target="#product_details_slider" data-slide-to="2" style="background-image: url(${image2}); background-repeat: no-repeat; background-size: 100% 100%;">
                                    </li>
                                    <li data-target="#product_details_slider" data-slide-to="3" style="background-image: url(${image3}); background-repeat: no-repeat; background-size: 100% 100%;">
                                    </li>
                                </ol>
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <a class="gallery_img" href="${image0}">
                                            <img class="d-block w-100" src="${image0}" alt="First slide">
                                        </a>
                                    </div>
                                    <div class="carousel-item">
                                        <a class="gallery_img" href="${image1}">
                                            <img class="d-block w-100" src="${image1}" alt="Second slide">
                                        </a>
                                    </div>
                                    <div class="carousel-item">
                                        <a class="gallery_img" href="${image2}">
                                            <img class="d-block w-100" src="${image2}" alt="Third slide">
                                        </a>
                                    </div>
                                    <div class="carousel-item">
                                        <a class="gallery_img" href="${image3}">
                                            <img class="d-block w-100" src="${image3}" alt="Fourth slide">
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-lg-5">
                        <div class="single_product_desc">
                            <!-- Product Meta Data -->
                            <div class="product-meta-data">
                                <div class="line"></div>
                                <p class="product-price">$${detail.price}</p>
                                <a>
                                    <h6>${detail.name}</h6>
                                </a>
                                <!-- Ratings & Review -->
                                <div class="ratings-review mb-15 d-flex align-items-center justify-content-between">
                                    <div class="ratings">
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                    </div>
                                    <div class="review">
                                        <a href="#">Write A Review</a>
                                    </div>
                                </div>
                                <!-- Avaiable -->
                                <p class="avaibility"><i class="fa fa-circle"></i> In Stock</p>
                                <h8>Size: </h8> 
                                <div class="container">
                                    <% 
                                    List<SizeDetail> sizes = (List<SizeDetail>) request.getAttribute("sizes");
                                    if (sizes != null) {
                                        for (SizeDetail size : sizes) {
                                       if(size.getQuantity() != 0) { 
                                    %>
                                    <a href="#" class="square" data-size="<%= size.getSizeID() %>" onclick="chooseSize(<%= size.getSizeID() %>)"/>
                                    <%= size.getSizevalue() %>
                                    </a>
                                    <% 
                                        }
                                      }
                                    }
                                    %>
                                </div>
                            </div>

                            <div class="short_overview my-5">
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid quae eveniet culpa officia quidem mollitia impedit iste asperiores nisi reprehenderit consequatur, autem, nostrum pariatur enim?</p>
                            </div>
                            <form class="cart clearfix" method="post" action="">
                                <input type="hidden" id="size_shoes" />
                                <input type="hidden" id="size_value_shoes" />
                                <c:if test="${detail.amount != 0}">
                                    <a href="#" onclick="addToCart(`${detail.id}`)"><button type="button" name="addtocart" value="${detail.id}&${size.getSizevalue()}" class="btn amado-btn">Add to cart</button></a>
                                </c:if>
                            </form>

                            <form action="loadMore" method="post">
                                <button type="submit" class="loadMore">Load More</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Product Details Area End -->
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
    <style>
        .loadMore{
            display: inline-block;
            min-width: 160px;
            height: 55px;
            color: #ffffff;
            border: none;
            border-radius: 0;
            padding: 0 7px;
            font-size: 18px;
            line-height: 56px;
            background-color: #fbb710;
            font-weight: 400;
            margin-top: 10px;
        }
        .loadMore.active,
        .loadMore:hover,
        .loadMore:focus {
            font-size: 18px;
            color: #ffffff;
            background-color: #131212;
        }
        .loadMore{
            width: 160px;
            display: block;
        }
        .loadMore{
            width: 310px;
            height: 80px;
            line-height: 80px;
        }
        @media only screen and (min-width: 992px) and (max-width: 1199px) {
            .loadMore{
                width: 250px;
            }
        }
        @media only screen and (max-width: 767px) {
            .loadMore{
                width: 280px;
            }
        }
    </style>
    <script type="text/javascript">
                                        function chooseSize(size) {
                                            var sizeInput = document.getElementById("size_shoes");
                                            sizeInput.value = size;
// Remove "active" class from all links
                                            var allLinks = document.querySelectorAll('a[data-size]');
                                            allLinks.forEach(function (link) {
                                                link.classList.remove("active");
                                            });

                                            // Add "active" class to the selected link
                                            var activeSize = document.querySelector('a[data-size="' + size + '"]');
                                            if (activeSize) {
                                                activeSize.classList.add("active");
                                            }

                                        }

                                        function addToCart(id) {
                                            var size = document.getElementById("size_shoes").value;
                                            if (size) {
                                                window.location.href = "cart?id=" + id + "&sizeId=" + size + "&action=add";
                                            } else {
                                                alert("Please choose size");
                                            }
                                        }
    </script>
</body>
</html>
