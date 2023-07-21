/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.ntlm.internal.msrpc;

import jcifs.dcerpc.UnicodeString;
import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrObject;
import jcifs.dcerpc.rpc;

/**
 * @author Marcellus Tavares
 */
public class NetlogonIdentityInfo extends NdrObject {

	public NetlogonIdentityInfo(
		String logonDomainName, int parameterControl, int reservedLow,
		int reservedHigh, String userName, String workstation) {

		_logonDomainName = new UnicodeString(logonDomainName, false);
		_parameterControl = parameterControl;
		_reservedLow = reservedLow;
		_reservedHigh = reservedHigh;
		_userName = new UnicodeString(userName, false);
		_workstation = new UnicodeString(workstation, false);
	}

	@Override
	public void decode(NdrBuffer ndrBuffer) {
	}

	@Override
	public void encode(NdrBuffer ndrBuffer) {
		ndrBuffer.enc_ndr_short(_logonDomainName.length);
		ndrBuffer.enc_ndr_short(_logonDomainName.maximum_length);
		ndrBuffer.enc_ndr_referent(_logonDomainName.buffer, 1);
		ndrBuffer.enc_ndr_long(_parameterControl);
		ndrBuffer.enc_ndr_long(_reservedLow);
		ndrBuffer.enc_ndr_long(_reservedHigh);
		ndrBuffer.enc_ndr_short(_userName.length);
		ndrBuffer.enc_ndr_short(_userName.maximum_length);
		ndrBuffer.enc_ndr_referent(_userName.buffer, 1);
		ndrBuffer.enc_ndr_short(_workstation.length);
		ndrBuffer.enc_ndr_short(_workstation.maximum_length);
		ndrBuffer.enc_ndr_referent(_workstation.buffer, 1);
	}

	public void encodeLogonDomainName(NdrBuffer ndrBuffer) {
		encodeUnicodeString(ndrBuffer, _logonDomainName);
	}

	public void encodeUserName(NdrBuffer ndrBuffer) {
		encodeUnicodeString(ndrBuffer, _userName);
	}

	public void encodeWorkStationName(NdrBuffer ndrBuffer) {
		encodeUnicodeString(ndrBuffer, _workstation);
	}

	protected void encodeUnicodeString(
		NdrBuffer ndrBuffer, rpc.unicode_string string) {

		ndrBuffer = ndrBuffer.deferred;

		int stringBufferl = string.length / 2;

		int stringBuffers = string.maximum_length / 2;

		ndrBuffer.enc_ndr_long(stringBuffers);

		ndrBuffer.enc_ndr_long(0);
		ndrBuffer.enc_ndr_long(stringBufferl);

		int stringBufferIndex = ndrBuffer.index;

		ndrBuffer.advance(2 * stringBufferl);

		ndrBuffer = ndrBuffer.derive(stringBufferIndex);

		for (int i = 0; i < stringBufferl; i++) {
			ndrBuffer.enc_ndr_short(string.buffer[i]);
		}
	}

	private final rpc.unicode_string _logonDomainName;
	private final int _parameterControl;
	private final int _reservedHigh;
	private final int _reservedLow;
	private final rpc.unicode_string _userName;
	private final rpc.unicode_string _workstation;

}