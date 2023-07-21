/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet.bridges.mvc;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;

import java.util.concurrent.Callable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * @author Bruno Basto
 */
public abstract class BaseTransactionalMVCActionCommand
	implements MVCActionCommand {

	@Override
	public boolean processAction(
			final ActionRequest actionRequest,
			final ActionResponse actionResponse)
		throws PortletException {

		try {
			Callable<Boolean> callable = new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					doTransactionalCommand(actionRequest, actionResponse);

					return SessionErrors.isEmpty(actionRequest);
				}

			};

			return TransactionInvokerUtil.invoke(_transactionConfig, callable);
		}
		catch (Throwable t) {
			if (t instanceof PortletException) {
				throw (PortletException)t;
			}

			throw new PortletException(t);
		}
	}

	protected abstract void doTransactionalCommand(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception;

	private static final TransactionConfig _transactionConfig;

	static {
		TransactionConfig.Builder builder = new TransactionConfig.Builder();

		builder.setPropagation(Propagation.REQUIRES_NEW);
		builder.setRollbackForClasses(Exception.class);

		_transactionConfig = builder.build();
	}

}