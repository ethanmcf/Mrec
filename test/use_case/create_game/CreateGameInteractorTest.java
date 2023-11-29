package use_case.create_game;

import data_access.InMemoryGameDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_game.CreateGamePresenter;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import org.junit.Before;
import org.junit.Test;
import view.RoundView;

import static org.junit.Assert.*;

public class CreateGameInteractorTest {

    private InMemoryGameDataAccessObject gameDataAccessObject;

    @Before
    public void init() {
        gameDataAccessObject = new InMemoryGameDataAccessObject();
    }

    @Test
    public void createGameOutputDataTest() {
        String genre = "Hip-Hop";
        String difficulty = "Hard";
        int lives = 3;
        int rounds = 10;
        int currentScore = 0;

        CreateGameInputData createGameInputData = new CreateGameInputData(genre, difficulty, lives, rounds);
        CreateGameOutputBoundary createGamePresenter = new CreateGameOutputBoundary() {
            @Override
            public void prepareFirstRoundView(CreateGameOutputData outputData) {
                assert outputData.getGenre().equals(genre);
                assert outputData.getDifficulty().equals(difficulty);
                assert outputData.getLives() == lives;
                assert outputData.getRounds() == rounds;
                assert outputData.getCurrentScore() == currentScore;
            }
        };

        CreateGameInputBoundary interactor = new CreateGameInteractor(gameDataAccessObject, createGamePresenter);
        interactor.execute(createGameInputData);
    }

    @Test
    public void gameCreatedTest () {

        String genre = "Hip-Hop";
        String difficulty = "Hard";
        int lives = 3;
        int rounds = 10;
        int currentScore = 0;

        CreateGameInputData createGameInputData = new CreateGameInputData(genre, difficulty, lives, rounds);

        CreateGameOutputBoundary createGamePresenter = new CreateGameOutputBoundary() {
            @Override
            public void prepareFirstRoundView(CreateGameOutputData outputData) {
                String ID = outputData.getGameId();

                assertEquals(gameDataAccessObject.getGameByID(ID).getDifficulty(), difficulty);
                assertEquals(gameDataAccessObject.getGameByID(ID).getGenre(), genre);
                assert gameDataAccessObject.getGameByID(ID).getInitialLives() == lives;
                assert gameDataAccessObject.getGameByID(ID).getCurrentLives() == lives;
                assert gameDataAccessObject.getGameByID(ID).getMaxRounds() == rounds;
                assert gameDataAccessObject.getGameByID(ID).getRounds().size() == 1;
                assert gameDataAccessObject.getGameByID(ID).getScore() == currentScore;
            }
        };

        CreateGameInputBoundary interactor = new CreateGameInteractor(gameDataAccessObject, createGamePresenter);
        interactor.execute(createGameInputData);
    }

    @Test
    public void roundStateTest () {
        String genre = "Hip-Hop";
        String difficulty = "Hard";
        int lives = 3;
        int rounds = 10;
        int currentScore = 0;

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreateGameInputData createGameInputData = new CreateGameInputData(genre, difficulty, lives, rounds);
        RoundViewModel roundViewModel = new RoundViewModel(RoundView.VIEW_NAME);
        CreateGamePresenter createGamePresenter = new CreateGamePresenter(viewManagerModel, roundViewModel);

        CreateGameInputBoundary interactor = new CreateGameInteractor(gameDataAccessObject, createGamePresenter);

        interactor.execute(createGameInputData);

        RoundState state = roundViewModel.getState();

        assert state.getInitialLives() == lives;
        assert state.getCurrentLives() == lives;
        assert state.getMaxRounds() == rounds;
        assertEquals(state.getGenre(), genre);
        assert state.getCurrentRoundNumber() == 1;
        assert state.getScore() == currentScore;
    }
}
