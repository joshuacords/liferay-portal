/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.exportimport.staged.model.repository;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService;
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
	property = "model.class.name=com.liferay.mobile.device.rules.model.MDRRuleGroup",
	service = StagedModelRepository.class
)
public class MDRRuleGroupStagedModelRepository
	implements StagedModelRepository<MDRRuleGroup> {

	@Override
	public MDRRuleGroup addStagedModel(
			PortletDataContext portletDataContext, MDRRuleGroup mdrRuleGroup)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteStagedModel(MDRRuleGroup mdrRuleGroup)
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
	public MDRRuleGroup fetchMissingReference(String uuid, long groupId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public MDRRuleGroup fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<MDRRuleGroup> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		throw new UnsupportedOperationException();
	}

	@Override
	public MDRRuleGroup getStagedModel(long ruleGroupId)
		throws PortalException {

		return _mdrRuleGroupLocalService.getMDRRuleGroup(ruleGroupId);
	}

	@Override
	public void restoreStagedModel(
			PortletDataContext portletDataContext, MDRRuleGroup mdrRuleGroup)
		throws PortletDataException {

		throw new UnsupportedOperationException();
	}

	@Override
	public MDRRuleGroup saveStagedModel(MDRRuleGroup mdrRuleGroup)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public MDRRuleGroup updateStagedModel(
			PortletDataContext portletDataContext, MDRRuleGroup mdrRuleGroup)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Reference
	private MDRRuleGroupLocalService _mdrRuleGroupLocalService;

}