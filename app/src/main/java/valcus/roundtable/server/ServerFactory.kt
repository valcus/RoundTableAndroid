package valcus.roundtable.server

/**
 * Created by Tim Shimp on 2/17/2018.
 */
object ServerFactory {
    fun getServer() : DummyServer {
        return DummyServer()
    }
}