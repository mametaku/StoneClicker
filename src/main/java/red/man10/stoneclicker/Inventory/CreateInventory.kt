package red.man10.stoneclicker.Inventory

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

object CreateInventory{
     fun CreateGameMenu(title:String, p: Player): Inventory {
        val inv = Bukkit.createInventory(p,54,title)
        val panel = ItemStack(Material.GRAY_STAINED_GLASS_PANE)
        for (i in 0..53){
            inv.setItem(i,panel)
        }
        val cobblestone = ItemStack(Material.COBBLESTONE)
        val itemmeta = cobblestone.itemMeta
        itemmeta.setDisplayName("あなたの丸石！")
         cobblestone.itemMeta = itemmeta
        inv.setItem(31,cobblestone)
        return inv
    }
}