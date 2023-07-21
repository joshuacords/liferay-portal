/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.model.listener;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.model.AssetEntryUsage;
import com.liferay.asset.service.AssetEntryUsageLocalService;
import com.liferay.layout.content.page.editor.web.internal.util.ContentUtil;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructureRel;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.Portal;

import java.util.Optional;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = ModelListener.class)
public class LayoutPageTemplateStructureRelModelListener
	extends BaseModelListener<LayoutPageTemplateStructureRel> {

	@Override
	public void onAfterUpdate(
			LayoutPageTemplateStructureRel layoutPageTemplateStructureRel)
		throws ModelListenerException {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					layoutPageTemplateStructureRel.
						getLayoutPageTemplateStructureId());

		if (layoutPageTemplateStructure == null) {
			return;
		}

		_assetEntryUsageLocalService.deleteAssetEntryUsages(
			_portal.getClassNameId(LayoutPageTemplateStructure.class),
			String.valueOf(
				layoutPageTemplateStructure.getLayoutPageTemplateStructureId()),
			layoutPageTemplateStructure.getClassPK());

		try {
			Set<AssetEntry> assetEntries =
				ContentUtil.getLayoutMappedAssetEntries(
					layoutPageTemplateStructureRel.getData());

			for (AssetEntry assetEntry : assetEntries) {
				AssetEntryUsage assetEntryUsage =
					_assetEntryUsageLocalService.fetchAssetEntryUsage(
						assetEntry.getEntryId(),
						_portal.getClassNameId(
							LayoutPageTemplateStructure.class),
						String.valueOf(
							layoutPageTemplateStructure.
								getLayoutPageTemplateStructureId()),
						layoutPageTemplateStructure.getClassPK());

				if (assetEntryUsage != null) {
					continue;
				}

				ServiceContext serviceContext = Optional.ofNullable(
					ServiceContextThreadLocal.getServiceContext()
				).orElse(
					new ServiceContext()
				);

				_assetEntryUsageLocalService.addAssetEntryUsage(
					layoutPageTemplateStructure.getGroupId(),
					assetEntry.getEntryId(),
					_portal.getClassNameId(LayoutPageTemplateStructure.class),
					String.valueOf(
						layoutPageTemplateStructure.
							getLayoutPageTemplateStructureId()),
					layoutPageTemplateStructure.getClassPK(), serviceContext);
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Reference
	private AssetEntryUsageLocalService _assetEntryUsageLocalService;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Reference
	private Portal _portal;

}