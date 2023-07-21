/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.taglib.servlet.taglib;

import com.liferay.asset.taglib.internal.servlet.ServletContextUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.servlet.taglib.ui.AssetAddonEntry;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Julio Camarero
 */
public class AssetAddonEntrySelectorTag extends IncludeTag {

	public List<AssetAddonEntry> getAssetAddonEntries() {
		return _assetAddonEntries;
	}

	public String getHiddenInput() {
		return _hiddenInput;
	}

	public List<AssetAddonEntry> getSelectedAssetAddonEntries() {
		return _selectedAssetAddonEntries;
	}

	public String getTitle() {
		return _title;
	}

	public void setAssetAddonEntries(List<AssetAddonEntry> assetAddonEntries) {
		_assetAddonEntries = assetAddonEntries;
	}

	public void setHiddenInput(String hiddenInput) {
		_hiddenInput = hiddenInput;
	}

	public void setId(String id) {
		_id = id;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setSelectedAssetAddonEntries(
		List<AssetAddonEntry> selectedAssetAddonEntries) {

		_selectedAssetAddonEntries = selectedAssetAddonEntries;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_assetAddonEntries = null;
		_hiddenInput = null;
		_id = null;
		_selectedAssetAddonEntries = null;
		_title = "select-entries";
	}

	protected String getId() {
		if (Validator.isNotNull(_id)) {
			return _id;
		}

		String id = PortalUtil.generateRandomKey(
			request, "taglib_ui_asset_addon_entry_selector_page");

		return id + StringPool.UNDERLINE;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			WebKeys.ASSET_ADDON_ENTRIES, _assetAddonEntries);
		httpServletRequest.setAttribute(
			"liferay-asset:asset-addon-entry-selector:hiddenInput",
			_hiddenInput);
		httpServletRequest.setAttribute(
			"liferay-asset:asset-addon-entry-selector:id", getId());
		httpServletRequest.setAttribute(
			"liferay-asset:asset-addon-entry-selector:" +
				"selectedAssetAddonEntries",
			_getFilteredSelectedAssetAddonEntries());
		httpServletRequest.setAttribute(
			"liferay-asset:asset-addon-entry-selector:title", _title);
	}

	private List<AssetAddonEntry> _getFilteredSelectedAssetAddonEntries() {
		Stream<AssetAddonEntry> stream = _selectedAssetAddonEntries.stream();

		stream = stream.filter(_assetAddonEntries::contains);

		return stream.collect(Collectors.toList());
	}

	private static final String _PAGE = "/asset_addon_entry_selector/page.jsp";

	private List<AssetAddonEntry> _assetAddonEntries;
	private String _hiddenInput;
	private String _id;
	private List<AssetAddonEntry> _selectedAssetAddonEntries;
	private String _title = "select-entries";

}