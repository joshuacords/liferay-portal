/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.ntlm.internal.msrpc;

import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrObject;

/**
 * @author Marcellus Tavares
 */
public class NetlogonAuthenticator extends NdrObject {

	public NetlogonAuthenticator() {
		_credential = new byte[8];
	}

	public NetlogonAuthenticator(byte[] credential, int timestamp) {
		_credential = credential;
		_timestamp = timestamp;
	}

	@Override
	public void decode(NdrBuffer ndrBuffer) {
		ndrBuffer.align(4);

		int index = ndrBuffer.index;

		ndrBuffer.advance(8);

		_timestamp = ndrBuffer.dec_ndr_long();

		ndrBuffer = ndrBuffer.derive(index);

		for (int i = 0; i < 8; i++) {
			_credential[i] = (byte)ndrBuffer.dec_ndr_small();
		}
	}

	@Override
	public void encode(NdrBuffer ndrBuffer) {
		ndrBuffer.align(4);

		int index = ndrBuffer.index;

		ndrBuffer.advance(8);

		ndrBuffer.enc_ndr_long(_timestamp);

		ndrBuffer = ndrBuffer.derive(index);

		for (int i = 0; i < 8; i++) {
			ndrBuffer.enc_ndr_small(_credential[i]);
		}
	}

	private final byte[] _credential;
	private int _timestamp;

}