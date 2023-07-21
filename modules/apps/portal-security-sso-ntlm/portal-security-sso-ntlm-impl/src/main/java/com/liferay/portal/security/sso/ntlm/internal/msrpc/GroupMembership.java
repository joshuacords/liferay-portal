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
public class GroupMembership extends NdrObject {

	public GroupMembership() {
	}

	public GroupMembership(int relativeId, int attributes) {
		_relativeId = relativeId;
		_attributes = attributes;
	}

	@Override
	public void decode(NdrBuffer ndrBuffer) {
		ndrBuffer.align(4);

		_relativeId = ndrBuffer.dec_ndr_long();
		_attributes = ndrBuffer.dec_ndr_long();
	}

	@Override
	public void encode(NdrBuffer ndrBuffer) {
		ndrBuffer.align(4);

		ndrBuffer.enc_ndr_long(_relativeId);
		ndrBuffer.enc_ndr_long(_attributes);
	}

	public int getAttributes() {
		return _attributes;
	}

	public int getRelativeId() {
		return _relativeId;
	}

	private int _attributes;
	private int _relativeId;

}