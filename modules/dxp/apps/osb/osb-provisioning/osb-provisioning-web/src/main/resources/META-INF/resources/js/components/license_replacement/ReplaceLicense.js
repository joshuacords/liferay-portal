/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {NAMESPACE} from '../../utilities/constants';
import {formatDate} from '../../utilities/date';
import ReplacementModal from './ReplacementModal';

export default function ReplaceLicense({
	expirationDate = '',
	replacementURL = '',
	startDate = ''
}) {
	const [modalVisible, setModalVisible] = useState(false);

	function handleClose() {
		setModalVisible(false);
	}

	function handleOnClick() {
		setModalVisible(true);
	}

	function handleReplace(newStartDate, newExpirationDate) {
		const form = document.getElementById(`${NAMESPACE}editLicenseFm`);

		const expirationDateField = document.getElementById(
			`${NAMESPACE}expirationDate`
		);
		const startDateField = document.getElementById(`${NAMESPACE}startDate`);

		if (form && expirationDateField && startDateField) {
			handleClose();

			expirationDateField.value = formatDate(newExpirationDate);
			startDateField.value = formatDate(newStartDate);

			form.action = replacementURL;
			form.submit();
		}
	}

	return (
		<>
			<button
				className="btn btn-secondary btn-sm"
				onClick={handleOnClick}
				type="button"
			>
				{Liferay.Language.get('replace')}
			</button>

			{modalVisible && (
				<ReplacementModal
					closeFn={handleClose}
					expirationDate={expirationDate}
					replaceFn={handleReplace}
					startDate={startDate}
				/>
			)}
		</>
	);
}

ReplaceLicense.propTypes = {
	expirationDate: PropTypes.string,
	replacementURL: PropTypes.string,
	startDate: PropTypes.string
};
