/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.kernel.model;

import com.liferay.social.kernel.util.SocialRelationTypesUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class SocialRelationConstants {

	public static final int TYPE_BI_CONNECTION = 12;

	public static final int TYPE_BI_COWORKER = 1;

	public static final int TYPE_BI_FRIEND = 2;

	public static final int TYPE_BI_ROMANTIC_PARTNER = 3;

	public static final int TYPE_BI_SIBLING = 4;

	public static final int TYPE_BI_SPOUSE = 5;

	public static final int TYPE_UNI_CHILD = 6;

	public static final int TYPE_UNI_ENEMY = 9;

	public static final int TYPE_UNI_FOLLOWER = 8;

	public static final int TYPE_UNI_PARENT = 7;

	public static final int TYPE_UNI_SUBORDINATE = 10;

	public static final int TYPE_UNI_SUPERVISOR = 11;

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             SocialRelationTypesUtil#getTypeLabel(int)}
	 */
	@Deprecated
	public static String getTypeLabel(int type) {
		return SocialRelationTypesUtil.getTypeLabel(type);
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             SocialRelationTypesUtil#isTypeBi(int)}
	 */
	@Deprecated
	public static boolean isTypeBi(int type) {
		return SocialRelationTypesUtil.isTypeBi(type);
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             SocialRelationTypesUtil#isTypeUni(int)}
	 */
	@Deprecated
	public static boolean isTypeUni(int type) {
		return SocialRelationTypesUtil.isTypeUni(type);
	}

}