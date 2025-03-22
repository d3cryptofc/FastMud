package br.minilambda.event;

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

public class DirtToMudUsingWater implements Listener {
    private Integer[][] blockDirections = {
        {0, -1, 0}, // Bottom.
        {-1, 0, 0}, // X negative.
        {+1, 0, 0}, // X positive.
        {0, 0, -1}, // Z negative.
        {0, 0, +1}  // Z positive.
    };

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

        // Iterating block direction weights.
        for(Integer[] weight : this.blockDirections){
            // Getting block.
            Block block = blockOccupiedByWater.getWorld().getBlockAt(
                blockOccupiedByWater.getX() + weight[0],
                blockOccupiedByWater.getY() + weight[1],
                blockOccupiedByWater.getZ() + weight[2]
            );

            // Jump to next loop if block is different of dirt.
            if(block.getType() != Material.DIRT){
                continue;
            }

            // Else, set block type to mud block.
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

        // Iterating block direction weights.
        for(Integer[] weight : this.blockDirections){
            // Getting block.
            Block block = facedBlock.getWorld().getBlockAt(
                facedBlock.getX() + weight[0],
                facedBlock.getY() + weight[1],
                facedBlock.getZ() + weight[2]
            );

            // Jump to next loop if block is different of dirt.
            if(block.getType() != Material.DIRT){
                continue;
            }

            // Else, set block type to mud block.
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

        // Iterating block direction weights.
        for(Integer[] weight : this.blockDirections){
            // Getting block.
            Block block = fadedBlock.getWorld().getBlockAt(
                fadedBlock.getX() + weight[0],
                fadedBlock.getY() + weight[1],
                fadedBlock.getZ() + weight[2]
            );

            // Jump to next loop if block is different of dirt.
            if(block.getType() != Material.DIRT){
                continue;
            }

            // Else, set block type to mud block.
            block.setType(Material.MUD);
        }
    }
}
