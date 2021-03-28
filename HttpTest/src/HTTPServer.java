import java.io.*;
import java.net.*;

public class HTTPServer {
    public static void main(String[] args) {
        int port;
        ServerSocket serverSocket;

        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("port = 8080 (默认)");
            port = 8080;
        }

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务器正在监听端口： " + serverSocket.getLocalPort());
            while (true) {
                try{
                    final Socket socket = serverSocket.accept();
                    System.out.println("建立了与客户的一个新的TCP连接，该客户的地址为： " +
                            socket.getInetAddress() + ":" + socket.getPort());
                    //响应客户端请求
                    service(socket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void service(Socket socket) throws Exception{
        InputStream socketIn = socket.getInputStream();
        Thread.sleep(500);
        //从输入流中能获得的字节个数
        int size = socketIn.available();
        //设置相同大小的字节数组用来存放从socket中读取的数据
        byte[] buffer = new byte[size];
        socketIn.read(buffer);
        //将字节数组转成字符串
        String request = new String(buffer);
        System.out.println(request);

        /*解析HTTP请求*/
        //获得HTTP请求的第一行
        String firstLineOfRequest = request.substring(0, request.indexOf("\r\n"));
        //解析第一行
        String[] parts = firstLineOfRequest.split(" ");
        String uri = parts[1];

        /*决定HTTP想用正文的类型，此处作了简化处理*/
        String contentType;
        if (uri.indexOf("html") != -1 || uri.indexOf("htm") != -1)
            contentType = "text/html";
        else if (uri.indexOf("jpg") != -1 || uri.indexOf("jpeg") != -1)
            contentType = "image/jpeg";
        else if(uri.indexOf("gif") != -1)
            contentType = "image/gif";
        else
            contentType = "application/octet-stream";

        /*创建Http响应结果*/
        //Http响应的第一行
        java.lang.String responseFirstLine="HTTP/1.1 200 OK\r\n";
        //HTTP响应头
        String responseHeader = "Content-Type:" + contentType + "\r\n\r\n";
        //获得读取响应正文数据的输入流
        InputStream in = HTTPServer.class.getResourceAsStream("root/"+uri);

        /*发送Http响应结果*/
        OutputStream socketOut = socket.getOutputStream();
        //发送HTTP响应的第一行
        socketOut.write(responseFirstLine.getBytes());
        //发送HTTP响应的头
        socketOut.write(responseHeader.getBytes());
        //发送HTTP响应的正文
        int len = 0;
        size = in.available();
        buffer = new byte[size];
        while ((len=in.read(buffer)) != -1)
            socketOut.write(buffer, 0, len);

        //睡眠1秒，等待客户端接收HTTP响应结果
        Thread.sleep(1000);
        socket.close();
    }
}
