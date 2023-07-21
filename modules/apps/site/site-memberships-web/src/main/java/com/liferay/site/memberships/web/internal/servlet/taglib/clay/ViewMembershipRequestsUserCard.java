/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.memberships.web.internal.servlet.taglib.clay;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.BaseUserCard;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.MembershipRequest;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class ViewMembershipRequestsUserCard extends BaseUserCard {

	public ViewMembershipRequestsUserCard(
		MembershipRequest membershipRequest, User user,
		RenderRequest renderRequest, RenderResponse renderResponse) {

		super(user, renderRequest, null);

		_membershipRequest = membershipRequest;
		_renderResponse = renderResponse;

		_httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
	}

	@Override
	public String getHref() {
		PortletURL previewURL = _renderResponse.createRenderURL();

		previewURL.setParameter("mvcPath", "/preview_membership_request.jsp");
		previewURL.setParameter("redirect", themeDisplay.getURLCurrent());
		previewURL.setParameter(
			"membershipRequestId",
			String.valueOf(_membershipRequest.getMembershipRequestId()));

		return previewURL.toString();
	}

	@Override
	public String getSubtitle() {
		String replier = _getReplier();

		Date replyDate = _membershipRequest.getReplyDate();

		String replyDateDescription = LanguageUtil.getTimeDescription(
			_httpServletRequest,
			System.currentTimeMillis() - replyDate.getTime(), true);

		if (Validator.isNull(replier)) {
			return LanguageUtil.format(
				_httpServletRequest, "replied-x-ago", replyDateDescription);
		}

		return LanguageUtil.format(
			_httpServletRequest, "replied-by-x-x-ago",
			new String[] {replier, replyDateDescription});
	}

	private String _getReplier() {
		User membershipRequestReplierUser = UserLocalServiceUtil.fetchUserById(
			_membershipRequest.getReplierUserId());

		if (membershipRequestReplierUser == null) {
			return StringPool.BLANK;
		}

		if (!membershipRequestReplierUser.isDefaultUser()) {
			return HtmlUtil.escape(membershipRequestReplierUser.getFullName());
		}

		try {
			Company membershipRequestReplierCompany =
				CompanyLocalServiceUtil.getCompanyById(
					membershipRequestReplierUser.getCompanyId());

			return HtmlUtil.escape(membershipRequestReplierCompany.getName());
		}
		catch (Exception exception) {
		}

		return StringPool.BLANK;
	}

	private final HttpServletRequest _httpServletRequest;
	private final MembershipRequest _membershipRequest;
	private final RenderResponse _renderResponse;

}