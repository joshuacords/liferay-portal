/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.renderer;

import com.liferay.fragment.constants.FragmentEntryLinkConstants;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * @author Jorge Ferrer
 */
public class DefaultFragmentRendererContext implements FragmentRendererContext {

	public DefaultFragmentRendererContext(FragmentEntryLink fragmentEntryLink) {
		_fragmentEntryLink = fragmentEntryLink;
	}

	public Optional<Map<String, Object>> getFieldValuesOptional() {
		return Optional.ofNullable(_fieldValues);
	}

	@Override
	public FragmentEntryLink getFragmentEntryLink() {
		return _fragmentEntryLink;
	}

	@Override
	public Locale getLocale() {
		return _locale;
	}

	@Override
	public String getMode() {
		return _mode;
	}

	@Override
	public long getPreviewClassPK() {
		return _previewClassPK;
	}

	@Override
	public int getPreviewType() {
		return _previewType;
	}

	@Override
	public long[] getSegmentsExperienceIds() {
		return _segmentsExperienceIds;
	}

	public void setFieldValues(Map<String, Object> fieldValues) {
		_fieldValues = fieldValues;
	}

	public void setLocale(Locale locale) {
		_locale = locale;
	}

	public void setMode(String mode) {
		_mode = mode;
	}

	public void setPreviewClassPK(long previewClassPK) {
		_previewClassPK = previewClassPK;
	}

	public void setPreviewType(int previewType) {
		_previewType = previewType;
	}

	public void setSegmentsExperienceIds(long[] segmentsExperienceIds) {
		_segmentsExperienceIds = segmentsExperienceIds;
	}

	private Map<String, Object> _fieldValues;
	private final FragmentEntryLink _fragmentEntryLink;
	private Locale _locale = LocaleUtil.getMostRelevantLocale();
	private String _mode = FragmentEntryLinkConstants.VIEW;
	private long _previewClassPK;
	private int _previewType;
	private long[] _segmentsExperienceIds = new long[0];

}