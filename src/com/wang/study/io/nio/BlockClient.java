package com.wang.study.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BlockClient {

    public static void main(String[] args) throws Exception {
        //1.获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6666));

        //2.获取 \resource\image.jpg 文件的路径，并获取文件通道
        String srcParentPath = System.getProperty("user.dir");
        FileChannel fileChannel = FileChannel.open(Paths.get(srcParentPath + "\\resource\\send.jpg")
                , StandardOpenOption.READ);

        //3.使用NIO，需要使用缓冲区Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //4.读取本地图片，发送给服务器
        while (fileChannel.read(byteBuffer) != -1) {
            //切换读模式
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            //读完切换写模式，让FileChannel能继续往缓冲区里写数据
            byteBuffer.clear();
        }

        //5.客户端告诉服务端，图片已经发送完毕了
        socketChannel.shutdownOutput();

        //6.接收服务端返回是否上传图片成功的消息
        int len;
        while ((len = socketChannel.read(byteBuffer)) != -1) {
            byteBuffer.flip();
            System.out.println("receive : " + new String(byteBuffer.array(), 0, len));
            byteBuffer.clear();
        }

        //7.关闭通道
        socketChannel.close();
        fileChannel.close();
    }

}
