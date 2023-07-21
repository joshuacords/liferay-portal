/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.agent;

/**
 * @author Shuyang Zhou
 */
public interface FabricAgentListener {

	public void registered(FabricAgent fabricAgent);

	public void unregistered(FabricAgent fabricAgent);

}