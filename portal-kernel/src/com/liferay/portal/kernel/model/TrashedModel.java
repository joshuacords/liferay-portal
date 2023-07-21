/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.trash.kernel.model.TrashEntry;

/**
 * @author Zsolt Berentey
 */
public interface TrashedModel {

	public int getStatus();

	public TrashEntry getTrashEntry() throws PortalException;

	public long getTrashEntryClassPK();

	public TrashHandler getTrashHandler();

	public boolean isInTrash();

	public boolean isInTrashContainer();

	public boolean isInTrashExplicitly();

	public boolean isInTrashImplicitly();

}