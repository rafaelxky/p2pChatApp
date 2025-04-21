const http = require('http');

const PORT = 7172;

const server = http.createServer((req, res) => {
  if (req.method === 'POST' && req.headers['content-type'] === 'application/json') {
    let body = '';

    req.on('data', chunk => {
      body += chunk.toString(); // Convert Buffer to string
    });

    req.on('end', () => {
      try {
        const jsonData = JSON.parse(body);
        console.log('Received JSON:', jsonData);
        console.log(jsonData.message);

        res.writeHead(200, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify({ status: 'success', received: jsonData }));
      } catch (err) {
        res.writeHead(400, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify({ status: 'error', message: 'Invalid JSON' }));
      }
    });
  } else {
    res.writeHead(404, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({ status: 'error', message: 'Not found' }));
  }
});

server.listen(PORT, 'localhost', () => {
  console.log(`Server listening on http://localhost:${PORT}`);
});
