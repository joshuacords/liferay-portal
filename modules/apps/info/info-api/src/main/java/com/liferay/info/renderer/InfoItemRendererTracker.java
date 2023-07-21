/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.renderer;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author     Jorge Ferrer
 * @deprecated As of Mueller (7.2.x), moved to {@link
 *             com.liferay.info.item.renderer.InfoItemRendererTracker}
 */
@Deprecated
@ProviderType
public interface InfoItemRendererTracker {

	public InfoItemRenderer getInfoItemRenderer(String key);

	public List<InfoItemRenderer> getInfoItemRenderers();

	public List<InfoItemRenderer> getInfoItemRenderers(String itemClassName);

}