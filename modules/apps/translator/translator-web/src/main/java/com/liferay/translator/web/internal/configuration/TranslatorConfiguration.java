/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.translator.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Raymond Augé
 * @author Peter Fellwock
 */
@ExtendedObjectClassDefinition(category = "localization")
@Meta.OCD(
	id = "com.liferay.translator.web.internal.configuration.TranslatorConfiguration",
	localization = "content/Language", name = "translator-configuration-name"
)
public interface TranslatorConfiguration {

	public static final String TRANSLATOR_TRANSLATION =
		"TRANSLATOR_TRANSLATION";

	@Meta.AD(
		deflt = "ar|bg|ca|cs|da|de|el|en|es|et|fi|fr|hi_IN|ht|hu|in|it|iw|ja|ko|lt|lv|mww|nb|nl|pl|pt_PT|ro|ru|sk|sl|sv|th|tr|uk|vi|zh_CN|zh_TW",
		id = "language.ids", name = "language-ids", required = false
	)
	public String[] languageIds();

	@Meta.AD(
		deflt = "en_es", id = "translation.id", name = "translation-id",
		required = false
	)
	public String translationId();

}