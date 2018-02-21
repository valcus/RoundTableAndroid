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
        var pick = Mission(getNumOfPlayersToSend())
        var pickCounter = 1

        while (pickCounter < 5) {
            pick = pickMission(pick)
            if (pick.players.isEmpty()) {
                return pick
            }
            pickCounter += 1
        }
        endGame()
        return Mission(0)
    }

    private fun missionPhase(sentMission: Mission) {
        val missionResults = server.sendMission(sentMission)

        sentMission.result = getMissionResult(missionResults)

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

    private fun pickMission(mission: Mission): Mission {
        val pick = server.getMissionPicks(players[leader], mission)

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

        mission.players.clear()
        return mission

    }

    private fun endGame() {
        gameEnded = true
    }

    private fun getNumOfPlayersToSend() = when (missions.size) {
        0 -> {
            when (settings.availableRoles.size) {
                5, 6, 7 -> 2
                8, 9, 10 -> 3
                else -> -1
            }
        }
        1 -> {
            when (settings.availableRoles.size) {
                5, 6, 7 -> 3
                8, 9 , 10 -> 4
                else -> -1
            }
        }
        2 -> {
            when (settings.availableRoles.size) {
                5 -> 2
                7 -> 3
                6, 8, 9, 10 -> 4
                else -> -1
            }
        }
        3 -> {
            when (settings.availableRoles.size) {
                5, 6 -> 3
                7 -> 4
                8, 9, 10 -> 5
                else -> -1
            }
        }
        4 -> {
            when (settings.availableRoles.size) {
                5 -> 3
                6, 7 -> 4
                8, 9, 10 -> 5
                else -> -1
            }
        }
        else -> -1
    }

    private fun getMissionResult(results: List<MissionResult>) : MissionResult {
        var missionResult: MissionResult = MissionResult.PASS

        if (missions.size == 3 && settings.availableRoles.size > 6) {
            var failCount = 0
            for (m in results){
                if (m == MissionResult.FAIL) {
                    failCount += 1
                }
            }
            if (failCount >= 2) {
                missionResult = MissionResult.FAIL
            }
        } else {
            if (results.contains(MissionResult.FAIL)) {
                missionResult = MissionResult.FAIL
            }
        }

        return missionResult
    }
}