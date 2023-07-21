/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.messaging;

import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * @author Michael Young
 */
public class OpenSocialHotDeployMessageListener
	extends HotDeployMessageListener {

	public OpenSocialHotDeployMessageListener(String... servletContextNames) {
		super(servletContextNames);
	}

	protected void checkExpando() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			try {
				ExpandoTableLocalServiceUtil.getTable(
					company.getCompanyId(), Layout.class.getName(),
					ShindigUtil.getTableOpenSocial());
			}
			catch (NoSuchTableException noSuchTableException) {

				// LPS-52675

				if (_log.isDebugEnabled()) {
					_log.debug(noSuchTableException, noSuchTableException);
				}

				ExpandoTableLocalServiceUtil.addTable(
					company.getCompanyId(), Layout.class.getName(),
					ShindigUtil.getTableOpenSocial());
			}

			try {
				ExpandoTableLocalServiceUtil.getTable(
					company.getCompanyId(), User.class.getName(),
					ShindigUtil.getTableOpenSocial());
			}
			catch (NoSuchTableException noSuchTableException) {

				// LPS-52675

				if (_log.isDebugEnabled()) {
					_log.debug(noSuchTableException, noSuchTableException);
				}

				ExpandoTableLocalServiceUtil.addTable(
					company.getCompanyId(), User.class.getName(),
					ShindigUtil.getTableOpenSocial());
			}
		}
	}

	@Override
	protected void onDeploy(Message message) throws Exception {
		verifyGadgets();

		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			PortletLocalServiceUtil.addPortletCategory(
				company.getCompanyId(), _GADGETS_CATEGORY);
		}

		GadgetLocalServiceUtil.initGadgets();

		checkExpando();
	}

	@Override
	protected void onUndeploy(Message message) throws Exception {
		GadgetLocalServiceUtil.destroyGadgets();
	}

	protected void verifyGadgets() throws Exception {
		List<Gadget> gadgets = GadgetLocalServiceUtil.getGadgets(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Gadget gadget : gadgets) {
			if (Validator.isNull(gadget.getUuid()) ||
				Validator.isNull(gadget.getPortletCategoryNames())) {

				gadget.setPortletCategoryNames(_GADGETS_CATEGORY);

				GadgetLocalServiceUtil.updateGadget(gadget);
			}
		}
	}

	private static final String _GADGETS_CATEGORY = "category.gadgets";

	private static final Log _log = LogFactoryUtil.getLog(
		OpenSocialHotDeployMessageListener.class);

}