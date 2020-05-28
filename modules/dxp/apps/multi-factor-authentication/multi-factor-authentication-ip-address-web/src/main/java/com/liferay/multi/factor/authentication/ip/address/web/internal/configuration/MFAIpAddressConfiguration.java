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

package com.liferay.multi.factor.authentication.ip.address.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Marta Medio
 */
@ExtendedObjectClassDefinition(
	category = "multi-factor-authentication",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.multi.factor.authentication.ip.address.web.internal.configuration.MFAIpAddressConfiguration",
	localization = "content/Language", name = "mfa-ip-configuration-name"
)
public interface MFAIpAddressConfiguration {

	@Meta.AD(
		deflt = "false", description = "mfa-ip-otp-enabled-description",
		name = "enabled", required = false
	)
	public boolean enabled();

	/**
	 * Allowed IPs and its network masks, use add button to add new entry for
	 * different integration. Can be both IPv4 and IPv6.
	 *
	 * @return allowed IPs and their network masks.
	 */
	@Meta.AD(
		deflt = "127.0.0.1/255.0.0.0|::1/128|10.0.0.0/8|172.16.0.0/12|192.168.0.0/16|fc00::/7",
		description = "allowed-ips-with-masks-description",
		name = "allowed-ips-with-masks-name", required = false
	)
	public String[] allowedIPsWithMasks();

}