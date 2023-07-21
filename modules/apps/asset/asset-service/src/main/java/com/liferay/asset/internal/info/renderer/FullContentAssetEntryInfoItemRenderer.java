/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.internal.info.renderer;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.info.item.renderer.InfoItemRenderer;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jorge Ferrer
 */
@Component(service = InfoItemRenderer.class)
public class FullContentAssetEntryInfoItemRenderer
	extends BaseAssetEntryInfoItemRenderer {

	@Override
	protected String getTemplate() {
		return AssetRenderer.TEMPLATE_FULL_CONTENT;
	}

}