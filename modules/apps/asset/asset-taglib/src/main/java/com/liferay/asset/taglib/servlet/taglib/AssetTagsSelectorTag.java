/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.taglib.servlet.taglib;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagServiceUtil;
import com.liferay.asset.taglib.internal.servlet.ServletContextUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author     Antonio Pol
 * @deprecated As of Mueller (7.2.x)
 */
@Deprecated
public class AssetTagsSelectorTag extends IncludeTag {

	public void setAddCallback(String addCallback) {
		_addCallback = addCallback;
	}

	public void setAllowAddEntry(boolean allowAddEntry) {
		_allowAddEntry = allowAddEntry;
	}

	public void setAutoFocus(boolean autoFocus) {
		_autoFocus = autoFocus;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setGroupIds(long[] groupIds) {
		_groupIds = groupIds;
	}

	public void setHiddenInput(String hiddenInput) {
		_hiddenInput = hiddenInput;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIgnoreRequestValue(boolean ignoreRequestValue) {
		_ignoreRequestValue = ignoreRequestValue;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setRemoveCallback(String removeCallback) {
		_removeCallback = removeCallback;
	}

	public void setTagNames(String tagNames) {
		_tagNames = tagNames;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_addCallback = null;
		_allowAddEntry = true;
		_autoFocus = false;
		_className = null;
		_classPK = 0;
		_groupIds = null;
		_hiddenInput = "assetTagNames";
		_id = null;
		_ignoreRequestValue = false;
		_removeCallback = null;
		_tagNames = null;
	}

	protected String getEventName() {
		String portletId = PortletProviderUtil.getPortletId(
			AssetTag.class.getName(), PortletProvider.Action.BROWSE);

		return PortalUtil.getPortletNamespace(portletId) + "selectTag";
	}

	protected long[] getGroupIds() {
		if (_groupIds != null) {
			return _groupIds;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] groupIds = null;

		Group group = themeDisplay.getScopeGroup();

		if (group.isLayout()) {
			groupIds = new long[] {group.getParentGroupId()};
		}
		else {
			groupIds = new long[] {group.getGroupId()};
		}

		if (group.getParentGroupId() != themeDisplay.getCompanyGroupId()) {
			groupIds = ArrayUtil.append(
				groupIds, themeDisplay.getCompanyGroupId());
		}

		return groupIds;
	}

	protected String getId() {
		if (Validator.isNotNull(_id)) {
			return _id;
		}

		String randomKey = PortalUtil.generateRandomKey(
			request, "taglib_ui_asset_tags_selector_page");

		return randomKey + StringPool.UNDERLINE;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	protected PortletURL getPortletURL() {
		try {
			PortletURL portletURL = PortletProviderUtil.getPortletURL(
				request, AssetTag.class.getName(),
				PortletProvider.Action.BROWSE);

			if (portletURL == null) {
				return null;
			}

			if (_groupIds != null) {
				portletURL.setParameter(
					"groupIds", StringUtil.merge(_groupIds, StringPool.COMMA));
			}

			portletURL.setParameter("eventName", getEventName());
			portletURL.setParameter("selectedTagNames", "{selectedTagNames}");
			portletURL.setWindowState(LiferayWindowState.POP_UP);

			return portletURL;
		}
		catch (Exception exception) {
		}

		return null;
	}

	protected String getTagNames() {
		String tagNames = _tagNames;

		if (Validator.isNotNull(_className) && (_classPK > 0)) {
			List<AssetTag> tags = AssetTagServiceUtil.getTags(
				_className, _classPK);

			tagNames = ListUtil.toString(tags, AssetTag.NAME_ACCESSOR);
		}

		if (!_ignoreRequestValue) {
			String[] curTagsParam = request.getParameterValues(_hiddenInput);

			if (curTagsParam != null) {
				tagNames = StringUtil.merge(curTagsParam, StringPool.COMMA);
			}
		}

		return tagNames;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			"liferay-asset:asset-tags-selector:addCallback",
			String.valueOf(_addCallback));
		httpServletRequest.setAttribute(
			"liferay-asset:asset-tags-selector:allowAddEntry",
			String.valueOf(_allowAddEntry));
		httpServletRequest.setAttribute(
			"liferay-asset:asset-tags-selector:autoFocus",
			String.valueOf(_autoFocus));
		httpServletRequest.setAttribute(
			"liferay-asset:asset-tags-selector:eventName", getEventName());
		httpServletRequest.setAttribute(
			"liferay-asset:asset-tags-selector:groupIds", getGroupIds());
		httpServletRequest.setAttribute(
			"liferay-asset:asset-tags-selector:hiddenInput", _hiddenInput);
		httpServletRequest.setAttribute(
			"liferay-asset:asset-tags-selector:id", getId());
		httpServletRequest.setAttribute(
			"liferay-asset:asset-tags-selector:portletURL", getPortletURL());
		httpServletRequest.setAttribute(
			"liferay-asset:asset-tags-selector:removeCallback",
			String.valueOf(_removeCallback));
		httpServletRequest.setAttribute(
			"liferay-asset:asset-tags-selector:tagNames", getTagNames());
	}

	private static final String _PAGE = "/asset_tags_selector/page.jsp";

	private String _addCallback;
	private boolean _allowAddEntry = true;
	private boolean _autoFocus;
	private String _className;
	private long _classPK;
	private long[] _groupIds;
	private String _hiddenInput = "assetTagNames";
	private String _id;
	private boolean _ignoreRequestValue;
	private String _removeCallback;
	private String _tagNames;

}