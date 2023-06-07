

$(document).ready(function() {
    //listen to click event for button delete
    $(".btn-delete-role").click(function(){
    var id = $(this).attr("roleid")
    // luu bien this cua btn lai boi vi xuong duoi no se cua thang khac
    var This = $(this)
    $.ajax({
      method: "GET",
      url: "http://localhost:8080/demoservlet/role/delete?roleid=" + id,
    }).done(function( result ) {
        This.closest("tr").remove();
        console.log("Result", result)
      });
    })
})