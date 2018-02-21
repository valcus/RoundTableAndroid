package valcus.roundtable.gameLogic.entities

/**
 * Created by Tim Shimp on 2/17/2018.
 */

class Mission (numToPick: Int) {

    var players: MutableList<Player> = ArrayList<Player>()
    var result: MissionResult? = null
    var numPlayers: Int

    init {
        this.numPlayers = numToPick
    }

}
