/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.auto.tagger.opennlp;

import java.util.Collection;
import java.util.Locale;

/**
 * Models a OpenNLP Document Asset Auto Tagger.
 *
 * @author     Cristina González
 * @deprecated As of Mueller (7.2.x)
 * @review
 */
@Deprecated
public interface OpenNLPDocumentAssetAutoTagger {

	/**
	 * Returns a list of tag names from the OpenNLP API.
	 *
	 * @param  companyId the company ID
	 * @param  content the text to be tagged.
	 * @param  locale the text's locale.
	 * @param  mimeType the mime type of the text.
	 * @return a list of tag names.
	 * @review
	 */
	public Collection<String> getTagNames(
			long companyId, String content, Locale locale, String mimeType)
		throws Exception;

	/**
	 * Returns a list of tag names from the OpenNLP API.
	 *
	 * @param  content the text to be tagged.
	 * @param  mimeType the mime type of the text.
	 * @return a list of tag names.
	 * @review
	 */
	public Collection<String> getTagNames(
			long companyId, String content, String mimeType)
		throws Exception;

}