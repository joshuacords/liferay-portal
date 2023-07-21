/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.text.localizer.address.AddressTextLocalizer;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class AddressModelListener extends BaseAuditModelListener<Address> {

	@Override
	public void onAfterCreate(Address address) throws ModelListenerException {
		if (!isEnabled()) {
			return;
		}

		if (address.getClassNameId() != classNameLocalService.getClassNameId(
				Account.class)) {

			return;
		}

		try {
			auditEntryLocalService.addAuditEntry(
				getUserId(), address.getClassNameId(), address.getClassPK(),
				classNameLocalService.getClassNameId(Address.class),
				address.getAddressId(), AuditEntry.Action.ADD.toString(),
				"Address", StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				_addressTextLocalizer.format(address), StringPool.BLANK,
				getServiceContext(address));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeRemove(Address address) throws ModelListenerException {
		if (!isEnabled()) {
			return;
		}

		if (address.getClassNameId() != classNameLocalService.getClassNameId(
				Account.class)) {

			return;
		}

		try {
			auditEntryLocalService.addAuditEntry(
				getUserId(), address.getClassNameId(), address.getClassPK(),
				classNameLocalService.getClassNameId(Address.class),
				address.getAddressId(), AuditEntry.Action.DELETE.toString(),
				"Address", StringPool.BLANK,
				_addressTextLocalizer.format(address), StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, getServiceContext(address));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeUpdate(Address address) throws ModelListenerException {
		if (!isEnabled()) {
			return;
		}

		if (address.getClassNameId() != classNameLocalService.getClassNameId(
				Account.class)) {

			return;
		}

		try {
			Address oldAddress = _addressLocalService.getAddress(
				address.getAddressId());

			String oldValue = _addressTextLocalizer.format(oldAddress);

			if (!Objects.equals(
					oldValue, _addressTextLocalizer.format(address))) {

				auditEntryLocalService.addAuditEntry(
					getUserId(), address.getClassNameId(), address.getClassPK(),
					classNameLocalService.getClassNameId(Address.class),
					address.getAddressId(), AuditEntry.Action.UPDATE.toString(),
					"Address", StringPool.BLANK, oldValue, StringPool.BLANK,
					_addressTextLocalizer.format(address), StringPool.BLANK,
					getServiceContext(address));
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Reference
	private AddressLocalService _addressLocalService;

	@Reference(target = "(country=US)")
	private AddressTextLocalizer _addressTextLocalizer;

}