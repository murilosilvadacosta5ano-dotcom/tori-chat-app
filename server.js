const express = require("express");
const app = express();

app.use(express.json());

// SEU TOKEN PRIVADO
const TOKEN = "TORI_SECRET_123";

function auth(req, res, next) {
    const token = req.headers["authorization"];
    
    if (!token || token !== `Bearer ${TOKEN}`) {
        return res.status(401).json({ error: "unauthorized" });
    }
    
    next();
}

app.post("/ai", auth, (req, res) => {
    const msg = req.body.message;

    const response = "Tori: " + msg;

    res.json({ response });
});

app.listen(8000, "0.0.0.0", () => {
    console.log("IA rodando na porta 8000");
});
