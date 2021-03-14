package red.man10.stoneclicker

import org.bson.Document
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.plugin.java.JavaPlugin
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import red.man10.man10datacenter.MongoDBManager
import red.man10.stoneclicker.Inventory.ClickInventory
import red.man10.stoneclicker.Inventory.CreateInventory
import red.man10.stoneclicker.Inventory.CreateInventory.CreateGameMenu
import java.util.HashMap


class StoneClicker : JavaPlugin(), Listener {

    var data = MongoDBManager(this, "StoneClicker")

    companion object{

        var prefix = "§e§l[§d§lStoneClicker§e§l]§f"
        var count: Map<Player, Long> = HashMap() //GUIにいれたアイテム数をプレイヤーごとに管理する

    }

    override fun onEnable() {
        saveDefaultConfig()
        server.pluginManager.registerEvents(this, this)
        server.pluginManager.registerEvents(ClickInventory, this)
        reloadConfig()
        getCommand("stoneclicker")!!.setExecutor(this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            return true
        }
        val p = sender
        if (!p.hasPermission("sc.use")) {
            p.sendMessage("Unknown command. Type \"/help\" for help.")
            return true
        }
        if (args.isEmpty()) {
            val uuid = p.uniqueId.toString()
            val filter = Document().append("uuid", uuid)
            val result = data.queryFind(filter)
            val parsed: JSONObject = JSONParser().parse(result[0].toJson()) as JSONObject
            val stonecount = parsed["stonecount"] as String
            val LongStoneCount = stonecount.toLong()
            p.openInventory(CreateGameMenu("$prefix ${LongStoneCount}個",p))
            return true
        }
        return false
    }

    @EventHandler
    fun LoginEvent(event: PlayerLoginEvent?) {
        val config = config
        if (!config.getBoolean("mode")) return
        Bukkit.getScheduler().runTaskAsynchronously(this, Runnable {
            try {
                setData(event as PlayerEvent?)
            } catch (e: Exception) {
                Bukkit.getLogger().info(e.message)
                println(e.message)
            }
        })
    }

    fun setData(event: PlayerEvent?) {
        val p = event?.player
        val uuid = p?.uniqueId.toString()
        val player = p?.name
        var stonecount = "0"
        val doc = Document()
        doc.append("uuid", uuid)
        val result = data.queryFind(doc)
        if(result.isEmpty()) {
            doc.append("mcid", player)
            doc.append("stonecount", stonecount)
            data.queryInsertOne(doc)
        }
    }
}
