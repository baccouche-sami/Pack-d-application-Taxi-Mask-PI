

module.exports = {
  devServer: {
    port: 8081,
    disableHostCheck: true,
    public: '0.0.0.0:8081'
  },
  publicPath: '/',
  transpileDependencies: [
    'vue-echarts',
    'resize-detector'
  ],
  configureWebpack: {
    optimization: {
      splitChunks: {
        chunks: 'all'
      }
    }
  },
  css: {
    loaderOptions: {
      sass: {

      }
    }
  }
}

