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
    <legend>添加规则组</legend>
</fieldset>

<form class="layui-form" action="">
    <div class="layui-form-item" style="display: none">
        <div class="layui-input-block">
            <input type="text" name="id" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">规则组名称</label>
        <div class="layui-input-block">
            <input type="text" name="ruleGroupName" lay-verify="required" autocomplete="off" placeholder="请输入规则组名称,不可为空" class="layui-input">
        </div>
    </div>

    <label class="layui-form-label">防御网关</label>
    <div class="layui-input-block" id="ruleGroups">

    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" name="remark" autocomplete="off" placeholder="请输入备注!" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addRuleGroup">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="/layui/layui.js" charset="utf-8"></script>
<script src="/js/operate.js"></script>

</body>
</html>