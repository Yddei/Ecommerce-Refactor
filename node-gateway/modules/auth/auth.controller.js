require('dotenv').config({ path: require('path').resolve(__dirname, '../.env') });
const axios = require('axios');

exports.register = async(req,res)=>{
    try {
        console.log(`${process.env.BACKEND_URL}`)
        const response = await axios.post(`${process.env.BACKEND_URL}/api/auth/register`, req.body)
        res.status(response.status).json(response.data);
    }
    catch(err) {
        if(err.response) {
            res.status(err.response.status).json(err.response.data)
        }
        else {
            res.status(500).json({error: "backend unreachable"});
            console.log('Server Unreachable')    
        };
    }
}

exports.login = async(req,res)=>{
    try {
        console.log(`${process.env.BACKEND_URL}`)
        const response = await axios.post(`${process.env.BACKEND_URL}/api/auth/login`, req.body)
        res.status(response.status).json(response.data);
    }
    catch(err) {
        if(err.response) {
            res.status(err.response.status).json(err.response.data)
        }
        else {
            res.status(500).json({error: "backend unreachable"});
            console.log('Server Unreachable')    
        };
    }
}