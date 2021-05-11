/**
 *@description: 服务治理
 *@author: JohnYehyo
 *@create: 2021-05-11 18:41:46
 */
let tableIns;
layui.use(['form', 'table'], function () {
    let form = layui.form;
    let table = layui.table;
    let $ = layui.$;

    //监听提交
    form.on('submit(formPages)', function (data) {
        console.log(data.field)
        reload(data.field.server_name)
        return false;
    });


    tableIns = table.render({
        elem: '#data_table',
        even: true,
        url: '/serverInfo/pages/',
        method: 'post',
        limit: 20,
        limits: [5, 10, 20, 50],
        cellMinWidth: 80,
        toolbar: '#toolbarDemo',
        // height: $(document).height() - $('#data_table').offset().top,
        cols: [[
            {field: 'serverName', title: '服务名称', width: '16%', sort: true},
            {field: 'serverUrl', width: '16%', title: '服务器地址'},
            {field: 'tomcatDir', width: '25%', title: 'tomcat路径'},
            {field: 'state', title: '状态', width: '6%', sort: true,
                templet: function (data) {
                    if (data.state == 0) {
                        return '<span style="text-align: center"><i class="layui-icon layui-icon-ok-circle" style="font-size: 30px; color: #008000;"></i></span>'
                    }
                    return '<span style="text-align: center"><i class="layui-icon layui-icon-close-fill" style="font-size: 30px; color: #FF0000"></i></span>'
                }
            },
            {field: 'bucketName', width: '10%', title: '存储桶', sort: true},
            {field: 'objectName', width: '17%', title: '对象名', sort: true},
            {field: 'action', width: '10%', title: '操作',
                templet: function (data) {
                    return '<a href="javascript:void(0);" style="color: #008000"' +
                        ' onclick="upgrade(' + JSON.stringify(data).replace(/\"/g, "'") + ')">升级</a>' +
                        ' &nbsp;' +
                        '<a href="javascript:void(0);" style="color: #0000FF" ' +
                        'onclick="reboot(' + JSON.stringify(data).replace(/\"/g, "'") + ')">重启</a>'
                }
            }
        ]],
        page: {
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'],
            curr: 1,
            groups: 5,
            first: false,
            last: false

        },
        parseData: function (res) {
            return {
                "code": res.code,
                "msg": res.msg,
                "count": res.data.total,
                "data": res.data.list
            };
        }
    });
});

function reload(server_name) {
    tableIns.reload({
        where: {
            serverName: server_name
        }
    })
}

function upgrade(param){
    axios.post('/tomcat/upgrade', {
        tomcatDir: param.tomcatDir,
        bucketName: param.bucketName,
        objectName: param.objectName
    }).then(res => {
        let data = res.data;
        if(data.code === 0){
            layer.msg(data.msg, {icon: 1});
        }else{
            layer.msg(data.msg, {icon: 2});
        }
    }).catch(e => {
        console.error(e)
        layer.msg('系统繁忙请稍后重试!', {icon: 0});
    })
}

function reboot(param){
    axios.post('/tomcat/reboot', {
        tomcatDir: param.tomcatDir
    }).then(res => {
        let data = res.data;
        if(data.code === 0){
            layer.msg(data.msg, {icon: 1});
        }else{
            layer.msg(data.msg, {icon: 2});
        }
    }).catch(e => {
        console.error(e)
        layer.msg('系统繁忙请稍后重试!', {icon: 0});
    })
}