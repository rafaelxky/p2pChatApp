// JavaScript
function toggleMenu() {
  const menu = document.getElementById('side-menu');
  menu.classList.toggle('open');
}

function addOptionsBar(arr, elemId){
  arr.forEach((element, id) => {
    
  let button = document.createElement("div");
  if (element.text)
  button.innerText = element.text;
  if (element.img){
    const img = document.createElement("img");
    img.src = element.img;
    button.appendChild(img);
  }

  button.classList.add("button_sidebar_right", "centered", "button_hover");
  document.getElementById(elemId).appendChild(button);
  });
}

function createElemObj(){
  return {
    text: undefined,
    img: undefined,

    withText: function(textIn){
      this.text = textIn;
      return this;
    },
    withImage: function(imgIn){
      this.img = imgIn
      return this;
    },
  }
}

function populateMainDiv(arr){
  arr.forEach((elem, id) => {
    let contact = document.createElement("div");
    document.getElementById("main_div").appendChild()
  });
}

let myArray = [createElemObj().withText(1),createElemObj().withText(2)];
addOptionsBar(myArray, "left_bar");
addOptionsBar(myArray, "right_bar");