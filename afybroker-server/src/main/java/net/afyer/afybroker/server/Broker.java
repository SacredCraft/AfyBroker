package net.afyer.afybroker.server;

import com.alipay.remoting.rpc.RpcServer;
import lombok.Getter;
import net.afyer.afybroker.server.plugin.PluginManager;
import net.afyer.afybroker.server.proxy.BrokerClientProxyManager;
import net.afyer.afybroker.server.proxy.BrokerPlayer;
import net.afyer.afybroker.server.proxy.BrokerPlayerManager;
import net.afyer.afybroker.server.scheduler.BrokerScheduler;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.Executor;

/**
 * @author Nipuru
 * @since 2022/8/13 9:34
 */
public class Broker {

    /**
     * broker 服务端
     */
    @Getter
    private static BrokerServer server;

    private Broker() {}

    /** 设置 broker 服务端 */
    public static void setServer(BrokerServer brokerServer) {
        if (Broker.server != null) {
            throw new UnsupportedOperationException("Cannot redefine singleton instance");
        }
        Broker.server = brokerServer;
    }

    /** 获取 rpc 服务端 */
    public static RpcServer getRpcServer() {
        return server.getRpcServer();
    }

    /**
     * 获取服务端端口
     */
    public static int getPort() {
        return server.getPort();
    }

    /**
     * 获取服务端事物线程池
     */
    public static Executor getBizThread() {
        return server.getBizThread();
    }

    /**
     * 获取插件管理器
     */
    public static PluginManager getPluginManager() {
        return server.getPluginManager();
    }

    /**
     * 获取计划任务管理器
     */
    public static BrokerScheduler getScheduler() {
        return server.getScheduler();
    }

    /**
     * 获取客户端代理管理器
     */
    public static BrokerClientProxyManager getBrokerClientProxyManager() {
        return server.getBrokerClientProxyManager();
    }

    /**
     * 获取玩家代理管理器
     */
    public static BrokerPlayerManager getBrokerPlayerManager() {
        return server.getBrokerPlayerManager();
    }

    /**
     * 通过uid获取玩家代理
     */
    @Nullable
    public static BrokerPlayer getPlayer(UUID uuid) {
        return server.getPlayer(uuid);
    }

    /**
     * 通过名称获取玩家代理
     */
    @Nullable
    public static BrokerPlayer getPlayer(String name) {
        return server.getPlayer(name);
    }

    /**
     * 获取插件目录
     */
    public static File getPluginsFolder() {
        return server.getPluginsFolder();
    }
}
