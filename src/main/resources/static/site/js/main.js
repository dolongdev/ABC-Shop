(function ($) {
    "use strict";
    
    // Dropdown on mouse hover
    $(document).ready(function () {
        function toggleNavbarMethod() {
            if ($(window).width() > 992) {
                $('.navbar .dropdown').on('mouseover', function () {
                    $('.dropdown-toggle', this).trigger('click');
                }).on('mouseout', function () {
                    $('.dropdown-toggle', this).trigger('click').blur();
                });
            } else {
                $('.navbar .dropdown').off('mouseover').off('mouseout');
            }
        }
        toggleNavbarMethod();
        $(window).resize(toggleNavbarMethod);
    });
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Vendor carousel
    $('.vendor-carousel').owlCarousel({
        loop: true,
        margin: 29,
        nav: false,
        autoplay: true,
        smartSpeed: 1000,
        responsive: {
            0:{
                items:2
            },
            576:{
                items:3
            },
            768:{
                items:4
            },
            992:{
                items:5
            },
            1200:{
                items:6
            }
        }
    });


    // Related carousel
    $('.related-carousel').owlCarousel({
        loop: true,
        margin: 29,
        nav: false,
        autoplay: true,
        smartSpeed: 1000,
        responsive: {
            0:{
                items:1
            },
            576:{
                items:2
            },
            768:{
                items:3
            },
            992:{
                items:4
            }
        }
    });


    // Product Quantity
    $('.quantity button').on('click', function () {
        var button = $(this);
        var oldValue = button.parent().parent().find('input').val();
        if (button.hasClass('btn-plus')) {
            var newVal = parseFloat(oldValue) + 1;
        } else {
            if (oldValue > 0) {
                var newVal = parseFloat(oldValue) - 1;
            } else {
                newVal = 0;
            }
        }
        button.parent().parent().find('input').val(newVal);
    });
    
})(jQuery);


function checkboxFunc(id) {
    if (this.checked){
        this.window.location = 'youtube.com';
    }

}

//cart
function addToCart(productId, productName, price){
    event.preventDefault();

    fetch("/api/cart", {
        method : 'post',
        body : JSON.stringify({
            "productId" : productId,
            "productName" : productName,
            "price" : price,
            "quantity" : 1
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res){
        return res.json();
    }).then(function(data){
        let counter = document.getElementById("cartCounter");
        counter.innerText = data;
    })
}

function updateCart(obj, productId){
    fetch("/api/cart", {
        method : 'put',
        body : JSON.stringify({
            "productId" : productId,
            "productName" : "",
            "price" : 0,
            "quantity" : obj.value
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res){
        return res.json();
    }).then(function(data){
        let counter = document.getElementById("cartCounter");
        counter.innerText = data;
    })
}

function deleteCart(productId){
    fetch(`/api/cart/${productId}`, {
        method : 'delete'
    }).then(function(res){
        return res.json();
    }).then(function(data){
        let counter = document.getElementById("cartCounter");
        counter.innerText = data;
        // location.reload();
        let row = document.getElementById(`product${productId}`)
        row.style.display = "none";
    })
}

function reloadPage(){
    location.reload();
}

function checkout() {
    fetch('/checkout', {
        method : 'get'
    }).then(function(res){
        return res.json();
    }).then(function(data){
    })
}

function pay() {
    fetch('/checkout', {
        method : 'post'
    }).then(function(res){
        return res.json();
    }).then(function(code){
        console.info(code)
    })

    console.log("Đặt hàng thành công!");
}