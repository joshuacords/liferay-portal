/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.portlet.action;

import com.liferay.dynamic.data.mapping.service.DDMStructureService;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + JournalPortletKeys.JOURNAL,
		"mvc.command.name=/journal/delete_ddm_structure"
	},
	service = MVCActionCommand.class
)
public class DeleteDDMStructureMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] deleteDDMStructureIds = null;

		long ddmStructureId = ParamUtil.getLong(
			actionRequest, "ddmStructureId");

		if (ddmStructureId > 0) {
			deleteDDMStructureIds = new long[] {ddmStructureId};
		}
		else {
			deleteDDMStructureIds = ParamUtil.getLongValues(
				actionRequest, "rowIds");
		}

		for (long deleteDDMStructureId : deleteDDMStructureIds) {
			_ddmStructureService.deleteStructure(deleteDDMStructureId);
		}
	}

	@Reference(unbind = "-")
	protected void setDDMStructureService(
		DDMStructureService ddmStructureService) {

		_ddmStructureService = ddmStructureService;
	}

	private DDMStructureService _ddmStructureService;

}