package com.wang.study.io.nio;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BlockServer {

    public static void main(String[] args) throws Exception {
        //1.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //2.获取文件通道，将客户端发送过来的图片保存为 \resource\receive.jpg 文件
        File imageFile = new File(System.getProperty("user.dir") + "\\resource\\receive.jpg");
        if (!imageFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            imageFile.createNewFile();
        }
        FileChannel fileChannel = FileChannel.open(Paths.get(imageFile.getPath()), StandardOpenOption.WRITE);

        //3.绑定端口
        serverSocketChannel.bind(new InetSocketAddress(6666));

        //4.获取客户端的连接，阻塞的
        SocketChannel socketChannel = serverSocketChannel.accept();

        //5.要使用NIO，需创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //6.将客户端传过来的图片保存到本地
        while (socketChannel.read(byteBuffer) != -1) {
            //在写之前切换为读模式
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            //读完切换写模式，让FileChannel能继续往缓冲区里写数据
            byteBuffer.clear();
        }

        //7.通知客户端，图片已经上传成功了
        byteBuffer.put("upload image success!".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();

        //8.关闭通道
        serverSocketChannel.close();
        socketChannel.close();
        fileChannel.close();
    }
}
