$(function(){
    //计算总价
    var array = $(".span1");
    // alert(array);
    var totalCost = 0;
    for(var i = 0;i < array.length;i++){
        var val = parseFloat($(".span1").eq(i).text()).toFixed(2);
        // alert(val);
        totalCost += val;
         $(".span1").eq(i).text(val);
    }
    $("#totalprice").html("￥"+parseFloat(totalCost).toFixed(2));
    //settlement2使用
    $("#settlement2_totalCost").val(parseFloat(totalCost).toFixed(2));
});

//商品数量++
function addQuantity(obj){

    var index = $(".car_btn_2").index(obj);
    var quantity = parseInt($(".car_ipt").eq(index).val());

    var number=parseInt($(".productStock").eq(index).val());
    if (quantity>=number){
        alert("库存不足！");
    }else{
        quantity++;
        $(".car_ipt").eq(index).val(quantity);
    }

    var price = parseFloat($(".productPrice").eq(index).val()).toFixed(1);
    var id = $(".id").eq(index).val();
    var cost = (quantity*price).toFixed(1)


    $(".span1").eq(index).text(cost);

    var array = $(".span1");
    var totalCost = 0;
    for(var i = 0;i < array.length;i++){
        var val = parseFloat($(".span1").eq(i).text());
        totalCost += val;
    }
    $("#totalprice").html("￥"+totalCost.toFixed(2));

    $.ajax({
        url:"/cart/updateCart/"+id+"/"+quantity+"/"+cost,
        type:"POST",
        dataType:"text",
        // success:function (data) {
        //     if(data == "success"){
        //         //更新toSettlement的数据
        //         // $(".qprice").eq(index).html("￥"+cost);
        //         // inputObj.val(quantity);
        //         // if(quantity < stock){
        //         //     var totalCost = parseInt($("#totalprice").html().substring(1));
        //         //     totalCost += price;
        //         //     $("#totalprice").html("￥"+totalCost);
        //         // }
        //     }
        // }
    });
}

//商品数量--
function subQuantity(obj){

    var index = $(".car_btn_1").index(obj);
    var quantity = parseInt($(".car_ipt").eq(index).val());
    if (quantity==1){
        alert("至少购买一件商品！");
    }else{
        quantity--;
        $(".car_ipt").eq(index).val(quantity);
    }
    var price = parseFloat($(".productPrice").eq(index).val()).toFixed(1);
    var id = $(".id").eq(index).val();
    var cost = (quantity*price).toFixed(1);
    $(".span1").eq(index).text(cost);

    var array = $(".span1");
    var totalCost = 0;
    for(var i = 0;i < array.length;i++){
        var val = parseFloat($(".span1").eq(i).text());
        totalCost += val;
    }
    $("#totalprice").html("￥"+totalCost.toFixed(2));

    $.ajax({
        url:"/cart/updateCart/"+id+"/"+quantity+"/"+cost,
        type:"POST",
        dataType:"text",
    });
}

//移出购物车
function removeCart(obj){
    var index = $(".delete").index(obj);
    var id = $(".id").eq(index).val();
    if(confirm("是否确定要删除？")){
        window.location.href = "/cart/removeCart/"+id;
    }

}
//判断购物车是否为空
function settlement2() {
    var cost = $("#totalprice").text();
    if (cost=="￥0"){
        alert("购物车为空，不能购买");
    }else{
        window.location.href="/cart/settlement2";
    }

}