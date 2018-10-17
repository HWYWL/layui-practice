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
            ,url:'/ruleGroup/fidnAll'
            ,cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', title: 'id'}
                ,{field:'ruleGroupName', title: '规则组名'}
                ,{field:'gatewayAddressIds', title: '所属该组的网关id'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'remark', title: '备注'}
                ,{field:'enable'+' checked', title: '随机端口', templet: '#switchTpl', unresize: true}
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

                    content: '/ruleGroup/detail?id=' + data.id
                });
            } else if(obj.event === 'del'){
                layer.confirm('确定删除?', {icon: 3, title:'删除'}, function(index){
                    $.ajax({
                        url: "/ruleGroup/delID",
                        type: "POST",
                        data : {"id":data.id},
                        async : true,
                        success: function(data){
                            if(data.code == 0){
                                obj.del();
                                layer.close(index);
                                layer.msg("删除成功", {icon: 6});
                            }else{
                                layer.msg(data.msg, {icon: 5});
                            }
                        }

                    });
                });
            } else if(obj.event === 'edit'){
                layer.open({
                    type: 2,
                    title: '编辑脚本',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['50%', '50%'],
                    about:true,

                    content: '/addRuleGroup',
                    success: function (layero, index) {
                        //传入参数，并赋值给iframe的元素
                        var body = layer.getChildFrame('body', index);
                        body.append("<label class='layui-form-label' id='ruleGroupId' style='display: none'>ID：" + data.id + "</label>");
                    }
                });
            } else if(obj.event === 'exe'){
                layer.confirm('确定执行?', {icon: 3, title:'执行'}, function(index){
                    $.ajax({
                        url: "/ruleGroup/execute",
                        type: "POST",
                        data : {"id":data.id},
                        async : true,
                        success: function(data){
                            if(data.code == 0){
                                layer.close(index);
                                layer.msg("执行成功", {icon: 6});
                            }else{
                                layer.msg(data.data, {icon: 5});
                            }
                        }

                    });
                });
            } else if(obj.event === 'sync'){
                layer.confirm('确定同步git?', {icon: 3, title:'同步'}, function(index){
                    var load = layer.load(2);
                    $.ajax({
                        url: "/ruleGroup/sync",
                        type: "POST",
                        data : {"id":data.id},
                        async : true,
                        success: function(data){
                            if(data.code == 0){
                                layer.close(index);
                                layer.close(load);
                                layer.msg("执行成功", {icon: 6});
                            }else{
                                layer.close(index);
                                layer.close(load);
                                layer.msg(data.data, {icon: 5});
                            }
                        }

                    });
                });
            } else if(obj.event === 'download'){
                var ruleGroupName = data.ruleGroupName;
                layer.open({
                    type: 1
                    ,title: false //不显示标题栏
                    ,closeBtn: false
                    ,area: '300px;'
                    ,shade: 0.8
                    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,btn: ['下载', '取消']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,about:true
                    ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">下载该配置文件<br>我们的征途是星辰大海 ^_^</div>'
                    ,success: function(layero){
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').attr({
                            href: '/ruleGroup/download?id=' + data.id
                            ,target: '_blank'
                        });
                    }
                });

            }
        });

        form.on('switch(switchEnable)', function(obj){
            updateStatus(obj);
        });

        form.on('submit(addRuleGroup)', function(){
            layer.open({
                type: 2,
                title: '添加脚本',
                shadeClose: true,
                shade: 0.8,
                area: ['50%', '50%'],
                about:true,

                content: '/addRuleGroup'
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
    var ruleGroup = '{"id":"'+id+'",'+'"enable":"'+newStatus+'"}';
    $.ajax({
        url: "/ruleGroup/updata",
        type: "POST",
        data : ruleGroup,
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

