package ch.cyberduck.core.cryptomator;

/*
 * Copyright (c) 2002-2017 iterate GmbH. All rights reserved.
 * https://cyberduck.io/
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
 */

import ch.cyberduck.core.ConnectionCallback;
import ch.cyberduck.core.Host;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.Session;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.features.TransferAcceleration;
import ch.cyberduck.core.features.Vault;
import ch.cyberduck.core.http.HttpSession;
import ch.cyberduck.core.ssl.X509KeyManager;
import ch.cyberduck.core.ssl.X509TrustManager;
import ch.cyberduck.core.transfer.TransferStatus;

public class CryptoTransferAccelerationFeature<C extends HttpSession<?>> implements TransferAcceleration<C> {

    private final Session<?> session;
    private final TransferAcceleration<C> delegate;
    private final Vault vault;

    public CryptoTransferAccelerationFeature(final Session<?> session, final TransferAcceleration<C> delegate, final Vault vault) {
        this.session = session;
        this.delegate = delegate;
        this.vault = vault;
    }

    @Override
    public boolean getStatus(final Path file) throws BackgroundException {
        return delegate.getStatus(vault.encrypt(session, file));
    }

    @Override
    public void setStatus(final Path file, final boolean enabled) throws BackgroundException {
        delegate.setStatus(vault.encrypt(session, file), enabled);
    }

    @Override
    public boolean prompt(final Host bookmark, final Path file, final TransferStatus status, final ConnectionCallback prompt) throws BackgroundException {
        return delegate.prompt(bookmark, vault.encrypt(session, file), status, prompt);
    }

    @Override
    public C open(final Host bookmark, final Path file, final X509TrustManager trust, final X509KeyManager key) throws BackgroundException {
        return delegate.open(bookmark, vault.encrypt(session, file), trust, key);
    }
}
