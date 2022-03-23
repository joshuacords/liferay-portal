/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.provisioning.auth.ProvisioningContactThreadLocal;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.provisioning.license.helper.constants.ProductEnvironment;
import com.liferay.osb.provisioning.license.model.CommonLicenseKey;
import com.liferay.osb.provisioning.license.service.CommonLicenseKeyLocalService;
import com.liferay.osb.provisioning.rest.dto.v1_0.ProductGroup;
import com.liferay.osb.provisioning.rest.resource.v1_0.CommonLicenseKeyResource;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.MimeTypesUtil;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/common-license-key.properties",
	scope = ServiceScope.PROTOTYPE, service = CommonLicenseKeyResource.class
)
public class CommonLicenseKeyResourceImpl
	extends BaseCommonLicenseKeyResourceImpl {

	@Override
	public Response
			getAccountAccountKeyProductGroupProductGroupNameProductEnvironmentProductEnvironmentNameCommonLicenseKey(
				String accountKey, String productGroupName,
				String productEnvironment, Date dateEnd, Date dateStart)
		throws Exception {

		_checkAccountMembership(accountKey);

		_checkProductPurchases(
			accountKey, productGroupName, productEnvironment, dateEnd);

		CommonLicenseKey commonLicenseKey =
			_commonLicenseKeyLocalService.fetchCommonLicenseKey(
				productGroupName, productEnvironment, StringPool.BLANK,
				dateStart, dateEnd);

		if (commonLicenseKey != null) {
			byte[] bytes = _commonLicenseKeyLocalService.getBytes(
				commonLicenseKey.getCommonLicenseKeyId());

			return Response.ok(
				new String(bytes)
			).header(
				"content-disposition",
				"attachment; filename=\"" + commonLicenseKey.getFileName() +
					"\""
			).type(
				MimeTypesUtil.getContentType(commonLicenseKey.getFileName())
			).build();
		}

		return Response.status(
			Response.Status.NOT_FOUND
		).build();
	}

	private void _checkAccountMembership(String accountKey)
		throws PrincipalException {

		Contact contact = ProvisioningContactThreadLocal.getContact();

		if (contact != null) {
			for (Account account : contact.getAccounts()) {
				if (accountKey.equals(account.getKey())) {
					return;
				}
			}
		}
		else if (_isOmniAdmin()) {
			return;
		}

		throw new PrincipalException();
	}

	private void _checkProductPurchases(
			String accountKey, String productGroupName,
			String productEnvironment, Date dateEnd)
		throws Exception {

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "accountKey", accountKey);

		List<ProductPurchase> productPurchases =
			_productPurchaseWebService.search(
				filterQuery, 1, 1000, StringPool.BLANK);

		for (ProductPurchase productPurchase : productPurchases) {
			ProductPurchase.Status status = productPurchase.getStatus();

			if (status != ProductPurchase.Status.APPROVED) {
				continue;
			}

			Date endDate = productPurchase.getEndDate();
			Date startDate = productPurchase.getStartDate();

			if (((startDate == null) || dateEnd.after(startDate)) &&
				((endDate == null) || dateEnd.before(endDate))) {

				Product product = productPurchase.getProduct();

				String name = product.getName();

				if (productGroupName.equals(
						ProductGroup.Name.COMMERCE.toString())) {

					if (productEnvironment.equals(ProductEnvironment.BACKUP) &&
						name.equals(
							ProductConstants.
								NAME_COMMERCE_SUBSCRIPTION_BACKUP)) {

						return;
					}

					if (productEnvironment.equals(
							ProductEnvironment.NON_PRODUCTION) &&
						name.equals(
							ProductConstants.
								NAME_COMMERCE_SUBSCRIPTION_NON_PRODUCTION)) {

						return;
					}

					if (productEnvironment.equals(
							ProductEnvironment.PRODUCTION) &&
						name.equals(
							ProductConstants.
								NAME_COMMERCE_SUBSCRIPTION_PRODUCTION)) {

						return;
					}
				}
				else if (productGroupName.equals(
							ProductGroup.Name.ENTERPRISE_SEARCH.toString())) {

					if (productEnvironment.equals(ProductEnvironment.BACKUP) &&
						name.equals(
							ProductConstants.NAME_ENTERPRISE_SEARCH_BACKUP)) {

						return;
					}

					if (productEnvironment.equals(
							ProductEnvironment.NON_PRODUCTION) &&
						name.equals(
							ProductConstants.
								NAME_ENTERPRISE_SEARCH_NON_PRODUCTION)) {

						return;
					}

					if (productEnvironment.equals(
							ProductEnvironment.PRODUCTION) &&
						name.equals(
							ProductConstants.
								NAME_ENTERPRISE_SEARCH_PRODUCTION)) {

						return;
					}
				}
			}
		}

		throw new PrincipalException();
	}

	private boolean _isOmniAdmin() {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker.isOmniadmin()) {
			return true;
		}

		return false;
	}

	@Reference
	private CommonLicenseKeyLocalService _commonLicenseKeyLocalService;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

}