package valcus.roundtable.gameLogic.entities

import valcus.roundtable.gameLogic.Role
import java.util.*

/**
 * Created by Tim Shimp on 2/17/2018.
 */

class Player (client: Client) {

    //todo does player implement client?

    internal var playerId: UUID = UUID.randomUUID()
    var role: Role = Role.GOOD
        set(value) {
            alignment = Role.getAlignment(value)
            field = value
        }
    var alignment: Role = Role.GOOD
    var displayName: String? = null
    val client: Client

    init {
        this.client = client
    }

//    fun getMissionPick(): List<Player> {
//
//    }
}
