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
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.petra.process.LoggingOutputProcessor;
import com.liferay.petra.process.ProcessUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.lock.model.Lock;
import com.liferay.portal.lock.service.LockLocalService;

import java.util.Map;
import java.util.concurrent.Future;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/sync_to_customer_portal"
	},
	service = MVCActionCommand.class
)
public class SyncToCustomerPortalMVCActionCommand extends BaseMVCActionCommand {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) throws Exception {
		_accountSyncFilePath = GetterUtil.getString(
			properties.get("accountSyncFilePath"));
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			String accountKey = ParamUtil.getString(
				actionRequest, "accountKey");

			Lock lock = _lockLocalService.lock(
				Account.class.getName(), Account.class.getName(), accountKey);

			if (!lock.isNew()) {
				SessionErrors.add(actionRequest, "syncInUse");

				sendRedirect(actionRequest, actionResponse);

				return;
			}

			Future<?> future = ProcessUtil.execute(
				new LoggingOutputProcessor(
					(stdErr, line) -> {
						if (stdErr) {
							_log.error(line);
						}
						else if (_log.isInfoEnabled()) {
							_log.info(line);
						}
					}),
				new String[] {
					_accountSyncFilePath, "--context_param",
					"accountSyncFilter=accountKey eq '" + accountKey + "'"
				});

			future.get();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			SessionErrors.add(actionRequest, exception.getClass());
		}
		finally {
			_lockLocalService.unlock(
				Account.class.getName(), Account.class.getName());
		}

		sendRedirect(actionRequest, actionResponse);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SyncToCustomerPortalMVCActionCommand.class);

	private String _accountSyncFilePath;

	@Reference
	private LockLocalService _lockLocalService;

}