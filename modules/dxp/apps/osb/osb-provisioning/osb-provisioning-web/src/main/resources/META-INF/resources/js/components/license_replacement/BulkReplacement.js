/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import {formatDate} from '../../utilities/date';
import HiddenForm from '../HiddenForm';
import ReplacementModal from './ReplacementModal';

export default function BulkReplacement({
	accountKey,
	productKey = '',
	replacementURL
}) {
	const formRef = useRef();

	const [expirationDate, setExpirationDate] = useState('');
	const [licenseKeyIds, setLicenseKeyIds] = useState('');
	const [modalVisible, setModalVisible] = useState(false);
	const [startDate, setStartDate] = useState('');

	useEffect(() => {
		const listener = event => {
			const {detail} = event;

			setLicenseKeyIds(detail.licenseKeyIds);
			setModalVisible(detail.modalVisible);
		};

		window.addEventListener('bulkReplaceLicenses', listener);

		return () =>
			window.removeEventListener('bulkReplaceLicenses', listener);
	});

	useEffect(() => {
		if (startDate && expirationDate && formRef.current) {
			formRef.current.submit();
		}
	}, [expirationDate, startDate]);

	return (
		<>
			{modalVisible && (
				<ReplacementModal
					closeFn={() => setModalVisible(false)}
					replaceFn={(startDate, expirationDate) => {
						setStartDate(startDate);
						setExpirationDate(expirationDate);
					}}
				/>
			)}

			<HiddenForm
				fields={{
					accountKey,
					expirationDate: formatDate(expirationDate),
					licenseKeyIds,
					productKey,
					startDate: formatDate(startDate)
				}}
				formAction={replacementURL}
				formName="bulkLicenseReplacement"
				ref={formRef}
			/>
		</>
	);
}

BulkReplacement.propTypes = {
	accountKey: PropTypes.string,
	productKey: PropTypes.string,
	replacementURL: PropTypes.string.isRequired
};
