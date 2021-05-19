/**
 *@description: 服务治理
 *@author: JohnYehyo
 *@create: 2021-05-11 18:41:46
 */
let tableIns;
let $;
layui.use(['form', 'table'], function () {
    let form = layui.form;
    let table = layui.table;
    $ = layui.$;

    //监听提交
    form.on('submit(formPages)', function (data) {
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
        toolbar: '#bar_tool',
        defaultToolbar: [
            'filter', 'exports', 'print'
            //     , {
            //     title: '提示',
            //     layEvent: 'LAYTABLE_TIPS',
            //     icon: 'layui-icon-tips'
            // }
        ],
        // height: $(document).height() - $('#data_table').offset().top,
        cols: [[
            {type: 'checkbox', width: '2%'},
            {
                field: 'serverName', title: '服务名称', width: '15%', sort: true,
                templet: function (data) {
                    let text = '';
                    switch (Number(data.serverName)) {
                        case 1:
                            text = '业务系统';
                            break;
                        case 2:
                            text = '核查系统';
                            break;
                        case 3:
                            text = '在线教育';
                            break;
                        case 4:
                            text = '数据分析';
                            break;
                        case 5:
                            text = '豫矫通';
                            break;
                        case 6:
                            text = '豫在矫';
                            break;
                        case 7:
                            text = '业务系统-测试服';
                            break;
                        case 8:
                            text = '核查系统-测试服';
                            break;
                        case 9:
                            text = '在线教育-测试服';
                            break;
                        case 10:
                            text = '数据分析-测试服';
                            break;
                        case 11:
                            text = '豫矫通-测试服';
                            break;
                        case 12:
                            text = '豫在矫-测试服';
                            break;
                        default:
                    }
                    return text;
                }
            },
            {field: 'serverUrl', width: '15%', title: '服务器地址'},
            {field: 'tomcatDir', width: '25%', title: 'tomcat路径'},
            {field: 'bucketName', width: '10%', title: '存储桶', sort: true},
            {field: 'objectName', width: '15%', title: '对象名', sort: true},
            {field: 'packageType', width: '8%', title: '包类型', sort: true,
                templet: function (data) {
                    if (data.packageType == 1) {
                        return '<span style="text-align: center">jar</span>'
                    }
                    return '<span style="text-align: center">war</span>'
                }
            },
            {field: 'action', width: '10%', title: '操作', toolbar: '#tr_tool'}
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

    table.on('toolbar(data_table_filter)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'add':
                layer.open({
                    type: 2,
                    content: '/view/settings_form',
                    area: ['400px', '100%'],
                    offset: 'rt',
                    anim: 5,
                    shadeClose: true,
                    end: function () {
                        reload($("select[name='server_name'] option:selected").val())
                    }
                });
                break;
            case 'delete':
                if (!checkStatus.data.length) {
                    layer.msg('请选择要删除的数据');
                    return
                }
                layer.confirm('确认删除所选数据吗?', function (index) {
                    deleteItems(checkStatus.data)
                    layer.close(index);
                });
                break;
        }
        ;
    });

    table.on('tool(data_table_filter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'update') {
            layer.open({
                type: 2,
                content: '/view/settings_form?id=' + data.id,
                area: ['400px', '100%'],
                offset: 'rt',
                anim: 5,
                shadeClose: true,
                end: function () {
                    reload($("select[name='server_name'] option:selected").val())
                }
            });
        }
    });
});

function reload(server_name) {
    tableIns.reload({
        where: {
            serverName: Number(server_name)
        }
    })
}

//删除
function deleteItems(param) {
    let data = param.map(item => item.id).join(",");
    axios.delete('/serverInfo/delete/'+data).then(res => {
        let data = res.data;
        if (data.code === 0) {
            reload($("select[name='server_name'] option:selected").val())
            layer.msg(data.msg, {icon: 1});
        } else {
            layer.msg(data.msg, {icon: 2});
        }
    }).catch(e => {
        console.error(e)
        layer.msg('系统繁忙请稍后重试!', {icon: 0});
    })
}
