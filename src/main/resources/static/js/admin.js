let menuToggle = document.querySelector('.toggle');
let navigation = document.querySelector('.navigation');
let main = document.querySelector('.main');
    menuToggle.onclick = function (){
        menuToggle.classList.toggle('active');
        navigation.classList.toggle('active');
        main.classList.toggle('active');
    }

let list = document.querySelectorAll('.list');
for(let i=0;i<list.length;i++){
    list[i].onmouseover = function (){
        let j=0;
        while (j<list.length){
            list[j++].className = 'list';
        }
        list[i].className = 'list active';
    }
}

