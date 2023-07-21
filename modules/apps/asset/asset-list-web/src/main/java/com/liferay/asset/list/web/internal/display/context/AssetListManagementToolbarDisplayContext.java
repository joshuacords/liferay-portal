/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.list.web.internal.display.context;

import com.liferay.asset.list.constants.AssetListActionKeys;
import com.liferay.asset.list.constants.AssetListEntryTypeConstants;
import com.liferay.asset.list.constants.AssetListPortletKeys;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.web.internal.security.permission.resource.AssetListEntryPermission;
import com.liferay.asset.list.web.internal.security.permission.resource.AssetListPermission;
import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.staging.StagingGroupHelperUtil;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class AssetListManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public AssetListManagementToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest httpServletRequest,
		AssetListDisplayContext assetListDisplayContext) {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			assetListDisplayContext.getAssetListEntriesSearchContainer());
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (_isLiveGroup(themeDisplay)) {
			return null;
		}

		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.putData(
							"action", "deleteSelectedAssetListEntries");
						dropdownItem.setIcon("times-circle");
						dropdownItem.setLabel(
							LanguageUtil.get(request, "delete"));
						dropdownItem.setQuickAction(true);
					});
			}
		};
	}

	public String getAvailableActions(AssetListEntry assetListEntry)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!_isLiveGroup(themeDisplay) &&
			AssetListEntryPermission.contains(
				themeDisplay.getPermissionChecker(), assetListEntry,
				ActionKeys.DELETE)) {

			return "deleteSelectedAssetListEntries";
		}

		return StringPool.BLANK;
	}

	@Override
	public String getClearResultsURL() {
		PortletURL clearResultsURL = getPortletURL();

		clearResultsURL.setParameter("keywords", StringPool.BLANK);

		return clearResultsURL.toString();
	}

	@Override
	public String getComponentId() {
		return "assetListEntriesEntriesManagementToolbar";
	}

	@Override
	public CreationMenu getCreationMenu() {
		return new CreationMenu() {
			{
				PortletURL addManualAssetListEntryURL =
					liferayPortletResponse.createActionURL();

				addManualAssetListEntryURL.setParameter(
					ActionRequest.ACTION_NAME,
					"/asset_list/add_asset_list_entry");
				addManualAssetListEntryURL.setParameter(
					"type",
					String.valueOf(AssetListEntryTypeConstants.TYPE_MANUAL));

				addPrimaryDropdownItem(
					dropdownItem -> {
						dropdownItem.putData("action", "addAssetListEntry");
						dropdownItem.putData(
							"addAssetListEntryURL",
							addManualAssetListEntryURL.toString());
						dropdownItem.putData(
							"title",
							LanguageUtil.format(
								request, "add-x-content-set",
								AssetListEntryTypeConstants.TYPE_MANUAL_LABEL,
								true));
						dropdownItem.setHref("#");
						dropdownItem.setLabel(
							LanguageUtil.get(request, "manual-selection"));
					});

				PortletURL addDynamicAssetListEntryURL =
					liferayPortletResponse.createActionURL();

				addDynamicAssetListEntryURL.setParameter(
					ActionRequest.ACTION_NAME,
					"/asset_list/add_asset_list_entry");
				addDynamicAssetListEntryURL.setParameter(
					"type",
					String.valueOf(AssetListEntryTypeConstants.TYPE_DYNAMIC));

				addPrimaryDropdownItem(
					dropdownItem -> {
						dropdownItem.putData("action", "addAssetListEntry");
						dropdownItem.putData(
							"addAssetListEntryURL",
							addDynamicAssetListEntryURL.toString());
						dropdownItem.putData(
							"title",
							LanguageUtil.format(
								request, "add-x-content-set",
								AssetListEntryTypeConstants.TYPE_DYNAMIC_LABEL,
								true));
						dropdownItem.setHref("#");
						dropdownItem.setLabel(
							LanguageUtil.get(request, "dynamic-selection"));
					});
			}
		};
	}

	@Override
	public String getDefaultEventHandler() {
		return "assetListEntriesManagementToolbarDefaultEventHandler";
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	@Override
	public String getSearchContainerId() {
		return "assetListEntries";
	}

	@Override
	public Boolean isShowCreationMenu() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (_isLiveGroup(themeDisplay)) {
			return false;
		}

		if (AssetListPermission.contains(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(),
				AssetListActionKeys.ADD_ASSET_LIST_ENTRY)) {

			return true;
		}

		return false;
	}

	@Override
	protected String[] getOrderByKeys() {
		return new String[] {"title", "create-date"};
	}

	private boolean _isLiveGroup(ThemeDisplay themeDisplay) {
		Group group = themeDisplay.getScopeGroup();

		if (group.isLayout()) {
			group = group.getParentGroup();
		}

		StagingGroupHelper stagingGroupHelper =
			StagingGroupHelperUtil.getStagingGroupHelper();

		if (stagingGroupHelper.isLiveGroup(group) &&
			stagingGroupHelper.isStagedPortlet(
				group, AssetListPortletKeys.ASSET_LIST)) {

			return true;
		}

		return false;
	}

}