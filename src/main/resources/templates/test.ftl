<!DOCTYPE html>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
</head>
<body>
<h1 >文件上传Demo</h1>
<form action="fileUpload" method="post" enctype="multipart/form-data">
    <p>选择文件: <input type="file" name="fileName"/></p>
    <p><input type="submit" value="提交"/></p>
</form>

<#if msg??>
    <span>${msg}</span><br>
<#else >
    <span>${msg!("文件未上传")}</span><br>
</#if>
<#if fileName??>
<img src="/show?fileName=${fileName}" style="width: 100px"/>
<#else>
<img src="/show" style="width: 100px"/>
</#if>
</body>
</html>
