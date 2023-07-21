/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface AccountFinder {

	public int countByContact(long contactId);

	public int countByTeam(long teamId);

	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
		findByContact(long contactId, int start, int end);

	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
		findByTeam(long teamId, int start, int end);

}