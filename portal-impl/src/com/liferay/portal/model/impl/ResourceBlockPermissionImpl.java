/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

/**
 * Stores the actions a role is permitted to perform on the resources in a
 * resource block.
 *
 * <p>
 * The <code>actionIds</code> attribute stores the bitwise IDs of all the
 * actions allowed by this permission.
 * </p>
 *
 * @author     Connor McKay
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class ResourceBlockPermissionImpl
	extends ResourceBlockPermissionBaseImpl {
}