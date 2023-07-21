/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.display.context;

import com.liferay.fragment.renderer.FragmentRendererController;
import com.liferay.layout.content.page.editor.sidebar.panel.ContentPageEditorSidebarPanel;
import com.liferay.layout.content.page.editor.web.internal.segments.SegmentsExperienceUtil;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructureRel;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalServiceUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureRelLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.template.soy.util.SoyContext;
import com.liferay.portal.template.soy.util.SoyContextFactoryUtil;
import com.liferay.segments.constants.SegmentsEntryConstants;
import com.liferay.segments.constants.SegmentsExperienceConstants;
import com.liferay.segments.constants.SegmentsPortletKeys;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.model.SegmentsExperimentRel;
import com.liferay.segments.service.SegmentsEntryServiceUtil;
import com.liferay.segments.service.SegmentsExperienceLocalServiceUtil;
import com.liferay.segments.service.SegmentsExperimentRelLocalServiceUtil;
import com.liferay.staging.StagingGroupHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class ContentPageLayoutEditorDisplayContext
	extends ContentPageEditorDisplayContext {

	public ContentPageLayoutEditorDisplayContext(
		HttpServletRequest httpServletRequest, RenderResponse renderResponse,
		CommentManager commentManager,
		List<ContentPageEditorSidebarPanel> contentPageEditorSidebarPanels,
		FragmentRendererController fragmentRendererController,
		StagingGroupHelper stagingGroupHelper) {

		super(
			httpServletRequest, renderResponse, commentManager,
			contentPageEditorSidebarPanels, fragmentRendererController);

		_stagingGroupHelper = stagingGroupHelper;
	}

	@Override
	public SoyContext getEditorSoyContext() throws Exception {
		if (_editorSoyContext != null) {
			return _editorSoyContext;
		}

		SoyContext soyContext = super.getEditorSoyContext();

		soyContext.put("sidebarPanels", getSidebarPanelSoyContexts(false));

		if (_isShowSegmentsExperiences()) {
			_populateSegmentsExperiencesSoyContext(soyContext);
		}

		_editorSoyContext = soyContext;

		return _editorSoyContext;
	}

	@Override
	public SoyContext getFragmentsEditorToolbarSoyContext()
		throws PortalException {

		if (_fragmentsEditorToolbarSoyContext != null) {
			return _fragmentsEditorToolbarSoyContext;
		}

		SoyContext soyContext = super.getFragmentsEditorToolbarSoyContext();

		if (_isShowSegmentsExperiences()) {
			_populateSegmentsExperiencesSoyContext(soyContext);
		}

		_fragmentsEditorToolbarSoyContext = soyContext;

		return _fragmentsEditorToolbarSoyContext;
	}

	@Override
	protected long getSegmentsExperienceId() {
		if (_segmentsExperienceId != null) {
			return _segmentsExperienceId;
		}

		_segmentsExperienceId = ParamUtil.getLong(
			PortalUtil.getOriginalServletRequest(request),
			"segmentsExperienceId", -1);

		if ((_segmentsExperienceId != -1) &&
			(_segmentsExperienceId != SegmentsExperienceConstants.ID_DEFAULT)) {

			_segmentsExperienceId = Optional.ofNullable(
				SegmentsExperienceLocalServiceUtil.fetchSegmentsExperience(
					_segmentsExperienceId)
			).map(
				SegmentsExperience::getSegmentsExperienceId
			).orElseGet(
				super::getSegmentsExperienceId
			);
		}
		else {
			_segmentsExperienceId = super.getSegmentsExperienceId();
		}

		return _segmentsExperienceId;
	}

	private SoyContext _getAvailableSegmentsEntriesSoyContext() {
		SoyContext availableSegmentsEntriesSoyContext =
			SoyContextFactoryUtil.createSoyContext();

		List<SegmentsEntry> segmentsEntries =
			SegmentsEntryServiceUtil.getSegmentsEntries(
				_getStagingAwareGroupId(SegmentsPortletKeys.SEGMENTS), true);

		for (SegmentsEntry segmentsEntry : segmentsEntries) {
			SoyContext segmentsEntrySoyContext =
				SoyContextFactoryUtil.createSoyContext();

			segmentsEntrySoyContext.put(
				"name", segmentsEntry.getName(themeDisplay.getLocale())
			).put(
				"segmentsEntryId",
				String.valueOf(segmentsEntry.getSegmentsEntryId())
			);

			availableSegmentsEntriesSoyContext.put(
				String.valueOf(segmentsEntry.getSegmentsEntryId()),
				segmentsEntrySoyContext);
		}

		SoyContext defaultSegmentsEntrySoyContext =
			SoyContextFactoryUtil.createSoyContext();

		defaultSegmentsEntrySoyContext.put(
			"name",
			SegmentsEntryConstants.getDefaultSegmentsEntryName(
				themeDisplay.getLocale())
		).put(
			"segmentsEntryId", SegmentsEntryConstants.ID_DEFAULT
		);

		availableSegmentsEntriesSoyContext.put(
			String.valueOf(SegmentsEntryConstants.ID_DEFAULT),
			defaultSegmentsEntrySoyContext);

		return availableSegmentsEntriesSoyContext;
	}

	private String _getEditSegmentsEntryURL() throws PortalException {
		if (_editSegmentsEntryURL != null) {
			return _editSegmentsEntryURL;
		}

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			request, SegmentsEntry.class.getName(),
			PortletProvider.Action.EDIT);

		if (portletURL == null) {
			_editSegmentsEntryURL = StringPool.BLANK;
		}
		else {
			portletURL.setParameter("redirect", themeDisplay.getURLCurrent());

			_editSegmentsEntryURL = portletURL.toString();
		}

		return _editSegmentsEntryURL;
	}

	private List<SoyContext> _getLayoutDataListSoyContext()
		throws PortalException {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			LayoutPageTemplateStructureLocalServiceUtil.
				fetchLayoutPageTemplateStructure(
					themeDisplay.getScopeGroupId(),
					PortalUtil.getClassNameId(Layout.class.getName()),
					themeDisplay.getPlid(), true);

		if (layoutPageTemplateStructure == null) {
			return Collections.emptyList();
		}

		List<SoyContext> soyContexts = new ArrayList<>();

		List<LayoutPageTemplateStructureRel> layoutPageTemplateStructureRels =
			LayoutPageTemplateStructureRelLocalServiceUtil.
				getLayoutPageTemplateStructureRels(
					layoutPageTemplateStructure.
						getLayoutPageTemplateStructureId());

		for (LayoutPageTemplateStructureRel layoutPageTemplateStructureRel :
				layoutPageTemplateStructureRels) {

			SoyContext soyContext = SoyContextFactoryUtil.createSoyContext();

			soyContext.put(
				"layoutData",
				JSONFactoryUtil.createJSONObject(
					layoutPageTemplateStructureRel.getData())
			).put(
				"segmentsExperienceId",
				layoutPageTemplateStructureRel.getSegmentsExperienceId()
			);

			soyContexts.add(soyContext);
		}

		return soyContexts;
	}

	private long _getSegmentsEntryId() {
		if (_segmentsEntryId != null) {
			return _segmentsEntryId;
		}

		_segmentsEntryId = ParamUtil.getLong(
			PortalUtil.getOriginalServletRequest(request), "segmentsEntryId");

		return _segmentsEntryId;
	}

	private long _getStagingAwareGroupId(String portletId) {
		Long groupId = getGroupId();

		if (_stagingGroupHelper.isStagingGroup(groupId) &&
			!_stagingGroupHelper.isStagedPortlet(groupId, portletId)) {

			Group group = _stagingGroupHelper.fetchLiveGroup(groupId);

			if (group != null) {
				groupId = group.getGroupId();
			}
		}

		return groupId;
	}

	private boolean _hasEditSegmentsEntryPermission() throws PortalException {
		if (Validator.isNull(_getEditSegmentsEntryURL())) {
			return false;
		}

		return true;
	}

	private boolean _isShowSegmentsExperiences() throws PortalException {
		if (_showSegmentsExperiences != null) {
			return _showSegmentsExperiences;
		}

		Group group = GroupLocalServiceUtil.getGroup(getGroupId());

		if (!group.isLayoutSetPrototype() && !group.isUser()) {
			_showSegmentsExperiences = true;
		}
		else {
			_showSegmentsExperiences = false;
		}

		return _showSegmentsExperiences;
	}

	private boolean _isSingleSegmentsExperienceMode() {
		long segmentsExperienceId = ParamUtil.getLong(
			PortalUtil.getOriginalServletRequest(request),
			"segmentsExperienceId", -1);

		if (segmentsExperienceId == -1) {
			return false;
		}

		SegmentsExperience segmentsExperience =
			SegmentsExperienceLocalServiceUtil.fetchSegmentsExperience(
				segmentsExperienceId);

		if (segmentsExperience != null) {
			DynamicQuery dynamicQuery =
				SegmentsExperimentRelLocalServiceUtil.dynamicQuery();

			dynamicQuery.add(
				RestrictionsFactoryUtil.eq(
					"segmentsExperienceId",
					segmentsExperience.getSegmentsExperienceId()));

			List<SegmentsExperimentRel> segmentsExperimentRels =
				SegmentsExperimentRelLocalServiceUtil.dynamicQuery(
					dynamicQuery);

			if (segmentsExperimentRels.isEmpty()) {
				return false;
			}

			SegmentsExperimentRel segmentsExperimentRel =
				segmentsExperimentRels.get(0);

			try {
				return !segmentsExperimentRel.isControl();
			}
			catch (PortalException portalException) {
				_log.error(portalException);
			}
		}

		return false;
	}

	private void _populateSegmentsExperiencesSoyContext(SoyContext soyContext)
		throws PortalException {

		soyContext.put(
			"addSegmentsExperienceURL",
			getFragmentEntryActionURL("/content_layout/add_segments_experience")
		).put(
			"availableSegmentsEntries", _getAvailableSegmentsEntriesSoyContext()
		).put(
			"availableSegmentsExperiences",
			SegmentsExperienceUtil.getAvailableSegmentsExperiencesSoyContext(
				themeDisplay)
		).put(
			"defaultSegmentsEntryId", SegmentsEntryConstants.ID_DEFAULT
		).put(
			"defaultSegmentsExperienceId",
			String.valueOf(SegmentsExperienceConstants.ID_DEFAULT)
		).put(
			"deleteSegmentsExperienceURL",
			getFragmentEntryActionURL(
				"/content_layout/delete_segments_experience")
		).put(
			"duplicateSegmentsExperienceURL",
			getFragmentEntryActionURL(
				"/content_layout/duplicate_segments_experience")
		).put(
			"editSegmentsEntryURL", _getEditSegmentsEntryURL()
		).put(
			"hasEditSegmentsEntryPermission", _hasEditSegmentsEntryPermission()
		).put(
			"layoutDataList", _getLayoutDataListSoyContext()
		).put(
			"segmentsExperienceId", String.valueOf(getSegmentsExperienceId())
		).put(
			"selectedSegmentsEntryId", String.valueOf(_getSegmentsEntryId())
		).put(
			"singleSegmentsExperienceMode", _isSingleSegmentsExperienceMode()
		);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ContentPageLayoutEditorDisplayContext.class);

	private SoyContext _editorSoyContext;
	private String _editSegmentsEntryURL;
	private SoyContext _fragmentsEditorToolbarSoyContext;
	private Long _segmentsEntryId;
	private Long _segmentsExperienceId;
	private Boolean _showSegmentsExperiences;
	private final StagingGroupHelper _stagingGroupHelper;

}