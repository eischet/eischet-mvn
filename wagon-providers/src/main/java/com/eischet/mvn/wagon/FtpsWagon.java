/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.eischet.mvn.wagon;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Configuration;

import java.io.IOException;

/**
 * FtpsWagon
 *
 *
 * @plexus.component role="org.apache.maven.wagon.Wagon"
 * role-hint="ftps"
 * instantiation-strategy="per-lookup"
 */
@Component(role = org.apache.maven.wagon.Wagon.class, hint = "eischet-ftp")
public class FtpsWagon extends FtpWagon {

    @Configuration(name = "securityProtocol", value = "TLS")
    private String securityProtocol = "TLS";

    @Configuration(name = "implicit", value = "false")
    private boolean implicit = false;

    @Configuration(name = "endpointChecking", value = "true")
    private boolean endpointChecking = true;

    @Override
    protected FTPClient createClient() {
        log.info(
                "Creating secure FTP client. Protocol: [{}], implicit mode: [{}], endpoint checking: [{}], upload url override: [{}].",
                securityProtocol,
                implicit,
                endpointChecking,
                uploadUrl);
        FTPSClient client = new FTPSClient(securityProtocol, implicit);
        client.setEndpointCheckingEnabled(endpointChecking);



        return client;
    }

    @Override
    protected void performAfterLoginSteps(final FTPClient ftp) throws IOException {
        // Wichtig für TLS auf Datenkanal:
        final FTPSClient ftps = (FTPSClient) ftp;
        ftps.execPBSZ(0);
        ftps.execPROT("P");

    }
}
