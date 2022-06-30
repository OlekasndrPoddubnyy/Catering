const pwShowHide = document.querySelector(".showHidePw"),
    pwFields = document.querySelector(".password");

//   js code to show/hide password and change icon
const showHidePW = () => {
    if(pwFields.type ==="password"){
        pwFields.type = "text";

        pwShowHide.classList.replace("uil-eye-slash", "uil-eye");
    }else{
        pwFields.type = "password";

        pwShowHide.classList.replace("uil-eye", "uil-eye-slash");
    }
};

pwShowHide.addEventListener("click", showHidePW);