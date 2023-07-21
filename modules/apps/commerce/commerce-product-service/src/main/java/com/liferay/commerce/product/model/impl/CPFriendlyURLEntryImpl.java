/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.exception.CPFriendlyURLEntryException;
import com.liferay.commerce.product.model.CPFriendlyURLEntry;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

/**
 * @author Marco Leo
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 * @deprecated As of Athanasius (7.3.x), replaced by {@link
 * 					 com.liferay.friendly.url.model.impl.FriendlyURLEntryImpl}
 */
@Deprecated
public class CPFriendlyURLEntryImpl extends CPFriendlyURLEntryBaseImpl {

	public static int validate(String urlTitle) {
		return validate(urlTitle, true);
	}

	public static int validate(String urlTitle, boolean checkMaxLength) {
		int maxLength = ModelHintsUtil.getMaxLength(
			CPFriendlyURLEntry.class.getName(), "urlTitle");

		if (checkMaxLength && (urlTitle.length() > maxLength)) {
			return CPFriendlyURLEntryException.TOO_LONG;
		}

		if (StringUtil.count(urlTitle, CharPool.SLASH) > 1) {
			return CPFriendlyURLEntryException.TOO_DEEP;
		}

		if (urlTitle.endsWith(StringPool.SLASH)) {
			return CPFriendlyURLEntryException.ENDS_WITH_SLASH;
		}

		if (urlTitle.contains(StringPool.DOUBLE_SLASH)) {
			return CPFriendlyURLEntryException.ADJACENT_SLASHES;
		}

		for (char c : urlTitle.toCharArray()) {
			if (!Validator.isChar(c) && !Validator.isDigit(c) &&
				(c != CharPool.DASH) && (c != CharPool.PERCENT) &&
				(c != CharPool.PERIOD) && (c != CharPool.PLUS) &&
				(c != CharPool.SLASH) && (c != CharPool.STAR) &&
				(c != CharPool.UNDERLINE)) {

				return CPFriendlyURLEntryException.INVALID_CHARACTERS;
			}
		}

		return -1;
	}

	public CPFriendlyURLEntryImpl() {
	}

	@Override
	public Locale getLocale() {
		return LocaleUtil.fromLanguageId(getLanguageId());
	}

}