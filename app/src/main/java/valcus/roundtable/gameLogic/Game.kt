package valcus.roundtable.gameLogic

import valcus.roundtable.gameLogic.entities.*
import valcus.roundtable.server.ServerFactory
import java.util.*

/**
 * Created by Tim Shimp on 2/17/2018.
 */

class Game (settings: GameSettings){

    private val players: MutableList<Player> = ArrayList<Player>()
    private val missions: MutableList<Mission> = ArrayList<Mission>()
    private val settings: GameSettings
    private val server: Server = ServerFactory.getServer()
    private val rand = Random()
    private var leader = -1
    private var gameEnded: Boolean = false

    init {
        this.settings = settings
    }

    fun addPlayer(player: Player) {
        val roleIndex = rand.nextInt(settings.availableRoles.size)
        player.role = settings.availableRoles[roleIndex]
        settings.availableRoles.removeAt(roleIndex)
        players.add(player)
        if(players.size == settings.availableRoles.size) {
            gameStart()
        }
    }

    fun gameStart() {
        setupPhase()
        while (!gameEnded) {
            mainLoop()
        }
        endPhase()
    }

    fun mainLoop() {
        val mission = pickPhase()
        if (!gameEnded) {
            missionPhase(mission)
            if (!gameEnded) {
                infoPhase()
            }
        }
    }

    fun setupPhase() {
        leader = rand.nextInt(settings.availableRoles.size)
        //assign roles here?
    }

    fun pickPhase() : Mission {
        var pick: Mission? = null
        var pickCounter = 1

        while (pickCounter < 5) {
            pick = pickMission(pickCounter)
            if (pick != null) {
                return pick
            }
        }
        endGame()
        return Mission(ArrayList<Player>())
    }

    fun missionPhase(missionToSend: Mission) {
        val sentMission = server.sendMission(missionToSend)
        missions.add(sentMission)
    }

    fun infoPhase() {
        //todo placeholder for lady, defectors, plots and whatnot
    }

    fun endPhase() {
        //todo determine who won
    }

    fun pickMission(voteNum: Int): Mission? {
        val pick = server.getMissionPicks(players[leader])

        val missionVotes = server.getPickVotes(pick)

        //next leader
        leader += 1
        if (leader == settings.availableRoles.size) {
            leader = 0
        }

        var approves: Int = 0
        for (v in missionVotes){
            if (v.approve) {
                approves += 1
            }
        }

        val missionApproved = (approves / settings.availableRoles.size.toDouble())  > 0.5

        if (missionApproved) {
            return pick
        }
        return null

    }

    fun endGame() {
        gameEnded = true
    }
}