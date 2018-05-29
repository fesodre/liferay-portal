/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.saml.persistence.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.saml.persistence.model.SamlIdpSpConnection;
import com.liferay.saml.persistence.service.SamlIdpSpConnectionLocalServiceUtil;

/**
 * The extended model base implementation for the SamlIdpSpConnection service. Represents a row in the &quot;SamlIdpSpConnection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SamlIdpSpConnectionImpl}.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlIdpSpConnectionImpl
 * @see SamlIdpSpConnection
 * @generated
 */
@ProviderType
public abstract class SamlIdpSpConnectionBaseImpl
	extends SamlIdpSpConnectionModelImpl implements SamlIdpSpConnection {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a saml idp sp connection model instance should use the {@link SamlIdpSpConnection} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			SamlIdpSpConnectionLocalServiceUtil.addSamlIdpSpConnection(this);
		}
		else {
			SamlIdpSpConnectionLocalServiceUtil.updateSamlIdpSpConnection(this);
		}
	}
}