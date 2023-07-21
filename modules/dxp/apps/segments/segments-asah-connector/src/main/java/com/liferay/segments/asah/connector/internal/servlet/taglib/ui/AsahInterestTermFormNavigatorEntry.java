/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.asah.connector.internal.servlet.taglib.ui;

import com.liferay.asset.list.constants.AssetListEntryTypeConstants;
import com.liferay.asset.list.constants.AssetListFormConstants;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.service.AssetListEntryService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.segments.asah.connector.internal.util.AsahUtil;
import com.liferay.segments.constants.SegmentsEntryConstants;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sarai Díaz
 */
@Component(
	property = "form.navigator.entry.order:Integer=300",
	service = FormNavigatorEntry.class
)
public class AsahInterestTermFormNavigatorEntry
	extends BaseJSPFormNavigatorEntry<AssetListEntry> {

	@Override
	public String getCategoryKey() {
		return StringPool.BLANK;
	}

	@Override
	public String getFormNavigatorId() {
		return AssetListFormConstants.FORM_NAVIGATOR_ID;
	}

	@Override
	public String getKey() {
		return "content-recommendation";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "content-recommendation");
	}

	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		long assetListEntryId = ParamUtil.getLong(
			httpServletRequest, "assetListEntryId");

		try {
			AssetListEntry assetListEntry =
				_assetListEntryService.fetchAssetListEntry(assetListEntryId);

			if (assetListEntry != null) {
				UnicodeProperties properties = new UnicodeProperties();

				long segmentsEntryId = ParamUtil.getLong(
					httpServletRequest, "segmentsEntryId",
					SegmentsEntryConstants.ID_DEFAULT);

				properties.load(
					assetListEntry.getTypeSettings(segmentsEntryId));

				boolean enableContentRecommendation = GetterUtil.getBoolean(
					properties.getProperty(
						"enableContentRecommendation",
						Boolean.FALSE.toString()));

				httpServletRequest.setAttribute(
					"enableContentRecommendation", enableContentRecommendation);
			}
		}
		catch (Exception exception) {
			_log.error("Unable to set content recommendation value");
		}

		super.include(httpServletRequest, httpServletResponse);
	}

	@Override
	public boolean isVisible(User user, AssetListEntry assetListEntry) {
		if (!AsahUtil.isAnalyticsEnabled(
				user.getCompanyId(), assetListEntry.getGroupId())) {

			return false;
		}

		if (assetListEntry.getType() !=
				AssetListEntryTypeConstants.TYPE_DYNAMIC) {

			return false;
		}

		return true;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.segments.asah.connector)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected String getJspPath() {
		return "/interest_terms.jsp";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AsahInterestTermFormNavigatorEntry.class);

	@Reference
	private AssetListEntryService _assetListEntryService;

}