/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.content.search.web.internal.util;

import com.liferay.journal.service.JournalContentSearchLocalServiceUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class ContentHits {

	public boolean isShowListed() {
		return _showListed;
	}

	public void recordHits(
			Hits hits, long groupId, boolean privateLayout, int start, int end)
		throws Exception {

		// This can later be optimized according to LEP-915.

		List<Document> docs = new ArrayList<>();
		List<Float> scores = new ArrayList<>();

		Document[] docsArray = hits.getDocs();

		for (int i = 0; i < docsArray.length; i++) {
			Document doc = hits.doc(i);

			String articleId = doc.get(Field.ARTICLE_ID);
			long articleGroupId = GetterUtil.getLong(doc.get(Field.GROUP_ID));

			int layoutIdsCount =
				JournalContentSearchLocalServiceUtil.getLayoutIdsCount(
					groupId, privateLayout, articleId);

			if ((layoutIdsCount > 0) ||
				(!isShowListed() && (articleGroupId == groupId))) {

				docs.add(hits.doc(i));
				scores.add(hits.score(i));
			}
		}

		int length = docs.size();

		hits.setLength(length);

		if (end > length) {
			end = length;
		}

		docs = docs.subList(start, end);
		scores = scores.subList(start, end);

		hits.setDocs(docs.toArray(new Document[0]));
		hits.setScores(ArrayUtil.toFloatArray(scores));

		hits.setSearchTime(
			(float)(System.currentTimeMillis() - hits.getStart()) /
				Time.SECOND);
	}

	public void setShowListed(boolean showListed) {
		_showListed = showListed;
	}

	private boolean _showListed = true;

}