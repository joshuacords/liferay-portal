/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.ntlm.internal;

import jcifs.util.DES;

/**
 * @author Michael C. Han
 */
public class NetlogonCredentialUtil {

	public static byte[] computeNetlogonCredential(
		byte[] input, byte[] sessionKey) {

		byte[] k1 = new byte[7];
		byte[] k2 = new byte[7];

		System.arraycopy(sessionKey, 0, k1, 0, 7);
		System.arraycopy(sessionKey, 7, k2, 0, 7);

		DES k3 = new DES(k1);
		DES k4 = new DES(k2);

		byte[] output1 = new byte[8];
		byte[] output2 = new byte[8];

		k3.encrypt(input, output1);
		k4.encrypt(output1, output2);

		return output2;
	}

}