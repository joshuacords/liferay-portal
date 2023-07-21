/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.support.websphere;

import com.ibm.ws.security.policy.DynamicPolicy;
import com.ibm.ws.security.policy.DynamicPolicyFactory;

import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.ProtectionDomain;

import java.util.Map;

/**
 * @author Raymond Augé
 */
public class DynamicPolicyHelper {

	protected void start() {
		_originalDynamicPolicy = DynamicPolicyFactory.getInstance();

		final DynamicPolicy originalDynamicPolicy = _originalDynamicPolicy;

		DynamicPolicy dynamicPolicy = new DynamicPolicy() {

			@Override
			public PermissionCollection getPermissions(
				CodeSource codeSource, Map map) {

				Policy policy = Policy.getPolicy();

				return policy.getPermissions(codeSource);
			}

			@Override
			public ProtectionDomain getProtectionDomain(CodeSource codeSource) {
				if (originalDynamicPolicy == null) {
					return null;
				}

				return originalDynamicPolicy.getProtectionDomain(codeSource);
			}

			@Override
			public void getSecurityPolicy(Map map1, Map map2) {
				if (originalDynamicPolicy == null) {
					return;
				}

				originalDynamicPolicy.getSecurityPolicy(map1, map2);
			}

			@Override
			public void removePolicy(Map map) {
				if (originalDynamicPolicy == null) {
					return;
				}

				originalDynamicPolicy.removePolicy(map);
			}

			@Override
			public void setupPolicy(Map map) {
				if (originalDynamicPolicy == null) {
					return;
				}

				originalDynamicPolicy.setupPolicy(map);
			}

		};

		DynamicPolicyFactory.setInstance(dynamicPolicy);
	}

	private static final DynamicPolicyHelper _dynamicPolicyHelper =
		new DynamicPolicyHelper();

	static {
		_dynamicPolicyHelper.start();
	}

	private DynamicPolicy _originalDynamicPolicy;

}