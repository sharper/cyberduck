package ch.cyberduck.core.sftp;

/*
 * Copyright (c) 2002-2013 David Kocher. All rights reserved.
 * http://cyberduck.ch/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * Bug fixes, suggestions and comments should be sent to feedback@cyberduck.ch
 */

import ch.cyberduck.core.Host;
import ch.cyberduck.core.LoginCallback;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.threading.CancelCallback;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

import net.schmizz.sshj.userauth.password.PasswordFinder;
import net.schmizz.sshj.userauth.password.Resource;

/**
 * @version $Id$
 */
public class SFTPPasswordAuthentication implements SFTPAuthentication {
    private static final Logger log = Logger.getLogger(SFTPPasswordAuthentication.class);

    private SFTPSession session;

    public SFTPPasswordAuthentication(final SFTPSession session) {
        this.session = session;
    }

    @Override
    public boolean authenticate(final Host host, final LoginCallback prompt, final CancelCallback cancel)
            throws BackgroundException {
        if(StringUtils.isBlank(host.getCredentials().getPassword())) {
            return false;
        }
        if(log.isDebugEnabled()) {
            log.debug(String.format("Login using password authentication with credentials %s", host.getCredentials()));
        }
        try {
            // Use both password and keyboard-interactive
            session.getClient().authPassword(host.getCredentials().getUsername(), new PasswordFinder() {
                @Override
                public char[] reqPassword(final Resource<?> resource) {
                    return host.getCredentials().getPassword().toCharArray();
                }

                @Override
                public boolean shouldRetry(final Resource<?> resource) {
                    return false;
                }
            });
            return session.getClient().isAuthenticated();
        }
        catch(IOException e) {
            throw new SFTPExceptionMappingService().map(e);
        }
    }
}