/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.internal.service;

import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalService;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalServiceWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class LayoutPageTemplateLayoutPrototypeLocalServiceWrapper
	extends LayoutPrototypeLocalServiceWrapper {

	public LayoutPageTemplateLayoutPrototypeLocalServiceWrapper() {
		super(null);
	}

	public LayoutPageTemplateLayoutPrototypeLocalServiceWrapper(
		LayoutPrototypeLocalService layoutPrototypeLocalService) {

		super(layoutPrototypeLocalService);
	}

	@Override
	public LayoutPrototype addLayoutPrototype(
			long userId, long companyId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		LayoutPrototype layoutPrototype = super.addLayoutPrototype(
			userId, companyId, nameMap, descriptionMap, active, serviceContext);

		if (ExportImportThreadLocal.isStagingInProcess() ||
			ExportImportThreadLocal.isImportInProcess()) {

			return layoutPrototype;
		}

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.
				fetchFirstLayoutPageTemplateEntry(
					layoutPrototype.getLayoutPrototypeId());

		if (layoutPageTemplateEntry != null) {
			return layoutPrototype;
		}

		_layoutPageTemplateEntryLocalService.addLayoutPageTemplateEntry(
			layoutPrototype);

		return layoutPrototype;
	}

	@Override
	public LayoutPrototype deleteLayoutPrototype(long layoutPrototypeId)
		throws PortalException {

		LayoutPrototype layoutPrototype = super.deleteLayoutPrototype(
			layoutPrototypeId);

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.
				fetchFirstLayoutPageTemplateEntry(
					layoutPrototype.getLayoutPrototypeId());

		if (layoutPageTemplateEntry != null) {
			_layoutPageTemplateEntryLocalService.deleteLayoutPageTemplateEntry(
				layoutPageTemplateEntry);
		}

		return layoutPrototype;
	}

	@Override
	public LayoutPrototype updateLayoutPrototype(
			long layoutPrototypeId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		LayoutPrototype layoutPrototype = super.updateLayoutPrototype(
			layoutPrototypeId, nameMap, descriptionMap, active, serviceContext);

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.
				fetchFirstLayoutPageTemplateEntry(
					layoutPrototype.getLayoutPrototypeId());

		if (layoutPageTemplateEntry == null) {
			return layoutPrototype;
		}

		String nameXML = layoutPrototype.getName();

		Locale defaultLocale = LocaleUtil.fromLanguageId(
			LocalizationUtil.getDefaultLanguageId(nameXML));

		int status = WorkflowConstants.STATUS_INACTIVE;

		if (layoutPrototype.isActive()) {
			status = WorkflowConstants.STATUS_APPROVED;
		}

		_layoutPageTemplateEntryLocalService.updateLayoutPageTemplateEntry(
			layoutPageTemplateEntry.getUserId(),
			layoutPageTemplateEntry.getLayoutPageTemplateEntryId(),
			nameMap.get(defaultLocale), status);

		return layoutPrototype;
	}

	@Reference
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

}