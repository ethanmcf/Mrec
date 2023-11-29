package use_case.load_game;

import entity.Game;

public class LoadGameInteractor implements LoadGameInputBoundary {

    private final LoadGameOutputBoundary presenter;
    private final LoadGameGameDataAccessInterface gameRepository;

    public LoadGameInteractor(LoadGameOutputBoundary presenter, LoadGameGameDataAccessInterface gameRepository) {
        this.presenter = presenter;
        this.gameRepository = gameRepository;
    }

    @Override
    public void execute(LoadGameInputData inputData) {
        Game gameToLoad = gameRepository.getGameByID(inputData.getGameID());
        LoadGameOutputData outputData = new LoadGameOutputData(gameToLoad.getID(),
                gameToLoad.getCurrentRound().getQuestion(),
                gameToLoad.getGenre(),
                gameToLoad.getInitialLives(),
                gameToLoad.getCurrentLives(),
                gameToLoad.getMaxRounds(),
                gameToLoad.getRoundsPlayed());
        presenter.prepareView(outputData);
    }
}
