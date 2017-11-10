
package com.lecloud.springbootlearn.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;


@Component
public class EmailUtil {


    @Value("${mail.host}")
    private String host;

    @Value("${mail.from.address}")
    private String fromAddress;

    @Value("${mail.from.name}")
    private String fromName;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.sendto}")
    private String sendto;

    public void sendMail(String subject, String content) throws Exception {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(host);
        email.setFrom(fromAddress, fromName);
        email.setAuthenticator(new DefaultAuthenticator(username, password));
        email.setCharset("UTF-8");
        email.setSubject(subject);
        email.setHtmlMsg(content);
        email.setSSL(false);
        for (String str : sendto.split(",")) {
            email.addTo(str, str);
        }
        email.send();
    }
}
