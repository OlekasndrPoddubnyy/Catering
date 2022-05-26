const hamburger = document.querySelector('.hamburger');
const nav = document.querySelector('.nav-links');
const navLinks = document.querySelectorAll('.nav-links li');



    hamburger.addEventListener('click', function () {
        this.classList.toggle('is-active');
        nav.classList.toggle('nav-active');
    });