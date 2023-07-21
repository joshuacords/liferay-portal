/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.internal.util;

import com.liferay.osb.provisioning.license.util.OSBCustomSQL;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = OSBCustomSQL.class)
public class OSBCustomSQLImpl implements OSBCustomSQL {

	public String[] keywords(String keywords) {
		if (Validator.isNull(keywords) ||
			!keywords.startsWith(StringPool.QUOTE) ||
			!keywords.endsWith(StringPool.QUOTE)) {

			List<String> keywordsList = new ArrayList<>();

			String[] keywordsArray = _customSQL.keywords(keywords);

			for (String curKeywords : keywordsArray) {
				if (Validator.isNull(curKeywords)) {
					continue;
				}

				if (curKeywords.length() > 3) {
					keywordsList.add(curKeywords);
				}
			}

			return keywordsList.toArray(new String[0]);
		}

		keywords = StringUtil.unquote(keywords);

		return new String[] {StringUtil.quote(keywords, StringPool.PERCENT)};
	}

	@Reference
	private CustomSQL _customSQL;

}