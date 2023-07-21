/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.processor;

import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.fragment.constants.FragmentEntryLinkConstants;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;

/**
 * @author Lance Ji
 */
public interface FragmentEntryProcessorRegistry {

	public void deleteFragmentEntryLinkData(
		FragmentEntryLink fragmentEntryLink);

	public default JSONArray getAvailableTagsJSONArray() {
		return null;
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public default JSONObject getDefaultEditableValuesJSONObject(String html) {
		return null;
	}

	public JSONObject getDefaultEditableValuesJSONObject(
		String html, String configuration);

	public default String processFragmentEntryLinkCSS(
			FragmentEntryLink fragmentEntryLink,
			FragmentEntryProcessorContext fragmentEntryProcessorContext)
		throws PortalException {

		return fragmentEntryLink.getCss();
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public default String processFragmentEntryLinkCSS(
			FragmentEntryLink fragmentEntryLink, String mode, Locale locale,
			long[] segmentsExperienceIds)
		throws PortalException {

		return processFragmentEntryLinkCSS(
			fragmentEntryLink, mode, locale, segmentsExperienceIds, 0);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public default String processFragmentEntryLinkCSS(
			FragmentEntryLink fragmentEntryLink, String mode, Locale locale,
			long[] segmentsExperienceIds, long previewClassPK)
		throws PortalException {

		return processFragmentEntryLinkCSS(
			fragmentEntryLink, mode, locale, segmentsExperienceIds,
			previewClassPK, AssetRendererFactory.TYPE_LATEST_APPROVED);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public default String processFragmentEntryLinkCSS(
			FragmentEntryLink fragmentEntryLink, String mode, Locale locale,
			long[] segmentsExperienceIds, long previewClassPK, int previewType)
		throws PortalException {

		DefaultFragmentEntryProcessorContext
			defaultFragmentEntryProcessorContext =
				new DefaultFragmentEntryProcessorContext(
					null, null, mode, locale);

		defaultFragmentEntryProcessorContext.setPreviewClassPK(previewClassPK);
		defaultFragmentEntryProcessorContext.setPreviewType(previewType);
		defaultFragmentEntryProcessorContext.setSegmentsExperienceIds(
			segmentsExperienceIds);

		return processFragmentEntryLinkCSS(
			fragmentEntryLink, defaultFragmentEntryProcessorContext);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public default String processFragmentEntryLinkHTML(
			FragmentEntryLink fragmentEntryLink)
		throws PortalException {

		return processFragmentEntryLinkHTML(
			fragmentEntryLink, FragmentEntryLinkConstants.EDIT);
	}

	public default String processFragmentEntryLinkHTML(
			FragmentEntryLink fragmentEntryLink,
			FragmentEntryProcessorContext fragmentEntryProcessorContext)
		throws PortalException {

		return fragmentEntryLink.getHtml();
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public default String processFragmentEntryLinkHTML(
			FragmentEntryLink fragmentEntryLink, String mode)
		throws PortalException {

		return processFragmentEntryLinkHTML(
			fragmentEntryLink, mode, LocaleUtil.getMostRelevantLocale());
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public default String processFragmentEntryLinkHTML(
			FragmentEntryLink fragmentEntryLink, String mode, Locale locale)
		throws PortalException {

		return processFragmentEntryLinkHTML(
			fragmentEntryLink, mode, locale, new long[0]);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public default String processFragmentEntryLinkHTML(
			FragmentEntryLink fragmentEntryLink, String mode, Locale locale,
			long[] segmentsExperienceIds)
		throws PortalException {

		return processFragmentEntryLinkHTML(
			fragmentEntryLink, mode, locale, segmentsExperienceIds, 0);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public default String processFragmentEntryLinkHTML(
			FragmentEntryLink fragmentEntryLink, String mode, Locale locale,
			long[] segmentsExperienceIds, long previewClassPK)
		throws PortalException {

		return processFragmentEntryLinkHTML(
			fragmentEntryLink, mode, locale, segmentsExperienceIds,
			previewClassPK, AssetRendererFactory.TYPE_LATEST_APPROVED);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public default String processFragmentEntryLinkHTML(
			FragmentEntryLink fragmentEntryLink, String mode, Locale locale,
			long[] segmentsExperienceIds, long previewClassPK, int previewType)
		throws PortalException {

		DefaultFragmentEntryProcessorContext
			defaultFragmentEntryProcessorContext =
				new DefaultFragmentEntryProcessorContext(
					null, null, mode, locale);

		defaultFragmentEntryProcessorContext.setPreviewClassPK(previewClassPK);
		defaultFragmentEntryProcessorContext.setPreviewType(previewType);
		defaultFragmentEntryProcessorContext.setSegmentsExperienceIds(
			segmentsExperienceIds);

		return processFragmentEntryLinkHTML(
			fragmentEntryLink, defaultFragmentEntryProcessorContext);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public default void validateFragmentEntryHTML(String html)
		throws PortalException {

		validateFragmentEntryHTML(html, null);
	}

	public void validateFragmentEntryHTML(String html, String configuration)
		throws PortalException;

}