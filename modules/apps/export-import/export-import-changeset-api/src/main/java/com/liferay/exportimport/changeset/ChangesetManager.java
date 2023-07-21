/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.changeset;

import java.util.Optional;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Máté Thurzó
 */
@ProviderType
public interface ChangesetManager {

	public void addChangeset(Changeset changeset);

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public void clearChangesets();

	public boolean hasChangeset(String changesetUuid);

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public Optional<Changeset> peekChangeset(String changesetUuid);

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #removeChangeset(String changesetUuid)}
	 */
	@Deprecated
	public Optional<Changeset> popChangeset(String changesetUuid);

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public long publishChangeset(
		Changeset changeset, ChangesetEnvironment changesetEnvironment);

	public Changeset removeChangeset(String changesetUuid);

}