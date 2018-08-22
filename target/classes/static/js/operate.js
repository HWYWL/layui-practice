layui.config({
    base : "js/"
}).use(['form','layer','jquery','laypage'],function() {

    layui.use('code', function(){ //加载code模块
        layui.code(); //引用code方法
    });

    //提交新增脚本数据
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,$ = layui.jquery;

        //监听提交
        form.on('submit(gatewayAddress)', function(data){
            var gatewayAddress = JSON.stringify(data.field);
            layer.confirm('确定提交?', {icon: 3, title:'提交'}, function(index){
                $.ajax({
                    type:"post",
                    url:"/gatewayAddress/save",
                    data:gatewayAddress,
                    async : false,
                    dataType : "json",
                    contentType: "application/json",
                    success:function(result){
                        if(result.code == 0){
                            layer.load(1,{time: 1000});
                            setTimeout(function(){
                                layer.msg(result.msg,{icon:1});
                            },1000);

                            setTimeout(function(){
                                parent.layer.close(index);
                                window.parent.location.reload();
                            },3000);
                        }else {
                            layer.load(1,{time: 1000});
                            setTimeout(function(){
                                layer.msg(result.msg,{icon:5});
                            },1000);
                        }
                    }
                });
            });

            return false;
        });

        //监听提交
        form.on('submit(addRuleGroup)', function(data){
            var ruleGroup = JSON.stringify(data.field);
            var itemsTemp = [];
            $("input[name='gatewayAddress']:checked").each(function() {
                itemsTemp.push($(this).val());
            });
            var items = JSON.stringify(itemsTemp);
            layer.confirm('确定提交?', {icon: 3, title:'提交'}, function(index){
                $.ajax({
                    type:"post",
                    url:"/ruleGroup/save",
                    data:{"ruleGroup":ruleGroup, "items":items},
                    async : false,
                    // dataType : "json",
                    // contentType: "application/json",
                    success:function(result){
                        if(result.code == 0){
                            layer.load(1,{time: 1000});
                            setTimeout(function(){
                                layer.msg(result.msg,{icon:1});
                            },1000);

                            setTimeout(function(){
                                parent.layer.close(index);
                                window.parent.location.reload();
                            },3000);
                        }else {
                            layer.load(1,{time: 1000});
                            setTimeout(function(){
                                layer.msg(result.msg,{icon:5});
                            },1000);
                        }
                    }
                });
            });

            return false;
        });

        //监听提交
        form.on('submit(addUserGroup)', function(data){
            var userGroup = JSON.stringify(data.field);
            var itemsTemp = [];
            $("input[name='gatewayAddress']:checked").each(function() {
                itemsTemp.push($(this).val());
            });
            var items = JSON.stringify(itemsTemp);

            layer.confirm('确定提交?', {icon: 3, title:'提交'}, function(index){
                $.ajax({
                    type:"post",
                    url:"/userGroup/save",
                    data:{"userGroup":userGroup, "items":items},
                    async : false,
                    // dataType : "json",
                    // contentType: "application/json",
                    success:function(result){
                        if(result.code == 0){
                            layer.load(1,{time: 1000});
                            setTimeout(function(){
                                layer.msg(result.msg,{icon:1});
                            },1000);

                            setTimeout(function(){
                                parent.layer.close(index);
                                window.parent.location.reload();
                            },3000);
                        }else {
                            layer.load(1,{time: 1000});
                            setTimeout(function(){
                                layer.msg(result.msg,{icon:5});
                            },1000);
                        }
                    }
                });
            });

            return false;
        });

        //监听提交
        form.on('submit(addGame)', function(data){
            var ruleGroup = JSON.stringify(data.field);
            layer.confirm('确定提交?', {icon: 3, title:'提交'}, function(index){
                $.ajax({
                    type:"post",
                    url:"/game/save",
                    data:ruleGroup,
                    async : false,
                    dataType : "json",
                    contentType: "application/json",
                    success:function(result){
                        if(result.code == 0){
                            layer.load(1,{time: 1000});
                            setTimeout(function(){
                                layer.msg(result.msg,{icon:1});
                            },1000);

                            setTimeout(function(){
                                parent.layer.close(index);
                                window.parent.location.reload();
                            },3000);
                        }else {
                            layer.load(1,{time: 1000});
                            setTimeout(function(){
                                layer.msg(result.msg,{icon:5});
                            },1000);
                        }
                    }
                });
            });

            return false;
        });

        //监听提交
        form.on('submit(addRules)', function(data){
            var ruleGroup = JSON.stringify(data.field);
            layer.confirm('确定提交?', {icon: 3, title:'提交'}, function(index){
                $.ajax({
                    type:"post",
                    url:"/rules/save",
                    data:ruleGroup,
                    async : false,
                    dataType : "json",
                    contentType: "application/json",
                    success:function(result){
                        if(result.code == 0){
                            layer.load(1,{time: 1000});
                            setTimeout(function(){
                                layer.msg(result.msg,{icon:1});
                            },1000);

                            setTimeout(function(){
                                parent.layer.close(index);
                                window.parent.location.reload();
                            },3000);
                        }else {
                            layer.load(1,{time: 1000});
                            setTimeout(function(){
                                layer.msg(result.msg,{icon:5});
                            },1000);
                        }
                    }
                });
            });

            return false;
        });

        form.on('select(games)', function(data){
            console.log(data.elem); //得到select原始DOM对象
            console.log(data.value); //得到被选中的值
        });

        form.on('select(ruleGroupIds)', function(data){
            console.log(data.elem); //得到select原始DOM对象
            console.log(data.value); //得到被选中的值
            var id = data.value;
            $.ajax({
                url: "/ruleGroup/detailGatewayAddress",
                type: "POST",
                data : {"id":id},
                async : true,
                success: function(data){
                    $("#userRuleGroups")[0].innerHTML = "";
                    $("#userRuleGroups")[0].innerText = "";
                    var usableGatewayAddresses = data.data;
                    // 可用网关
                    for (var i = 0; i < usableGatewayAddresses.length;i++){
                        $("#userRuleGroups").append("<input type='checkbox' name='gatewayAddress'  value="+ usableGatewayAddresses[i].id +" title="+ usableGatewayAddresses[i].gatewayAddressName +">");
                    }

                    form.render();
                }

            });
        });

        $.ajax({
            url: "/rules/fidnNetGroupGateway",
            type: "POST",
            async : true,
            success: function(data){
                console.log(data);
                var game = data.data.games;
                var gatewayAddresses = data.data.gatewayAddresses;
                var usableGatewayAddresses = data.data.usableGatewayAddresses;
                var ruleGroups = data.data.ruleGroups;
                var userGroups = data.data.userGroups;
                for (var i = 0; i < game.length;i++){
                    $("#games").append("<option value="+ game[i].id +">"+ game[i].gameName +"</option>");
                }

                // for (var i = 0; i < gatewayAddresses.length;i++){
                //     $("#ruleGroups").append("<input type='checkbox' name='gatewayAddress'  value="+ gatewayAddresses[i].id +" title="+ gatewayAddresses[i].gatewayAddressName +">");
                // }

                for (var i = 0; i < ruleGroups.length;i++){
                    $("#userGroups").append("<option value="+ ruleGroups[i].id +">"+ ruleGroups[i].ruleGroupName +"</option>");
                }

                for (var i = 0; i < ruleGroups.length;i++){
                    $("#ruleUserGroups").append("<option value="+ ruleGroups[i].id +">"+ ruleGroups[i].ruleGroupName +"</option>");
                }

                // 可用网关
                for (var i = 0; i < usableGatewayAddresses.length;i++){
                    $("#ruleGroups").append("<input type='checkbox' name='gatewayAddress'  value="+ usableGatewayAddresses[i].id +" title="+ usableGatewayAddresses[i].gatewayAddressName +">");
                }

                form.render();
            }
        });



    });
});
