/**
 *@description: index
 *@author: JohnYehyo
 *@create: 2021-05-14 16:02:55
 */
layui.use(['element', 'layer', 'util'], function () {
    let element = layui.element,
        layer = layui.layer,
        util = layui.util,
        $ = layui.$;

    $("#default").addClass('layui-this');

    //头部事件
    let isShow = true;
    util.event('lay-header-event', {
        //左侧菜单事件
        menuLeft: function (othis) {
            if($('.layui-layout-admin .layui-logo span').is(':hidden')){
                $('.layui-layout-admin .layui-logo span').show();
            }else{
                $('.layui-layout-admin .layui-logo span').hide();
            }
            $('.layui-nav-tree .layui-nav-item span').each(function () {
                if ($(this).is(':hidden')) {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            });
            //判断isshow的状态
            if (isShow) {
                $('#change').removeClass('layui-icon-shrink-right');
                $('#change').addClass('layui-icon-spread-left');
                $('.layui-layout-admin .layui-logo').width(60);
                $('.layui-layout-admin .layui-side').width(60);
                $('.layui-side-scroll').width(60);
                $('.layui-side-scroll').css('margin-right', '70%');
                $('.layui-layout-left').css('left', 60 + 'px');
                $('.layui-body').css('left', 60 + 'px');
                $('.layui-footer').css('left', 60 + 'px');
            } else {
                $('#change').removeClass('layui-icon-spread-left');
                $('#change').addClass(' layui-icon-shrink-right');
                $('.layui-layout-admin .layui-logo').width(200);
                $('.layui-layout-admin .layui-side').width(200);
                $('.layui-side-scroll').width(200);
                $('.layui-side-scroll').css('margin-right', '10%');
                $('.layui-layout-left').css('left', 200 + 'px');
                $('.layui-body').css('left', 200 + 'px');
                $('.layui-footer').css('left', 200 + 'px');
            }
            isShow = !isShow;
        },
        menuRight: function () {
            layer.open({
                type: 1,
                content: '<div style="padding: 15px;">处理右侧面板的操作</div>',
                area: ['260px', '100%'],
                offset: 'rt',
                anim: 5,
                shadeClose: true
            });
        }
    });

    $("a[link]").on('click', function () {
        let link = $(this).attr("link");
        $("#body_frame").attr("src", link);
    })

});