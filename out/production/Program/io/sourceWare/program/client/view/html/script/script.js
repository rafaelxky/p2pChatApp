// JavaScript
// script.js
let hoverClass =  "inset_hover";
hoverClass = "button_hover";

console.log("script.js started");

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

  // html sidebar button classes
  button.classList.add("button_sidebar_right", "centered","square_button", hoverClass);
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

function createContact(){
  return{
    description: undefined,
    img: undefined,
    contact: undefined,
    onClick: undefined,
    redirectUrl: undefined,

    withImage: function(imgUrl){
      this.img = imgUrl;
      return this;
    },
    withDescription: function(desc){
      this.description = desc;
      return this;
    },
    withContact: function(info){
      this.contact = info;
      return this;
    },
    withClickEvent: function(func){
      this.onClick = func;
      return this;
    },
    withRedirectUrl: function(url){
      this.redirectUrl = url;
      return this;
    }
  }
}

function populateContacts(arr){
  arr.forEach((elem, id) => {
    let contact = document.createElement("div");
    contact.classList.add("contact_div", "debug"); 

    let contact_img = document.createElement("div")
    contact_img.classList.add("contact_img");

    let contact_info = document.createElement("div");
    contact_info.classList.add("debug1" + "contact_info_div");

    if (elem.description){
      contact_info.innerText = elem.description; 
    }
    if (elem.img){
      contact_img.src = elem.img;
    }
    if (elem.contact){

    }
    if (elem.onClick){
      contact.onclick = elem.onClick;
      contact.classList.add("pointer_cursor");
    }
    if (elem.redirectUrl){
      contact.onclick = function(){
        window.location = elem.redirectUrl;
      }
      contact.classList.add("pointer_cursor");
    }

    contact.appendChild(contact_img);
    contact.appendChild(contact_info);
    document.getElementById("main_div").appendChild(contact);
  });
}

console.log(window.electron.sayHello());
console.log("Listening");

window.electron.onJsonReceived((data) => {
  console.log("Received JSON data:", data);
  let myArrayleft = data.left_bar;
  let myArrayRight = data.right_bar;

  addOptionsBar(myArrayleft, "left_bar");
  addOptionsBar(myArrayRight, "right_bar");
});

let contactsArr = [createContact().withDescription("Contact 1").withClickEvent(() => console.log("Hello!!!")), createContact().withDescription("Contact 2").withRedirectUrl("contact.html")];
populateContacts(contactsArr);
