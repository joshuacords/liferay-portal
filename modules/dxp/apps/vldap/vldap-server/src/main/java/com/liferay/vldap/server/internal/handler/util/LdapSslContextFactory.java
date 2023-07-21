/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.handler.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.vldap.server.internal.util.PortletPropsValues;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.security.KeyStore;
import java.security.Security;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class LdapSslContextFactory {

	public static SSLContext getSSLContext(boolean server) {
		return _ldapSslContextFactory._getSSLContext(server);
	}

	private LdapSslContextFactory() {
		SSLContext clientSSLContext = null;
		SSLContext serverSSLContext = null;

		try {
			clientSSLContext = _createClientSSLContext();
			serverSSLContext = _createServerSSLContext();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		_clientSSLContext = clientSSLContext;
		_serverSSLContext = serverSSLContext;
	}

	private SSLContext _createClientSSLContext() throws Exception {
		SSLContext sslContext = SSLContext.getInstance(
			PortletPropsValues.SSL_PROTOCOL);

		TrustManagerFactory trustManagerFactory =
			TrustManagerFactory.getInstance(
				TrustManagerFactory.getDefaultAlgorithm());

		sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

		return sslContext;
	}

	private SSLContext _createServerSSLContext() throws Exception {
		KeyStore keyStore = KeyStore.getInstance("JKS");

		InputStream inputStream = null;

		try {
			File file = new File(PortletPropsValues.SSL_KEYSTORE_FILE_NAME);

			if (!file.exists()) {
				throw new IOException(file.toString() + " does not exist");
			}

			inputStream = new FileInputStream(file);

			keyStore.load(
				inputStream, PortletPropsValues.SSL_KEYSTORE_PASSWORD);
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (IOException ioException) {
					if (_log.isDebugEnabled()) {
						_log.debug(ioException, ioException);
					}
				}
			}
		}

		String algorithm = Security.getProperty(
			"ssl.KeyManagerFactory.algorithm");

		if (algorithm == null) {
			algorithm = KeyManagerFactory.getDefaultAlgorithm();
		}

		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
			algorithm);

		keyManagerFactory.init(
			keyStore, PortletPropsValues.SSL_KEYSTORE_PASSWORD);

		SSLContext sslContext = SSLContext.getInstance(
			PortletPropsValues.SSL_PROTOCOL);

		TrustManagerFactory trustManagerFactory =
			TrustManagerFactory.getInstance(
				TrustManagerFactory.getDefaultAlgorithm());

		sslContext.init(
			keyManagerFactory.getKeyManagers(),
			trustManagerFactory.getTrustManagers(), null);

		return sslContext;
	}

	private SSLContext _getSSLContext(boolean server) {
		if (server) {
			return _serverSSLContext;
		}

		return _clientSSLContext;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LdapSslContextFactory.class);

	private static final LdapSslContextFactory _ldapSslContextFactory =
		new LdapSslContextFactory();

	private final SSLContext _clientSSLContext;
	private final SSLContext _serverSSLContext;

}