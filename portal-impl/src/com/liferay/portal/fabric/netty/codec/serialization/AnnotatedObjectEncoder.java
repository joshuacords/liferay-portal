/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.codec.serialization;

import com.liferay.petra.io.AnnotatedObjectOutputStream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
@ChannelHandler.Sharable
public class AnnotatedObjectEncoder extends MessageToByteEncoder<Serializable> {

	public static final AnnotatedObjectEncoder INSTANCE =
		new AnnotatedObjectEncoder();

	public static final String NAME = AnnotatedObjectEncoder.class.getName();

	@Override
	protected void encode(
			ChannelHandlerContext channelHandlerContext,
			Serializable serializable, ByteBuf byteBuf)
		throws IOException {

		int startIndex = byteBuf.writerIndex();

		ByteBufOutputStream byteBufOutputStream = new ByteBufOutputStream(
			byteBuf);

		byteBufOutputStream.writeInt(0);

		ObjectOutputStream objectOutputStream = new AnnotatedObjectOutputStream(
			byteBufOutputStream);

		objectOutputStream.writeObject(serializable);

		objectOutputStream.flush();

		int endIndex = byteBuf.writerIndex();

		byteBuf.setInt(startIndex, endIndex - startIndex - 4);
	}

	private AnnotatedObjectEncoder() {
	}

}