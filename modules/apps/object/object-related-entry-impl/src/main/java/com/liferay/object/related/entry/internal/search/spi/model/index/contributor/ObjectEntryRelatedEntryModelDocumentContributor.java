/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.related.entry.internal.search.spi.model.index.contributor;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.related.entry.constants.ObjectRelatedEntryConstants;
import com.liferay.object.related.entry.internal.helper.ObjectRelatedEntryHelper;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentHelper;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import java.util.List;

/**
 * @author Joshua Cords
 */
public class ObjectEntryRelatedEntryModelDocumentContributor
	implements ModelDocumentContributor<ObjectEntry> {

	public ObjectEntryRelatedEntryModelDocumentContributor(
		ObjectRelatedEntryHelper objectRelatedEntryHelper, Portal portal) {

		_objectRelatedEntryHelper = objectRelatedEntryHelper;
		_portal = portal;
	}

	@Override
	public void contribute(Document document, ObjectEntry objectEntry) {
		try {
			_contribute(document, objectEntry);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Unable to index related entry fields for object ",
						"entry ", objectEntry.getObjectEntryId()),
					exception);
			}
		}
	}

	private void _contribute(Document document, ObjectEntry objectEntry) {
		ObjectDefinition objectDefinition = objectEntry.getObjectDefinition();

		if (objectDefinition == null) {
			return;
		}

		for (ObjectRelationship objectRelationship :
				_objectRelatedEntryHelper.getParentObjectRelationships(
					objectDefinition)) {

			long parentObjectEntryId =
				_objectRelatedEntryHelper.getParentObjectEntryId(
					objectEntry, objectRelationship);

			if (parentObjectEntryId == 0) {
				continue;
			}

			ObjectDefinition parentObjectDefinition =
				_objectRelatedEntryHelper.fetchParentObjectDefinition(
					objectRelationship);

			DocumentHelper documentHelper = new DocumentHelper(document);

			documentHelper.setAttachmentOwnerKey(
				_portal.getClassNameId(parentObjectDefinition.getClassName()),
				parentObjectEntryId);

			document.addKeyword(Field.RELATED_ENTRY, true);

			break;
		}

		List<ObjectEntry> ancestorObjectEntries =
			_objectRelatedEntryHelper.getAncestorObjectEntries(objectEntry);

		if (ListUtil.isEmpty(ancestorObjectEntries)) {
			return;
		}

		document.addKeyword(
			ObjectRelatedEntryConstants.FIELD_RELATED_ENTRY_ANCESTOR_KEYS,
			TransformUtil.transformToArray(
				ancestorObjectEntries,
				_objectRelatedEntryHelper::getRelatedEntryKey, String.class));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectEntryRelatedEntryModelDocumentContributor.class);

	private final ObjectRelatedEntryHelper _objectRelatedEntryHelper;
	private final Portal _portal;

}