<%@ page language="java" import="java.util.*, javax.mail.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
  <head>
    <title>geekmail markdown editor</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    
    <!-- Styles -->
    <link type="text/css" rel="stylesheet" href="wmd/wmd.css"/>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <link type="text/css" rel="stylesheet" href="css/theme-balupton.css"/>
    <script type="text/javascript">
    function validate()
    	{
    		
    		var toAddress = document.getElementsByName("toAddress")[0];
    		var subject = document.getElementsByName("subject")[0];
    		
    		
    		if(toAddress.value.length == 0)
    		{
				alert("toAddress can't be blank!");
				
				return false;
    		}
    		
    		if(subject.value.length == 0)
    		{
    			alert("subject can't be blank!!");
    			
    			return false;
    		}
    		
    		return true;
    	}
    
    
    </script>
  </head>
  <body>
        <form action="MarkdownCServlet" method="post" onsubmit="return validate()">
          <div id="editor">
            <div id="toolbar" class="wmd-toolbar"></div>
            <textarea id="input" class="wmd-input" rows="25" cols="80" name="content" style="resize:none"></textarea>
    <!--         <div id="tools">
              <button id="highlightCode" class="toolbutton">Highlight Code</button>
              <button id="selectall" class="toolbutton" onclick="selectAll();">Select all preview text</button>
              <button id="tohtml" class="toolbutton" onclick="toHtml();">Print</button>
              <!-- <button id="copyall" class="toolbutton" onclick="copyAll();">Copy to clipboard</button> 
            </div>-->
          </div>
          <div id="preview" class="wmd-preview">
          </div>


    <div id="footer">
        <strong>geek@mail markdown editor</strong> &nbsp;&nbsp;收件人：<input name="toAddress" type="text" size="20">&nbsp;&nbsp;  
                         主题：<input name="subject" type="text" size="16">&nbsp; <input type="submit" value="发送"> 
                <input type="reset" value="重置"></br> 
    </div>
    </form>
    <!-- jQuery and Syntax Highlight -->
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.syntaxhighlighter.min.js"></script>

    <!-- WMD -->
    <script type="text/javascript" src="wmd/showdown.js"></script>
    <script type="text/javascript" src="wmd/wmd.js"></script>
    
    <!-- Additional scripts -->
    <script type="text/javascript" src="js/functions.js"></script>
    
    <!-- jQuery listener for syntax highlight -->
    <script type="text/javascript">
    $(document).ready(function() {
      $("#highlightCode").click(function(){
        $.SyntaxHighlighter.init({
        'lineNumbers': false,
        'debug': true
        });
      });
      new WMD("input", "toolbar", { preview: "preview" });
    });
    </script>
  </body>
</html>

