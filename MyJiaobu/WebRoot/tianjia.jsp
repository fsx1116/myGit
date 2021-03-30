<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
     <script>
     $(function () {
         //重置
         $('#reset').click(function () {
             $('[type=text]').val('');
         });
         
         //添加进去
         $('#save').click(function(){

             var area=$('#area').val();
             var time=$('#time').val();
             var felling=$('#felling').val();
             var r=new RegExp(/\d{4}-\d{2}-\d{2}/);//日期格式验证的正则表达式

             if (area==""||time==""||felling=="") {
                 alert("所有选项必填!");
             }else if (!r.test(time)){
                 alert("日期格式不正确，必须是yyyy-MM-dd格式！");
             }else{
                 window.location.href="javascript:document.form.submit()";
             }
         });  
     });
     </script>
  </head>
  
  <body>
    <div align="center">
    	<h2>添加脚印</h2>
    	<form name="form" action="${pageContext.request.contextPath }/tianjia" method="post">
            &nbsp;&nbsp;国家地区:<input type="text" name="area" id="area"><br><br>
            &nbsp;&nbsp;脚印时间:<input type="text" name="time" id="time">&nbsp;&nbsp;<span>yyyy-MM-dd格式</span><br><br>
            &nbsp;&nbsp;站点地址:<textarea rows="5" cols="17" type="text" name="felling" id="felling"></textarea><br><br>
            
        </form>
        	<input type="submit" value="提交" id="save">
            <input type="button" value="重置" id="reset">
    </div>
  </body>
</html>
