/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import partition from 'lodash.partition';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {useNewLicense} from '../../hooks/newLicense';
import {
	LICENSE_TYPE_DEVELOPER,
	LICENSE_TYPE_DEVELOPER_CLUSTER,
	LICENSE_TYPE_VIRTUAL_CLUSTER
} from '../../utilities/constants';
import CancelLink from '../CancelLink';
import Purchases from './Purchases';
import SelectAccount from './SelectAccount';

const DEFAULT_MAX_CLUSTER_NODES = 0;
const DEFAULT_MAX_CLUSTER_NODES_FOR_VIRTUAL_CLUSTER_LICENSES = 1;
const DEFAULT_MAX_HTTP_SESSIONS = 0;
const DEFAULT_MAX_HTTP_SESSIONS_FOR_DEVELOPER_LICENSES = 5;

function GeneralInformation({
	accountKey = '',
	accountName = '',
	currentProduct = '',
	licensableProducts = [],
	purchasedProducts = {},
	redirect,
	selectAccountActionURL,
	selectAccountRenderURL
}) {
	const [
		{
			licenseEntry: {licenseEntryId},
			product,
			version
		},
		{updateLicense}
	] = useNewLicense();

	const [selectedProduct, setSelectedProduct] = useState(
		findCurrentProduct(
			product.productKey ? product.productKey : currentProduct
		)
	);

	useEffect(() => {
		updateLicense(license =>
			license
				.set('accountKey', accountKey)
				.set('accountName', accountName)
				.set('description', accountName)
				.set('name', accountName)
				.set('owner', accountName)
				.updateIn(['product', 'productKey'], productKey =>
					currentProduct && selectedProduct
						? selectedProduct.productKey
						: productKey
				)
				.updateIn(['product', 'productName'], productName =>
					currentProduct && selectedProduct
						? selectedProduct.productName
						: productName
				)
		);
	}, [
		accountKey,
		accountName,
		currentProduct,
		selectedProduct,
		updateLicense
	]);

	function findCurrentProduct(productKey) {
		return licensableProducts.find(
			product => product.productKey === productKey
		);
	}

	function getAvailableLicenseEntries() {
		if (selectedProduct && version) {
			return selectedProduct.productVersions[version];
		}
	}

	function getAvailableVersions() {
		if (selectedProduct) {
			return Object.keys(selectedProduct.productVersions);
		}
	}

	function getLicenseEntry(licenseEntryId) {
		const availableLicenseEntries = getAvailableLicenseEntries();

		if (availableLicenseEntries && licenseEntryId) {
			return availableLicenseEntries.find(
				entry => entry.licenseEntryId === licenseEntryId
			);
		}
	}

	function handleLicenseEntryOnChange(event) {
		const currentLicenseEntry = getLicenseEntry(event.currentTarget.value);

		updateLicense(license =>
			license
				.updateIn(['licenseEntry', 'licenseEntryId'], licenseEntryId =>
					currentLicenseEntry
						? currentLicenseEntry.licenseEntryId
						: licenseEntryId
				)
				.updateIn(
					['licenseEntry', 'licenseEntryName'],
					licenseEntryName =>
						currentLicenseEntry
							? currentLicenseEntry.licenseEntryName
							: licenseEntryName
				)
				.updateIn(
					['licenseEntry', 'licenseEntryType'],
					licenseEntryType =>
						currentLicenseEntry
							? currentLicenseEntry.licenseEntryType
							: licenseEntryType
				)
				.update('maxHttpSessions', maxHttpSessions => {
					if (currentLicenseEntry) {
						const type = currentLicenseEntry.licenseEntryType;

						return type === LICENSE_TYPE_DEVELOPER ||
							type === LICENSE_TYPE_DEVELOPER_CLUSTER
							? DEFAULT_MAX_HTTP_SESSIONS_FOR_DEVELOPER_LICENSES
							: DEFAULT_MAX_HTTP_SESSIONS;
					}

					return maxHttpSessions;
				})
				.update('maxClusterNodes', maxClusterNodes => {
					if (currentLicenseEntry) {
						const type = currentLicenseEntry.licenseEntryType;

						return type === LICENSE_TYPE_VIRTUAL_CLUSTER
							? DEFAULT_MAX_CLUSTER_NODES_FOR_VIRTUAL_CLUSTER_LICENSES
							: DEFAULT_MAX_CLUSTER_NODES;
					}

					return maxClusterNodes;
				})
		);
	}

	function handleProductOnChange(event) {
		const currentSelectedProduct = findCurrentProduct(
			event.currentTarget.value
		);

		setSelectedProduct(currentSelectedProduct);

		updateLicense(license =>
			license
				.set('version', '')
				.setIn(['licenseEntry', 'licenseEntryId'], '')
				.setIn(['licenseEntry', 'licenseEntryName'], '')
				.setIn(['licenseEntry', 'licenseEntryType'], '')
				.updateIn(['product', 'productKey'], productKey =>
					currentSelectedProduct
						? currentSelectedProduct.productKey
						: productKey
				)
				.updateIn(['product', 'productName'], productName =>
					currentSelectedProduct
						? currentSelectedProduct.productName
						: productName
				)
		);
	}

	function handleVersionOnChange(event) {
		updateLicense(license =>
			license
				.set('version', event.target.value)
				.setIn(['licenseEntry', 'licenseEntryId'], '')
				.setIn(['licenseEntry', 'licenseEntryName'], '')
				.setIn(['licenseEntry', 'licenseEntryType'], '')
		);
	}

	return (
		<>
			<div className="page-steps">
				<span>{Liferay.Language.get('general-information')}</span>

				<span>{Liferay.Language.get('step-1-of-2')}</span>
			</div>

			<div className="container-fluid-max-xl generate-license-sheet sheet">
				<div className="general-information generate-license-container">
					<h3>{Liferay.Language.get('general-information')}</h3>

					<div className="row">
						<div className="col-md-6 form-group">
							<h5 className="form-check-inline">
								{Liferay.Language.get('account')}
							</h5>

							<SelectAccount
								accountKey={accountKey}
								accountName={accountName}
								actionURL={selectAccountActionURL}
								dialogURL={selectAccountRenderURL}
							/>
						</div>
					</div>

					<div className="row">
						<div className="col-md-6 form-group">
							<label htmlFor="product">
								{Liferay.Language.get('product')}
							</label>

							<select
								className="form-control"
								disabled={!licensableProducts.length}
								id="product"
								onChange={handleProductOnChange}
								value={product.productKey}
							>
								{!!licensableProducts.length && (
									<ProductDropdown
										products={licensableProducts}
										purchased={Object.keys(
											purchasedProducts
										)}
									/>
								)}
							</select>
						</div>
					</div>

					<div className="row">
						<div className="col-md-6 form-group">
							<label htmlFor="version">
								{Liferay.Language.get('version')}
							</label>

							<select
								className="form-control"
								disabled={!product.productKey}
								id="version"
								onChange={handleVersionOnChange}
								value={version}
							>
								{!!product.productKey && (
									<>
										<option value=""></option>
										{!!getAvailableVersions() &&
											getAvailableVersions()
												.sort()
												.map(availableVersion => (
													<option
														key={availableVersion}
														value={availableVersion}
													>
														{availableVersion}
													</option>
												))}
									</>
								)}
							</select>
						</div>

						<div className="col-md-6 form-group">
							<label htmlFor="type">
								{Liferay.Language.get('type')}
							</label>

							<select
								className="form-control"
								disabled={!version}
								id="type"
								onChange={handleLicenseEntryOnChange}
								value={licenseEntryId}
							>
								{!!version && (
									<>
										<option value=""></option>
										{!!getAvailableLicenseEntries() &&
											getAvailableLicenseEntries().map(
												entry => (
													<option
														key={
															entry.licenseEntryId
														}
														value={
															entry.licenseEntryId
														}
													>
														{
															entry.licenseEntryDisplayName
														}
													</option>
												)
											)}
									</>
								)}
							</select>
						</div>
					</div>
				</div>

				{!!licenseEntryId && (
					<Purchases
						detached={selectedProduct.detached}
						purchased={purchasedProducts[product.productKey]}
					/>
				)}

				<CancelLink redirect={redirect} />
			</div>
		</>
	);
}

