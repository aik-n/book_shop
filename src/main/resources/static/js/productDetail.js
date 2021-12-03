// $(function(){
//     //给type绑定点击事件
//     $(".type").click(function () {
//         var index = $(".type").index(this);
//         var obj = $(".type").eq(index);
//         $(".type").removeClass("checked");
//         obj.addClass("checked");
//     });
//     //给color绑定点击事件
//     $(".color").click(function () {
//         var index = $(".color").index(this);
//         var obj = $(".color").eq(index);
//         $(".color").removeClass("checked");
//         obj.addClass("checked");
//     });
// });

//商品数量++
function increase() {
    var value = $("#quantity").val();
    var stock = $("#stock").text();
    value++;
    if(value > stock){
        value = stock;
    }
    $("#quantity").val(value);
}

//商品数量--
function reduce() {
    var value = $("#quantity").val();
    value--;
    if(value <= 0){
        value = 1;
    }
    $("#quantity").val(value);
}

//添加购物车
function addCart(bookId,bookPrice){
    var stock=parseInt($("#stock").val());
    // var bookId = $("#bookId").val();
    // var bookPrice = $("#bookPrice").val();
    // alert(bookId);
    // alert(bookPrice);
    var quantity = $("#quantity").val();
    // alert(quantity);

    if (stock==0){
        alert("库存不足，无法购买");
        return false;
    }

    window.location.href="/cart/add/"+bookId+"/"+bookPrice+"/"+quantity;
}