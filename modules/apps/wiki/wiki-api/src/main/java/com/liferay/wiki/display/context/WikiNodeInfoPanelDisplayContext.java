/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.display.context;

import com.liferay.wiki.model.WikiNode;

/**
 * @author Roberto Díaz
 */
public interface WikiNodeInfoPanelDisplayContext extends WikiDisplayContext {

	public WikiNode getFirstNode();

	public int getNodesCount();

	public int getSelectedNodesCount();

	public boolean isMultipleNodeSelection();

	public boolean isSingleNodeSelection();

}