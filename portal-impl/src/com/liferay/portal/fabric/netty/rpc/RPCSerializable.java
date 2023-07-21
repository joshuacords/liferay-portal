/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.rpc;

import io.netty.channel.Channel;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public abstract class RPCSerializable implements Serializable {

	public RPCSerializable(long id) {
		this.id = id;
	}

	public abstract void execute(Channel channel);

	protected final long id;

	private static final long serialVersionUID = 1L;

}