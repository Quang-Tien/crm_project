// khi nào html content đã được load lên browser
// thì sẽ chạy code bên trong function
$(document).ready(function() {
    //listen to click event for button delete
    $(".btn-delete-user").click(function(){
    var id = $(this).attr("userid")
    // luu bien this cua btn lai boi vi xuong duoi no se cua thang khac
    var This = $(this)
    $.ajax({
      method: "GET",
      url: "http://localhost:8080/demoservlet/user/delete?userid=" + id,
    }).done(function( result ) {
        This.closest("tr").remove();
        console.log("Result", result)
      });
    })
})