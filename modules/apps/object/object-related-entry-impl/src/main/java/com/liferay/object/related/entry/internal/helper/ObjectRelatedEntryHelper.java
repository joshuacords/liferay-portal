/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.related.entry.internal.helper;

import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.related.entry.constants.ObjectRelatedEntryConstants;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Joshua Cords
 */
@Component(service = ObjectRelatedEntryHelper.class)
public class ObjectRelatedEntryHelper {

	public ObjectDefinition fetchChildObjectDefinition(
		ObjectRelationship objectRelationship) {

		return _fetchRelatedEntryObjectDefinition(
			objectRelationship.getObjectDefinitionId2());
	}

	public ObjectDefinition fetchParentObjectDefinition(
		ObjectRelationship objectRelationship) {

		return _fetchRelatedEntryObjectDefinition(
			objectRelationship.getObjectDefinitionId1());
	}

	public List<ObjectEntry> getAncestorObjectEntries(ObjectEntry objectEntry) {
		Map<Long, ObjectEntry> ancestorObjectEntries = new LinkedHashMap<>();

		List<ObjectEntry> objectEntries = Collections.singletonList(
			objectEntry);

		for (int i = 0; i < ObjectRelatedEntryConstants.MAX_DEPTH; i++) {
			List<ObjectEntry> parentObjectEntries = new ArrayList<>();

			for (ObjectEntry childObjectEntry : objectEntries) {
				for (ObjectEntry parentObjectEntry :
						getParentObjectEntries(childObjectEntry)) {

					ObjectEntry ancestorObjectEntry =
						ancestorObjectEntries.putIfAbsent(
							parentObjectEntry.getObjectEntryId(),
							parentObjectEntry);

					if (ancestorObjectEntry == null) {
						parentObjectEntries.add(parentObjectEntry);
					}
				}
			}

			objectEntries = parentObjectEntries;
		}

		return new ArrayList<>(ancestorObjectEntries.values());
	}

	public List<ObjectRelationship> getChildObjectRelationships(
		ObjectDefinition objectDefinition) {

		List<ObjectRelationship> objectRelationships = new ArrayList<>();

		for (ObjectRelationship objectRelationship :
				_objectRelationshipLocalService.getObjectRelationships(
					objectDefinition.getObjectDefinitionId(), false,
					ObjectRelationshipConstants.TYPE_ONE_TO_MANY)) {

			if (fetchChildObjectDefinition(objectRelationship) == null) {
				continue;
			}

			objectRelationships.add(objectRelationship);
		}

		return ListUtil.sort(
			objectRelationships,
			Comparator.comparing(ObjectRelationship::getName));
	}

	public List<ObjectDefinition> getDescendantObjectDefinitions(
		ObjectDefinition objectDefinition) {

		Map<Long, ObjectDefinition> descendantObjectDefinitions =
			new LinkedHashMap<>();

		List<ObjectDefinition> objectDefinitions = Collections.singletonList(
			objectDefinition);

		for (int i = 0; i < ObjectRelatedEntryConstants.MAX_DEPTH; i++) {
			List<ObjectDefinition> childObjectDefinitions = new ArrayList<>();

			for (ObjectDefinition parentObjectDefinition : objectDefinitions) {
				for (ObjectRelationship objectRelationship :
						getChildObjectRelationships(parentObjectDefinition)) {

					ObjectDefinition childObjectDefinition =
						fetchChildObjectDefinition(objectRelationship);

					if (childObjectDefinition.getObjectDefinitionId() ==
							objectDefinition.getObjectDefinitionId()) {

						continue;
					}

					ObjectDefinition descendantObjectDefinition =
						descendantObjectDefinitions.putIfAbsent(
							childObjectDefinition.getObjectDefinitionId(),
							childObjectDefinition);

					if (descendantObjectDefinition != null) {
						continue;
					}

					childObjectDefinitions.add(childObjectDefinition);
				}
			}

			objectDefinitions = childObjectDefinitions;
		}

		return new ArrayList<>(descendantObjectDefinitions.values());
	}

	public List<ObjectEntry> getParentObjectEntries(ObjectEntry objectEntry) {
		List<ObjectEntry> parentObjectEntries = new ArrayList<>();

		ObjectDefinition objectDefinition = objectEntry.getObjectDefinition();

		if (objectDefinition == null) {
			return parentObjectEntries;
		}

		for (ObjectRelationship objectRelationship :
				getParentObjectRelationships(objectDefinition)) {

			long parentObjectEntryId = getParentObjectEntryId(
				objectEntry, objectRelationship);

			if (parentObjectEntryId == 0) {
				continue;
			}

			ObjectEntry parentObjectEntry =
				_objectEntryLocalService.fetchObjectEntry(parentObjectEntryId);

			if (parentObjectEntry != null) {
				parentObjectEntries.add(parentObjectEntry);
			}
		}

		return parentObjectEntries;
	}

	public long getParentObjectEntryId(
		ObjectEntry objectEntry, ObjectRelationship objectRelationship) {

		ObjectField objectField = _objectFieldLocalService.fetchObjectField(
			objectRelationship.getObjectFieldId2());

		if (objectField == null) {
			return 0;
		}

		return MapUtil.getLong(objectEntry.getValues(), objectField.getName());
	}

	public List<ObjectRelationship> getParentObjectRelationships(
		ObjectDefinition objectDefinition) {

		List<ObjectRelationship> objectRelationships = new ArrayList<>();

		for (ObjectRelationship objectRelationship :
				_objectRelationshipLocalService.
					getObjectRelationshipsByObjectDefinitionId2(
						objectDefinition.getObjectDefinitionId(),
						ObjectRelationshipConstants.TYPE_ONE_TO_MANY)) {

			if (fetchParentObjectDefinition(objectRelationship) == null) {
				continue;
			}

			objectRelationships.add(objectRelationship);
		}

		return ListUtil.sort(
			objectRelationships,
			Comparator.comparing(ObjectRelationship::getName));
	}

	public String getRelatedEntryKey(ObjectEntry objectEntry) {
		return StringBundler.concat(
			_portal.getClassNameId(objectEntry.getModelClassName()),
			StringPool.DASH, objectEntry.getObjectEntryId());
	}

	public boolean isRelatedEntryObjectDefinition(
		ObjectDefinition objectDefinition) {

		if ((objectDefinition == null) || !objectDefinition.isActive() ||
			!objectDefinition.isApproved() ||
			!objectDefinition.isEnableIndexSearch() ||
			objectDefinition.isSystem()) {

			return false;
		}

		return true;
	}

	private ObjectDefinition _fetchRelatedEntryObjectDefinition(
		long objectDefinitionId) {

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.fetchObjectDefinition(
				objectDefinitionId);

		if (!isRelatedEntryObjectDefinition(objectDefinition)) {
			return null;
		}

		return objectDefinition;
	}

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

	@Reference
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

	@Reference
	private Portal _portal;

}