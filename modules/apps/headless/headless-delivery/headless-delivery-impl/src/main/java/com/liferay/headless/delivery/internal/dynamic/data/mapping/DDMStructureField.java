/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.internal.dynamic.data.mapping;

import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Locale;

/**
 * @author Javier de Arcos
 */
public class DDMStructureField {

	public static DDMStructureField from(String ddmStructureField) {
		String[] ddmStructureParts = StringUtil.split(
			ddmStructureField, DDMIndexer.DDM_FIELD_SEPARATOR);

		String name = StringUtil.removeSubstring(
			ddmStructureParts[3], "_sortable");

		String type = name.substring(
			name.lastIndexOf(StringPool.UNDERLINE) + 1);

		name = name.substring(0, name.lastIndexOf(StringPool.UNDERLINE));

		String locale = _getSuffixLocale(name);

		if (locale != null) {
			name = StringUtil.removeSubstring(
				name, StringPool.UNDERLINE + locale);
		}

		return new DDMStructureField(
			ddmStructureParts[2], ddmStructureParts[1], locale, name, type);
	}

	public static String getNestedFieldName() {
		return StringBundler.concat(
			DDMIndexer.DDM_FIELD_ARRAY, StringPool.PERIOD,
			DDMIndexer.DDM_FIELD_NAME);
	}

	public String getDDMStructureFieldName() {
		return StringBundler.concat(
			DDMIndexer.DDM_FIELD_PREFIX, _indexType,
			DDMIndexer.DDM_FIELD_SEPARATOR, _ddmStructureId,
			DDMIndexer.DDM_FIELD_SEPARATOR, _name, _getLocaleSuffix());
	}

	public String getDDMStructureNestedFieldName() {
		return StringBundler.concat(
			DDMIndexer.DDM_FIELD_ARRAY, StringPool.PERIOD,
			DDMIndexer.DDM_VALUE_FIELD_NAME_PREFIX,
			StringUtil.upperCaseFirstLetter(_indexType), _getLocaleSuffix());
	}

	public String getDDMStructureNestedTypeSortableFieldName() {
		return StringBundler.concat(
			DDMIndexer.DDM_FIELD_ARRAY, StringPool.PERIOD,
			DDMIndexer.DDM_VALUE_FIELD_NAME_PREFIX,
			StringUtil.upperCaseFirstLetter(_indexType), _getLocaleSuffix(),
			StringPool.UNDERLINE, _type, StringPool.UNDERLINE,
			Field.SORTABLE_FIELD_SUFFIX);
	}

	public String getLocale() {
		return _locale;
	}

	private static String _getSuffixLocale(String string) {
		for (Locale availableLocale : LanguageUtil.getAvailableLocales()) {
			String availableLanguageId = LocaleUtil.toLanguageId(
				availableLocale);

			if (string.endsWith(availableLanguageId)) {
				return availableLanguageId;
			}
		}

		return null;
	}

	private DDMStructureField(
		String ddmStructureId, String indexType, String locale, String name,
		String type) {

		_ddmStructureId = ddmStructureId;
		_indexType = indexType;
		_locale = locale;
		_name = name;
		_type = type;
	}

	private String _getLocaleSuffix() {
		if (_locale == null) {
			return StringPool.BLANK;
		}

		return StringPool.UNDERLINE.concat(_locale);
	}

	private final String _ddmStructureId;
	private final String _indexType;
	private final String _locale;
	private final String _name;
	private final String _type;

}