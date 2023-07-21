/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.model.listener;

import com.liferay.mobile.device.rules.model.MDRRule;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.staging.model.listener.StagingModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Akos Thurzo
 */
@Component(immediate = true, service = ModelListener.class)
public class MDRRuleStagingModelListener extends BaseModelListener<MDRRule> {

	@Override
	public void onAfterCreate(MDRRule mdrRule) throws ModelListenerException {
		_stagingModelListener.onAfterCreate(mdrRule);
	}

	@Override
	public void onAfterRemove(MDRRule mdrRule) throws ModelListenerException {
		_stagingModelListener.onAfterRemove(mdrRule);
	}

	@Override
	public void onAfterUpdate(MDRRule mdrRule) throws ModelListenerException {
		_stagingModelListener.onAfterUpdate(mdrRule);
	}

	@Reference
	private StagingModelListener<MDRRule> _stagingModelListener;

}