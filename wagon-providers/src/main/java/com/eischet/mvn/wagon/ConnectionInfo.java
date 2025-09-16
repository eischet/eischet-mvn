package com.eischet.mvn.wagon;

import org.apache.maven.wagon.repository.Repository;
import org.apache.maven.wagon.repository.RepositoryPermissions;

public class ConnectionInfo {
    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final boolean passiveMode;
    private final String controlEncoding;
    private final String basedir;
    private final RepositoryPermissions permissions;

    public ConnectionInfo(String host,
                          int port,
                          String basedir,
                          String username,
                          String password,
                          boolean passiveMode,
                          String controlEncoding,
                          final RepositoryPermissions permissions) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.passiveMode = passiveMode;
        this.controlEncoding = controlEncoding;
        this.basedir = basedir;
        this.permissions = permissions;

    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isPassiveMode() {
        return passiveMode;
    }

    public String getControlEncoding() {
        return controlEncoding;
    }

    public String getBasedir() {
        return basedir;
    }

    public RepositoryPermissions getPermissions() {
        return permissions;
    }
}
