package imodv6

import org.apache.commons.lang.builder.HashCodeBuilder


/**
 * This describes what permissions a User of the IMODS system has
 */
class ImodUserRole implements Serializable {

	ImodUser imodUser
	Role role

	boolean equals(other) {
		if (!(other instanceof ImodUserRole)) {
			return false
		}

		other.imodUser?.id == imodUser?.id &&
			other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (imodUser) builder.append(imodUser.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static ImodUserRole get(long imodUserId, long roleId) {
		find 'from ImodUserRole where imodUser.id=:imodUserId and role.id=:roleId',
			[imodUserId: imodUserId, roleId: roleId]
	}

	static ImodUserRole create(ImodUser imodUser, Role role) {
		new ImodUserRole(imodUser: imodUser, role: role).save( insert: true)
	}

	static boolean remove(ImodUser imodUser, Role role) {
		ImodUserRole instance = ImodUserRole.findByImodUserAndRole(imodUser, role)
		if (!instance) {
			return false
		}

		instance.delete()
		true
	}

	static void removeAll(ImodUser imodUser) {
		executeUpdate 'DELETE FROM ImodUserRole WHERE imodUser=:imodUser', [imodUser: imodUser]
	}

	static void removeAll(Role role) {
		executeUpdate 'DELETE FROM ImodUserRole WHERE role=:role', [role: role]
	}

	static mapping = {
		id composite: ['role', 'imodUser']
		version false
	}
}
