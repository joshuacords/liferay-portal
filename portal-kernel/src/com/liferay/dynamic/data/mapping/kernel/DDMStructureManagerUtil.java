/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.kernel;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Leonardo Barros
 */
public class DDMStructureManagerUtil {

	public static void addAttributes(
			long structureId, Document document, DDMFormValues ddmFormValues)
		throws PortalException {

		_ddmStructureManager.addAttributes(
			structureId, document, ddmFormValues);
	}

	public static DDMStructure addStructure(
			long userId, long groupId, String parentStructureKey,
			long classNameId, String structureKey, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, DDMForm ddmForm,
			String storageType, int type, ServiceContext serviceContext)
		throws PortalException {

		return _ddmStructureManager.addStructure(
			userId, groupId, parentStructureKey, classNameId, structureKey,
			nameMap, descriptionMap, ddmForm, storageType, type,
			serviceContext);
	}

	public static void deleteStructure(long structureId)
		throws PortalException {

		_ddmStructureManager.deleteStructure(structureId);
	}

	public static String extractAttributes(
			long structureId, DDMFormValues ddmFormValues, Locale locale)
		throws PortalException {

		return _ddmStructureManager.extractAttributes(
			structureId, ddmFormValues, locale);
	}

	public static DDMStructure fetchStructure(long structureId) {
		return _ddmStructureManager.fetchStructure(structureId);
	}

	public static DDMStructure fetchStructure(
		long groupId, long classNameId, String structureKey) {

		return _ddmStructureManager.fetchStructure(
			groupId, classNameId, structureKey);
	}

	public static DDMStructure fetchStructureByUuidAndGroupId(
		String uuid, long groupId) {

		return _ddmStructureManager.fetchStructureByUuidAndGroupId(
			uuid, groupId);
	}

	public static List<DDMStructure> getClassStructures(
		long companyId, long classNameId) {

		return _ddmStructureManager.getClassStructures(companyId, classNameId);
	}

	public static List<DDMStructure> getClassStructures(
		long companyId, long classNameId, int structureComparator) {

		return _ddmStructureManager.getClassStructures(
			companyId, classNameId, structureComparator);
	}

	public static List<DDMStructure> getClassStructures(
		long companyId, long classNameId, int start, int end) {

		return _ddmStructureManager.getClassStructures(
			companyId, classNameId, start, end);
	}

	public static JSONArray getDDMFormFieldsJSONArray(
			long structureId, String script)
		throws PortalException {

		return _ddmStructureManager.getDDMFormFieldsJSONArray(
			structureId, script);
	}

	public static Class<?> getDDMStructureModelClass() {
		return _ddmStructureManager.getDDMStructureModelClass();
	}

	public static Serializable getIndexedFieldValue(
			Serializable fieldValue, String fieldType)
		throws Exception {

		return _ddmStructureManager.getIndexedFieldValue(fieldValue, fieldType);
	}

	public static DDMStructure getStructure(long structureId)
		throws PortalException {

		return _ddmStructureManager.getStructure(structureId);
	}

	public static DDMStructure getStructure(
			long groupId, long classNameId, String structureKey)
		throws PortalException {

		return _ddmStructureManager.getStructure(
			groupId, classNameId, structureKey);
	}

	public static DDMStructure getStructureByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return _ddmStructureManager.getStructureByUuidAndGroupId(uuid, groupId);
	}

	public static List<DDMStructure> getStructures(
		long[] groupIds, long classNameId) {

		return _ddmStructureManager.getStructures(groupIds, classNameId);
	}

	public static int getStructureStorageLinksCount(long structureId) {
		return _ddmStructureManager.getStructureStorageLinksCount(structureId);
	}

	public static DDMStructure updateStructure(
			long userId, long structureId, long parentStructureId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			DDMForm ddmForm, ServiceContext serviceContext)
		throws PortalException {

		return _ddmStructureManager.updateStructure(
			userId, structureId, parentStructureId, nameMap, descriptionMap,
			ddmForm, serviceContext);
	}

	public static void updateStructureDefinition(
			long structureId, String definition)
		throws PortalException {

		_ddmStructureManager.updateStructureDefinition(structureId, definition);
	}

	public static void updateStructureKey(long structureId, String structureKey)
		throws PortalException {

		_ddmStructureManager.updateStructureKey(structureId, structureKey);
	}

	private static volatile DDMStructureManager _ddmStructureManager =
		ServiceProxyFactory.newServiceTrackedInstance(
			DDMStructureManager.class, DDMStructureManagerUtil.class,
			"_ddmStructureManager", true);

}