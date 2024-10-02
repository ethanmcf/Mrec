# Welcome to the Spotify Bandits ... Mrec

## About

This Java program allows users to play a trivia game designed to test their song recognition abilities. Users can
customize the way each game is played, with game configuration functionality for changing the maximum number of rounds,
the number of lives, the difficulty, and the genre of music to focus on. For each round in a game, users can play a
snippet of audio from a random song and guess what song it is. Depending on the difficulty, users can give their answers
by selecting one of 2 or 4 multiple-choice options, or by typing it in manually. Users can see their score in each game,
which is determined by the correctness of every given answer. After starting at least one game, users can view curated
statistics based on all the games they have played up until that point, such as their average score per game and their
most chosen genre. Lastly, users can leave their current game at any point and load it back up later via the load games
screen. 

## Demo

A short demonstration highlighting the key features of the game is shown below:

https://github.com/rayceramsay/music-trivia/assets/74834442/0206f67b-7fa8-40c5-b63f-2e2095a79b72

## Setup

1. Open a new terminal on your machine and change to the directory in which you would like the repository to be cloned.
Then clone the repository by running:

```bash
git clone https://github.com/rayceramsay/music-trivia.git
```

Alternatively, you can use IntelliJ's `Get From VCS` feature, pasting in the link above as the URL.

2. Open the project in IntelliJ (if it has not already opened).

3. Under the root project folder, right-click on `src` and then select `Mark Directory as -> Sources Root`. Similarly,
right-click on `test` and then select `Mark Directory as -> Test Sources Root`.

4. Ensure that all dependencies listed in `pom.xml` are visible in the `External Libraries` folder. They should load
automatically, but if they don't, you can right-click on the root project folder and then select
`Maven -> Reload project` to tell Maven to download them. Note that we have tested the project using Java version 21,
although some earlier versions may work as well.

6. Right-click on the root project folder in IntelliJ and then select `New -> File`. Name the file `.env` and press
enter. Then, open this file and paste the following template (substituting the dummy values with real ones):

```.env
SQLITE_DB_PATH_PRODUCTION=/absolute/path/to/sqlite/production-database.db
SQLITE_DB_PATH_TEST=/absolute/path/to/sqlite/test-database.db
SPOTIFY_CLIENT_ID=...
SPOTIFY_CLIENT_SECRET=...
```

Note that the directory portion of `SQLITE_DB_PATH_PRODUCTION` and `SQLITE_DB_PATH_TEST` can be any existing folder on
your machine as long as you have read/write privileges. The filename portions of these paths can be anything as long as
they are unique and end in `.db`. Using a path relative to your root project folder instead of an absolute one should
also work if you prefer. We recommend creating a `local` directory in your root project folder and setting the paths as
follows:

```.env
SQLITE_DB_PATH_PRODUCTION=/<root_project_path>/local/music-trivia-production.db  # or `local/music-trivia-production.db`
SQLITE_DB_PATH_TEST=/<root_project_path>/local/music-trivia-test.db  # or `local/music-trivia-test.db`
```

This app uses the Spotify Web API to stream songs and thus requires the client ID and client secret of a registered
Spotify app. Instructions for registering an app and getting the appropriate credentials are on
[Spotify's Web API documentation page](https://developer.spotify.com/documentation/web-api). Once this is done, make
sure to paste the corresponding values into `SPOTIFY_CLIENT_ID` and `SPOTIFY_CLIENT_SECRET`.

6. To run the app, go to `src -> app` from the root project folder and open up `Main.java`. From there you can use
`Run 'Main.java'`, a.k.a. the green play button, to start the program and play the game. Enjoy!
