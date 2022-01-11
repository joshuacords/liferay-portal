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

import ClayTable from '@clayui/table';
import React from 'react';

import DatePicker from '../DatePicker';
import FieldNumber from './FieldNumber';
import FieldNumberWithLabel from './FieldNumberWithLabel';
import FieldSelect from './FieldSelect';
import FieldText from './FieldText';

export const Date = ({editHandler, fieldName, value}) => (
	<ClayTable.Cell className="input-group-sm">
		<label htmlFor={fieldName}>
			<DatePicker
				defaultValue={value}
				id={fieldName}
				inputName={fieldName}
				placeholder={Liferay.Language.get('varied-data')}
				updateFn={editHandler}
			/>
		</label>
	</ClayTable.Cell>
);

export const Number = React.forwardRef((props, ref) => (
	<BulkInputWrapper Field={FieldNumber} ref={ref} {...props} />
));
export const NumberWithLabel = React.forwardRef((props, ref) => (
	<BulkInputWrapper Field={FieldNumberWithLabel} ref={ref} {...props} />
));
export const Select = React.forwardRef((props, ref) => (
	<BulkInputWrapper Field={FieldSelect} ref={ref} {...props} />
));
export const Text = React.forwardRef((props, ref) => (
	<BulkInputWrapper Field={FieldText} ref={ref} {...props} />
));

const BulkInputWrapper = React.forwardRef((props, ref) => {
	const {
		Field,
		editHandler,
		fieldName,
		isValid = true,
		showField,
		...rest
	} = props;

	return (
		<ClayTable.Cell className={isValid ? '' : 'has-error'}>
			{showField && <Field fieldName={fieldName} ref={ref} {...rest} />}

			{!showField && (
				<VariedData handler={editHandler} name={fieldName} {...rest} />
			)}
		</ClayTable.Cell>
	);
});

const VariedData = ({disableEdit = false, handler, name = ''}) => (
	<button
		aria-label={name}
		className="form-control form-control-sm varied-data"
		disabled={disableEdit}
		name={name}
		onClick={handler}
		type="button"
	>
		{Liferay.Language.get('varied-data')}
	</button>
);
