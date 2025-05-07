// preload.js
const { contextBridge, ipcRenderer } = require('electron');

// Expose specific functionalities to the renderer process
contextBridge.exposeInMainWorld('electron', {
  // Simple function to send a message to the main process
  sayHello: () => 'Hello from the main process!',

  // Function to listen for incoming JSON data from the main process
  onJsonReceived: (callback) => ipcRenderer.on('json-received', (event, data) => callback(data)),
});
