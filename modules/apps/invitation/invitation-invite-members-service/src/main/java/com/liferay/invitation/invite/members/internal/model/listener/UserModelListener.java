/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.invitation.invite.members.internal.model.listener;

import com.liferay.invitation.invite.members.service.MemberRequestLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Norbert Kocsis
 */
@Component(immediate = true, service = ModelListener.class)
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterCreate(User user) {
		try {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			if (serviceContext == null) {
				return;
			}

			Map<String, String> headers = serviceContext.getHeaders();

			if (headers == null) {
				return;
			}

			String refererURL = headers.get(WebKeys.REFERER);

			String portletId = _http.getParameter(refererURL, "p_p_id", false);

			String redirectURL = _http.getParameter(
				refererURL,
				_portal.getPortletNamespace(portletId) + "redirectURL", false);

			String key = _http.getParameter(
				redirectURL, _portal.getPortletNamespace(portletId) + "key",
				false);

			if (Validator.isNotNull(key)) {
				_memberRequestLocalService.updateMemberRequest(
					key, user.getUserId());
			}
		}
		catch (Exception exception) {
			throw new ModelListenerException(exception);
		}
	}

	@Reference(unbind = "-")
	protected void setMemberRequestLocalService(
		MemberRequestLocalService memberRequestLocalService) {

		_memberRequestLocalService = memberRequestLocalService;
	}

	@Reference(unbind = "-")
	protected void setPortal(Portal portal) {
		_portal = portal;
	}

	@Reference
	private Http _http;

	private MemberRequestLocalService _memberRequestLocalService;
	private Portal _portal;

}