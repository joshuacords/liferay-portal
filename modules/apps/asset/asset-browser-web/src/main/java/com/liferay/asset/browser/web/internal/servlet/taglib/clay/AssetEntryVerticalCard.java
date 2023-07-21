/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.browser.web.internal.servlet.taglib.clay;

import com.liferay.asset.browser.web.internal.display.context.AssetBrowserDisplayContext;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.frontend.taglib.clay.servlet.taglib.soy.BaseVerticalCard;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Map;

import javax.portlet.RenderRequest;

/**
 * @author Eudaldo Alonso
 */
public class AssetEntryVerticalCard extends BaseVerticalCard {

	public AssetEntryVerticalCard(
		AssetEntry assetEntry, RenderRequest renderRequest,
		AssetBrowserDisplayContext assetBrowserDisplayContext) {

		super(
			assetEntry, renderRequest,
			assetBrowserDisplayContext.getAssetBrowserSearch().getRowChecker());

		_assetEntry = assetEntry;
		_renderRequest = renderRequest;
		_assetBrowserDisplayContext = assetBrowserDisplayContext;

		_themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_assetRenderer = assetEntry.getAssetRenderer();
		_assetRendererFactory =
			_assetBrowserDisplayContext.getAssetRendererFactory();
	}

	@Override
	public Map<String, String> getData() {
		if (_assetBrowserDisplayContext.isMultipleSelection()) {
			return null;
		}

		if (_assetEntry.getEntryId() ==
				_assetBrowserDisplayContext.getRefererAssetEntryId()) {

			return null;
		}

		Map<String, String> data = HashMapBuilder.put(
			"assetclassname", _assetEntry.getClassName()
		).put(
			"assetclassnameid", String.valueOf(_assetEntry.getClassNameId())
		).put(
			"assetclasspk", String.valueOf(_assetEntry.getClassPK())
		).put(
			"assettitle", _assetRenderer.getTitle(_themeDisplay.getLocale())
		).put(
			"assettype",
			_assetRendererFactory.getTypeName(
				_themeDisplay.getLocale(),
				_assetBrowserDisplayContext.getSubtypeSelectionId())
		).put(
			"entityid", String.valueOf(_assetEntry.getEntryId())
		).build();

		Group group = GroupLocalServiceUtil.fetchGroup(
			_assetEntry.getGroupId());

		if (group != null) {
			try {
				data.put(
					"groupdescriptivename",
					group.getDescriptiveName(_themeDisplay.getLocale()));
			}
			catch (Exception exception) {
			}
		}

		return data;
	}

	@Override
	public String getElementClasses() {
		if (_assetEntry.getEntryId() !=
				_assetBrowserDisplayContext.getRefererAssetEntryId()) {

			return "card-interactive card-interactive-secondary " +
				"selector-button";
		}

		return StringPool.BLANK;
	}

	@Override
	public String getIcon() {
		return _assetRendererFactory.getIconCssClass();
	}

	@Override
	public String getImageSrc() {
		try {
			return _assetRenderer.getThumbnailPath(_renderRequest);
		}
		catch (Exception exception) {
		}

		return null;
	}

	@Override
	public String getInputValue() {
		return String.valueOf(_assetEntry.getEntryId());
	}

	@Override
	public String getSubtitle() {
		if (Validator.isNull(_assetBrowserDisplayContext.getTypeSelection())) {
			return HtmlUtil.escape(
				_assetRendererFactory.getTypeName(
					_themeDisplay.getLocale(),
					_assetBrowserDisplayContext.getSubtypeSelectionId()));
		}

		Group group = GroupLocalServiceUtil.fetchGroup(
			_assetEntry.getGroupId());

		try {
			return HtmlUtil.escape(
				group.getDescriptiveName(_themeDisplay.getLocale()));
		}
		catch (Exception exception) {
		}

		return null;
	}

	@Override
	public String getTitle() {
		return _assetRenderer.getTitle(_themeDisplay.getLocale());
	}

	@Override
	public boolean isSelectable() {
		return _assetBrowserDisplayContext.isMultipleSelection();
	}

	private final AssetBrowserDisplayContext _assetBrowserDisplayContext;
	private final AssetEntry _assetEntry;
	private final AssetRenderer _assetRenderer;
	private final AssetRendererFactory _assetRendererFactory;
	private final RenderRequest _renderRequest;
	private final ThemeDisplay _themeDisplay;

}