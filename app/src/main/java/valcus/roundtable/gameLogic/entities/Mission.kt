package valcus.roundtable.gameLogic.entities

/**
 * Created by Tim Shimp on 2/17/2018.
 */

class Mission (onMission: List<Player>) {

    var players: List<Player>
    var result: MissionResult? = null

    init {
        this.players = onMission
    }

}
