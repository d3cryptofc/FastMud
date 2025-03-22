package br.minilambda.event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Directional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import br.minilambda.utils.GetDirtAroundBlockGenerator;

public class DirtToMudUsingWater implements Listener {
    @EventHandler
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event){
        /*
         * When player uses water bucket.
         */
        // Exit if not using water bucket.
        if(event.getBucket() != Material.WATER_BUCKET) {
            return;
        }

        // Get block occupied by water.
        Block blockOccupiedByWater = event.getBlock();

        // To get dirt blocks around.
        GetDirtAroundBlockGenerator dirtBlocks = new GetDirtAroundBlockGenerator(blockOccupiedByWater);
        Block block;

        // Iterating dirt blocks around.
        while((block = dirtBlocks.next()) != null){
            // Set block type to mud block.
            block.setType(Material.MUD);
        }
    }

    @EventHandler
    public void onWaterFlow(BlockFromToEvent event) {
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

    @EventHandler
    public void onBlockPlaceUnderWater(BlockPlaceEvent event){
        /*
         * When block is placed under water.
         */
        // Getting block placed.
        Block block = event.getBlock();

        // Exit if block is different of dirt.
        if(block.getType() != Material.DIRT){
            return;
        }

        // Getting top block.
        Block topBlock = block.getRelative(0, 1, 0);

        // Exit if top block is water.
        if(topBlock.getType() != Material.WATER){
            return;
        }

        // Set block type to mud.
        block.setType(Material.MUD);
    }

    @EventHandler
    public void onDispenseWater(BlockDispenseEvent event){
        /*
         * When dispenser dispenses water.
         */
        // Exit if item dispensed is different of water bucket.
        if(event.getItem().getType() != Material.WATER_BUCKET){
            return;
        }

        // Get dispense block.
        Block dispenseBlock = event.getBlock();
        // Get faced block.
        Block facedBlock = dispenseBlock.getRelative(
            ((Directional) dispenseBlock.getBlockData()).getFacing()
        );

        // To get dirt blocks around.
        GetDirtAroundBlockGenerator dirtBlocks = new GetDirtAroundBlockGenerator(facedBlock);
        Block block;

        // Iterating dirt blocks around.
        while((block = dirtBlocks.next()) != null){
            // Set block type to mud block.
            block.setType(Material.MUD);
        }
    }

    @EventHandler
    public void onBlockFadeToWater(BlockFadeEvent event){
        /*
         * When block fade to water (ex: ice block).
         */
        // Getting fadded block.
        Block fadedBlock = event.getBlock();
        // Exit if new block state is different of water.
        if(event.getNewState().getType() != Material.WATER){
            return;
        }

        // To get dirt blocks around.
        GetDirtAroundBlockGenerator dirtBlocks = new GetDirtAroundBlockGenerator(fadedBlock);
        Block block;

        // Iterating dirt blocks around.
        while((block = dirtBlocks.next()) != null){
            // Set block type to mud block.
            block.setType(Material.MUD);
        }
    }
}
