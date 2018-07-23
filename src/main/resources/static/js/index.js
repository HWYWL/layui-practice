layui.use(['table','form'], function() {
    $ = layui.jquery;
    table = layui.table;
    tableIns = initTable();
});

function initTable() {
    layui.use('table', function(){
        var table = layui.table,form = layui.form;
        var request = GetRequest();
        var token = request['token'];
        table.render({
            elem: '#scriptTable'
            ,url:'/gatewayAddress/fidnAll'
            ,cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'internalNetworkIp', title: '网关内网ip地址'}
                ,{field:'outsideNetworkIp', title: '网关外网ip地址'}
                ,{field:'gatewayAddressName', title: '网关服务器名称'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'remark', title: '备注'}
                ,{field:'crttime', title: '创建时间'}
                ,{field:'', title: '查看/执行/删除', templet: '#buttonl', unresize: true}
            ]]
        });

        //工具栏操作
        table.on('tool(useruv)', function(obj){
            var data = obj.data;

            if(obj.event === 'detail'){
                layer.open({
                    type: 2,
                    title: 'ID：'+ data.id + ' 的查看操作',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['70%', '90%'],
                    about:true,

                    content: '/gatewayAddress/detail?id=' + data.id
                });
            } else if(obj.event === 'edit'){
                layer.open({
                    type: 2,
                    title: '编辑脚本',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['50%', '50%'],
                    about:true,

                    content: '/addGatewayAddress',
                    success: function (layero, index) {
                        //传入参数，并赋值给iframe的元素
                        var body = layer.getChildFrame('body', index);
                        body.append("<label class='layui-form-label' id='gatewayAddresId' style='display: none'>ID：" + data.id + "</label>");
                    }
                });
            } else if(obj.event === 'del'){
                layer.confirm('确定删除?', {icon: 3, title:'删除'}, function(index){
                    $.ajax({
                        url: "/gatewayAddress/delID",
                        type: "POST",
                        data : {"id":data.id},
                        async : true,
                        success: function(data){
                            if(data.code == 0){
                                obj.del();
                                layer.close(index);
                                layer.msg("删除成功", {icon: 6});
                            }else{
                                layer.msg("删除失败", {icon: 5});
                            }
                        }

                    });
                });
            }
        });

        form.on('switch(switchEnable)', function(obj){
            updateStatus(obj);
        });

        form.on('submit(addGatewayAddress)', function(){
            layer.open({
                type: 2,
                title: '添加脚本',
                shadeClose: true,
                shade: 0.8,
                area: ['50%', '50%'],
                about:true,

                content: '/addGatewayAddress'
            });

            //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            // return false;
        });
    });
}

//更改状态
function updateStatus(obj){
    layer.load(1,{time: 2*1000});
    var newStatus = obj.elem.checked?0:-1;
    var id = obj.elem.value;
    var scriptInfo = '{"id":"'+id+'",'+'"enable":"'+newStatus+'"}';
    $.ajax({
        url: "/updata",
        type: "POST",
        data : scriptInfo,
        async : false,
        dataType : "json",
        contentType: "application/json",
        success: function(data){
            layer.closeAll('loading');
            if(data.code==0){
                layer.msg(data.msg,{icon: 1});
            }else{
                layer.msg(data.msg,{icon: 2});
            }
        }

    });
}

function GetRequest() {
    //获取url中"?"符后的字串
    var url = location.search;
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

