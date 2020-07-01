


/*

module.exports={


    //跨域
    //请求转发的一个代理，配置代理对象
    devServer:{
        host:'localhost',
        port:8080,
        proxy:{
            '/':{
                ws:false,
                target:'http://localhost:8989',
                changeOrigin:true,
                pathRewrite:{
                    '^/':''
                }
            }
        }    /!*代理，转发到8989上*!/

    }
}*/


let proxyObj = {};

proxyObj['/'] = {
    ws: false,
    target: 'http://localhost:8989',
    changeOrigin: true,
    pathRewrite: {
        '^/': ''
    }
}
module.exports = {
    devServer: {
        host: 'localhost',
        port: 8080,
        proxy: proxyObj
    }
}