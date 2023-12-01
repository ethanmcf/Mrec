package data_access.game_data;

import entity.Game;
import entity.LifetimeStatistics;

import java.util.List;

public interface GameDataAccessInterface {
    Game getGameByID(String gameID);
    void save(Game game);
    List<Game> getLoadableGames();
    LifetimeStatistics avgStats();
    boolean gameExists(Game game);
    void clear();
}
