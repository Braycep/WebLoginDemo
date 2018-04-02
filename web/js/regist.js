function refresh(obj) {
    obj.src = "img?time=" + new Date().getTime();
}

function doRefresh() {
    document.getElementById("authImg").src="img?time=" + new Date().getTime();
}

function checkPassword() {
    var checkbox = document.getElementById("checkbox");
    var checkbox_text = document.getElementById("checkbox_text");
    if (checkbox.checked){
        checkbox_text.style="border:none";
        var pass = document.getElementById("password");
        var re_pass = document.getElementById("re-password");
        if (pass.value != re_pass.value){
            pass.style="border:1px #F17C67 dashed"
            re_pass.style="border:1px #F17C67 dashed"
            return false;
        }else{
            pass.style="border:1px #dddddd solid"
            re_pass.style="border:1px #dddddd solid"
            return true;
        }
    }else {
        checkbox_text.style="border:1px #F17C67 solid";
        return false;
    }
}

function check() {
    var checkbox = document.getElementById("checkbox");
    var checkbox_text = document.getElementById("checkbox_text");
    if (checkbox.checked){
        checkbox_text.style="border:none";
    } else {
        checkbox_text.style="border:1px #F17C67 solid";
    }
}