package valcus.roundtable

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import valcus.roundtable.Client.DummyClient
import valcus.roundtable.gameLogic.Game
import valcus.roundtable.gameLogic.Role
import valcus.roundtable.gameLogic.entities.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val settings = GameSettings()
    val server : Server = Mockito.mock(Server::class.java)
    val successVotes: MutableList<Vote> = ArrayList<Vote>()
    val successfulMission: MutableList<MissionResult> = ArrayList<MissionResult>()

    @Before
    fun setup() {
        successVotes.add(Vote(true))
        successVotes.add(Vote(true))
        successVotes.add(Vote(true))
        successVotes.add(Vote(true))
        successVotes.add(Vote(true))

        successfulMission.add(MissionResult.PASS)
        successfulMission.add(MissionResult.PASS)
        successfulMission.add(MissionResult.PASS)
        successfulMission.add(MissionResult.PASS)

//        fun getPickVotes(mission: Mission): List<Vote>
//
//        fun sendMission(mission: Mission): List<MissionResult>
//
//        fun getMissionPicks(leader : Player, mission: Mission): Mission
//
//        fun getAssassinGuess(assassin: Player?): Player
//
//        fun gameEnded(winner: MissionResult)
//
//        fun sendInfo(players: List<Player>)
    }

    @Test
    fun gameRunsToCompletionGoodWins() {
        //todo run this test

        Mockito.`when`(server.getPickVotes(Mockito.any())).thenReturn(successVotes)
        Mockito.`when`(server.sendMission(Mockito.any())).thenReturn(successfulMission)
        Mockito.`when`(server.getMissionPicks(Mockito.any(), Mockito.any())).thenReturn(Mission(2))

        settings.availableRoles.add(Role.GOOD)
        settings.availableRoles.add(Role.GOOD)
        settings.availableRoles.add(Role.GOOD)
        settings.availableRoles.add(Role.GOOD)
        settings.availableRoles.add(Role.EVIL)
        settings.availableRoles.add(Role.EVIL)

        val game = Game(settings, server)

        game.addPlayer(Player(DummyClient()))
        game.addPlayer(Player(DummyClient()))
        game.addPlayer(Player(DummyClient()))
        game.addPlayer(Player(DummyClient()))
        game.addPlayer(Player(DummyClient()))
        game.addPlayer(Player(DummyClient()))

        Mockito.verify(server.gameEnded(MissionResult.PASS))
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
