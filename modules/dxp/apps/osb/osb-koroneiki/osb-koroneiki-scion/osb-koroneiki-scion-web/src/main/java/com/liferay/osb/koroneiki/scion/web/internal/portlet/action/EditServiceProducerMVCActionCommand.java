/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.web.internal.portlet.action;

import com.liferay.osb.koroneiki.scion.constants.ScionPortletKeys;
import com.liferay.osb.koroneiki.scion.exception.ServiceProducerNameException;
import com.liferay.osb.koroneiki.scion.service.ServiceProducerLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ScionPortletKeys.SERVICE_PRODUCERS_ADMIN,
		"mvc.command.name=/service_producers_admin/edit_service_producer"
	},
	service = MVCActionCommand.class
)
public class EditServiceProducerMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteServiceProducer(ActionRequest actionRequest)
		throws PortalException {

		long serviceProducerId = ParamUtil.getLong(
			actionRequest, "serviceProducerId");

		_serviceProducerLocalService.deleteServiceProducer(serviceProducerId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteServiceProducer(actionRequest);
			}
			else {
				updateServiceProducer(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			if (exception instanceof ServiceProducerNameException) {
				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter(
					"mvcRenderCommandName",
					"/service_producers_admin/edit_service_producer");
			}
			else {
				_log.error(exception, exception);

				throw exception;
			}
		}
	}

	protected void updateServiceProducer(ActionRequest actionRequest)
		throws PortalException {

		long serviceProducerId = ParamUtil.getLong(
			actionRequest, "serviceProducerId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");

		if (serviceProducerId <= 0) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			_serviceProducerLocalService.addServiceProducer(
				themeDisplay.getUserId(), name, description);
		}
		else {
			_serviceProducerLocalService.updateServiceProducer(
				serviceProducerId, name, description);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditServiceProducerMVCActionCommand.class);

	@Reference
	private ServiceProducerLocalService _serviceProducerLocalService;

}