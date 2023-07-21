/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.taglib.servlet.taglib;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.AssetAddonEntry;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.servlet.PipingServletResponse;
import com.liferay.taglib.util.IncludeTag;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

/**
 * @author Julio Camarero
 */
public class AssetAddonEntryDisplayTag extends IncludeTag {

	@Override
	public int doEndTag() throws JspException {
		for (AssetAddonEntry assetAddonEntry : _assetAddonEntries) {
			try {
				assetAddonEntry.include(request, getResponse());
			}
			catch (IOException ioException) {
				_log.error(ioException, ioException);
			}
		}

		return super.doEndTag();
	}

	public List<? extends AssetAddonEntry> getAssetAddonEntries() {
		return _assetAddonEntries;
	}

	public void setAssetAddonEntries(
		List<? extends AssetAddonEntry> assetAddonEntries) {

		_assetAddonEntries = assetAddonEntries;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_assetAddonEntries = null;
	}

	protected HttpServletResponse getResponse() {
		return PipingServletResponse.createPipingServletResponse(pageContext);
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			WebKeys.ASSET_ADDON_ENTRIES, _assetAddonEntries);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetAddonEntryDisplayTag.class);

	private List<? extends AssetAddonEntry> _assetAddonEntries;

}