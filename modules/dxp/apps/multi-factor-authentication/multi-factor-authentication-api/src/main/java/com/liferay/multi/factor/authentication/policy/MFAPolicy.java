/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.policy;

import com.liferay.multi.factor.authentication.checker.MFAChecker;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Tomas Polesovsky
 */
@ProviderType
public interface MFAPolicy {

	public MFAChecker getMFAChecker();

	public Set<String> getMFACheckerNames();

	public String getName();

}