/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.web.internal.portlet.action;

import com.liferay.osb.koroneiki.scion.constants.ScionPortletKeys;
import com.liferay.osb.koroneiki.scion.model.ServiceProducer;
import com.liferay.osb.koroneiki.scion.service.AuthenticationTokenService;
import com.liferay.osb.koroneiki.scion.service.ServiceProducerLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ScionPortletKeys.AUTHENTICATION_TOKEN_MANAGER,
		"mvc.command.name=/authentication_token_manager/edit_authentication_token"
	},
	service = MVCActionCommand.class
)
public class EditAuthenticationTokenMVCActionCommand
	extends BaseMVCActionCommand {

	protected void deactivateAuthenticationToken(ActionRequest actionRequest)
		throws PortalException {

		long authenticationTokenId = ParamUtil.getLong(
			actionRequest, "authenticationTokenId");

		_authenticationTokenService.updateStatus(
			authenticationTokenId, WorkflowConstants.STATUS_INACTIVE);
	}

	protected void deleteAuthenticationToken(ActionRequest actionRequest)
		throws PortalException {

		long authenticationTokenId = ParamUtil.getLong(
			actionRequest, "authenticationTokenId");

		_authenticationTokenService.deleteAuthenticationToken(
			authenticationTokenId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DEACTIVATE)) {
				deactivateAuthenticationToken(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteAuthenticationToken(actionRequest);
			}
			else if (cmd.equals(Constants.RESTORE)) {
				restoreAuthenticationToken(actionRequest);
			}
			else {
				updateAuthenticationToken(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected void restoreAuthenticationToken(ActionRequest actionRequest)
		throws PortalException {

		long authenticationTokenId = ParamUtil.getLong(
			actionRequest, "authenticationTokenId");

		_authenticationTokenService.updateStatus(
			authenticationTokenId, WorkflowConstants.STATUS_APPROVED);
	}

	protected void updateAuthenticationToken(ActionRequest actionRequest)
		throws PortalException {

		long authenticationTokenId = ParamUtil.getLong(
			actionRequest, "authenticationTokenId");

		String name = ParamUtil.getString(actionRequest, "name");
		String token = ParamUtil.getString(actionRequest, "token");

		if (authenticationTokenId > 0) {
			_authenticationTokenService.updateAuthenticationToken(
				authenticationTokenId, name);
		}
		else {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceProducer serviceProducer =
				_serviceProducerLocalService.getAuthorizedServiceProducer(
					themeDisplay.getUserId());

			_authenticationTokenService.addAuthenticationToken(
				serviceProducer.getServiceProducerId(), name, token);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditAuthenticationTokenMVCActionCommand.class);

	@Reference
	private AuthenticationTokenService _authenticationTokenService;

	@Reference
	private ServiceProducerLocalService _serviceProducerLocalService;

}