/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.internal.exportimport.data.handler;

import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.site.navigation.model.SiteNavigationMenu;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class SiteNavigationMenuStagedModelDataHandler
	extends BaseStagedModelDataHandler<SiteNavigationMenu> {

	public static final String[] CLASS_NAMES = {
		SiteNavigationMenu.class.getName()
	};

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(SiteNavigationMenu siteNavigationMenu) {
		return siteNavigationMenu.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			SiteNavigationMenu siteNavigationMenu)
		throws Exception {

		Element siteNavigationMenuElement =
			portletDataContext.getExportDataElement(siteNavigationMenu);

		portletDataContext.addClassedModel(
			siteNavigationMenuElement,
			ExportImportPathUtil.getModelPath(siteNavigationMenu),
			siteNavigationMenu);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			SiteNavigationMenu siteNavigationMenu)
		throws Exception {

		SiteNavigationMenu importedSiteNavigationMenu =
			(SiteNavigationMenu)siteNavigationMenu.clone();

		importedSiteNavigationMenu.setGroupId(
			portletDataContext.getScopeGroupId());

		SiteNavigationMenu existingSiteNavigationMenu =
			_stagedModelRepository.fetchStagedModelByUuidAndGroupId(
				siteNavigationMenu.getUuid(),
				portletDataContext.getScopeGroupId());

		if ((existingSiteNavigationMenu == null) ||
			!portletDataContext.isDataStrategyMirror()) {

			importedSiteNavigationMenu = _stagedModelRepository.addStagedModel(
				portletDataContext, importedSiteNavigationMenu);
		}
		else {
			importedSiteNavigationMenu.setMvccVersion(
				existingSiteNavigationMenu.getMvccVersion());
			importedSiteNavigationMenu.setSiteNavigationMenuId(
				existingSiteNavigationMenu.getSiteNavigationMenuId());

			importedSiteNavigationMenu =
				_stagedModelRepository.updateStagedModel(
					portletDataContext, importedSiteNavigationMenu);
		}

		portletDataContext.importClassedModel(
			siteNavigationMenu, importedSiteNavigationMenu);
	}

	@Override
	protected StagedModelRepository<SiteNavigationMenu>
		getStagedModelRepository() {

		return _stagedModelRepository;
	}

	@Reference(
		target = "(model.class.name=com.liferay.site.navigation.model.SiteNavigationMenu)",
		unbind = "-"
	)
	protected void setStagedModelRepository(
		StagedModelRepository<SiteNavigationMenu> stagedModelRepository) {

		_stagedModelRepository = stagedModelRepository;
	}

	private StagedModelRepository<SiteNavigationMenu> _stagedModelRepository;

}