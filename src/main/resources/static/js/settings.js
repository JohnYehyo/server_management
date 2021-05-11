/**
 *@description: 服务管理
 *@author: JohnYehyo
 *@create: 2021-05-11 18:41:46
 */
let table;
layui.use(['form', 'table'], function () {
    let form = layui.form;
    table = layui.table;
    let $ = layui.$;

    //监听提交
    form.on('submit(formPages)', function (data) {
        console.log(data.field)
        reload(data.field.server_url, data.field.server_name)
        return false;
    });


    table.render({
        elem: '#data_table',
        even: true,
        url: '/tomcat/pages/',
        limit: 20,
        limits: [5, 10, 20, 50],
        cellMinWidth: 80,
        toolbar: '#toolbarDemo',
        // height: $(document).height() - $('#data_table').offset().top,
        cols: [[
            {field: 'server_name', title: '服务名称', sort: true},
            {field: 'server_url', title: '服务器地址'},
            {field: 'tomcat_dir', title: 'tomcat路径'},
            {field: 'state', title: '状态', sort: true},
            {field: 'bucketName', title: '存储桶', sort: true},
            {field: 'objectName', title: '对象名', sort: true},
            {field: 'action', title: '操作',
                templet: function (data) {
                    return '<a href="javascript:void(0);" style="color: #008000"' +
                        ' onclick="upgrade(' + JSON.stringify(data).replace(/\"/g, "'") + ')">配置</a>' +
                        ' &nbsp;' +
                        '<a href="javascript:void(0);" style="color: #0000FF" ' +
                        'onclick="reboot(' + JSON.stringify(data).replace(/\"/g, "'") + ')">删除</a>'
                }
            }
        ]],
        page: {
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'],
            curr: 20,
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

function reload(pid, server_name) {
    table.reload({
        where: {
            pid: pid,
            server_name: server_name
        }
    })
}

function upgrade(){
    axios.post('/tomcat/manageTest', {
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

function reboot(){
    axios.post('/tomcat/manageTest', {
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