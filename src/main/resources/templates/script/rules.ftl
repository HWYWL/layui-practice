<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>规则</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"  media="all">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/script.css">
</head>
<body>
<ul class="layui-nav layui-bg-green" lay-filter="demo">
    <li class="layui-nav-item"><a href="/gatewayAddress">防御网关</a></li>
    <li class="layui-nav-item"><a href="/ruleGroup">规则组</a></li>
    <li class="layui-nav-item"><a href="/userGroup">用户组</a></li>
    <li class="layui-nav-item layui-this"><a href="/rules">规则</a></li>
</ul>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <img src="/image/logo.gif" class="img-responsive center-block" alt="Cinque Terre">
            <h3 class="text-center">规则管理</h3>
        </div>
    </div>
</div>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">规则组</label>
        <div class="layui-input-block" lay-filter="selFilter">
            <select id="userGroups" name="ruleGroupIds" lay-filter="ruleGroupIds" lay-verify="required">
                <option value="">全部规则</option>
            </select>
        </div>
    </div>
</form>
<div class="leftright">
    <script type="text/html" id="titleTpl"></script>

<#--表单-->
    <table class="layui-hide" lay-size="sm" id="scriptTable" lay-filter="useruv"></table>
    <script type="text/html" id="switchTpl">
        <input type="checkbox" value="{{d.id}}" lay-filter="switchEnable" lay-skin="switch" lay-text="生效|失效" {{ d.enable == 0 ? 'checked' : '' }}>
    </script>

<#--按键-->
    <script type="text/html" id="buttonl">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">
            <i class="layui-icon">&#xe60a;</i>查看</a>
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">
            <i class="layui-icon">&#xe642;</i>编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">
            <i class="layui-icon">&#xe640;</i>删除</a>
    </script>
</div>
<div class="center-button">
    <button class="layui-btn layui-btn layui-btn-sm" lay-submit lay-filter="addRules">
        <i class="layui-icon">&#xe608;</i>添加规则</button>
</div>


<script src="/layui/layui.js" charset="utf-8"></script>
<script src="/js/rules.js"></script>
</body>
</html>