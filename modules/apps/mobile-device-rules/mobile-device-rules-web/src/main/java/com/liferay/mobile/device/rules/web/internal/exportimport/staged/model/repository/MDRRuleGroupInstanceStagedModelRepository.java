/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.exportimport.staged.model.repository;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Akos Thurzo
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.mobile.device.rules.model.MDRRuleGroupInstance",
	service = StagedModelRepository.class
)
public class MDRRuleGroupInstanceStagedModelRepository
	implements StagedModelRepository<MDRRuleGroupInstance> {

	@Override
	public MDRRuleGroupInstance addStagedModel(
			PortletDataContext portletDataContext,
			MDRRuleGroupInstance mdrRuleGroupInstance)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteStagedModel(MDRRuleGroupInstance mdrRuleGroupInstance)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteStagedModels(PortletDataContext portletDataContext)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public MDRRuleGroupInstance fetchMissingReference(
		String uuid, long groupId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public MDRRuleGroupInstance fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<MDRRuleGroupInstance> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		throw new UnsupportedOperationException();
	}

	@Override
	public MDRRuleGroupInstance getStagedModel(long ruleGroupInstanceId)
		throws PortalException {

		return _mdrRuleGroupInstanceLocalService.getMDRRuleGroupInstance(
			ruleGroupInstanceId);
	}

	@Override
	public void restoreStagedModel(
			PortletDataContext portletDataContext,
			MDRRuleGroupInstance mdrRuleGroupInstance)
		throws PortletDataException {

		throw new UnsupportedOperationException();
	}

	@Override
	public MDRRuleGroupInstance saveStagedModel(
			MDRRuleGroupInstance mdrRuleGroupInstance)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public MDRRuleGroupInstance updateStagedModel(
			PortletDataContext portletDataContext,
			MDRRuleGroupInstance mdrRuleGroupInstance)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Reference
	private MDRRuleGroupInstanceLocalService _mdrRuleGroupInstanceLocalService;

}