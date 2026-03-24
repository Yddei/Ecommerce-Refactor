require('dotenv').config();
const path = require('path');
const port = process.env.NODE_PORT;
const express = require('express');
const app = express();

const authRoutes = require('./modules/auth/auth.routes.js')


app.use(express.json());
app.use(express.static('public'));

//mounting routes
app.use('/api/auth', authRoutes);

//get pages
app.get('/login', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'login.html'));
});

//Run
app.listen(port, ()=> console.log('node.js server started on port ' + port));