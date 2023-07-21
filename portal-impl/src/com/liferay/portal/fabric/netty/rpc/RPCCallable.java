/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.rpc;

import com.liferay.petra.concurrent.NoticeableFuture;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public interface RPCCallable<T extends Serializable> extends Serializable {

	public NoticeableFuture<T> call() throws Throwable;

}