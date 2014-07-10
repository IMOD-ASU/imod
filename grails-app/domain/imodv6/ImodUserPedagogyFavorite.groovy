package imodv6

/**
 * TODO What is this?
 */
class ImodUserPedagogyFavorite {
	static belongsTo = [
		imodUser:ImodUser,
		pedagogyTechnique:PedagogyTechnique
	]
	static constraints = {
	}
}
