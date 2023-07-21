/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

/**
 * @author     Connor McKay
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public interface PermissionedModel extends PersistedModel {

	public long getResourceBlockId();

	public void setResourceBlockId(long resourceBlockId);

}