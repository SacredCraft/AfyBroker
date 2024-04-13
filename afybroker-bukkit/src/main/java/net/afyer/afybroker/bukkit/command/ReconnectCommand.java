package net.afyer.afybroker.bukkit.command;

import com.alipay.remoting.exception.RemotingException;
import net.afyer.afybroker.bukkit.AfyBroker;
import net.afyer.afybroker.core.BrokerClientType;
import net.afyer.afybroker.core.message.BroadcastChatMessage;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author Nipuru
 * @since 2022/8/11 18:37
 */
public class ReconnectCommand extends AbstractTabExecutor {

    private final AfyBroker plugin;

    public ReconnectCommand(AfyBroker plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getCommand() {
        return "reconnect";
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        return sender.hasPermission("afyer.afybroker.command.reconnect");
    }

    @Override
    public boolean canConsoleExecute() {
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        if (AfyBroker.getInstance().isConnected()) {
            sender.sendMessage("already connected");
            return true;
        }
        try {
            AfyBroker.getInstance().getBrokerClient().startup();
            AfyBroker.getInstance().getBrokerClient().ping();
            AfyBroker.getInstance().setConnected(true);
        } catch (RemotingException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return Arrays.stream(BrokerClientType.values()).map(Enum::name).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
