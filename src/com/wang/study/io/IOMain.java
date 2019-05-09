package com.wang.study.io;


import java.io.File;
import java.io.RandomAccessFile;

public class IOMain {

    public static void main(String[] args) throws Exception {
        String srcParentPath = System.getProperty("user.dir");
        //列出指定目录的全部内容
        listFiles(new File(srcParentPath));
        //使用RandomAccessFile对文件进行操作
        userRandomAccessFile(srcParentPath);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void userRandomAccessFile(String srcParentPath) throws Exception {
        File file = new File(srcParentPath + File.separator + "resource", "random.txt");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        /*
         * RandomAccessFile的第二个参数表示打开文件的方式：
         * r   , 表示以只读方式打开文件，调用任何 write 方法，将抛出 IOException
         * rw  , 表示以读写的方式打开文件，如果该文件不存在，则会创建该文件
         * rws , 表示以读写的方式打开文件，还要求对文件的内容或元数据的每个更新都同步写入到底层存储设备
         * rwd , 表示以读写的方式打开文件，还要求对文件的内容的每个更新都同步写入到底层存储设备
         */
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        /*
         * 准备写入的数据格式如下：
         * |----------------------|
         * |      zhangsan        |
         * |      lisi            |
         * |      wangwu          |
         * |----------------------|
         */
        raf.writeBytes("zhangsan");
        raf.writeBytes("\r\n");       //换行
        raf.writeBytes("lisi");
        raf.writeBytes("\r\n");
        raf.writeBytes("wangwu");

        //读取数据
        raf.seek(0);
        System.out.println(raf.readLine());
        System.out.println(raf.readLine());
        System.out.println(raf.readLine());
        raf.close();
    }

    private static void listFiles(File srcFile) {
        if (srcFile == null) {
            return;
        }

        if (srcFile.isDirectory()) {
            File[] files = srcFile.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    listFiles(file);
                }
            }
        } else {
            System.out.println(srcFile.getPath());
        }
    }

}
