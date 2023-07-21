/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.mail;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;

/**
 * @author Brian Wing Shun Chan
 * @see    com.liferay.util.mail.JavaMailUtil
 */
public class JavaMailUtil {

	public static byte[] getBytes(Part part)
		throws IOException, MessagingException {

		InputStream is = part.getInputStream();

		return FileUtil.getBytes(is);
	}

	public static String toUnicodeString(Address[] addresses) {
		return toUnicodeString((InternetAddress[])addresses);
	}

	public static String toUnicodeString(InternetAddress[] addresses) {
		if (ArrayUtil.isEmpty(addresses)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler((addresses.length * 2) - 1);

		for (int i = 0; i < addresses.length; i++) {
			if (addresses[i] != null) {
				sb.append(addresses[i].toUnicodeString());
			}

			if ((i + 1) != addresses.length) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}

}