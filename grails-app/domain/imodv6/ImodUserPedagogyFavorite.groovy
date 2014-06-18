package imodv6

class ImodUserPedagogyFavorite {
	static belongsTo = [imodUser:ImodUser,pedagogyTechnique:PedagogyTechnique]
    static constraints = {
    }
}
