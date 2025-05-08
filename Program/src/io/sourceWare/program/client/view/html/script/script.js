// script.js
console.log("script.js started");

// Function to add options to the sidebar
function addOptionsBar(arr, elemId) {
  arr.forEach((element, id) => {
    let button = document.createElement("div");
    if (element.text) button.innerText = element.text;
    if (element.img) {
      const img = document.createElement("img");
      img.src = element.img;
      button.appendChild(img);
    }

    button.classList.add("button_sidebar_right", "centered", "square_button", "button_hover");
    document.getElementById(elemId).appendChild(button);
  });
}

// Function to populate contacts
// todo: fix this 
function populateContacts(arr) {
  arr.forEach((elem, id) => {
    let contact = document.createElement("div");
    contact.classList.add("contact_div", "debug");

    let contact_img = document.createElement("div");
    contact_img.classList.add("contact_img");

    let contact_info = document.createElement("div");
    contact_info.classList.add("contact_info_div");

    if (elem.description) contact_info.innerText = elem.description;
    if (elem.img) contact_img.src = elem.img;

    if (elem.onClick) {
      contact.onclick = elem.onClick;
      contact.classList.add("pointer_cursor");
    }

    if (elem.redirectUrl) {
      contact.onclick = function () {
        window.location = elem.redirectUrl;
      };
      contact.classList.add("pointer_cursor");
    }

    contact.appendChild(contact_img);
    contact.appendChild(contact_info);
    document.getElementById("main_div").appendChild(contact);
  });
}

// Test the communication with the main process
console.log(window.electron.sayHello());
console.log("Listening for JSON data...");

// Listen for the JSON data from the main process
window.electron.onJsonReceived((data) => {
  console.log("Received JSON data:", data);
  let myArrayLeft = data.left_bar;
  let myArrayRight = data.right_bar;
  let contacts = data.contacts;

  addOptionsBar(myArrayLeft, "left_bar");
  addOptionsBar(myArrayRight, "right_bar");
  populateContacts(contacts);
});
