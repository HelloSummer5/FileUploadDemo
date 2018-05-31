# 图片上传Demo

### 思路
- 一般情况下都是将用户上传的图片放到服务器的某个文件夹中，然后将图片在服务器中的路径存入数据库。本Demo也是这样做的。
- 由于用户自己保存的图片文件名可能跟其他用户同名造成冲突，因此本Demo选择了使用UUID来生成随机的文件名解决冲突。
- 但是本Demo不涉及任何有关数据库的操作，便于演示，就用原来的文件名。
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

```
package com.wu.demo.fileupload.demo.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传工具包
 */
public class FileUtils {

    /**
     *
     * @param file 文件
     * @param path 文件存放路径
     * @param fileName 源文件名
     * @return
     */
    public static boolean upload(MultipartFile file, String path, String fileName){


        // 生成新的文件名
        //String realPath = path + "/" + FileNameUtils.getFileName(fileName);

        //使用原文件名
        String realPath = path + "/" + fileName;

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }

        try {
            //保存文件
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }


}
```
