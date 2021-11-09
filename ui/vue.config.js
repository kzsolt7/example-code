module.exports = {
    "devServer": {
        "proxy": {
            "/api": {
                "target": "http://localhost:7070",
                "ws": true,
                "changeOrigin": true
            }
        }
    },
    "outputDir": "target/dist",
    "assetsDir": "static",
}