function ProductDropdown({products, purchased = []}) {
	const [purchasedProducts, notPurchasedProducts] = partition(
		products,
		({productKey}) => purchased.find(item => item === productKey)
	);

	return (
		<>
			<option value=""></option>
			{!!purchasedProducts.length && (
				<optgroup label={Liferay.Language.get('purchased')}>
					{purchasedProducts.map(product => (
						<option
							key={product.productKey}
							value={product.productKey}
						>
							{product.productName}
						</option>
					))}
				</optgroup>
			)}

			{!!notPurchasedProducts.length && (
				<optgroup label={Liferay.Language.get('not-purchased')}>
					{notPurchasedProducts.map(product => (
						<option
							key={product.productKey}
							value={product.productKey}
						>
							{product.productName}
						</option>
					))}
				</optgroup>
			)}
		</>
	);
}

GeneralInformation.propTypes = {
	accountKey: PropTypes.string,
	accountName: PropTypes.string,
	allowPermanentLicenses: PropTypes.bool,
	currentProduct: PropTypes.string,
	licensableProducts: PropTypes.arrayOf(
		PropTypes.shape({
			detached: PropTypes.shape({
				instanceSizes: PropTypes.arrayOf(PropTypes.number),
				licenseKeysGenerated: PropTypes.string
			}),
			productKey: PropTypes.string,
			productName: PropTypes.string,
			productVersions: PropTypes.shape({
				[PropTypes.string]: PropTypes.arrayOf(
					PropTypes.shape({
						licenseEntryDisplayName: PropTypes.string,
						licenseEntryId: PropTypes.string,
						licenseEntryName: PropTypes.string,
						licenseEntryType: PropTypes.string
					})
				)
			})
		})
	),
	purchasedProducts: PropTypes.shape({
		[PropTypes.string]: PropTypes.arrayOf(
			PropTypes.shape({
				endDate: PropTypes.string,
				licenseKeysGenerated: PropTypes.string,
				perpetual: PropTypes.bool,
				productPurchaseKey: PropTypes.string,
				sizing: PropTypes.number,
				startDate: PropTypes.string
			})
		)
	}),
	redirect: PropTypes.string.isRequired,
	selectAccountActionURL: PropTypes.string.isRequired,
	selectAccountRenderURL: PropTypes.string.isRequired
};

export default GeneralInformation;
