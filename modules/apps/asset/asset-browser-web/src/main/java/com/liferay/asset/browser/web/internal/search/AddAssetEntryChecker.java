/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.browser.web.internal.search;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;

/**
 * @author Jürgen Kappler
 */
public class AddAssetEntryChecker extends EmptyOnClickRowChecker {

	public AddAssetEntryChecker(
		PortletRequest portletRequest, RenderResponse renderResponse,
		long assetEntryId) {

		super(renderResponse);

		_portletRequest = portletRequest;
		_assetEntryId = assetEntryId;
	}

	@Override
	public boolean isChecked(Object obj) {
		if ((_portletRequest == null) ||
			(ParamUtil.getString(_portletRequest, "selectedAssetEntryIds") ==
				null)) {

			return super.isChecked(obj);
		}

		String[] selectedAssetEntryIds = StringUtil.split(
			ParamUtil.getString(_portletRequest, "selectedAssetEntryIds"));

		if (ArrayUtil.isEmpty(selectedAssetEntryIds)) {
			return false;
		}

		AssetEntry entry = (AssetEntry)obj;

		if (!ArrayUtil.contains(
				selectedAssetEntryIds, String.valueOf(entry.getEntryId()))) {

			return false;
		}

		return true;
	}

	@Override
	public boolean isDisabled(Object obj) {
		AssetEntry assetEntry = (AssetEntry)obj;

		if (assetEntry.getEntryId() == _assetEntryId) {
			return true;
		}

		return super.isDisabled(obj);
	}

	private final long _assetEntryId;
	private PortletRequest _portletRequest;

}