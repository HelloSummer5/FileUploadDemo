# 文件上传Demo

### 准备工作
>文件名的生成 ( FileNameUtils )
- 不准备重新将用户上传的文件重新命名可以略过此步骤。
```
package com.wu.demo.fileupload.demo.util;

public class FileNameUtils {

    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName){
        return UUIDUtils.getUUID() + FileNameUtils.getSuffix(fileOriginName);
    }

}

```

### 文件上传
