/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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