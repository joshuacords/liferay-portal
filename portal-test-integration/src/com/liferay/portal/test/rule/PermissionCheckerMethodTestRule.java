/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.test.rule;

import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.test.rule.MethodTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.security.permission.SimplePermissionChecker;

import org.junit.runner.Description;

/**
 * @author Tom Wang
 */
public class PermissionCheckerMethodTestRule extends MethodTestRule<Void> {

	public static final PermissionCheckerMethodTestRule INSTANCE =
		new PermissionCheckerMethodTestRule();

	@Override
	public void afterMethod(Description description, Void c, Object target)
		throws Throwable {

		PermissionThreadLocal.setPermissionChecker(_originalPermissionChecker);

		PrincipalThreadLocal.setName(_originalName);
	}

	@Override
	public Void beforeMethod(Description description, Object target)
		throws Exception {

		setUpPermissionThreadLocal();
		setUpPrincipalThreadLocal();

		return null;
	}

	protected void setUpPermissionThreadLocal() throws Exception {
		_originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		PermissionThreadLocal.setPermissionChecker(
			new SimplePermissionChecker() {
				{
					init(TestPropsValues.getUser());
				}

				@Override
				public boolean hasOwnerPermission(
					long companyId, String name, String primKey, long ownerId,
					String actionId) {

					return true;
				}

			});
	}

	protected void setUpPrincipalThreadLocal() throws Exception {
		_originalName = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(TestPropsValues.getUserId());
	}

	private PermissionCheckerMethodTestRule() {
	}

	private String _originalName;
	private PermissionChecker _originalPermissionChecker;

}