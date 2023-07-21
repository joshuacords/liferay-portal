/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.type.controller.content.internal.model;

import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypeAccessPolicy;
import com.liferay.portal.kernel.model.impl.DefaultLayoutTypeAccessPolicyImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jürgen Kappler
 */
@Component(
	immediate = true, property = "layout.type=" + LayoutConstants.TYPE_CONTENT,
	service = LayoutTypeAccessPolicy.class
)
public class ContentLayoutTypeAccessPolicy
	extends DefaultLayoutTypeAccessPolicyImpl {
}