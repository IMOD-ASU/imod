package imodv6

/**
 * This allows a user to mark a pedagogy as a favourite
 */
class ImodUserPedagogyFavorite {
	static belongsTo = [
		imodUser:ImodUser,
		pedagogyTechnique:PedagogyTechnique
	]
	static constraints = {
	}
}
