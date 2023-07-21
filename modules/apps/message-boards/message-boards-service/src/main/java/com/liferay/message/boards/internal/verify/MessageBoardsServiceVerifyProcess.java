/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.internal.verify;

import com.liferay.message.boards.internal.verify.model.MBDiscussionVerifiableModel;
import com.liferay.message.boards.internal.verify.model.MBThreadFlagVerifiableModel;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.verify.VerifyGroupedModel;
import com.liferay.portal.verify.VerifyProcess;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author     Brian Wing Shun Chan
 * @author     Zsolt Berentey
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Component(
	immediate = true,
	property = "verify.process.name=com.liferay.message.boards.service",
	service = VerifyProcess.class
)
@Deprecated
public class MessageBoardsServiceVerifyProcess extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		verifyGroupedModels();
	}

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.message.boards.service)(&(release.schema.version>=2.0.0)(!(release.schema.version>=3.0.0))))",
		unbind = "-"
	)
	protected void setRelease(Release release) {
	}

	protected void verifyGroupedModels() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			_verifyGroupedModel.verify(
				new MBDiscussionVerifiableModel(),
				new MBThreadFlagVerifiableModel());
		}
	}

	private final VerifyGroupedModel _verifyGroupedModel =
		new VerifyGroupedModel();

}