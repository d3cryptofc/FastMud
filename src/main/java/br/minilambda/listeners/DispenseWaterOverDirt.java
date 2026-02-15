package br.minilambda.listeners;

import br.minilambda.utils.GetDirtAroundBlockGenerator;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Directional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;

public class DispenseWaterOverDirt implements Listener {

    @EventHandler
    public void onBlockDispenseEvent(BlockDispenseEvent event) {
        /*
         * When dispenser dispenses water.
         */
        // Exit if item dispensed is different of water bucket.
        if (event.getItem().getType() != Material.WATER_BUCKET) {
            return;
        }

        // Get dispense block.
        Block dispenseBlock = event.getBlock();
        // Get faced block.
        Block facedBlock = dispenseBlock.getRelative(
            ((Directional) dispenseBlock.getBlockData()).getFacing()
        );

        // To get dirt blocks around.
        GetDirtAroundBlockGenerator dirtBlocks =
            new GetDirtAroundBlockGenerator(facedBlock);
        Block block;

        // Iterating dirt blocks around.
        while ((block = dirtBlocks.next()) != null) {
            // Set block type to mud block.
            block.setType(Material.MUD);
        }
    }
}
