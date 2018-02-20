package valcus.roundtable.gameLogic

import valcus.roundtable.gameLogic.entities.*
import java.util.*

/**
 * Created by Tim Shimp on 2/17/2018.
 */

class Game (settings: GameSettings, server: Server){

    private val players: MutableList<Player> = ArrayList<Player>()
    private val missions: MutableList<Mission> = ArrayList<Mission>()
    private val settings: GameSettings
    private val server: Server
    private val rand = Random()
    private var leader = -1
    private var gameEnded: Boolean = false

    init {
        this.settings = settings
        this.server = server
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

    private fun gameStart() {
        setupPhase()
        while (!gameEnded) {
            mainLoop()
        }
        endPhase()
    }

    private fun mainLoop() {
        val mission = pickPhase()
        if (!gameEnded) {
            missionPhase(mission)
            if (!gameEnded) {
                infoPhase()
            }
        }
    }

    private fun setupPhase() {
        leader = rand.nextInt(settings.availableRoles.size)
        //get role info
        for(p in players) {
            val knownPlayers: MutableList<Player> = ArrayList<Player>()
            for (p2 in players) {
                if (Role.knows(p.role, p2.role)) {
                    knownPlayers.add(p2)
                }
            }
            p.knowsAbout = knownPlayers
        }
        server.sendInfo(players)
    }

    private fun pickPhase() : Mission {
        //todo determine how many get sent here?
        var pick: Mission?
        var pickCounter = 1

        while (pickCounter < 5) {
            pick = pickMission(pickCounter)
            if (pick != null) {
                return pick
            }
            pickCounter += 1
        }
        endGame()
        return Mission(ArrayList<Player>())
    }

    private fun missionPhase(sentMission: Mission) {
        val missionResults = server.sendMission(sentMission)

        //todo improve mission result check
        if(missionResults.contains(MissionResult.FAIL)) {
            sentMission.result = MissionResult.FAIL
        } else {
            sentMission.result = MissionResult.PASS
        }

        missions.add(sentMission)

        val passingMissions = missions.filter { it.result == MissionResult.PASS }
        val failingMissions = missions.filter { it.result == MissionResult.FAIL }
        if (failingMissions.size == 3 || passingMissions.size == 3) {
            endGame()
        }
    }

    private fun infoPhase() {
        //todo placeholder for lady, defectors, plots and whatnot
    }

    private fun endPhase() {
        var gameResult: MissionResult
        var missionSuccesses = 0
        var missionFailures = 0
        for(m in missions) {
            if (m.result == MissionResult.PASS) {
                missionSuccesses += 1
            } else {
                missionFailures += 1
            }
        }

        if (missionSuccesses >= 3) {
            //assassin guesses commander
            if (settings.availableRoles.contains(Role.ASSASSIN)) {
               val guess : Player = server.getAssassinGuess(players.find { it.role == Role.ASSASSIN })

                if (guess.role == Role.COMMANDER) {
                    gameResult = MissionResult.FAIL
                } else {
                    gameResult = MissionResult.PASS
                }
            } else {
                gameResult = MissionResult.PASS
            }
        } else {
            gameResult = MissionResult.FAIL
        }

        server.gameEnded(gameResult)
    }

    private fun pickMission(voteNum: Int): Mission? {
        //todo how many players does the leader pick?
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

    private fun endGame() {
        gameEnded = true
    }
}