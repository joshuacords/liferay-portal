/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.cluster;

import java.lang.annotation.Annotation;

/**
 * @author     Shuyang Zhou
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
@SuppressWarnings("all")
public class NullClusterable implements Clusterable {

	public static final Clusterable NULL_CLUSTERABLE = new NullClusterable();

	@Override
	public Class<? extends ClusterInvokeAcceptor> acceptor() {
		return null;
	}

	@Override
	public Class<? extends Annotation> annotationType() {
		return Clusterable.class;
	}

	@Override
	public boolean onMaster() {
		return false;
	}

	private NullClusterable() {
	}

}