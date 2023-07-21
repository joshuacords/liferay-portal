/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.synonyms.web.internal.index;

/**
 * @author Adam Brandizzi
 */
public class SynonymSet {

	public SynonymSet(SynonymSet synonymSet) {
		_id = synonymSet._id;
		_synonyms = synonymSet._synonyms;
	}

	public String getId() {
		return _id;
	}

	public String getSynonyms() {
		return _synonyms;
	}

	public static class SynonymSetBuilder {

		public SynonymSetBuilder() {
			_synonymSet = new SynonymSet();
		}

		public SynonymSetBuilder(SynonymSet synonymSet) {
			_synonymSet = synonymSet;
		}

		public SynonymSet build() {
			return new SynonymSet(_synonymSet);
		}

		public SynonymSetBuilder id(String id) {
			_synonymSet._id = id;

			return this;
		}

		public SynonymSetBuilder synonyms(String synonyms) {
			_synonymSet._synonyms = synonyms;

			return this;
		}

		private final SynonymSet _synonymSet;

	}

	private SynonymSet() {
	}

	private String _id;
	private String _synonyms;

}