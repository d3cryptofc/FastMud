package com.github.d3cryptofc.fastmud.listener;

import com.github.d3cryptofc.fastmud.constant.AroundBlockOffsets;
import com.github.d3cryptofc.fastmud.iterable.GetBlockAroundBlockIterable;
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

        // Iterating dirts around the block.
        for (Block block : new GetBlockAroundBlockIterable(
            toBlock,
            AroundBlockOffsets.XZB,
            Material.DIRT
        )) {
            // Set block type to mud block.
            block.setType(Material.MUD);
        }
    }
}
