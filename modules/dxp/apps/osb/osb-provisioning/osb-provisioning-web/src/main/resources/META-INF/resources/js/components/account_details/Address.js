/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {usePermissions} from '../../hooks/permissions';
import {NAMESPACE} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import IconButton from '../IconButton';
import * as AddressField from './address_fields/AddressField';

function Address({accountKey, addFn, address, count, countryOptions}) {
	const [disableSave, setDisableSave] = useState(true);
	const [editable, setEditable] = useState(false);
	const [selectedCountry, setSelectedCountry] = useState(
		countryOptions.find(({name}) => name === address.addressCountry)
	);
	const [values, setValues] = useState({
		addressCountryName: convertDashToEmptyString(address.addressCountry),
		addressLocality: convertDashToEmptyString(address.addressLocality),
		addressPrimary: convertDashToEmptyString(address.primary),
		addressRegionName: convertDashToEmptyString(address.addressRegion),
		addressZip: convertDashToEmptyString(address.postalCode),
		streetAddressLine1: convertDashToEmptyString(
			address.streetAddressLine1
		),
		streetAddressLine2: convertDashToEmptyString(
			address.streetAddressLine2
		),
		streetAddressLine3: convertDashToEmptyString(address.streetAddressLine3)
	});

	const {updatePermission} = usePermissions();

	useEffect(() => {
		setSelectedCountry(
			countryOptions.find(({name}) => name === values.addressCountryName)
		);
	}, [countryOptions, values.addressCountryName]);

	useEffect(() => {
		setDisableSave(!values.addressCountryName);
	}, [values]);

	function getRegionOptions() {
		return selectedCountry ? selectedCountry.countryRegions : [];
	}

	function handleCancel() {
		location.reload();
	}

	function handleOnChange(fieldName, value) {
		const newValue = {};
		newValue[fieldName] = value;

		if (fieldName === 'addressCountryName') {
			newValue['addressRegionName'] = '';
		}

		setValues({...values, ...newValue});
	}

	function handleSetEditable(bool) {
		setEditable(bool);
	}

	return (
		<form
			action={address.editPostalAddressURL}
			key={address.id}
			method="post"
			name="addressForm"
		>
			<input
				name={`${NAMESPACE}accountKey`}
				type="hidden"
				value={accountKey}
			/>

			<ClayList>
				<ClayList.Header>
					{Liferay.Language.get('address')} {count}
				</ClayList.Header>

				<AddressField.Text
					editable={editable}
					fieldLabel={Liferay.Language.get('street-1')}
					fieldName="streetAddressLine1"
					onChangeFn={handleOnChange}
					readOnly={!updatePermission}
					setEditableFn={handleSetEditable}
					value={values.streetAddressLine1}
				/>

				<AddressField.Text
					editable={editable}
					fieldLabel={Liferay.Language.get('city')}
					fieldName="addressLocality"
					onChangeFn={handleOnChange}
					readOnly={!updatePermission}
					setEditableFn={handleSetEditable}
					value={values.addressLocality}
				/>

				<AddressField.Text
					editable={editable}
					fieldLabel={Liferay.Language.get('street-2')}
					fieldName="streetAddressLine2"
					onChangeFn={handleOnChange}
					readOnly={!updatePermission}
					setEditableFn={handleSetEditable}
					value={values.streetAddressLine2}
				/>

				<AddressField.Select
					editable={editable}
					fieldLabel={Liferay.Language.get('state-province')}
					fieldName="addressRegionName"
					onChangeFn={handleOnChange}
					options={getRegionOptions()}
					readOnly={!updatePermission}
					readOnlyValue={address.addressRegion}
					setEditableFn={handleSetEditable}
					value={values.addressRegionName}
				/>

				<AddressField.Text
					editable={editable}
					fieldLabel={Liferay.Language.get('street-3')}
					fieldName="streetAddressLine3"
					onChangeFn={handleOnChange}
					readOnly={!updatePermission}
					setEditableFn={handleSetEditable}
					value={values.streetAddressLine3}
				/>

				<AddressField.Text
					editable={editable}
					fieldLabel={Liferay.Language.get('postal-code')}
					fieldName="addressZip"
					onChangeFn={handleOnChange}
					readOnly={!updatePermission}
					setEditableFn={handleSetEditable}
					value={values.addressZip}
				/>

				<AddressField.Select
					editable={editable}
					fieldLabel={Liferay.Language.get('country')}
					fieldName="addressCountryName"
					onChangeFn={handleOnChange}
					options={countryOptions}
					readOnly={!updatePermission}
					readOnlyValue={address.addressCountry}
					required
					setEditableFn={handleSetEditable}
					value={values.addressCountryName}
				/>

				<AddressField.Toggle
					editable={editable}
					fieldLabel={Liferay.Language.get('primary')}
					fieldName="addressPrimary"
					onChangeFn={handleOnChange}
					readOnly={!updatePermission}
					setEditableFn={handleSetEditable}
					value={values.addressPrimary}
				/>

				{updatePermission && (
					<ClayList.Item
						className={`address-controls ${
							editable ? 'editing' : ''
						}`}
						flex
					>
						{editable && (
							<div className="btn-group" role="group">
								<div className="btn-group-item">
									<button
										className="btn btn-primary btn-sm save-btn"
										disabled={disableSave}
										role="button"
										type="submit"
									>
										{Liferay.Language.get('save')}
									</button>
								</div>

								<div className="btn-group-item">
									<button
										className="btn btn-secondary btn-sm cancel-btn"
										onClick={handleCancel}
										role="button"
										type="button"
									>
										{Liferay.Language.get('cancel')}
									</button>
								</div>
							</div>
						)}

						{!!address.id && (
							<div className="btn-group" role="group">
								<div className="btn-group-item">
									<IconButton
										cssClass="add-address btn btn-secondary nav-btn nav-btn-monospaced"
										labelName={Liferay.Language.get('add')}
										onClick={addFn}
										svgId="#plus"
										title={Liferay.Language.get('add')}
									/>
								</div>

								{!!address.deletePostalAddressURL && (
									<div className="btn-group-item">
										<IconButton
											cssClass="btn-secondary delete-address nav-btn nav-btn-monospaced"
											labelName={Liferay.Language.get(
												'delete'
											)}
											onClick={() => {
												if (
													window.confirm(
														Liferay.Language.get(
															'are-you-sure-you-want-to-delete-this-address'
														)
													)
												) {
													window.location.assign(
														address.deletePostalAddressURL
													);
												}
											}}
											svgId="#hr"
											title={Liferay.Language.get(
												'delete'
											)}
										/>
									</div>
								)}
							</div>
						)}
					</ClayList.Item>
				)}
			</ClayList>
		</form>
	);
}

Address.propTypes = {
	accountKey: PropTypes.string,
	addFn: PropTypes.func.isRequired,
	address: PropTypes.shape({
		addressCountry: PropTypes.string,
		addressLocality: PropTypes.string,
		deletePostalAddressURL: PropTypes.string,
		editPostalAddressURL: PropTypes.string,
		id: PropTypes.string,
		postalCode: PropTypes.string,
		primary: PropTypes.bool,
		streetAddressLine1: PropTypes.string,
		streetAddressLine2: PropTypes.string,
		streetAddressLine3: PropTypes.string
	}),
	count: PropTypes.number,
	countryOptions: PropTypes.arrayOf(
		PropTypes.shape({
			active: PropTypes.bool,
			countryRegions: PropTypes.array,
			name: PropTypes.string,
			zipRequired: PropTypes.bool
		})
	)
};

export default Address;
