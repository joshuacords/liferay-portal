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
public class NetlogonNetworkInfo extends NdrObject {

	public NetlogonNetworkInfo(
		NetlogonIdentityInfo netlogonIdentityInfo, byte[] lmChallenge,
		byte[] ntChallengeResponse, byte[] lmChallengeResponse) {

		_netlogonIdentityInfo = netlogonIdentityInfo;
		_lmChallenge = lmChallenge;
		_ntChallengeResponse = ntChallengeResponse;
		_lmChallengeResponse = lmChallengeResponse;
	}

	@Override
	public void decode(NdrBuffer ndrBuffer) {
	}

	@Override
	public void encode(NdrBuffer ndrBuffer) {
		ndrBuffer.align(4);

		_netlogonIdentityInfo.encode(ndrBuffer);

		int lmChallengeIndex = ndrBuffer.index;

		ndrBuffer.advance(8);

		ndrBuffer.enc_ndr_short((short)_ntChallengeResponse.length);
		ndrBuffer.enc_ndr_short((short)_ntChallengeResponse.length);
		ndrBuffer.enc_ndr_referent(_ntChallengeResponse, 1);

		ndrBuffer.enc_ndr_short((short)_lmChallengeResponse.length);
		ndrBuffer.enc_ndr_short((short)_lmChallengeResponse.length);
		ndrBuffer.enc_ndr_referent(_lmChallengeResponse, 1);

		_netlogonIdentityInfo.encodeLogonDomainName(ndrBuffer);
		_netlogonIdentityInfo.encodeUserName(ndrBuffer);
		_netlogonIdentityInfo.encodeWorkStationName(ndrBuffer);

		ndrBuffer = ndrBuffer.derive(lmChallengeIndex);

		for (int i = 0; i < 8; i++) {
			ndrBuffer.enc_ndr_small(_lmChallenge[i]);
		}

		encodeChallengeResponse(ndrBuffer, _ntChallengeResponse);
		encodeChallengeResponse(ndrBuffer, _lmChallengeResponse);
	}

	protected void encodeChallengeResponse(
		NdrBuffer ndrBuffer, byte[] challenge) {

		ndrBuffer = ndrBuffer.deferred;

		ndrBuffer.enc_ndr_long(challenge.length);
		ndrBuffer.enc_ndr_long(0);
		ndrBuffer.enc_ndr_long(challenge.length);

		int index = ndrBuffer.index;

		ndrBuffer.advance(challenge.length);

		ndrBuffer = ndrBuffer.derive(index);

		for (byte b : challenge) {
			ndrBuffer.enc_ndr_small(b);
		}
	}

	private final byte[] _lmChallenge;
	private final byte[] _lmChallengeResponse;
	private final NetlogonIdentityInfo _netlogonIdentityInfo;
	private final byte[] _ntChallengeResponse;

}