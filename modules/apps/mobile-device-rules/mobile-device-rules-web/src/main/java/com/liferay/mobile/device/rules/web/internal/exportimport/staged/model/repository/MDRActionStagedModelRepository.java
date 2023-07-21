/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.exportimport.staged.model.repository;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.mobile.device.rules.model.MDRAction;
import com.liferay.mobile.device.rules.service.MDRActionLocalService;
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
	property = "model.class.name=com.liferay.mobile.device.rules.model.MDRAction",
	service = StagedModelRepository.class
)
public class MDRActionStagedModelRepository
	implements StagedModelRepository<MDRAction> {

	@Override
	public MDRAction addStagedModel(
			PortletDataContext portletDataContext, MDRAction mdrAction)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteStagedModel(MDRAction mdrAction) throws PortalException {
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
	public MDRAction fetchMissingReference(String uuid, long groupId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public MDRAction fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<MDRAction> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		throw new UnsupportedOperationException();
	}

	@Override
	public MDRAction getStagedModel(long actionId) throws PortalException {
		return _mdrActionLocalService.getMDRAction(actionId);
	}

	@Override
	public void restoreStagedModel(
			PortletDataContext portletDataContext, MDRAction mdrAction)
		throws PortletDataException {

		throw new UnsupportedOperationException();
	}

	@Override
	public MDRAction saveStagedModel(MDRAction mdrAction)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public MDRAction updateStagedModel(
			PortletDataContext portletDataContext, MDRAction mdrAction)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Reference
	private MDRActionLocalService _mdrActionLocalService;

}