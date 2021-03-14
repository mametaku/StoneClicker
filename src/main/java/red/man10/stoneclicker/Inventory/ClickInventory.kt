package red.man10.stoneclicker.Inventory

import me.nikl.nmsutilities.NmsUtility
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import red.man10.stoneclicker.StoneClicker
import red.man10.stoneclicker.StoneClicker.Companion.count
import java.util.ArrayList

object ClickInventory : Listener {

    private val nms : NmsUtility? = null

    @EventHandler
    fun onItemClick(e: InventoryClickEvent) {
        val p = e.whoClicked as Player
        val q = e.view.title
        if (!q.contains("§e§l[§d§lStoneClicker§e§l]§f")) return
        val slot = e.currentItem?.type
        if (e.currentItem == null) return
        if (slot == Material.COBBLESTONE){
            Bukkit.broadcastMessage("test")
            var count1 = count[p]
            if (count1 == null){
                count1 = 0
            }
            val result = count1.plus(1L)
            val cobblestone = e.currentItem
            val itemmeta = cobblestone?.itemMeta
            itemmeta?.setDisplayName("あなたの丸石！")
            val lore = ArrayList<String>()
            lore.add("§f§l現在の丸石所持数$result")
            lore.add("§f§l現在のCPS")
            lore.add("§f§l現在のCPC")
            itemmeta?.lore = lore
            cobblestone?.itemMeta = itemmeta
        }
        e.isCancelled = true
    }
}