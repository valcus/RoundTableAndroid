package valcus.roundtable.gameLogic

import valcus.roundtable.gameLogic.entities.Mission
import valcus.roundtable.gameLogic.entities.Player
import valcus.roundtable.gameLogic.entities.Server
import valcus.roundtable.server.ServerFactory
import java.util.*

/**
 * Created by Tim Shimp on 2/17/2018.
 */

class Game (numberOfPlayers: Int, availableRoles: MutableList<Int>){

    var gameId: UUID = UUID.randomUUID()
    var displayName: String? = null
    val maxPlayers: Int
    private val players: MutableList<Player> = ArrayList<Player>()
    private val missions: MutableList<Mission> = ArrayList<Mission>()
    private val availableRoles: MutableList<Int>
    private val server: Server = ServerFactory.getServer()
    private val rand = Random()
    private var leader = -1

    init {
        this.availableRoles = availableRoles
        this.maxPlayers = numberOfPlayers
    }

    //TODO move this to the server, I think it's more appropriate there
//    fun assignRoles(players: List<Player>) {
//        val rand = Random()
//        for (p in players) {
//            val roleIndex = rand.nextInt(roles.size)
//            p.role = roles[roleIndex]
//            roles.removeAt(roleIndex)
//        }
//    }

    fun addPlayer(player: Player) {
        val roleIndex = rand.nextInt(availableRoles.size)
        player.role = availableRoles[roleIndex]
        availableRoles.removeAt(roleIndex)
        players.add(player)
        if(players.size == maxPlayers) {
            gameStart()
        }
    }

    fun gameStart() {
        leader = rand.nextInt(maxPlayers)
    }

    interface pickResultHnadler {
        fun getPicks()
    }
}