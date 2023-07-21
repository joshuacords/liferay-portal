/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.oauth;

import com.liferay.portal.kernel.oauth.Verifier;

/**
 * @author Brian Wing Shun Chan
 */
public class VerifierImpl implements Verifier {

	public VerifierImpl(org.scribe.model.Verifier verifier) {
		_verifier = verifier;
	}

	@Override
	public Object getWrappedVerifier() {
		return _verifier;
	}

	private final org.scribe.model.Verifier _verifier;

}