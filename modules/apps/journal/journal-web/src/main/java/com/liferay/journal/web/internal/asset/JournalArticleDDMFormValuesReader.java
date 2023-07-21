/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.asset;

import com.liferay.asset.kernel.model.BaseDDMFormValuesReader;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.dynamic.data.mapping.util.DDMBeanTranslatorUtil;
import com.liferay.dynamic.data.mapping.util.FieldsToDDMFormValuesConverter;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.util.JournalConverter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * @author Adolfo Pérez
 */
public final class JournalArticleDDMFormValuesReader
	extends BaseDDMFormValuesReader {

	public JournalArticleDDMFormValuesReader(JournalArticle article) {
		_article = article;
	}

	@Override
	public DDMFormValues getDDMFormValues() throws PortalException {
		try {
			DDMStructure ddmStructure =
				DDMStructureLocalServiceUtil.getStructure(
					PortalUtil.getSiteGroupId(_article.getGroupId()),
					PortalUtil.getClassNameId(JournalArticle.class),
					_article.getDDMStructureKey(), true);

			Fields fields = _journalConverter.getDDMFields(
				ddmStructure, _article.getContent());

			return DDMBeanTranslatorUtil.translate(
				_fieldsToDDMFormValuesConverter.convert(ddmStructure, fields));
		}
		catch (Exception exception) {
			throw new PortalException(
				"Unable to read fields for article " + _article.getId(),
				exception);
		}
	}

	public void setFieldsToDDMFormValuesConverter(
		FieldsToDDMFormValuesConverter fieldsToDDMFormValuesConverter) {

		_fieldsToDDMFormValuesConverter = fieldsToDDMFormValuesConverter;
	}

	public void setJournalConverter(JournalConverter journalConverter) {
		_journalConverter = journalConverter;
	}

	private final JournalArticle _article;
	private FieldsToDDMFormValuesConverter _fieldsToDDMFormValuesConverter;
	private JournalConverter _journalConverter;

}