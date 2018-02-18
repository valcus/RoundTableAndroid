package valcus.roundtable.gameLogic

/**
 * Created by Tim Shimp on 2/17/2018.
 */
interface Server {

    fun connect()

    fun getGameList(): List<Game>

    fun joinGame(game: Game, me: Player)

    fun vote(myVote: Vote)

    fun pick(pick: List<Player>)

    fun getMissionResult(): List<Vote>

    fun getMissionPicks(): List<Player>?

    fun getRole(): List<Player>
}