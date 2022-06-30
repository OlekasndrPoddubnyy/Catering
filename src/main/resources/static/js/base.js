const navSlide = function () {
    const hamburger = document.querySelector('.hamburger');
    const nav = document.querySelector('.nav-links');


    hamburger.addEventListener('click', function () {
        this.classList.toggle('is-active');
        nav.classList.toggle('nav-active');

    });
}

navSlide();