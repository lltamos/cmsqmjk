package com.quanmin.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
public class NIOTest {
    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port=Integer.parseInt(args[0]);
        } else {
            port=8080;
        }
        new DiscardServer(port).run();
    }


    static class DiscardServer {
        private int port;

        public DiscardServer(int port) {
            this.port=port;
        }

        public void run() throws Exception {
            EventLoopGroup bossGroup=new NioEventLoopGroup(); // (1)
            EventLoopGroup workerGroup=new NioEventLoopGroup();
            try {
                ServerBootstrap b=new ServerBootstrap(); // (2)
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class) // (3)
                        .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new DiscardServerHandler());
                            }
                        })
                        .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                        .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

                ChannelFuture f=b.bind(port).sync(); // (7)

                f.channel().closeFuture().sync();
                System.out.println(1);
            } finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
            }
        }


    }

    static class DiscardServerHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
            // 以静默方式丢弃接收的数据
            ctx.write(msg); // (1)
            ctx.flush(); // (2)
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
            // 出现异常时关闭连接。
            cause.printStackTrace();
            ctx.close();
        }


    }

    static class EchoServerHandler {


    }


}
