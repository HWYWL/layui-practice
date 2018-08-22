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
            ,url:'/game/fidnAll'
            ,cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', title: 'id'}
                ,{field:'ip', title: '游戏服务器IP地址'}
                ,{field:'gameName', title: '游戏名'}
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

                    content: '/game/detail?id=' + data.id
                });
            } else if(obj.event === 'del'){
                layer.confirm('确定删除?', {icon: 3, title:'删除'}, function(index){
                    $.ajax({
                        url: "/game/delID",
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

        form.on('submit(addGame)', function(){
            layer.open({
                type: 2,
                title: '添加脚本',
                shadeClose: true,
                shade: 0.8,
                area: ['50%', '50%'],
                about:true,

                content: '/addGame'
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

