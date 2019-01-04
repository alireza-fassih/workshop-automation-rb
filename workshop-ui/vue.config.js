module.exports = {
    devServer: {
        port: 3000,
        proxy: [{
            path: '^/rest/*',
            target: 'http://localhost:8080'
        }]
    }
}
  