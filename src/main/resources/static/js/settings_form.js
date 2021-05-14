/**
 *@description: 服务治理
 *@author: JohnYehyo
 *@create: 2021-05-11 18:41:46
 */
layui.use('form', function () {
    let form = layui.form;
    let $ = layui.$;

    let id = $("input[name = id]").val();
    if (id > 0) {
        //编辑页面表单先赋值
        rendering(form, id);
    }

    //监听提交
    form.on('submit(submit_filter)', function (data) {
        if(id > 0){
            update(data.field);
        }else{
            add(data.field)
        }
        return false;
    });
    form.on('submit(cancel_filter)', function (data) {
        return false;
    });

});


function rendering(form, id) {
    axios.get('/serverInfo/info/'+ id).then(res => {
        let data = res.data;
        if (data.code === 0) {
            form.val('form_filter', {
                "id": id,
                "serverName": data.data.serverName,
                "serverUrl": data.data.serverUrl,
                "tomcatDir": data.data.tomcatDir,
                "bucketName": data.data.bucketName,
                "objectName": data.data.objectName
            });
        } else {
            layer.msg(data.msg, {icon: 2});
            return;
        }
    }).catch(e => {
        console.error(e)
        layer.msg('系统繁忙请稍后重试!', {icon: 0});
        return;
    })
}

//新增
function add(param) {
    console.log(param.serverName)
    let serverName = Number(param.serverName);
    param.serverName = serverName;
    axios.post('/serverInfo/add', param).then(res => {
        let data = res.data;
        if (data.code === 0) {
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
            layer.msg(data.msg, {icon: 1});
        } else {
            layer.msg(data.msg, {icon: 2});
        }
    }).catch(e => {
        console.error(e)
        layer.msg('系统繁忙请稍后重试!', {icon: 0});
    })
}

//编辑
function update(param) {
    let id = Number(param.id);
    let serverName = Number(param.serverName);
    param.id = id;
    param.serverName = serverName;
    axios.put('/serverInfo/update', param).then(res => {
        let data = res.data;
        if (data.code === 0) {
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
            layer.msg(data.msg, {icon: 1});
        } else {
            layer.msg(data.msg, {icon: 2});
        }
    }).catch(e => {
        console.error(e)
        layer.msg('系统繁忙请稍后重试!', {icon: 0});
    })
}

