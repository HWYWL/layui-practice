<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"  media="all">
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>添加规则</legend>
</fieldset>

<form class="layui-form" action="">
    <div class="layui-form-item" style="display: none">
        <div class="layui-input-block">
            <input type="text" name="id" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">规则名称</label>
        <div class="layui-input-block">
            <input type="text" name="ruleName" lay-verify="required" autocomplete="off" placeholder="请输入规则名称,不可为空!" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">规则组</label>
        <div class="layui-input-block" lay-filter="selFilter">
            <select id="ruleUserGroups" name="ruleGroupId" lay-filter="userGroupId" lay-verify="required">
                <option value=""></option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">来源端口</label>
            <div class="layui-input-inline">
                <input type="text" name="fromPort" lay-verify="required" placeholder="请输入,例如8080" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">协议</label>
            <div class="layui-input-inline">
                <select id="agreement" name="agreement" lay-verify="required">
                    <option value=""></option>
                    <option value="tcp">tcp</option>
                    <option value="udp">udp</option>
                    <option value="http">http</option>
                    <option value="tcp_game">tcp_game</option>
                    <option value="udp_game">udp_game</option>
                    <option value="http_game">http_game</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">转发端口</label>
            <div class="layui-input-inline">
                <input type="text" name="toPort" lay-verify="required" placeholder="请输入,例如8080" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">转发ip</label>
            <div class="layui-input-inline">
                <input type="text" name="toIp" lay-verify="required" placeholder="请输入,例如192.168.1.105" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">代理最大并发连接数</label>
            <div class="layui-input-inline">
                <input type="text" name="maxConcurrentConn" lay-verify="required" placeholder="请输入,例如500" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">每个ip最大并发连接数</label>
            <div class="layui-input-inline">
                <input type="text" name="maxConcurrentConnPerIp" lay-verify="required" placeholder="请输入,例如10" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">每个ip每分钟最大新建连接数</label>
            <div class="layui-input-inline">
                <input type="text" name="maxNewConnPerMinPerIp" lay-verify="required" placeholder="请输入,例如10" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">首包超时毫秒数</label>
            <div class="layui-input-inline">
                <input type="text" name="revFirstPkgTimeoutMills" lay-verify="required" placeholder="请输入,例如2000" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" name="remark" autocomplete="off" placeholder="请输入备注!" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addRules">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="/layui/layui.js" charset="utf-8"></script>
<script src="/js/operate.js"></script>

</body>
</html>