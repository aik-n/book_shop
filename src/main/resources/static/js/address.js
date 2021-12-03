var i=0;

//删除地址
function deleteAddress(obj){
    var index = $(".delete").index(obj);
    var id = $(".id").eq(index).text();
    if(confirm("是否确定要删除？")){
        alert("删除成功");
        window.location.href = "/user/removeAddress/"+id;
    }
}

//更新地址
var index = null;
var id = null;
var address=null;
function updateAddress(obj){
    $(".address1").show();
    $(".addressBtn").show();
    index=$(".delete").index(obj);
    id=$(".id").eq(index).text();
}
function sure(){

    address=$(".address1").val();
    $(".address1").hidden;
    $(".addressBtn").hidden;
    window.location.href = "/user/updateAddress/"+id+"/"+address;
}