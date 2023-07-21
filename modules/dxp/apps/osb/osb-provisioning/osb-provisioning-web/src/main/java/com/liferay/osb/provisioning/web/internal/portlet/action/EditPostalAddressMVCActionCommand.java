/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.PostalAddressWebService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
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
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/edit_postal_address"
	},
	service = MVCActionCommand.class
)
public class EditPostalAddressMVCActionCommand extends BaseMVCActionCommand {

	protected void deletePostalAddress(ActionRequest actionRequest, User user)
		throws Exception {

		long postalAddressId = ParamUtil.getLong(
			actionRequest, "postalAddressId");

		_postalAddressWebService.deletePostalAddress(
			user.getFullName(), user.getUuid(), postalAddressId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long postalAddressId = ParamUtil.getLong(
			actionRequest, "postalAddressId");

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			if (cmd.equals(Constants.DELETE)) {
				deletePostalAddress(actionRequest, user);
			}
			else {
				updatePostalAddress(actionRequest, postalAddressId, user);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			SessionErrors.add(actionRequest, exception.getClass(), exception);

			if (exception instanceof Problem.ProblemException) {
				sendRedirect(actionRequest, actionResponse);
			}
			else {
				throw exception;
			}
		}
	}

	protected void updatePostalAddress(
			ActionRequest actionRequest, long postalAddressId, User user)
		throws Exception {

		String accountKey = ParamUtil.getString(actionRequest, "accountKey");
		String streetAddressLine1 = ParamUtil.getString(
			actionRequest, "streetAddressLine1");
		String streetAddressLine2 = ParamUtil.getString(
			actionRequest, "streetAddressLine2");
		String streetAddressLine3 = ParamUtil.getString(
			actionRequest, "streetAddressLine3");
		String addressLocality = ParamUtil.getString(
			actionRequest, "addressLocality");
		String addressZip = ParamUtil.getString(actionRequest, "addressZip");
		String addressCountryName = ParamUtil.getString(
			actionRequest, "addressCountryName");
		String addressRegionName = ParamUtil.getString(
			actionRequest, "addressRegionName");

		boolean primary = ParamUtil.getBoolean(actionRequest, "addressPrimary");

		PostalAddress postalAddress = new PostalAddress();

		if (Validator.isNull(streetAddressLine1)) {
			streetAddressLine1 = StringPool.DASH;
		}

		postalAddress.setStreetAddressLine1(streetAddressLine1);

		postalAddress.setStreetAddressLine2(streetAddressLine2);
		postalAddress.setStreetAddressLine3(streetAddressLine3);

		if (Validator.isNull(addressLocality)) {
			addressLocality = StringPool.DASH;
		}

		postalAddress.setAddressLocality(addressLocality);

		if (Validator.isNull(addressZip)) {
			addressZip = StringPool.DASH;
		}

		postalAddress.setPostalCode(addressZip);

		postalAddress.setAddressRegion(addressRegionName);
		postalAddress.setAddressCountry(addressCountryName);
		postalAddress.setPrimary(primary);

		if (postalAddressId > 0) {
			_postalAddressWebService.updatePostalAddress(
				user.getFullName(), user.getUuid(), postalAddressId,
				postalAddress);
		}
		else {
			_postalAddressWebService.addPostalAddress(
				user.getFullName(), user.getUuid(), accountKey, postalAddress);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditPostalAddressMVCActionCommand.class);

	@Reference
	private PostalAddressWebService _postalAddressWebService;

}