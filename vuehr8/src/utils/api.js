/*
/!*封装网络请求*!/

import axios from 'axios'

import { Message } from 'element-ui';

/!*axios响应拦截器*!/
axios.interceptors.response.use(success=>{
    if (success.status && success.status==200 && success.data.status == 500){

        Message.error({message:success.data.msg})
        return;
    }
    if(success.data.msg){
        Message.success({message:success.data.msg})
    }
    return success.data;
},error => {
    if(error.response.status == 504 || error.response.status == 404){
        Message.error({
            message:'找不到服务器'
        })
    }else if(error.response.status == 403){
        Message.error({
            message:'权限不足，请联系管理'
        })
    }else if(error.response.status == 401){
        Message.error({
            message:'请登录'
        })
    }else{
        if (error.response.data.msg){
            Message.error({message:error.response.data.msg})
        }else{
            Message.error({message:'未知错误'})
        }
    }
    return;
})

let base='';
/!*前端请求方法*!/
export const postKeyValueRequest=(url,params)=>{
    return axios({
        method:'post',
        url:`${base}${url}`,
        data:params,
        transformRequest:[function (data) {
            let ret='';
            let resdata=JSON.parse(JSON.stringify(data));

            console.log("运行记录组件接到的数据",resdata);
            /!*需要把data转化为json对象*!/
            for(let i in data){
                 ret+='"'+encodeURIComponent(i)+'"'+':'+'"'+encodeURIComponent(data[i])+'"'+','

            }
            ret = ret.substr(0,ret.length-1);
            ret='{'+ret+'}';
            console.log(ret);
            console.log("------");
            console.log(JSON.parse(ret));
            console.log("------2");
            return resdata;
        }],
        headers:{
            'Content-Type':'application/json'
        }
    })
}
export const postRequest=(url,params)=>{
    return axios({
        method:'post',
        url:`${base}${url}`,
        data:params,
        headers:{
            'Content-Type':'application/json;charset=UTF-8'
        }

    })
}
export const putRequest=(url,params)=>{
    return axios({
        method:'put',
        url:`${base}${url}`,
        data:params
    })
}
export const getRequest=(url,params)=>{
    return axios({
        method:'get',
        url:`${base}${url}`,
        data:params
    })
}
export const deleteRequest=(url,params)=>{
    return axios({
        method:'delete',
        url:`${base}${url}`,
        data:params
    })
}
*/
