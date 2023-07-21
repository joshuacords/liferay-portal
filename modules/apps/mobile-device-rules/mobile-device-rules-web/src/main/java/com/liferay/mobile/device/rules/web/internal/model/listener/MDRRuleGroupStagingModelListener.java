/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.model.listener;

import com.liferay.mobile.device.rules.model.MDRRuleGroup;
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
public class MDRRuleGroupStagingModelListener
	extends BaseModelListener<MDRRuleGroup> {

	@Override
	public void onAfterCreate(MDRRuleGroup mdrRuleGroup)
		throws ModelListenerException {

		_stagingModelListener.onAfterCreate(mdrRuleGroup);
	}

	@Override
	public void onAfterRemove(MDRRuleGroup mdrRuleGroup)
		throws ModelListenerException {

		_stagingModelListener.onAfterRemove(mdrRuleGroup);
	}

	@Override
	public void onAfterUpdate(MDRRuleGroup mdrRuleGroup)
		throws ModelListenerException {

		_stagingModelListener.onAfterUpdate(mdrRuleGroup);
	}

	@Reference
	private StagingModelListener<MDRRuleGroup> _stagingModelListener;

}