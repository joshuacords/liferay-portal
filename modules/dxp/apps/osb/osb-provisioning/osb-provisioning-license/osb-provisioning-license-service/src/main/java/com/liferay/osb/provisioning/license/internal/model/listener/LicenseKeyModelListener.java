/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.internal.model.listener;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.AuditEntry;
import com.liferay.osb.provisioning.koroneiki.web.service.AuditEntryWebService;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class LicenseKeyModelListener extends BaseModelListener<LicenseKey> {

	@Override
	public void onAfterCreate(LicenseKey licenseKey)
		throws ModelListenerException {

		try {
			_reindex(licenseKey);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onAfterUpdate(LicenseKey licenseKey)
		throws ModelListenerException {

		try {
			_reindex(licenseKey);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onBeforeCreate(LicenseKey licenseKey)
		throws ModelListenerException {

		try {
			_postAuditEntries(licenseKey);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onBeforeUpdate(LicenseKey licenseKey)
		throws ModelListenerException {

		try {
			_postAuditEntries(licenseKey);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	private boolean _isIgnoredField(String field) {
		if (field.equals("entityCacheEnabled") ||
			field.equals("finderCacheEnabled") || field.equals("mvccVersion")) {

			return true;
		}

		return false;
	}

	private boolean _isSkipFieldUpdate(Object oldValue, Object newValue) {
		if (Objects.equals(oldValue, newValue)) {
			return true;
		}

		return false;
	}

	private void _postAuditEntries(LicenseKey licenseKey) throws Exception {
		if (Validator.isNull(licenseKey.getAccountKey())) {
			return;
		}

		LicenseKey oldLicenseKey = _licenseKeyLocalService.fetchLicenseKey(
			licenseKey.getLicenseKeyId());

		List<AuditEntry> auditEntries = new ArrayList<>();

		Map<String, Object> oldAttributes = new HashMap<>();

		Map<String, Object> attributes = licenseKey.getModelAttributes();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			AuditEntry auditEntry = new AuditEntry();

			if (oldLicenseKey == null) {
				auditEntry.setAction(AuditEntry.Action.ADD);
			}
			else {
				auditEntry.setAction(AuditEntry.Action.UPDATE);

				oldAttributes = oldLicenseKey.getModelAttributes();
			}

			auditEntry.setFieldClassLabel("License Key");
			auditEntry.setFieldClassPK(licenseKey.getLicenseKeyId());

			String field = entry.getKey();

			if (_isIgnoredField(field)) {
				continue;
			}

			Object oldValue = StringPool.BLANK;

			if (oldLicenseKey != null) {
				oldValue = oldAttributes.get(field);
			}

			Object value = entry.getValue();

			if (_isSkipFieldUpdate(oldValue, value)) {
				continue;
			}

			auditEntry.setField(field);
			auditEntry.setOldValue(String.valueOf(oldValue));
			auditEntry.setNewValue(String.valueOf(value));

			auditEntries.add(auditEntry);
		}

		if (Validator.isNotNull(licenseKey.getAccountKey())) {
			_auditEntryWebService.postAccountAuditEntries(
				licenseKey.getModifiedUserName(),
				licenseKey.getModifiedUserUuid(), licenseKey.getAccountKey(),
				auditEntries.toArray(new AuditEntry[0]));
		}
		else {
			_auditEntryWebService.postContactAuditEntries(
				licenseKey.getModifiedUserName(),
				licenseKey.getModifiedUserUuid(),
				licenseKey.getModifiedUserUuid(),
				auditEntries.toArray(new AuditEntry[0]));
		}
	}

	private void _reindex(LicenseKey licenseKey) throws PortalException {
		_licenseKeyLocalService.reindex(licenseKey.getLicenseKeyId());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseKeyModelListener.class);

	@Reference
	private AuditEntryWebService _auditEntryWebService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

	@Reference
	private UserLocalService _userLocalService;

}