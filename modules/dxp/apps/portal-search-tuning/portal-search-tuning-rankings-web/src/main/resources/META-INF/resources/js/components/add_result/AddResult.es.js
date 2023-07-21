/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import ErrorBoundary from '../shared/ErrorBoundary.es';
import AddResultModal from './AddResultModal.es';

/**
 * A button that opens a modal to be able to search, select, and add results.
 */
function AddResult({fetchDocumentsSearchUrl, onAddResultSubmit}) {
	const [showModal, setShowModal] = useState(false);

	/**
	 * Opens the modal when the add result button is clicked.
	 */
	function _handleAddResultButton() {
		setShowModal(true);
	}

	/**
	 * Hides the modal.
	 */
	function _handleCloseModal() {
		setShowModal(false);
	}

	return (
		<>
			<ClayButton
				key="ADD_RESULT_BUTTON"
				onClick={_handleAddResultButton}
			>
				{Liferay.Language.get('add-result')}
			</ClayButton>

			<ErrorBoundary component={Liferay.Language.get('add-result')} toast>
				{showModal ? (
					<AddResultModal
						fetchDocumentsSearchUrl={fetchDocumentsSearchUrl}
						onAddResultSubmit={onAddResultSubmit}
						onCloseModal={_handleCloseModal}
					/>
				) : null}
			</ErrorBoundary>
		</>
	);
}

AddResult.propTypes = {
	fetchDocumentsSearchUrl: PropTypes.string.isRequired,
	onAddResultSubmit: PropTypes.func.isRequired
};

export default AddResult;
