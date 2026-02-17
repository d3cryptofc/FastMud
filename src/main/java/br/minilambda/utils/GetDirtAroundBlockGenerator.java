package br.minilambda.utils;

import br.minilambda.Constants;
import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class GetDirtAroundBlockGenerator implements Iterator<Block> {

    private Block fromBlock;
    private int blockOffsetPosition = 0;
    private boolean stopNext = false;

    public GetDirtAroundBlockGenerator(Block fromBlock) {
        this.fromBlock = fromBlock;
    }

    @Override
    public boolean hasNext() {
        // Has next while block offsets position is less than block offsets length.
        return (
            this.blockOffsetPosition < Constants.AROUND_BLOCK_OFFSETS.length &&
            !this.stopNext
        );
    }

    @Override
    public Block next() {
        int[] offsets;
        int offsetX, offsetY, offsetZ;
        Block block;

        while (this.hasNext()) {
            // Get around block offesets by current position.
            offsets = Constants.AROUND_BLOCK_OFFSETS[this.blockOffsetPosition];
            // Unpacking offsets.
            offsetX = offsets[0];
            offsetY = offsets[1];
            offsetZ = offsets[2];

            // Increase current offset position.
            this.blockOffsetPosition++;

            // Getting relative block.
            block = this.fromBlock.getRelative(offsetX, offsetY, offsetZ);
            // Jump to next loop if block is different of dirt.
            if (block.getType() != Material.DIRT) {
                continue;
            }
            // Returns dirt block.
            return block;
        }

        // Stop next.
        this.stopNext = true;
        return null;
    }
}
