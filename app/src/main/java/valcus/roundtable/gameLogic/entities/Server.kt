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

    fun getMissionResult(): List<Vote>

    fun setMissionResultHandler(resultHandler: (List<Vote>) -> Any)

    fun getMissionPicks(): List<Player>?

    fun createGame(game: Game, player: Player)
}