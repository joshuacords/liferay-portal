/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.info.display.contributor.field;

import com.liferay.info.display.contributor.field.BaseInfoDisplayContributorField;
import com.liferay.info.display.contributor.field.InfoDisplayContributorField;
import com.liferay.info.display.contributor.field.InfoDisplayContributorFieldType;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = "model.class.name=com.liferay.journal.model.JournalArticle",
	service = InfoDisplayContributorField.class
)
public class JournalArticleSmallImageAssetDisplayContributorField
	extends BaseInfoDisplayContributorField<JournalArticle> {

	@Override
	public String getKey() {
		return "smallImage";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			locale, "com.liferay.journal.lang");

		return LanguageUtil.get(resourceBundle, "small-image");
	}

	@Override
	public InfoDisplayContributorFieldType getType() {
		return InfoDisplayContributorFieldType.IMAGE;
	}

	@Override
	public Object getValue(JournalArticle article, Locale locale) {
		ThemeDisplay themeDisplay = getThemeDisplay();

		if (themeDisplay != null) {
			String url = article.getArticleImageURL(themeDisplay);

			return JSONUtil.put("url", (url != null) ? url : StringPool.BLANK);
		}

		return StringPool.BLANK;
	}

}