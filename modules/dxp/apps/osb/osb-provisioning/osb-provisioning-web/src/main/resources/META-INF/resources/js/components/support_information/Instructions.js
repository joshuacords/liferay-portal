/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React, {useRef} from 'react';

import {usePermissions} from '../../hooks/permissions';
import {
	DASH,
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_TEXTAREA
} from '../../utilities/constants';
import SupportField from './SupportField';

function Instructions({
	accountAttachmentURL,
	accountKey,
	fileName,
	instructions,
	updateAccountAttachmentURL,
	updateInstructionsURL
}) {
	const {updatePermission} = usePermissions();

	function handleUpdateSupportInstructions(instructions) {
		return {
			instructions
		};
	}

	return (
		<ClayList className="instructions">
			<ClayList.Header>
				{Liferay.Language.get('instructions')}
			</ClayList.Header>

			{!!updateAccountAttachmentURL && !!updateInstructionsURL && (
				<>
					<FileUpload
						accountKey={accountKey}
						fieldLabel={Liferay.Language.get('oem-instructions')}
						fieldName="oemInstructions"
						fileName={fileName}
						fileURL={accountAttachmentURL}
						formAction={updateAccountAttachmentURL}
					/>

					<SupportField
						fieldLabel={Liferay.Language.get(
							'support-instructions'
						)}
						fieldName="instructions"
						formAction={updateInstructionsURL}
						type={
							updatePermission
								? FIELD_TYPE_TEXTAREA
								: FIELD_TYPE_NONEDITABLE
						}
						updateFormData={handleUpdateSupportInstructions}
						value={instructions}
					/>
				</>
			)}

			{!updateAccountAttachmentURL && !updateInstructionsURL && (
				<ClayList.Item flex>
					<div className="detail-field list-group-text text-muted">
						{Liferay.Language.get('support-project-does-not-exist')}
					</div>
				</ClayList.Item>
			)}
		</ClayList>
	);
}

Instructions.propTypes = {
	accountAttachmentURL: PropTypes.string,
	accountKey: PropTypes.string,
	fileName: PropTypes.string,
	instructions: PropTypes.string,
	updateAccountAttachmentURL: PropTypes.string,
	updateInstructionsURL: PropTypes.string
};

function FileUpload({
	accountKey,
	fieldLabel,
	fieldName,
	fileName,
	fileURL,
	formAction
}) {
	const formRef = useRef();
	const {updatePermission} = usePermissions();

	function handleChange() {
		formRef.current.submit();
	}

	return (
		<ClayList.Item flex>
			<div className="detail-field">
				<ClayList.ItemTitle>{fieldLabel}</ClayList.ItemTitle>

				{!!fileName && (
					<a
						className="account-attachment"
						href={fileURL}
						target="_blank"
					>
						{fileName}
					</a>
				)}

				{!fileName && !updatePermission && <>{DASH}</>}

				{updatePermission && (
					<form
						action={formAction}
						encType="multipart/form-data"
						method="post"
						ref={formRef}
					>
						<input
							name="koroneikiAccountKey"
							type="hidden"
							value={accountKey}
						/>

						<label
							aria-label={fieldName}
							className="form-control-label"
							htmlFor={fieldName}
						>
							<input
								className="form-control"
								id={fieldName}
								name={fieldName}
								onChange={handleChange}
								type="file"
							/>
						</label>
					</form>
				)}
			</div>
		</ClayList.Item>
	);
}

export default Instructions;
