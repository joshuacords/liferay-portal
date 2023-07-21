/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import {NAMESPACE} from '../../utilities/constants';
import {itemSelectorDialogSelection} from '../../utilities/itemSelectorDialogHelper';
import ExternalSelectField from '../ExternalSelectField';

function SelectAccount({
	accountKey = '',
	accountName = '',
	actionURL,
	dialogURL
}) {
	const formRef = useRef();

	const [selectedAccountKey, setSelectedAccountKey] = useState(accountKey);
	const [selectedAccountName, setSelectedAccountName] = useState(accountName);

	useEffect(() => {
		if (
			formRef.current &&
			selectedAccountKey !== '' &&
			selectedAccountKey !== accountKey
		) {
			formRef.current.submit();
		}
	}, [accountKey, selectedAccountKey]);

	function handleClick() {
		const assignInputValueFromDialog = fieldData => {
			const {key, name} = JSON.parse(fieldData);

			if (key) {
				setSelectedAccountKey(key);
			}

			if (name) {
				setSelectedAccountName(name);
			}
		};

		itemSelectorDialogSelection(
			{
				formField: 'accountKey',
				title: Liferay.Language.get('select-account'),
				url: dialogURL
			},
			assignInputValueFromDialog
		);
	}

	return (
		<form
			action={actionURL}
			method="post"
			name="selectAccount"
			ref={formRef}
		>
			<input
				name={`${NAMESPACE}accountKey`}
				type="hidden"
				value={selectedAccountKey}
			/>

			<ExternalSelectField
				clickFn={handleClick}
				id={'accountName'}
				value={selectedAccountName}
			/>
		</form>
	);
}

SelectAccount.propTypes = {
	actionURL: PropTypes.string.isRequired,
	dialogURL: PropTypes.string.isRequired
};

export default SelectAccount;
