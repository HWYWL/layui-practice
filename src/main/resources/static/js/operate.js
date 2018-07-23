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
            var gatewayAddresId;
            var gatewayAddres = $("#gatewayAddresId")[0];
            if (typeof(gatewayAddres) != "undefined"){
                gatewayAddresId = gatewayAddres.innerText.split("：")[1];
            }
            // 赋值
            data.field.id = gatewayAddresId;
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
            var ruleGroupId;
            var ruleGroup = $("#ruleGroupId")[0];
            var itemsTemp = [];
            $("input[name='gatewayAddress']:checked").each(function() {
                itemsTemp.push($(this).val());
            });

            if (typeof(ruleGroup) != "undefined"){
                ruleGroupId = ruleGroup.innerText.split("：")[1];
            }

            // 赋值
            data.field.id = ruleGroupId;
            var ruleGroup = JSON.stringify(data.field);

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
            var itemsTemp = [];
            $("input[name='gatewayAddress']:checked").each(function() {
                itemsTemp.push($(this).val());
            });
            var items = JSON.stringify(itemsTemp);

            var userGroupId;
            var userGroup = $("#userGroupId")[0];

            if (typeof(userGroup) != "undefined"){
                userGroupId = userGroup.innerText.split("：")[1];
            }
            // 赋值
            data.field.id = userGroupId;
            var userGroup = JSON.stringify(data.field);

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
        form.on('submit(addRules)', function(data){
            var rulesId;
            var rules = $("#rulesId")[0];
            var itemsTemp;

            $("input[name='agreement']:checked").each(function() {
                itemsTemp.push($(this).val());
            });

            if (typeof(rules) != "undefined"){
                rulesId = rules.innerText.split("：")[1];
            }
            // 赋值
            data.field.id = rulesId;
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
                // 获取父目录传递过来的id

                var gatewayAddresId;
                var gatewayAddres = $("#gatewayAddresId")[0];
                if (typeof(gatewayAddres) != "undefined"){
                    gatewayAddresId = gatewayAddres.innerText.split("：")[1];
                }

                var rulesId;
                var rules = $("#rulesId")[0];
                if (typeof(rules) != "undefined"){
                    rulesId = rules.innerText.split("：")[1];
                }

                var ruleGroupId;
                var ruleGroup = $("#ruleGroupId")[0];
                if (typeof(ruleGroup) != "undefined"){
                    ruleGroupId = ruleGroup.innerText.split("：")[1];
                }

                var userGroupId;
                var userGroup = $("#userGroupId")[0];
                var userGroups = $("#userGroups");
                if (typeof(userGroup) != "undefined"){
                    userGroupId = userGroup.innerText.split("：")[1];
                }

                console.log(data);
                var gatewayAddresses = data.data.gatewayAddresses;
                var usableGatewayAddresses = data.data.usableGatewayAddresses;
                var ruleGroups = data.data.ruleGroups;
                var userGroups = data.data.userGroups;

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

                if (typeof(gatewayAddresId) != "undefined"){
                    $.ajax({
                        url: "/gatewayAddress/echo",
                        type: "GET",
                        data : {"id":gatewayAddresId},
                        async : true,
                        success: function(data){
                            $("input[name='internalNetworkIp']").attr("value",data.data.internalNetworkIp);
                            $("input[name='outsideNetworkIp']").attr("value",data.data.outsideNetworkIp);
                            $("input[name='gatewayAddressName']").attr("value",data.data.gatewayAddressName);
                            $("input[name='remark']").attr("value",data.data.remark);

                            form.render();
                        }

                    });
                }

                if (typeof(rulesId) != "undefined"){
                    $.ajax({
                        url: "/rules/echo",
                        type: "GET",
                        data : {"id":rulesId},
                        async : true,
                        success: function(data){
                            $("input[name='ruleName']").attr("value",data.data.ruleName);
                            // 动态选中规则组下拉
                            $("#ruleUserGroups").val(data.data.ruleGroupId);
                            // 来源端口不可被修改
                            $("input[name='fromPort']").attr("value",data.data.fromPort).attr("class", "layui-input layui-disabled").attr("disabled", "disabled");
                            $("input[name='toPort']").attr("value",data.data.toPort);
                            $("input[name='toIp']").attr("value",data.data.toIp);
                            $("input[name='maxConcurrentConn']").attr("value",data.data.maxConcurrentConn);
                            $("input[name='maxConcurrentConnPerIp']").attr("value",data.data.maxConcurrentConnPerIp);
                            $("input[name='maxNewConnPerMinPerIp']").attr("value",data.data.maxNewConnPerMinPerIp);
                            $("input[name='revFirstPkgTimeoutMills']").attr("value",data.data.revFirstPkgTimeoutMills);
                            // 动态选中协议下拉
                            $("#agreement").val(data.data.agreement);
                            $("input[name='remark']").attr("value",data.data.remark);

                            form.render();
                        }

                    });
                }

                if (typeof(ruleGroupId) != "undefined"){
                    $.ajax({
                        url: "/ruleGroup/echo",
                        type: "GET",
                        data : {"id":ruleGroupId},
                        async : true,
                        success: function(data){
                            $("input[name='ruleGroupName']").attr("value",data.data.ruleGroup.ruleGroupName);
                            $("input[name='remark']").attr("value",data.data.ruleGroup.remark);

                            // 可用网关
                            for (var i = 0; i < data.data.gatewayAddresses.length;i++){
                                $("#ruleGroups").append("<input type='checkbox' name='gatewayAddress' checked='checked' value="+ data.data.gatewayAddresses[i].id +" title="+ data.data.gatewayAddresses[i].gatewayAddressName +">");
                            }

                            form.render();
                        }

                    });
                }

                if (typeof(userGroupId) != "undefined"){
                    $.ajax({
                        url: "/userGroup/echo",
                        type: "GET",
                        data : {"id":userGroupId},
                        async : true,
                        success: function(data){
                            $("input[name='userGroupName']").attr("value",data.data.userGroup.userGroupName);
                            $("input[name='remark']").attr("value",data.data.userGroup.remark);
                            // 动态选中规则组下拉
                            $("#userGroups").val(data.data.userGroup.ruleGroupIds);

                            var usableGatewayAddresses = data.data.usableGatewayAddresses;
                            var usedGatewayAddresses = data.data.usedGatewayAddresses;

                            // 可用网关
                            if (usableGatewayAddresses != null){
                                for (var i = 0; i < usableGatewayAddresses.length;i++){
                                    $("#userRuleGroups").append("<input type='checkbox' name='gatewayAddress' value="+ data.data.usableGatewayAddresses[i].id +" title="+ data.data.usableGatewayAddresses[i].gatewayAddressName +">");
                                }
                            }

                            if (usedGatewayAddresses != null){
                                // 已用网关
                                for (var i = 0; i < usedGatewayAddresses.length;i++){
                                    $("#userRuleGroups").append("<input type='checkbox' name='gatewayAddress' checked='checked' value="+ data.data.usedGatewayAddresses[i].id +" title="+ data.data.usedGatewayAddresses[i].gatewayAddressName +">");
                                }
                            }


                            form.render();
                        }

                    });
                }

                form.render();
            }
        });



    });
});
