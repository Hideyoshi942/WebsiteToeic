const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/auth': {
        target: 'http://localhost:8080',  // Địa chỉ backend API
        changeOrigin: true,
        secure: false,
        pathRewrite: {
          '^/auth': ''  // Loại bỏ prefix nếu cần
        }
      }
    }
  }
});
