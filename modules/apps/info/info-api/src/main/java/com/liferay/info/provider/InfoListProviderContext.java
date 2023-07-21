/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.provider;

import com.liferay.info.display.contributor.InfoDisplayObjectProvider;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;

import java.util.Optional;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author     Jorge Ferrer
 * @deprecated As of Mueller (7.2.x), moved to {@link
 *             com.liferay.info.list.provider.InfoListProviderContext}
 */
@Deprecated
@ProviderType
public interface InfoListProviderContext {

	public Company getCompany();

	public Optional<Group> getGroupOptional();

	public Optional<InfoDisplayObjectProvider>
		getInfoDisplayObjectProviderOptional();

	public Optional<Layout> getLayoutOptional();

	public User getUser();

}