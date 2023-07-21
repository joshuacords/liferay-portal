/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.client.dto.v1_0;

import com.liferay.data.engine.rest.client.function.UnsafeSupplier;
import com.liferay.data.engine.rest.client.serdes.v1_0.DataDefinitionPermissionSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Jeyvison Nascimento
 * @generated
 */
@Generated("")
public class DataDefinitionPermission implements Cloneable, Serializable {

	public static DataDefinitionPermission toDTO(String json) {
		return DataDefinitionPermissionSerDes.toDTO(json);
	}

	public Boolean getAddDataDefinition() {
		return addDataDefinition;
	}

	public void setAddDataDefinition(Boolean addDataDefinition) {
		this.addDataDefinition = addDataDefinition;
	}

	public void setAddDataDefinition(
		UnsafeSupplier<Boolean, Exception> addDataDefinitionUnsafeSupplier) {

		try {
			addDataDefinition = addDataDefinitionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean addDataDefinition;

	public Boolean getDefinePermissions() {
		return definePermissions;
	}

	public void setDefinePermissions(Boolean definePermissions) {
		this.definePermissions = definePermissions;
	}

	public void setDefinePermissions(
		UnsafeSupplier<Boolean, Exception> definePermissionsUnsafeSupplier) {

		try {
			definePermissions = definePermissionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean definePermissions;

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public void setDelete(
		UnsafeSupplier<Boolean, Exception> deleteUnsafeSupplier) {

		try {
			delete = deleteUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean delete;

	public String[] getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String[] roleNames) {
		this.roleNames = roleNames;
	}

	public void setRoleNames(
		UnsafeSupplier<String[], Exception> roleNamesUnsafeSupplier) {

		try {
			roleNames = roleNamesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String[] roleNames;

	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	public void setUpdate(
		UnsafeSupplier<Boolean, Exception> updateUnsafeSupplier) {

		try {
			update = updateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean update;

	public Boolean getView() {
		return view;
	}

	public void setView(Boolean view) {
		this.view = view;
	}

	public void setView(UnsafeSupplier<Boolean, Exception> viewUnsafeSupplier) {
		try {
			view = viewUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean view;

	@Override
	public DataDefinitionPermission clone() throws CloneNotSupportedException {
		return (DataDefinitionPermission)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DataDefinitionPermission)) {
			return false;
		}

		DataDefinitionPermission dataDefinitionPermission =
			(DataDefinitionPermission)object;

		return Objects.equals(toString(), dataDefinitionPermission.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return DataDefinitionPermissionSerDes.toJSON(this);
	}

}