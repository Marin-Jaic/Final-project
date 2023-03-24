package hr.fer.projektr.game.entities;

import hr.fer.projektr.game.GameState;

/**
 * This Enum holds all cactus types.
 * Each cactus type is defined by its height and width.
 */
public enum CactusType {
    /**
     * The most standard cactus, declared by the ISO
     */
    STANDARD(GameState.STANDARD_CACTUS_WIDTH, GameState.STANDARD_CACTUS_HEIGHT, EntityType.CACTUS_STANDARD),

    SMALL(GameState.SMALL_CACTUS_WIDTH, GameState.SMALL_CACTUS_HEIGHT, EntityType.CACTUS_SMALL),

    LARGE(GameState.LARGE_CACTUS_WIDTH, GameState.LARGE_CACTUS_HEIGHT, EntityType.CACTUS_LARGE),
    LONG(GameState.LONG_CACTUS_WIDTH, GameState.LONG_CACTUS_HEIGHT, EntityType.CACTUS_LONG);

    final double width;
    final double height;
    final EntityType entityType;

    CactusType(double width, double height, EntityType entityType) {
        this.width = width;
        this.height = height;
        this.entityType = entityType;
    }
}
