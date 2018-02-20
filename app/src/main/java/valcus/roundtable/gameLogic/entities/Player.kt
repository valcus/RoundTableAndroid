package valcus.roundtable.gameLogic.entities

import valcus.roundtable.gameLogic.Role

/**
 * Created by Tim Shimp on 2/17/2018.
 */

class Player (client: Client) {

    var role: Role = Role.GOOD
        set(value) {
            alignment = Role.getAlignment(value)
            field = value
        }
    var alignment: Role = Role.GOOD
    var displayName: String? = null
    var knowsAbout: List<Player>? = null
    val client: Client

    init {
        this.client = client
    }
}
