package com.eischet.mvn.wagon;

import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;

public class PrintCommandListener implements ProtocolCommandListener {
    private FtpWagon wagon;

    public PrintCommandListener(FtpWagon wagon) {
        this.wagon = wagon;
    }

    @Override
    public void protocolCommandSent(ProtocolCommandEvent event) {
        wagon.fireSessionDebug("Command sent: " + event.getMessage());
    }

    @Override
    public void protocolReplyReceived(ProtocolCommandEvent event) {
        wagon.fireSessionDebug("Reply received: " + event.getMessage());
    }
}
