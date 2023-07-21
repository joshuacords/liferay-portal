/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import {formatDate, generateNewDateByDay} from '../../utilities/date';
import HiddenForm from '../HiddenForm';
import ExtendEndDateModal from './ExtendEndDateModal';

export default function ExtendAllSubscriptions({
	extendActiveSubscriptionsURL,
	latestActiveSubscriptionEndDate
}) {
	const formRef = useRef();

	const [endDate, setEndDate] = useState('');
	const [modalVisible, setModalVisible] = useState(false);

	useEffect(() => {
		const listener = event => {
			setModalVisible(event.detail.modalVisible);
		};

		window.addEventListener('extendAllActiveSubscriptions', listener);

		return () =>
			window.removeEventListener(
				'extendAllActiveSubscriptions',
				listener
			);
	});

	useEffect(() => {
		if (endDate && formRef.current) {
			formRef.current.submit();
		}
	}, [endDate]);

	return (
		<>
			{modalVisible && (
				<ExtendEndDateModal
					closeFn={() => setModalVisible(false)}
					extendFn={endDate => setEndDate(endDate)}
					latestActiveSubscriptionEndDate={
						latestActiveSubscriptionEndDate
					}
					newEndDate={generateNewDateByDay(
						latestActiveSubscriptionEndDate
					)}
				/>
			)}

			<HiddenForm
				fields={{
					endDate: formatDate(endDate)
				}}
				formAction={extendActiveSubscriptionsURL}
				formName="extendAllSubscriptions"
				ref={formRef}
			/>
		</>
	);
}

ExtendAllSubscriptions.propTypes = {
	extendActiveSubscriptionsURL: PropTypes.string.isRequired,
	latestActiveSubscriptionEndDate: PropTypes.string.isRequired
};
