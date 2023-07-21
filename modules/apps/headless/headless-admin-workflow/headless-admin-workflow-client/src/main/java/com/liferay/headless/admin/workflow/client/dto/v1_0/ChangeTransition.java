/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.workflow.client.dto.v1_0;

import com.liferay.headless.admin.workflow.client.function.UnsafeSupplier;
import com.liferay.headless.admin.workflow.client.serdes.v1_0.ChangeTransitionSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class ChangeTransition implements Cloneable, Serializable {

	public static ChangeTransition toDTO(String json) {
		return ChangeTransitionSerDes.toDTO(json);
	}

	public String getTransition() {
		return transition;
	}

	public void setTransition(String transition) {
		this.transition = transition;
	}

	public void setTransition(
		UnsafeSupplier<String, Exception> transitionUnsafeSupplier) {

		try {
			transition = transitionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String transition;

	@Override
	public ChangeTransition clone() throws CloneNotSupportedException {
		return (ChangeTransition)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ChangeTransition)) {
			return false;
		}

		ChangeTransition changeTransition = (ChangeTransition)object;

		return Objects.equals(toString(), changeTransition.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ChangeTransitionSerDes.toJSON(this);
	}

}