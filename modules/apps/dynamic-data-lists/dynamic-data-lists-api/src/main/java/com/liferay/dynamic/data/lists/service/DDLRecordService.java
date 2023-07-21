/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.service;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for DDLRecord. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface DDLRecordService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.dynamic.data.lists.service.impl.DDLRecordServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the ddl record remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link DDLRecordServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds a record referencing the record set.
	 *
	 * @param groupId the primary key of the record's group
	 * @param recordSetId the primary key of the record set
	 * @param displayIndex the index position in which the record is displayed
	 in the spreadsheet view
	 * @param ddmFormValues the record values. See <code>DDMFormValues</code>
	 in the <code>dynamic.data.mapping.api</code> module.
	 * @param serviceContext the service context to be applied. This can set
	 the UUID, guest permissions, and group permissions for the
	 record.
	 * @return the record
	 * @throws PortalException if a portal exception occurred
	 */
	public DDLRecord addRecord(
			long groupId, long recordSetId, int displayIndex,
			DDMFormValues ddmFormValues, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Adds a record referencing the record set.
	 *
	 * @param groupId the primary key of the record's group
	 * @param recordSetId the primary key of the record set
	 * @param displayIndex the index position in which the record is
	 displayed in the spreadsheet view
	 * @param fields the record values. See <code>Fields</code> in the
	 <code>dynamic.data.mapping.api</code> module.
	 * @param serviceContext the service context to be applied. This can
	 set the UUID, guest permissions, and group permissions for
	 the record.
	 * @return the record
	 * @throws PortalException if a portal exception occurred
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #addRecord(long, long, int, DDMFormValues, ServiceContext)}
	 */
	@Deprecated
	public DDLRecord addRecord(
			long groupId, long recordSetId, int displayIndex, Fields fields,
			ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Adds a record referencing the record set.
	 *
	 * @param groupId the primary key of the record's group
	 * @param recordSetId the primary key of the record set
	 * @param displayIndex the index position in which the record is
	 displayed in the spreadsheet view
	 * @param fieldsMap the record values. The fieldsMap is a map of field
	 names and its Serializable values.
	 * @param serviceContext the service context to be applied. This can
	 set the UUID, guest permissions, and group permissions for
	 the record.
	 * @return the record
	 * @throws PortalException if a portal exception occurred
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #addRecord(long, long, int, DDMFormValues, ServiceContext)}
	 */
	@Deprecated
	public DDLRecord addRecord(
			long groupId, long recordSetId, int displayIndex,
			Map<String, Serializable> fieldsMap, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Deletes the record and its resources.
	 *
	 * @param recordId the primary key of the record to be deleted
	 * @throws PortalException
	 */
	public void deleteRecord(long recordId) throws PortalException;

	/**
	 * Disassociates the locale from the record.
	 *
	 * @param recordId the primary key of the record
	 * @param locale the locale of the record values to be removed
	 * @param serviceContext the service context to be applied. This can
	 set the record modified date.
	 * @return the affected record
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #updateRecord(long, boolean, int, DDMFormValues,
	 ServiceContext)}
	 */
	@Deprecated
	public DDLRecord deleteRecordLocale(
			long recordId, Locale locale, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * Returns the record with the ID.
	 *
	 * @param recordId the primary key of the record
	 * @return the record with the ID
	 * @throws PortalException if a portal exception occurred
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecord getRecord(long recordId) throws PortalException;

	/**
	 * Returns all the records matching the record set ID
	 *
	 * @param recordSetId the record's record set ID
	 * @return the matching records
	 * @throws PortalException if a portal exception occurred
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecord> getRecords(long recordSetId) throws PortalException;

	/**
	 * Reverts the record to a given version.
	 *
	 * @param recordId the primary key of the record
	 * @param version the version to be reverted
	 * @param serviceContext the service context to be applied. This can set
	 the record modified date.
	 * @throws PortalException if a portal exception occurred
	 */
	public void revertRecord(
			long recordId, String version, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #revertRecord(long, String, ServiceContext)}
	 */
	@Deprecated
	public void revertRecordVersion(
			long recordId, String version, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Updates a record, replacing its display index and values.
	 *
	 * @param recordId the primary key of the record
	 * @param majorVersion whether this update is a major change. A major
	 change increments the record's major version number.
	 * @param displayIndex the index position in which the record is displayed
	 in the spreadsheet view
	 * @param ddmFormValues the record values. See <code>DDMFormValues</code>
	 in the <code>dynamic.data.mapping.api</code> module.
	 * @param serviceContext the service context to be applied. This can set
	 the record modified date.
	 * @return the record
	 * @throws PortalException if a portal exception occurred
	 */
	public DDLRecord updateRecord(
			long recordId, boolean majorVersion, int displayIndex,
			DDMFormValues ddmFormValues, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Updates a record, replacing its display index and values.
	 *
	 * @param recordId the primary key of the record
	 * @param majorVersion whether this update is a major change. Major
	 changes causes the increment of the major version number.
	 * @param displayIndex the index position in which the record is
	 displayed in the spreadsheet view
	 * @param fields the record values. See <code>Fields</code> in the
	 <code>dynamic.data.mapping.api</code> module.
	 * @param mergeFields whether to merge the new fields with the existing
	 ones; otherwise replace the existing fields
	 * @param serviceContext the service context to be applied. This can
	 set the record modified date.
	 * @return the record
	 * @throws PortalException if a portal exception occurred
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #updateRecord(long, boolean, int, DDMFormValues,
	 ServiceContext)}
	 */
	@Deprecated
	public DDLRecord updateRecord(
			long recordId, boolean majorVersion, int displayIndex,
			Fields fields, boolean mergeFields, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Updates a record, replacing its display index and values.
	 *
	 * @param recordId the primary key of the record
	 * @param displayIndex the index position in which the record is
	 displayed in the spreadsheet view
	 * @param fieldsMap the record values. The fieldsMap is a map of field
	 names and its Serializable values.
	 * @param mergeFields whether to merge the new fields with the existing
	 ones; otherwise replace the existing fields
	 * @param serviceContext the service context to be applied. This can
	 set the record modified date.
	 * @return the record
	 * @throws PortalException if a portal exception occurred
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #updateRecord(long, boolean, int, DDMFormValues,
	 ServiceContext)}
	 */
	@Deprecated
	public DDLRecord updateRecord(
			long recordId, int displayIndex,
			Map<String, Serializable> fieldsMap, boolean mergeFields,
			ServiceContext serviceContext)
		throws PortalException;

}