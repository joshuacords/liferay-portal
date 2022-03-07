/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.exception.ContactEmailAddressException;
import com.liferay.osb.provisioning.exception.ContactNameException;
import com.liferay.osb.provisioning.exception.ContactRequiredException;
import com.liferay.osb.provisioning.exception.DuplicateContactRoleException;
import com.liferay.osb.provisioning.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.osb.provisioning.web.internal.util.ZendeskValidator;
import com.liferay.portal.kernel.exception.NoSuchContactException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

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
		"mvc.command.name=/accounts/assign_contact_roles"
	},
	service = MVCActionCommand.class
)
public class AssignAccountContactRolesMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			String accountKey = ParamUtil.getString(
				actionRequest, "accountKey");

			String emailAddress = ParamUtil.getString(
				actionRequest, "emailAddress");
			String[] addContactRoleKeys = ParamUtil.getStringValues(
				actionRequest, "addContactRoleKeys");
			String[] deleteContactRoleKeys = ParamUtil.getStringValues(
				actionRequest, "deleteContactRoleKeys");

			if (!ArrayUtil.isEmpty(addContactRoleKeys)) {
				_validateAccountWorkerContactRole(
					accountKey, ContactRoleConstants.NAME_PRIMARY_CONTACT,
					emailAddress, addContactRoleKeys);

				_validateAccountWorkerContactRole(
					accountKey, ContactRoleConstants.NAME_SECONDARY_CONTACT,
					emailAddress, addContactRoleKeys);

				Contact contact =
					_contactIdentityProvider.fetchContactByEmailAddress(
						emailAddress);

				if (contact == null) {
					Account account = _accountWebService.getAccount(accountKey);

					String subscriptionState =
						_accountReader.getSubscriptionState(account);

					if (subscriptionState.equals(
							ProductPurchaseConstants.STATE_ACTIVE)) {

						String firstName = ParamUtil.getString(
							actionRequest, "firstName");
						String lastName = ParamUtil.getString(
							actionRequest, "lastName");

						_contactIdentityProvider.createContact(
							emailAddress, firstName, StringPool.BLANK,
							lastName);
					}
					else {
						throw new NoSuchContactException();
					}
				}
				else {
					_contactIdentityProvider.syncContact(contact);
				}

				_accountWebService.assignContactRolesByEmailAddress(
					user.getFullName(), user.getUuid(), accountKey,
					emailAddress, addContactRoleKeys);
			}

			if (!ArrayUtil.isEmpty(deleteContactRoleKeys)) {
				ContactRole supportAdministratorContactRole =
					_contactRoleWebService.getContactRole(
						ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
						ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR);
				ContactRole supportRequesterContactRole =
					_contactRoleWebService.getContactRole(
						ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
						ContactRoleConstants.NAME_SUPPORT_REQUESTER);

				if (ArrayUtil.contains(
						deleteContactRoleKeys,
						supportAdministratorContactRole.getKey()) ||
					ArrayUtil.contains(
						deleteContactRoleKeys,
						supportRequesterContactRole.getKey())) {

					_zendeskValidator.validateCustomerZendeskTickets(
						accountKey, emailAddress);
				}

				_accountWebService.unassignContactRolesByEmailAddress(
					user.getFullName(), user.getUuid(), accountKey,
					emailAddress, deleteContactRoleKeys);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			if (exception instanceof ContactEmailAddressException ||
				exception instanceof ContactNameException ||
				exception instanceof DuplicateContactRoleException ||
				exception instanceof NoSuchContactException ||
				exception instanceof Problem.ProblemException) {

				SessionErrors.add(
					actionRequest, exception.getClass(), exception);

				String contactRoleType = ParamUtil.getString(
					actionRequest, "contactRoleType");

				if (Validator.isNotNull(contactRoleType)) {
					if (contactRoleType.equals(
							ContactRole.Type.ACCOUNT_CUSTOMER.toString())) {

						actionResponse.setRenderParameter(
							"mvcRenderCommandName",
							"/accounts/assign_contacts");
					}
					else {
						actionResponse.setRenderParameter(
							"mvcRenderCommandName",
							"/accounts/assign_liferay_workers");
					}
				}
			}
			else if (exception instanceof ContactRequiredException) {
				SessionErrors.add(
					actionRequest, exception.getClass(), exception);

				sendRedirect(actionRequest, actionResponse);
			}
			else {
				_log.error(exception, exception);

				throw exception;
			}
		}
	}

	private void _validateAccountWorkerContactRole(
			String accountKey, String contactRoleName, String emailAddress,
			String[] addContactRoleKeys)
		throws Exception {

		ContactRole contactRole = _contactRoleWebService.getContactRole(
			ContactRole.Type.ACCOUNT_WORKER.toString(), contactRoleName);

		if (ArrayUtil.contains(addContactRoleKeys, contactRole.getKey())) {
			FilterQuery filterQuery = new FilterQuery();

			filterQuery.addLambdaEquals(
				true, "accountKeysContactRoleKeys",
				accountKey + "_" + contactRole.getKey());

			List<Contact> contacts = _contactWebService.search(
				StringPool.BLANK, filterQuery, 1, 1, StringPool.BLANK);

			if (!contacts.isEmpty()) {
				Contact contact = contacts.get(0);

				if (!emailAddress.equals(contact.getEmailAddress())) {
					throw new DuplicateContactRoleException();
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssignAccountContactRolesMVCActionCommand.class);

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _contactIdentityProvider;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private ZendeskValidator _zendeskValidator;

}