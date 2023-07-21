/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.io;

import com.liferay.petra.string.StringBundler;

import java.io.OutputStream;

/**
 * @author Michael C. Han
 */
public class StringOutputStream extends OutputStream {

	@Override
	public String toString() {
		return _sb.toString();
	}

	@Override
	public void write(int b) {
		_sb.append(b);
	}

	private final StringBundler _sb = new StringBundler();

}