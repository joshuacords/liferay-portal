/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayAlert from '@clayui/alert';
import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import {
	useInitialDateFormatValidators,
	useSetDisplayAlert,
	useSubscriptions
} from '../../hooks/subscriptions';
import {ADD_SUBSCRIPTIONS, EDIT_SUBSCRIPTIONS} from '../../utilities/constants';
import {itemSelectorDialogSelection} from '../../utilities/itemSelectorDialogHelper';
import ExternalSelectField from '../ExternalSelectField';
import HiddenForm from '../HiddenForm';
import SubscriptionActions from './SubscriptionActions';
import Subscriptions from './Subscriptions';

export function AddView({
	accountName,
	editProductPurchasesURL,
	redirect,
	selectProductsActionURL,
	selectProductsRenderURL,
	sizing
}) {
	const [subscriptions] = useSubscriptions();

	const [displayAlert, setDisplayAlert] = useState(false);
	const [dateFormatValidators, setDateFormatValidators] = useState(
		useInitialDateFormatValidators(subscriptions)
	);

	useSetDisplayAlert(setDisplayAlert, subscriptions.toList());

	function getAllowSave() {
		return dateFormatValidators.every(validator => validator.isValid());
	}

	function getInitialProductKeys() {
		const productsKeys = subscriptions.keySeq().toJS();

		return productsKeys
			.map(product => {
				const [key] = product.split('_');

				return key;
			})
			.join(',');
	}

	function validateDateFormat(keyPath, value) {
		setDateFormatValidators(dateFormatValidators.setIn(keyPath, value));
	}

	return (
		<div className="subscriptions-container">
			<div className="subscriptions-header">
				<b>{Liferay.Language.get('configure-subscriptions')}</b>

				<ProductSelection
					formAction={selectProductsActionURL}
					initialProductKeys={getInitialProductKeys()}
					selectionURL={selectProductsRenderURL}
				/>
			</div>

			{displayAlert && (
				<InvalidDateAlert
					message={Liferay.Language.get(
						'please-make-sure-the-start-date-is-before-the-end-date'
					)}
				/>
			)}

			<div className="info-container">
				<div className="info">
					<svg
						aria-label={Liferay.Language.get('info-icon')}
						className="lexicon-icon lexicon-icon-info-circle-full"
						role="img"
					>
						<use xlinkHref="#info-circle-full" />
					</svg>

					{Liferay.Language.get(
						'date-and-time-displayed-in-utc-all-end-dates-are-exclusive'
					)}
				</div>
			</div>

			<div className="subscriptions">
				<Subscriptions
					accountName={accountName}
					instanceSizes={sizing}
					subscriptionsType={ADD_SUBSCRIPTIONS}
					validateDateFormat={validateDateFormat}
				/>
			</div>

			<SubscriptionActions
				allowSave={getAllowSave()}
				formAction={editProductPurchasesURL}
				redirectURL={redirect}
				subscriptionsType={ADD_SUBSCRIPTIONS}
			/>
		</div>
	);
}

AddView.propTypes = {
	accountName: PropTypes.string.isRequired,
	editProductPurchasesURL: PropTypes.string.isRequired,
	redirect: PropTypes.string.isRequired,
	selectProductsActionURL: PropTypes.string.isRequired,
	selectProductsRenderURL: PropTypes.string,
	sizing: PropTypes.arrayOf(PropTypes.number)
};

export function EditView({
	accountName,
	backURL,
	editProductPurchasesURL,
	redirect,
	sizing,
	status
}) {
	const [subscriptions] = useSubscriptions();

	const [dateFormatValidators, setDateFormatValidators] = useState(
		useInitialDateFormatValidators(subscriptions)
	);
	const [displayAlert, setDisplayAlert] = useState(false);

	useSetDisplayAlert(setDisplayAlert, subscriptions.toList());

	function getAllowSave() {
		return dateFormatValidators.every(validator => validator.isValid());
	}

	function validateDateFormat(keyPath, value) {
		setDateFormatValidators(dateFormatValidators.setIn(keyPath, value));
	}

	return (
		<>
			<div className="page-steps">
				<span>{Liferay.Language.get('edit-details')}</span>

				<span>{Liferay.Language.get('step-2-of-2')}</span>
			</div>

			<div className="subscriptions-container">
				{displayAlert && (
					<InvalidDateAlert
						message={Liferay.Language.get(
							'please-make-sure-the-start-date-is-before-the-end-date-and-the-grace-period-is-a-positive-number'
						)}
					/>
				)}

				<div className="info-container">
					<div className="info">
						<svg
							aria-label={Liferay.Language.get('info-icon')}
							className="lexicon-icon lexicon-icon-info-circle-full"
							role="img"
						>
							<use xlinkHref="#info-circle-full" />
						</svg>

						{Liferay.Language.get(
							'date-and-time-displayed-in-utc-all-end-dates-are-exclusive'
						)}
					</div>
				</div>

				<div className="subscriptions">
					<Subscriptions
						accountName={accountName}
						instanceSizes={sizing}
						statusOptions={status}
						subscriptionsType={EDIT_SUBSCRIPTIONS}
						validateDateFormat={validateDateFormat}
					/>
				</div>

				<SubscriptionActions
					allowSave={getAllowSave()}
					backURL={backURL}
					formAction={editProductPurchasesURL}
					redirectURL={redirect}
					subscriptionsType={EDIT_SUBSCRIPTIONS}
				/>
			</div>
		</>
	);
}

EditView.propTypes = {
	accountName: PropTypes.string.isRequired,
	backURL: PropTypes.string,
	editProductPurchasesURL: PropTypes.string.isRequired,
	redirect: PropTypes.string.isRequired,
	sizing: PropTypes.arrayOf(PropTypes.number),
	status: PropTypes.arrayOf(PropTypes.string)
};

function InvalidDateAlert({message}) {
	return (
		<ClayAlert
			displayType="danger"
			title={Liferay.Language.get('invalid-date')}
		>
			{message}
		</ClayAlert>
	);
}

function ProductSelection({formAction, initialProductKeys, selectionURL}) {
	const formRef = useRef();

	const [productBundleIds, setProductBundleIds] = useState('');
	const [productKeys, setProductKeys] = useState('');

	useEffect(() => {
		if (formRef.current && productKeys) {
			formRef.current.submit();
		}
	}, [productBundleIds, productKeys]);

	function handleSelectMoreSubscriptions() {
		const addselectionFromDialog = selectionData => {
			const selectionKeys = selectionData.map(data => {
				const [key] = data.split('_');

				return key;
			});

			setProductBundleIds(
				selectionKeys.filter(key => !key.startsWith('KOR')).join(',')
			);

			const newKeys = selectionKeys.filter(key => key.startsWith('KOR'));

			if (newKeys.length) {
				setProductKeys(
					initialProductKeys.concat(',', newKeys.join(','))
				);
			}
			else {
				setProductKeys(initialProductKeys);
			}
		};

		itemSelectorDialogSelection(
			{
				title: Liferay.Language.get('select-subscriptions'),
				url: selectionURL
			},
			addselectionFromDialog
		);
	}

	return (
		<>
			<HiddenForm
				fields={{
					productBundleIds,
					productKeys
				}}
				formAction={formAction}
				formName="addProductPurchasesFm"
				ref={formRef}
			/>

			<ExternalSelectField clickFn={handleSelectMoreSubscriptions} />
		</>
	);
}
