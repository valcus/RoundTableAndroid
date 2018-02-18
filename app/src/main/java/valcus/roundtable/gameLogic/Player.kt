package valcus.roundtable.gameLogic

import java.util.*

/**
 * Created by Tim Shimp on 2/17/2018.
 */

class Player {

    internal var playerId: UUID = UUID.randomUUID()
    var role: Int? = null
        set(value) {
            alignment = RoleInformation.getAlignment(value)
            field = value
        }
    var alignment: Int = 0
    var displayName: String? = null
}
