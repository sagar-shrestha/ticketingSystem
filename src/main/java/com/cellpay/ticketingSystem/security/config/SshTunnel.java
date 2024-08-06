package com.cellpay.ticketingSystem.security.config;


import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import jakarta.annotation.PostConstruct;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.LocalPortForwarder;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.userauth.keyprovider.OpenSSHKeyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SshTunnel {

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${ssh.user}")
    private String user;

    @Value("${ssh.host}")
    private String host;

    @Value("${ssh.port}")
    private int port;

    @Value("${ssh.private.key}")
    private Resource privateKeyResource;

    @Value("${ssh.private.key}")
    private String privateKey;

    @Value("${ssh.local.host}")
    private String localhost;

    @Value("${ssh.local.port}")
    private int localport;

    @Value("${ssh.remote.host}")
    private String remotehost;

    @Value("${ssh.remote.port}")
    private int remoteport;


    @PostConstruct
    public void init() {
        try {
            JSch jsch = new JSch();
            Resource resource = resourceLoader.getResource(privateKey);
            jsch.addIdentity(resource.getFile().getAbsolutePath());
            Session session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            session.setPortForwardingL(localport, remotehost, remoteport);
        } catch (JSchException | IOException e) {
            throw new RuntimeException(e);
        }

//        try {
//            SSHClient sshClient = new SSHClient();
//            sshClient.addHostKeyVerifier(new PromiscuousVerifier());
//            sshClient.connect(host, port);
//
//            OpenSSHKeyFile keyFile = new OpenSSHKeyFile();
//            keyFile.init(privateKeyResource.getFile(), null);
//            sshClient.authPublickey(user, keyFile);
//
//            LocalPortForwarder prams = new LocalPortForwarder.Parameters(localhost, localport, remotehost, remoteport);
//            LocalPortForwarder forwarder = sshClient.newLocalPortForwarder(prams);
//            new Thread(() -> {
//                try {
//                    forwarder.listen();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
