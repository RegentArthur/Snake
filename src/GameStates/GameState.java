package GameStates;

/**
 * The {@code GameState} enum represents the different states of a game.
 *
 * <p>This enum defines states such as {@code PLAY} for when the game is actively being played
 * and {@code MENU} for when the game is in the menu screen. It also maintains a static field
 * to track the current state of the game.
 */
public enum GameState {
    PLAY, MENU;

    public static GameState state = MENU;
}
