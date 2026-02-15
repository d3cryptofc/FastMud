package br.minilambda.listeners;

import br.minilambda.utils.GetDirtAroundBlockGenerator;
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
        Block fromBlock = event.getBlock();
        // To block.
        Block toBlock = event.getToBlock();

        // Exit if block type is different of water.
        if (fromBlock.getType() != Material.WATER) {
            return;
        }

        // To get dirt blocks around.
        GetDirtAroundBlockGenerator dirtBlocks =
            new GetDirtAroundBlockGenerator(toBlock);

        Block block;

        // Iterating dirt blocks around.
        while ((block = dirtBlocks.next()) != null) {
            // Set block type to mud block.
            block.setType(Material.MUD);
        }
    }
}
