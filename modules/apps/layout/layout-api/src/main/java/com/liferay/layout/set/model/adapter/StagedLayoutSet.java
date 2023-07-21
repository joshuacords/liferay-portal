/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.set.model.adapter;

import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.StagedGroupedModel;

import java.util.Optional;

/**
 * @author Máté Thurzó
 */
public interface StagedLayoutSet extends LayoutSet, StagedGroupedModel {

	public Optional<String> getLayoutSetPrototypeName();

}