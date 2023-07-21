/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.taglib.servlet.taglib;

import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalServiceUtil;
import com.liferay.layout.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.segments.constants.SegmentsExperienceConstants;
import com.liferay.segments.constants.SegmentsWebKeys;
import com.liferay.taglib.util.IncludeTag;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Víctor Galán
 */
public class RenderFragmentLayoutTag extends IncludeTag {

	public Map<String, Object> getFieldValues() {
		return _fieldValues;
	}

	public long getGroupId() {
		return _groupId;
	}

	public String getMode() {
		return _mode;
	}

	public long getPlid() {
		return _plid;
	}

	public boolean getShowPreview() {
		return _showPreview;
	}

	public void setFieldValues(Map<String, Object> fieldValues) {
		_fieldValues = fieldValues;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setMode(String mode) {
		_mode = mode;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setPlid(long plid) {
		_plid = plid;
	}

	public void setShowPreview(boolean showPreview) {
		_showPreview = showPreview;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_fieldValues = null;
		_groupId = 0;
		_mode = null;
		_plid = 0;
		_showPreview = false;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		super.setAttributes(httpServletRequest);

		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:draftLayout",
			_getDraftLayout());
		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:fieldValues", _fieldValues);
		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:mode", _mode);

		String data = _getData();

		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:" +
				"nonIndexableFragmentEntryLinkIds",
			_getNonIndexableFragmentEntryLinkIds(data));

		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:previewClassPK",
			_getPreviewClassPK());
		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:previewType",
			_getPreviewType());
		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:segmentsExperienceIds",
			_getSegmentsExperienceIds());

		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:structureJSONArray",
			_getStructureJSONArray(data));
	}

	private String _getData() {
		try {
			Layout layout = LayoutLocalServiceUtil.fetchLayout(_plid);

			LayoutPageTemplateStructure layoutPageTemplateStructure =
				LayoutPageTemplateStructureLocalServiceUtil.
					fetchLayoutPageTemplateStructure(
						layout.getGroupId(),
						PortalUtil.getClassNameId(Layout.class.getName()),
						_plid, true);

			long[] segmentsExperienceIds = _getSegmentsExperienceIds();

			return layoutPageTemplateStructure.getData(segmentsExperienceIds);
		}
		catch (Exception exception) {
			_log.error("Unable to get structure data", exception);

			return "";
		}
	}

	private Layout _getDraftLayout() {
		long plid = getPlid();

		if (plid <= 0) {
			return null;
		}

		Layout layout = LayoutLocalServiceUtil.fetchLayout(plid);

		if (layout == null) {
			return null;
		}

		if (!Objects.equals(layout.getType(), LayoutConstants.TYPE_CONTENT)) {
			return null;
		}

		if ((layout.getClassNameId() == PortalUtil.getClassNameId(
				Layout.class)) &&
			(layout.getClassPK() > 0)) {

			return layout;
		}

		return null;
	}

	private List<String> _getNonIndexableFragmentEntryLinkIds(String data) {
		try {
			if (Validator.isNull(data)) {
				return null;
			}

			JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject(data);

			JSONArray nonIndexableFragmentEntryLinkIdsJSONArray =
				dataJSONObject.getJSONArray("nonIndexableFragmentEntryLinkIds");

			return JSONUtil.toStringList(
				nonIndexableFragmentEntryLinkIdsJSONArray);
		}
		catch (Exception exception) {
			_log.error("Unable to get structure JSON array", exception);

			return Collections.emptyList();
		}
	}

	private long _getPreviewClassPK() {
		if (!_showPreview) {
			return 0;
		}

		return ParamUtil.getLong(request, "previewAssetEntryId");
	}

	private int _getPreviewType() {
		if (!_showPreview) {
			return 0;
		}

		return ParamUtil.getInteger(request, "previewAssetEntryType");
	}

	private long[] _getSegmentsExperienceIds() {
		long[] selectedSegmentsExperienceIds = ParamUtil.getLongValues(
			request, "segmentsExperienceId");

		if (selectedSegmentsExperienceIds.length > 0) {
			return selectedSegmentsExperienceIds;
		}

		return GetterUtil.getLongValues(
			request.getAttribute(SegmentsWebKeys.SEGMENTS_EXPERIENCE_IDS),
			new long[] {SegmentsExperienceConstants.ID_DEFAULT});
	}

	private JSONArray _getStructureJSONArray(String data) {
		try {
			if (Validator.isNull(data)) {
				return null;
			}

			JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject(data);

			return dataJSONObject.getJSONArray("structure");
		}
		catch (Exception exception) {
			_log.error("Unable to get structure JSON array", exception);

			return null;
		}
	}

	private static final String _PAGE = "/render_fragment_layout/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		RenderFragmentLayoutTag.class);

	private Map<String, Object> _fieldValues;
	private long _groupId;
	private String _mode;
	private long _plid;
	private boolean _showPreview;

}