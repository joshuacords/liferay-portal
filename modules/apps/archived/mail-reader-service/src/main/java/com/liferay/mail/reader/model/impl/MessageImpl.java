/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.model.impl;

import com.liferay.mail.reader.model.Attachment;
import com.liferay.mail.reader.service.AttachmentLocalServiceUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class MessageImpl extends MessageBaseImpl {

	public MessageImpl() {
	}

	@Override
	public String getBcc() {
		return normalizeAddress(super.getBcc());
	}

	@Override
	public String getCc() {
		return normalizeAddress(super.getCc());
	}

	@Override
	public long getGroupId() throws PortalException {
		User user = UserLocalServiceUtil.getUser(getUserId());

		Group group = user.getGroup();

		return group.getGroupId();
	}

	@Override
	public String getTo() {
		return normalizeAddress(super.getTo());
	}

	@Override
	public boolean hasAttachments() {
		String contentType = getContentType();

		if ((contentType != null) && contentType.startsWith(_MULTIPART_MIXED)) {
			return true;
		}

		List<Attachment> attachments =
			AttachmentLocalServiceUtil.getAttachments(getMessageId());

		return !attachments.isEmpty();
	}

	@Override
	public boolean hasFlag(int flag) {
		int[] flags = StringUtil.split(getFlags(), 0);

		return ArrayUtil.contains(flags, flag);
	}

	protected String normalizeAddress(String address) {
		return StringUtil.replace(
			address, CharPool.COMMA, StringPool.COMMA_AND_SPACE);
	}

	private static final String _MULTIPART_MIXED = "multipart/MIXED";

}