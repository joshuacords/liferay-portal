/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy;

import com.liferay.portal.kernel.template.TemplateResource;

import java.util.List;

/**
 * @author Tina Tian
 */
public interface SoyTemplateResource extends TemplateResource {

	public List<TemplateResource> getTemplateResources();

}