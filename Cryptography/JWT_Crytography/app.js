const express = require('express');
const jwt = require('jsonwebtoken');

const app = express();
const PORT = 3000;
const JWT_SECRET = 'your_secret_key';

// Middleware to parse JSON request bodies
app.use(express.json());

// Generate a new JWT
app.post('/login', (req, res) => {
    const { username, password } = req.body;

    // Replace this with your actual authentication logic
    if (username === 'admin' && password === 'rumman4567') {
        const token = jwt.sign({ username }, JWT_SECRET, { expiresIn: '1h' });
        res.json({ token });
    } else {
        res.status(401).json({ error: 'Invalid credentials' });
    }
});

// Protected route that requires a valid JWT
app.get('/protected', (req, res) => {
    const authHeader = req.headers.authorization;

    if (authHeader) {
        const token = authHeader.split(' ')[1];

        jwt.verify(token, JWT_SECRET, (err, decoded) => {
            if (err) {
                res.status(403).json({ error: 'Invalid or expired token' });
            } else {
                res.json({ message: 'Access granted', decoded });
            }
        });
    } else {
        res.status(401).json({ error: 'Authorization header missing' });
    }
});

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});