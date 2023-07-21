/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.search;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class ButtonSearchEntry extends TextSearchEntry {

	@Override
	public Object clone() {
		ButtonSearchEntry buttonSearchEntry = new ButtonSearchEntry();

		BeanPropertiesUtil.copyProperties(this, buttonSearchEntry);

		return buttonSearchEntry;
	}

	@Override
	public void print(
			Writer writer, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		StringBuilder sb = new StringBuilder();

		sb.append("<input type=\"button\" value=\"");
		sb.append(getName());
		sb.append("\" onClick=\"");
		sb.append(getHref());
		sb.append("\">");

		writer.write(sb.toString());
	}

}