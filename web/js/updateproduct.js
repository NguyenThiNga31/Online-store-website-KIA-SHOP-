/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



                                            document.getElementById("UpdateProductForm").addEventListener("submit", function (event) {
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
                                                if (price === "") {
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