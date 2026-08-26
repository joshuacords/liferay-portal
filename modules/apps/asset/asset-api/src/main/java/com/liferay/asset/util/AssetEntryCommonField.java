/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.util;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Joshua Cords
 */
public enum AssetEntryCommonField {

	CREATE_DATE(Field.CREATE_DATE, "created-date", "date", false, true),
	DISPLAY_DATE(Field.DISPLAY_DATE, "display-date", "date", false, true),
	EXPIRATION_DATE(Field.EXPIRATION_DATE, "expiration-date", "date", false, true),
	EXTERNAL_REFERENCE_CODE(
		"externalReferenceCode", "external-reference-code", "text", false, true),
	MODIFIED_DATE(Field.MODIFIED_DATE, "modified-date", "date", false, true),
	PRIORITY(Field.PRIORITY, "priority", "decimal", false, true),
	PUBLISH_DATE(Field.PUBLISH_DATE, "publish-date", "date", false, true),
	REVIEW_DATE(Field.REVIEW_DATE, "review-date", "date", false, true),
	STATUS(Field.STATUS, "status", "integer", false, true),
	TITLE(Field.TITLE, "title", "text", true, true),
	USER_NAME(Field.USER_NAME, "author-name", "text", false, true),
	VIEW_COUNT("viewCount", "view-count", "integer", false, true);

	public static AssetEntryCommonField fetchByName(String name) {
		return _assetEntryCommonFields.get(name);
	}

	public String getLabelKey() {
		return _labelKey;
	}

	public String getName() {
		return _name;
	}

	public int getSortType() {
		if (_type.equals("date")) {
			return Sort.LONG_TYPE;
		}

		if (_type.equals("decimal")) {
			return Sort.DOUBLE_TYPE;
		}

		if (_type.equals("integer")) {
			return Sort.INT_TYPE;
		}

		return Sort.STRING_TYPE;
	}

	public String getType() {
		return _type;
	}

	public boolean isLocalized() {
		return _localized;
	}

	public boolean isSortable() {
		return _sortable;
	}

	private AssetEntryCommonField(
		String name, String labelKey, String type, boolean localized,
		boolean sortable) {

		_labelKey = labelKey;
		_localized = localized;
		_name = name;
		_sortable = sortable;
		_type = type;
	}

	private static final Map<String, AssetEntryCommonField>
		_assetEntryCommonFields = new HashMap<>();

	static {
		for (AssetEntryCommonField assetEntryCommonField : values()) {
			_assetEntryCommonFields.put(
				assetEntryCommonField.getName(), assetEntryCommonField);
		}
	}

	private final String _labelKey;
	private final boolean _localized;
	private final String _name;
	private final boolean _sortable;
	private final String _type;

}
