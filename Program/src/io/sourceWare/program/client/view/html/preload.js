import { contextBridge, ipcRenderer } from 'electron';

contextBridge.exposeInMainWorld('electron', {
  sayHello: () => 'Hello from the main process!',
  onJsonReceived: (callback) => ipcRenderer.on('json-received', (event, data) => callback(data)),
});
