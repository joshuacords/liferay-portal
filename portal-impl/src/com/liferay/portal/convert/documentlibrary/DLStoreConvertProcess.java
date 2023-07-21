/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.convert.documentlibrary;

import com.liferay.document.library.kernel.store.Store;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Iván Zaera
 * @author Roberto Díaz
 */
public interface DLStoreConvertProcess {

	public void copy(Store sourceStore, Store targetStore)
		throws PortalException;

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link #copy(Store, Store)}
	 */
	@Deprecated
	public default void migrate(DLStoreConverter dlStoreConverter)
		throws PortalException {
	}

	public void move(Store sourceStore, Store targetStore)
		throws PortalException;

}