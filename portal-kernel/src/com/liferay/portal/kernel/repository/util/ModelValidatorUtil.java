/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.repository.util;

import com.liferay.document.library.kernel.util.DLValidatorUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileContentReference;
import com.liferay.portal.kernel.repository.model.ModelValidator;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author     Adolfo Pérez
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class ModelValidatorUtil {

	public static final <T> ModelValidator<T> compose(
		ModelValidator<T>... modelValidators) {

		return new CompositeModelValidator<>(modelValidators);
	}

	public static final ModelValidator<FileContentReference>
		getDefaultDLFileEntryModelValidator() {

		return compose(
			getDefaultFileNameModelValidator(),
			getDefaultFileExtensionModelValidator(),
			getDefaultFileSizeModelValidator());
	}

	public static final ModelValidator<FileContentReference>
		getDefaultFileExtensionModelValidator() {

		return _defaultFileExtensionModelValidator;
	}

	public static final ModelValidator<FileContentReference>
		getDefaultFileNameModelValidator() {

		return _defaultFileNameModelValidator;
	}

	public static final ModelValidator<FileContentReference>
		getDefaultFileSizeModelValidator() {

		return _defaultFileSizeModelValidator;
	}

	private static final ModelValidator<FileContentReference>
		_defaultFileExtensionModelValidator =
			new ModelValidator<FileContentReference>() {

				@Override
				public void validate(FileContentReference fileContentReference)
					throws PortalException {

					if ((fileContentReference.getFileEntryId() != 0) &&
						Validator.isNull(
							fileContentReference.getSourceFileName())) {

						return;
					}

					DLValidatorUtil.validateFileExtension(
						fileContentReference.getSourceFileName());

					DLValidatorUtil.validateSourceFileExtension(
						fileContentReference.getExtension(),
						fileContentReference.getSourceFileName());
				}

			};

	private static final ModelValidator<FileContentReference>
		_defaultFileNameModelValidator =
			new ModelValidator<FileContentReference>() {

				@Override
				public void validate(FileContentReference fileContentReference)
					throws PortalException {

					if (Validator.isNotNull(
							fileContentReference.getSourceFileName())) {

						DLValidatorUtil.validateFileName(
							fileContentReference.getSourceFileName());
					}
				}

			};

	private static final ModelValidator<FileContentReference>
		_defaultFileSizeModelValidator =
			new ModelValidator<FileContentReference>() {

				@Override
				public void validate(FileContentReference fileContentReference)
					throws PortalException {

					DLValidatorUtil.validateFileSize(
						fileContentReference.getSourceFileName(),
						fileContentReference.getSize());
				}

			};

	private static class CompositeModelValidator<T>
		implements ModelValidator<T> {

		public CompositeModelValidator(ModelValidator<T>... modelValidators) {
			_modelValidators = modelValidators;
		}

		@Override
		public void validate(T t) throws PortalException {
			for (ModelValidator<T> modelValidator : _modelValidators) {
				modelValidator.validate(t);
			}
		}

		private final ModelValidator<T>[] _modelValidators;

	}

}