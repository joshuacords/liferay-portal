/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.comment.web.internal.asset.model;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.comment.constants.CommentConstants;
import com.liferay.comment.web.internal.constants.CommentPortletKeys;
import com.liferay.portal.kernel.comment.Comment;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.comment.DiscussionPermission;
import com.liferay.portal.kernel.comment.WorkflowableComment;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Ferrer
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + CommentPortletKeys.COMMENT,
	service = AssetRendererFactory.class
)
public class CommentAssetRendererFactory
	extends BaseAssetRendererFactory<WorkflowableComment> {

	public static final String TYPE = "discussion";

	public CommentAssetRendererFactory() {
		setCategorizable(false);
		setClassName(CommentConstants.getDiscussionClassName());
		setLinkable(true);
		setPortletId(CommentPortletKeys.COMMENT);
	}

	@Override
	public AssetRenderer<WorkflowableComment> getAssetRenderer(
			long classPK, int type)
		throws PortalException {

		Comment comment = _commentManager.fetchComment(classPK);

		if (!(comment instanceof WorkflowableComment)) {
			return null;
		}

		WorkflowableComment workflowableComment = (WorkflowableComment)comment;

		CommentAssetRenderer commentAssetRenderer = new CommentAssetRenderer(
			workflowableComment, this);

		commentAssetRenderer.setAssetRendererType(type);
		commentAssetRenderer.setServletContext(_servletContext);

		return commentAssetRenderer;
	}

	@Override
	public String getIconCssClass() {
		return "comments";
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public PortletURL getURLView(
		LiferayPortletResponse liferayPortletResponse,
		WindowState windowState) {

		LiferayPortletURL liferayPortletURL =
			liferayPortletResponse.createLiferayPortletURL(
				CommentPortletKeys.COMMENT, PortletRequest.RENDER_PHASE);

		try {
			liferayPortletURL.setWindowState(windowState);
		}
		catch (WindowStateException windowStateException) {
		}

		return liferayPortletURL;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		DiscussionPermission discussionPermission =
			_commentManager.getDiscussionPermission(permissionChecker);

		return discussionPermission.hasPermission(classPK, actionId);
	}

	@Override
	public boolean isSelectable() {
		return _SELECTABLE;
	}

	private static final boolean _SELECTABLE = false;

	@Reference
	private CommentManager _commentManager;

	@Reference(target = "(osgi.web.symbolicname=com.liferay.comment.web)")
	private ServletContext _servletContext;

}