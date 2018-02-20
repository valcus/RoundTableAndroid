package valcus.roundtable.gameLogic.entities

import valcus.roundtable.gameLogic.Game

/**
 * Created by Tim Shimp on 2/17/2018.
 */
interface Server {

    fun getGameList(): List<Game>

    fun joinGame(game: Game, me: Player)

    fun vote(myVote: Vote)

    fun pick(pick: List<Player>)

    fun getPickVotes(mission: Mission): List<Vote>

    fun sendMission(mission: Mission): Mission

    fun getMissionPicks(leader : Player): Mission
}