module.exports = {
  "devServer": {
    "proxy": {
      "/api": {
        "target": "http://localhost:7071",
        "ws": true,
        "changeOrigin": true
      }
    }
  },
  "outputDir": "target/dist",
  "assetsDir": "static",
  transpileDependencies: [
    'vuetify'
  ]
}
