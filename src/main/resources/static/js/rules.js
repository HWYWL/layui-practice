layui.use(['table','form'], function() {
    $ = layui.jquery;
    table = layui.table;
    tableIns = initTable();
});

function initTable() {
    layui.use('table', function(){
        var table = layui.table,form = layui.form;

        table.render({
            elem: '#scriptTable'
            ,url:'/rules/fidnAll'
            ,cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'ruleName', title: '规则'}
                ,{field:'ruleGroupName', title: '规则组'}
                ,{field:'fromPort', title: '来源端口'}
                ,{field:'toPort', title: '转发端口'}
                ,{field:'toIp', title: '转发ip'}
                ,{field:'agreement', title: '协议'}
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

                    content: '/rules/detail?id=' + data.id
                });
            } else if(obj.event === 'edit'){
                layer.open({
                    type: 2,
                    title: '编辑脚本',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['60%', '65%'],
                    about:true,

                    content: '/addRules',
                    success: function (layero, index) {
                        //传入参数，并赋值给iframe的元素
                        var body = layer.getChildFrame('body', index);
                        body.append("<label class='layui-form-label' id='rulesId' style='display: none'>ID：" + data.id + "</label>");
                    }
                });
            } else if(obj.event === 'del'){
                layer.confirm('确定删除?', {icon: 3, title:'删除'}, function(index){
                    $.ajax({
                        url: "/rules/delID",
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

        form.on('submit(addRules)', function(){
            layer.open({
                type: 2,
                title: '添加脚本',
                shadeClose: true,
                shade: 0.8,
                area: ['60%', '65%'],
                about:true,

                content: '/addRules'
            });

            //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            // return false;
        });


    });
}

//更改状态
function updateStatus(obj){
    var load = layer.load(2);
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
            layer.closeAll(load);
            if(data.code==0){
                layer.msg(data.msg,{icon: 1});
            }else{
                layer.msg(data.msg,{icon: 2});
            }
        }

    });
}

