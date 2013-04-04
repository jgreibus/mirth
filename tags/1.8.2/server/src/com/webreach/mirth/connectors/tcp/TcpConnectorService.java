package com.webreach.mirth.connectors.tcp;

import java.util.Map;

import com.webreach.mirth.connectors.ConnectorService;
import com.webreach.mirth.server.util.ConnectorUtil;

public class TcpConnectorService implements ConnectorService {
    public Object invoke(String method, Object object, String sessionsId) throws Exception {
        if (method.equals("testConnection")) {
            Map<String, String> params = (Map<String, String>) object;
            String host = params.get(TCPSenderProperties.TCP_ADDRESS);
            int port = Integer.parseInt(params.get(TCPSenderProperties.TCP_PORT));
            int timeout = Integer.parseInt(params.get(TCPSenderProperties.TCP_SERVER_TIMEOUT));
            return ConnectorUtil.testConnection(host, port, timeout);
        }

        return null;
    }
}