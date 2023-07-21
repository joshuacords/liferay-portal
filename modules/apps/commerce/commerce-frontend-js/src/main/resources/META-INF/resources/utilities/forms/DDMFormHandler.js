/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import AJAX from '../AJAX/index';
import {CP_INSTANCE_CHANGED} from '../eventsDefinitions';
import {getDefaultFieldsShape, updateFields} from './formsHelper';

class DDMFormHandler {
	constructor({DDMFormInstance, actionURL, addToCartId = '', portletId}) {
		this.actionURL = actionURL;
		this.addToCartId = addToCartId;
		this.DDMFormInstance = DDMFormInstance;
		this.portletId = portletId;
		this.fields = getDefaultFieldsShape(DDMFormInstance);

		this._attachFormListener();
		this.checkCPInstance();
	}

	_attachFormListener() {
		this.DDMFormInstance.on('fieldEdited', field => {
			this.fields = updateFields(this.fields, field);
			this.checkCPInstance();
		});
	}

	checkCPInstance() {
		const fieldsParam = new FormData();

		fieldsParam.append(
			`_${this.portletId}_ddmFormValues`,
			JSON.stringify(this.fields)
		);

		AJAX.POST(this.actionURL, null, {
			body: fieldsParam,
			headers: new Headers({'x-csrf-token': Liferay.authToken})
		}).then(cpInstance => {
			if (cpInstance.cpInstanceExist) {
				const dispatchedPayload = {
					addToCartId: this.addToCartId,
					cpInstance,
					formFields: this.fields
				};

				Liferay.fire(CP_INSTANCE_CHANGED, dispatchedPayload);
			}
		});
	}
}

export default DDMFormHandler;
