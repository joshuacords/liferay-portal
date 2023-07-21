/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.ntlm.internal;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;

/**
 * @author Michael C. Han
 */
public interface NetlogonConnectionManager {

	public NetlogonConnection connect(
			String domainController, String domainControllerName,
			NtlmServiceAccount ntlmServiceAccount)
		throws IOException, NoSuchAlgorithmException, NtlmLogonException;

}