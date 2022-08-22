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

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.exception.DuplicateAnalyticsCloudGroupIdException;
import com.liferay.osb.provisioning.exception.DuplicateDXPCloudProjectIdException;
import com.liferay.osb.provisioning.exception.DuplicateDossieraKeyException;
import com.liferay.osb.provisioning.exception.MultipleDossieraKeysException;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
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
		"mvc.command.name=/edit_external_link"
	},
	service = MVCActionCommand.class
)
public class EditExternalLinkMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteExternalLink(ActionRequest actionRequest, User user)
		throws Exception {

		String externalLinkKey = ParamUtil.getString(
			actionRequest, "externalLinkKey");

		_externalLinkWebService.deleteExternalLink(
			user.getFullName(), user.getUuid(), externalLinkKey);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			if (cmd.equals(Constants.DELETE)) {
				deleteExternalLink(actionRequest, user);
			}
			else {
				updateExternalLink(actionRequest, user);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			if (exception instanceof DuplicateAnalyticsCloudGroupIdException ||
				exception instanceof DuplicateDossieraKeyException ||
				exception instanceof DuplicateDXPCloudProjectIdException ||
				exception instanceof MultipleDossieraKeysException ||
				exception instanceof Problem.ProblemException) {

				SessionErrors.add(
					actionRequest, exception.getClass(), exception);
			}
			else {
				throw exception;
			}
		}

		sendRedirect(actionRequest, actionResponse);
	}

	protected void updateExternalLink(ActionRequest actionRequest, User user)
		throws Exception {

		String externalLinkKey = ParamUtil.getString(
			actionRequest, "externalLinkKey");

		String entityId = ParamUtil.getString(actionRequest, "entityId");

		if (Validator.isNotNull(externalLinkKey) &&
			Validator.isNull(entityId)) {

			deleteExternalLink(actionRequest, user);

			return;
		}

		String accountKey = ParamUtil.getString(actionRequest, "accountKey");
		String domain = ParamUtil.getString(actionRequest, "domain");
		String entityName = ParamUtil.getString(actionRequest, "entityName");

		_validate(accountKey, domain, entityName, entityId);

		ExternalLink externalLink = new ExternalLink();

		externalLink.setDomain(domain);
		externalLink.setEntityName(entityName);
		externalLink.setEntityId(entityId);

		if (Validator.isNotNull(externalLinkKey)) {
			_externalLinkWebService.updateExternalLink(
				user.getFullName(), user.getUuid(), externalLinkKey,
				externalLink);
		}
		else {
			_externalLinkWebService.addAccountExternalLink(
				user.getFullName(), user.getUuid(), accountKey, externalLink);
		}
	}

	private void _validate(
			String accountKey, String domain, String entityName,
			String entityId)
		throws Exception {

		if (!domain.equals(ExternalLinkDomain.ANALYTICS_CLOUD) &&
			!domain.equals(ExternalLinkDomain.DOSSIERA) &&
			!domain.equals(ExternalLinkDomain.DXP_CLOUD)) {

			return;
		}

		List<Account> accounts = _accountWebService.getAccounts(
			domain, entityName, entityId, 1, 1);

		if (!accounts.isEmpty()) {
			if (domain.equals(ExternalLinkDomain.ANALYTICS_CLOUD)) {
				throw new DuplicateAnalyticsCloudGroupIdException();
			}
			else if (domain.equals(ExternalLinkDomain.DOSSIERA)) {
				throw new DuplicateDossieraKeyException();
			}
			else if (domain.equals(ExternalLinkDomain.DXP_CLOUD)) {
				throw new DuplicateDXPCloudProjectIdException();
			}
		}

		List<ExternalLink> externalLinks =
			_externalLinkWebService.getExternalLinks(accountKey, 1, 1000);

		for (ExternalLink externalLink : externalLinks) {
			String curDomain = externalLink.getDomain();

			if (!curDomain.equals(ExternalLinkDomain.DOSSIERA)) {
				continue;
			}

			String curEntityName = externalLink.getEntityName();

			if ((entityName.equals(ExternalLinkEntityName.DOSSIERA_ACCOUNT) &&
				 curEntityName.equals(
					 ExternalLinkEntityName.DOSSIERA_PROJECT)) ||
				(entityName.equals(ExternalLinkEntityName.DOSSIERA_PROJECT) &&
				 curEntityName.equals(
					 ExternalLinkEntityName.DOSSIERA_ACCOUNT))) {

				throw new MultipleDossieraKeysException();
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditExternalLinkMVCActionCommand.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ExternalLinkWebService _externalLinkWebService;

}