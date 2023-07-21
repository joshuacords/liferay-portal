/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.user.associated.data.web.internal.portlet.action;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.user.associated.data.anonymizer.UADAnonymizer;
import com.liferay.user.associated.data.constants.UserAssociatedDataPortletKeys;
import com.liferay.user.associated.data.web.internal.registry.UADRegistry;
import com.liferay.user.associated.data.web.internal.util.UADAnonymizerHelper;

import java.util.Collection;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Samuel Trong Tran
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + UserAssociatedDataPortletKeys.USER_ASSOCIATED_DATA,
		"mvc.command.name=/anonymize_nonreviewable_uad_data"
	},
	service = MVCActionCommand.class
)
public class AnonymizeNonreviewableUADDataMVCActionCommand
	extends BaseUADMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Collection<UADAnonymizer> uadAnonymizers =
			_uadRegistry.getNonreviewableUADAnonymizers();

		for (UADAnonymizer uadAnonymizer : uadAnonymizers) {
			User selectedUser = getSelectedUser(actionRequest);

			User anonymousUser = _uadAnonymizerHelper.getAnonymousUser(
				selectedUser.getCompanyId());

			uadAnonymizer.autoAnonymizeAll(
				selectedUser.getUserId(), anonymousUser);
		}

		doNonreviewableRedirect(actionRequest, actionResponse);
	}

	@Reference
	private UADAnonymizerHelper _uadAnonymizerHelper;

	@Reference
	private UADRegistry _uadRegistry;

}