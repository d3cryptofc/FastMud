package br.minilambda.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class WaterFlowOverDirt implements Listener {
    @EventHandler
    public void onBlockFromToEvent(BlockFromToEvent event) {
        /*
         * When water flows.
         */
        // From block.
        Block block = event.getBlock();
        // To block.
        Block toBlock = event.getToBlock();

        // Exit if block type is different of water.
        if(block.getType() != Material.WATER) {
            return;
        }

        // Getting bottom block.
        Block bottomBlock = toBlock.getRelative(0, -1, 0);

        // Exit if bottom block is different of dirt.
        if(bottomBlock.getType() != Material.DIRT){
            return;
        }

        // Set bottom block type to mud block.
        bottomBlock.setType(Material.MUD);
    }
}
