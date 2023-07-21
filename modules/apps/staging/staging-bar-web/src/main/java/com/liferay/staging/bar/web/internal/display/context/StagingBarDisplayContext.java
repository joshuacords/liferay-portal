/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.staging.bar.web.internal.display.context;

import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutRevision;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutRevisionLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Date;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Jürgen Kappler
 */
public class StagingBarDisplayContext {

	public StagingBarDisplayContext(
		LiferayPortletRequest liferayPortletRequest, Layout layout) {

		_liferayPortletRequest = liferayPortletRequest;

		_themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_layout = layout;
	}

	public String getApproveDraftURL() {
		PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
			_liferayPortletRequest,
			ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET,
			PortletRequest.ACTION_PHASE);

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "/content_layout/publish_layout");
		portletURL.setParameter("redirect", _themeDisplay.getURLCurrent());
		portletURL.setParameter("classPK", String.valueOf(_layout.getPlid()));

		return portletURL.toString();
	}

	public boolean isDraftLayout() {
		if (_draftLayout != null) {
			return _draftLayout;
		}

		if (!Objects.equals(_layout.getType(), LayoutConstants.TYPE_CONTENT)) {
			_draftLayout = false;

			return _draftLayout;
		}

		boolean draftLayout = false;

		if ((_layout.getClassNameId() == PortalUtil.getClassNameId(
				Layout.class)) &&
			(_layout.getClassPK() > 0)) {

			draftLayout = true;
		}

		_draftLayout = draftLayout;

		return _draftLayout;
	}

	public boolean isStatusDraft() {
		if (_statusDraft != null) {
			return _statusDraft;
		}

		if (!isDraftLayout()) {
			_statusDraft = false;

			return _statusDraft;
		}

		if (!Objects.equals(_layout.getType(), LayoutConstants.TYPE_CONTENT)) {
			_statusDraft = false;

			return _statusDraft;
		}

		boolean statusDraft = false;

		Layout draftLayout = _layout;

		Layout layout = LayoutLocalServiceUtil.fetchLayout(
			draftLayout.getClassPK());

		if ((draftLayout != null) && (layout != null)) {
			Date modifiedDate = draftLayout.getModifiedDate();

			Date publishDate = layout.getPublishDate();

			if (publishDate == null) {
				publishDate = modifiedDate;
			}

			boolean published = GetterUtil.getBoolean(
				draftLayout.getTypeSettingsProperty("published"));

			if (((draftLayout.getPublishDate() == null) && !published) ||
				(modifiedDate.getTime() > publishDate.getTime())) {

				statusDraft = true;
			}
		}

		_statusDraft = statusDraft;

		return _statusDraft;
	}

	public LayoutRevision updateLayoutRevision(LayoutRevision layoutRevision) {
		if (!Objects.equals(_layout.getType(), LayoutConstants.TYPE_CONTENT)) {
			return layoutRevision;
		}

		if ((layoutRevision == null) || layoutRevision.isApproved() ||
			isDraftLayout()) {

			return layoutRevision;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		try {
			layoutRevision =
				LayoutRevisionLocalServiceUtil.updateLayoutRevision(
					_themeDisplay.getUserId(),
					layoutRevision.getLayoutRevisionId(),
					layoutRevision.getLayoutBranchId(),
					layoutRevision.getName(), layoutRevision.getTitle(),
					layoutRevision.getDescription(),
					layoutRevision.getKeywords(), layoutRevision.getRobots(),
					layoutRevision.getTypeSettings(),
					layoutRevision.getIconImage(),
					layoutRevision.getIconImageId(),
					layoutRevision.getThemeId(),
					layoutRevision.getColorSchemeId(), layoutRevision.getCss(),
					serviceContext);
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portalException, portalException);
			}
		}

		return layoutRevision;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		StagingBarDisplayContext.class);

	private Boolean _draftLayout;
	private final Layout _layout;
	private final LiferayPortletRequest _liferayPortletRequest;
	private Boolean _statusDraft;
	private final ThemeDisplay _themeDisplay;

}