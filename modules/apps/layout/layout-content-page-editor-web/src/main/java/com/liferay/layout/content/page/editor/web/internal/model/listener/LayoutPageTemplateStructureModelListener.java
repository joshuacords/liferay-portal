/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.model.listener;

import com.liferay.asset.service.AssetEntryUsageLocalService;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.Portal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = ModelListener.class)
public class LayoutPageTemplateStructureModelListener
	extends BaseModelListener<LayoutPageTemplateStructure> {

	@Override
	public void onBeforeRemove(
			LayoutPageTemplateStructure layoutPageTemplateStructure)
		throws ModelListenerException {

		_assetEntryUsageLocalService.deleteAssetEntryUsages(
			_portal.getClassNameId(LayoutPageTemplateStructure.class),
			String.valueOf(
				layoutPageTemplateStructure.getLayoutPageTemplateStructureId()),
			layoutPageTemplateStructure.getClassPK());
	}

	@Reference
	private AssetEntryUsageLocalService _assetEntryUsageLocalService;

	@Reference
	private Portal _portal;

}