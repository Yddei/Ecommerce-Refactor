require('dotenv').config();
const path = require('path');
const node_port = process.env.NODE_PORT;
const spring_boot_port = process.env.SPRING_BOOT_PORT;
const express = require('express');
const app = express();

const {createProxyMiddleware} = require('http-proxy-middleware');


app.use(createProxyMiddleware({
    pathFilter: '/api',
    target: `http://ebookshop-api:${spring_boot_port}`,
    changeOrigin: true,
    pathRewrite: (path, req) => path, 
    onProxyReq: (proxyReq, req, res) => {
        console.log(`[Proxying] ${req.method} ${req.url} -> http://ebookshop-api:${spring_boot_port}${req.url}`);
    },
    onError: (err, req, res) => {
        console.error("Proxy Error:", err);
    }
}));
app.use(express.json());
app.use(express.static('public'));


//get pages
app.get('/home', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'home.html'));
});

app.get('/login', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'login.html'));
});

app.get('/register', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'register.html'));
});







//Run
app.listen(node_port, ()=> console.log('node.js server started on port ' + node_port));