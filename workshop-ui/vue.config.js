module.exports = {
    devServer: {
        port: 3000,
        proxy: [{
            path: '^/rest/*',
            headers: {
                'Workshop-app': 'demo'
            },
            target: 'http://localhost:8080'
        }]
    }
}
  