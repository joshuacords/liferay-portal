/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.relationship;

import java.util.Collection;

/**
 * @author Máté Thurzó
 */
@FunctionalInterface
public interface MultiRelationshipFunction<T, U> {

	public Collection<U> apply(T model);

}