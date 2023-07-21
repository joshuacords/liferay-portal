/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.currency.converter.web.internal.portlet.action;

import com.liferay.currency.converter.web.internal.constants.CurrencyConverterPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ValidatorException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Peter Fellwock
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CurrencyConverterPortletKeys.CURRENCY_CONVERTER,
		"mvc.command.name=edit"
	},
	service = MVCActionCommand.class
)
public class EditPreferencesMVCActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		if (!(actionResponse instanceof ActionResponse)) {
			return false;
		}

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return false;
		}

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		String[] symbols = StringUtil.split(
			StringUtil.toUpperCase(
				ParamUtil.getString(actionRequest, "symbols")));

		portletPreferences.setValues("symbols", symbols);

		try {
			portletPreferences.store();
		}
		catch (Exception exception) {
			SessionErrors.add(
				actionRequest, ValidatorException.class.getName(), exception);

			return false;
		}

		SessionMessages.add(
			actionRequest,
			_portal.getPortletId(actionRequest) +
				SessionMessages.KEY_SUFFIX_UPDATED_PREFERENCES);

		actionResponse.setRenderParameter("mvcPath", "/edit.jsp");

		return true;
	}

	@Reference
	private Portal _portal;

}