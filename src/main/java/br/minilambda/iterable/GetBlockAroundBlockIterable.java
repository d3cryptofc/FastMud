package br.minilambda.iterable;

import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class GetBlockAroundBlockIterable implements Iterable<Block> {

    // Space for origin block argument.
    private Block fromBlock;
    // Space for relative offsets argument.
    private int[][] relativeOffsets;
    // Space for expected materials argument.
    private Material[] expectedMaterials;

    public GetBlockAroundBlockIterable(
        Block fromBlock,
        int[][] relativeOffsets,
        Material... expectedMaterials
    ) {
        // Receives the origin block.
        this.fromBlock = fromBlock;
        // Receives the relative offsets.
        this.relativeOffsets = relativeOffsets;
        // Receives the expected materials.
        this.expectedMaterials = expectedMaterials;
    }

    public Iterator<Block> iterator() {
        return new GetBlockAroundBlockIterator(
            this.fromBlock,
            this.relativeOffsets,
            this.expectedMaterials
        );
    }
}
