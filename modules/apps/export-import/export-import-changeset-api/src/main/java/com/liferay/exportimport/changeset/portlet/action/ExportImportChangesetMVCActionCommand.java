/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.changeset.portlet.action;

import com.liferay.exportimport.changeset.Changeset;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Máté Thurzó
 */
@ProviderType
public interface ExportImportChangesetMVCActionCommand
	extends MVCActionCommand {

	public void processExportAction(
			ActionRequest actionRequest, ActionResponse actionResponse,
			Changeset changeset)
		throws Exception;

	public void processPublishAction(
			ActionRequest actionRequest, ActionResponse actionResponse,
			Changeset changeset)
		throws Exception;

}