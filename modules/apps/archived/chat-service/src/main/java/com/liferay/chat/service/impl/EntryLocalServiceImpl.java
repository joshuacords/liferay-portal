/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.chat.service.impl;

import com.liferay.chat.internal.jabber.JabberUtil;
import com.liferay.chat.model.Entry;
import com.liferay.chat.service.base.EntryLocalServiceBaseImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.liferay.chat.model.Entry",
	service = AopService.class
)
public class EntryLocalServiceImpl extends EntryLocalServiceBaseImpl {

	@Override
	public Entry addEntry(
		long createDate, long fromUserId, long toUserId, String content) {

		List<Entry> entries = entryFinder.findByEmptyContent(
			fromUserId, toUserId, 0, 5);

		for (Entry entry : entries) {
			entryPersistence.remove(entry);
		}

		if (Validator.isNull(content)) {
			content = StringPool.BLANK;
		}
		else {
			int contentMaxLength = 500;

			DB db = DBManagerUtil.getDB();

			// LPS-33975

			if (db.getDBType() == DBType.SQLSERVER) {
				contentMaxLength = 442;
			}

			if (content.length() > contentMaxLength) {
				content = content.substring(0, contentMaxLength);
			}
		}

		long entryId = counterLocalService.increment();

		Entry entry = entryPersistence.create(entryId);

		entry.setCreateDate(createDate);
		entry.setFromUserId(fromUserId);
		entry.setToUserId(toUserId);
		entry.setContent(content);

		entry = entryPersistence.update(entry);

		JabberUtil.sendMessage(fromUserId, toUserId, content);

		return entry;
	}

	@Override
	public Entry addEntry(long fromUserId, long toUserId, String content) {
		long createDate = System.currentTimeMillis();

		return addEntry(createDate, fromUserId, toUserId, content);
	}

	@Override
	public void deleteEntries(long userId) {
		entryPersistence.removeByFromUserId(userId);
		entryPersistence.removeByToUserId(userId);
	}

	@Override
	public List<Entry> getNewEntries(
		long userId, long createDate, int start, int end) {

		return entryFinder.findByNew(userId, createDate, start, end);
	}

	@Override
	public List<Entry> getOldEntries(long createDate, int start, int end) {
		return entryFinder.findByOld(createDate, start, end);
	}

}