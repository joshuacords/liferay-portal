/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.ntlm.internal;

import com.liferay.portal.security.sso.ntlm.internal.msrpc.NetlogonAuthenticator;

import java.io.IOException;

import jcifs.dcerpc.DcerpcHandle;

import jcifs.util.Encdec;

/**
 * @author Michael C. Han
 */
public class NetlogonConnection {

	public NetlogonConnection(byte[] clientCredential, byte[] sessionKey) {
		_clientCredential = clientCredential;
		_sessionKey = sessionKey;
	}

	public NetlogonAuthenticator computeNetlogonAuthenticator() {
		int timestamp = (int)System.currentTimeMillis();

		int input = Encdec.dec_uint32le(_clientCredential, 0) + timestamp;

		Encdec.enc_uint32le(input, _clientCredential, 0);

		byte[] credential = NetlogonCredentialUtil.computeNetlogonCredential(
			_clientCredential, _sessionKey);

		return new NetlogonAuthenticator(credential, timestamp);
	}

	public void disconnect() throws IOException {
		if (_dcerpcHandle != null) {
			_dcerpcHandle.close();
		}
	}

	public byte[] getClientCredential() {
		return _clientCredential;
	}

	public DcerpcHandle getDcerpcHandle() {
		return _dcerpcHandle;
	}

	public byte[] getSessionKey() {
		return _sessionKey;
	}

	public void setDcerpcHandle(DcerpcHandle dcerpcHandle) {
		_dcerpcHandle = dcerpcHandle;
	}

	private final byte[] _clientCredential;
	private DcerpcHandle _dcerpcHandle;
	private final byte[] _sessionKey;

}