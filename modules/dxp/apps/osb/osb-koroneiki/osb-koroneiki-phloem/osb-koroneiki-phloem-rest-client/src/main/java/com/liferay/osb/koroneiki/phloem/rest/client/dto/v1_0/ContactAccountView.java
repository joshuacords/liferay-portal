/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.function.UnsafeSupplier;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactAccountViewSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class ContactAccountView implements Cloneable, Serializable {

	public static ContactAccountView toDTO(String json) {
		return ContactAccountViewSerDes.toDTO(json);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setAccount(
		UnsafeSupplier<Account, Exception> accountUnsafeSupplier) {

		try {
			account = accountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Account account;

	public ContactRole[] getCustomerContactRoles() {
		return customerContactRoles;
	}

	public void setCustomerContactRoles(ContactRole[] customerContactRoles) {
		this.customerContactRoles = customerContactRoles;
	}

	public void setCustomerContactRoles(
		UnsafeSupplier<ContactRole[], Exception>
			customerContactRolesUnsafeSupplier) {

		try {
			customerContactRoles = customerContactRolesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ContactRole[] customerContactRoles;

	public ContactRole[] getWorkerContactRoles() {
		return workerContactRoles;
	}

	public void setWorkerContactRoles(ContactRole[] workerContactRoles) {
		this.workerContactRoles = workerContactRoles;
	}

	public void setWorkerContactRoles(
		UnsafeSupplier<ContactRole[], Exception>
			workerContactRolesUnsafeSupplier) {

		try {
			workerContactRoles = workerContactRolesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ContactRole[] workerContactRoles;

	@Override
	public ContactAccountView clone() throws CloneNotSupportedException {
		return (ContactAccountView)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactAccountView)) {
			return false;
		}

		ContactAccountView contactAccountView = (ContactAccountView)object;

		return Objects.equals(toString(), contactAccountView.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ContactAccountViewSerDes.toJSON(this);
	}

}