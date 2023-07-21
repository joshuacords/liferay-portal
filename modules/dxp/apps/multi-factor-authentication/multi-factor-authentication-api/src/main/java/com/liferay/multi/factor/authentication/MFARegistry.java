/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication;

import com.liferay.multi.factor.authentication.checker.MFAChecker;
import com.liferay.multi.factor.authentication.policy.MFAPolicy;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Tomas Polesovsky
 */
@ProviderType
public interface MFARegistry {

	public MFAChecker getMFAChecker(String name);

	public List<MFAChecker> getMFACheckers();

	public List<MFAPolicy> getMFAPolicies();

	public MFAPolicy getMFAPolicy(String name);

}