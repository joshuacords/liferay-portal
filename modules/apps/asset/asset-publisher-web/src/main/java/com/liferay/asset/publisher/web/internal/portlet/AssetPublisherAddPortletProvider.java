/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.publisher.web.internal.portlet;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.model.AssetEntryUsage;
import com.liferay.asset.publisher.constants.AssetPublisherPortletKeys;
import com.liferay.asset.publisher.web.internal.util.AssetPublisherWebUtil;
import com.liferay.asset.service.AssetEntryUsageLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.AddPortletProvider;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.ViewPortletProvider;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.asset.kernel.model.AssetEntry",
	service = {AddPortletProvider.class, ViewPortletProvider.class}
)
public class AssetPublisherAddPortletProvider
	extends BasePortletProvider
	implements AddPortletProvider, ViewPortletProvider {

	@Override
	public String getPortletName() {
		return AssetPublisherPortletKeys.ASSET_PUBLISHER;
	}

	@Override
	public PortletURL getPortletURL(HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL assetPublisherURL = super.getPortletURL(httpServletRequest);

		assetPublisherURL.setParameter("mvcPath", "/view_content.jsp");

		return assetPublisherURL;
	}

	@Override
	public PortletURL getPortletURL(
			HttpServletRequest httpServletRequest, Group group)
		throws PortalException {

		return PortletURLFactoryUtil.create(
			httpServletRequest, getPortletName(), PortletRequest.RENDER_PHASE);
	}

	@Override
	public void updatePortletPreferences(
			PortletPreferences portletPreferences, String portletId,
			String className, long classPK, ThemeDisplay themeDisplay)
		throws Exception {

		portletPreferences.setValue("displayStyle", "full-content");
		portletPreferences.setValue(
			"emailAssetEntryAddedEnabled", Boolean.FALSE.toString());
		portletPreferences.setValue("selectionStyle", "manual");
		portletPreferences.setValue(
			"showAddContentButton", Boolean.FALSE.toString());
		portletPreferences.setValue("showAssetTitle", Boolean.FALSE.toString());
		portletPreferences.setValue("showExtraInfo", Boolean.FALSE.toString());

		AssetEntry assetEntry = _assetEntryLocalService.getEntry(
			className, classPK);

		_assetPublisherWebUtil.addSelection(
			portletPreferences, assetEntry.getEntryId(), -1,
			assetEntry.getClassName());

		_addAssetEntryUsage(assetEntry, themeDisplay.getLayout(), portletId);
	}

	private void _addAssetEntryUsage(
		AssetEntry assetEntry, Layout layout, String portletId) {

		AssetEntryUsage assetEntryUsage =
			_assetEntryUsageLocalService.fetchAssetEntryUsage(
				assetEntry.getEntryId(), _portal.getClassNameId(Portlet.class),
				portletId, layout.getPlid());

		if (assetEntryUsage != null) {
			return;
		}

		_assetEntryUsageLocalService.addAssetEntryUsage(
			layout.getGroupId(), assetEntry.getEntryId(),
			_portal.getClassNameId(Portlet.class), portletId, layout.getPlid(),
			ServiceContextThreadLocal.getServiceContext());
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetEntryUsageLocalService _assetEntryUsageLocalService;

	@Reference
	private AssetPublisherWebUtil _assetPublisherWebUtil;

	@Reference
	private Portal _portal;

}