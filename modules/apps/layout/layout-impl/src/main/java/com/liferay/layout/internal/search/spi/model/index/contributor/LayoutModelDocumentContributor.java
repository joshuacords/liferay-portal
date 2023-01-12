/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.layout.internal.search.spi.model.index.contributor;

import com.liferay.layout.model.LayoutLocalization;
import com.liferay.layout.service.LayoutLocalizationLocalService;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Vagner B.C
 */
@Component(
	property = "indexer.class.name=com.liferay.portal.kernel.model.Layout",
	service = ModelDocumentContributor.class
)
public class LayoutModelDocumentContributor
	implements ModelDocumentContributor<Layout> {

	public static final String CLASS_NAME = Layout.class.getName();

	@Override
	public void contribute(Document document, Layout layout) {
		if (layout.isSystem() ||
			(layout.getStatus() != WorkflowConstants.STATUS_APPROVED)) {

			return;
		}

		document.addText(
			Field.DEFAULT_LANGUAGE_ID, layout.getDefaultLanguageId());
		document.addText(
			"privateLayout", String.valueOf(layout.isPrivateLayout()));
		document.addKeyword(Field.STATUS, _getStatus(layout));
		document.addText(Field.TYPE, layout.getType());

		Map<Locale, String> nameMap = layout.getNameMap();

		for (String languageId : layout.getAvailableLanguageIds()) {
			Locale locale = LocaleUtil.fromLanguageId(languageId);

			document.addText(
				_localization.getLocalizedName(Field.NAME, languageId),
				nameMap.get(locale));

			document.addText(
				Field.getLocalizedName(locale, Field.TITLE),
				layout.getName(locale));
		}

		List<LayoutLocalization> layoutLocalizations =
			_layoutLocalizationLocalService.getLayoutLocalizations(
				layout.getPlid());

		String[] availableLanguages = layout.getAvailableLanguageIds();

		Set<String> availableLanguagesSet = new HashSet<>(
			Arrays.asList(availableLanguages));

		for (LayoutLocalization layoutLocalization : layoutLocalizations) {
			if (availableLanguagesSet.contains(
					layoutLocalization.getLanguageId())) {

				document.addText(
					Field.getLocalizedName(
						layoutLocalization.getLanguageId(), Field.CONTENT),
					layoutLocalization.getContent());
			}
		}
	}

	private int _getStatus(Layout layout) {
		if (layout.isPublished()) {
			return WorkflowConstants.STATUS_APPROVED;
		}

		return WorkflowConstants.STATUS_DRAFT;
	}

	@Reference
	private Language _language;

	@Reference
	private LayoutLocalizationLocalService _layoutLocalizationLocalService;

	@Reference
	private Localization _localization;

}