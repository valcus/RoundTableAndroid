package valcus.roundtable.gameLogic.entities

import java.util.*

/**
 * Created by Tim Shimp on 2/17/2018.
 */

class Vote (approve: Boolean) {

    internal var approve: Boolean = false
    internal var playerId: UUID? = null

    init {
        this.approve = approve
    }
}
