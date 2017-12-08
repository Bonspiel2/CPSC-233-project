Super Cool Space Shooter XL lite

An arcade-style space shooter game that can be played in text or GUI format. Player must avoid enemies and enemy projectiles in order to collect either money collectables to improve their score, health collectables to increase their health or fire rate collectables to increase the rate of projectiles shot by the player. In the GUI version, there are 5 levels of increasing difficulty in which the number of enemies and enemy projectiles increase with each level. Each level lasts 30 seconds and once a player completes all 5 levels, their score is reported and the existing high score is displayed.


Installing and Running

1. download zip file
2. unzip file into chosen directory
3. navigate to chosen directory
4. move the directory “tests” into the ca directory
5. compile files in src directory using the command: javac ca/ucalgary/*/*.java
6. run game using the command: java ca.ucalgary.main.Main
7. follow instructions to play either the text game or GUI game



Running the Automated Tests

1.check that the “tests” directory is in the ucalgary directory. If not, move it there.
2.compile Unit tests with the command: javac -cp .:ca/ucalgary/tests/junit-4.12.jar:ca/ucalgary/tests/hamcrest-core-1.3.jar ca/ucalgary/*/*.java 
run any of the 7 tests using the command: java -cp .:ca/ucalgary/tests/3.junit-4.12.jar:ca/ucalgary/tests/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ca.ucalgary.tests.<TestName>

Testing the Interface

Test Condition: User is able to establish their own high score and compare it to lower scores the user gets in subsequent levels.
Expected Result: Highest score will be displayed in "Game Over" screen along with latest, lower user's score.
Procedure: 1. Select 2 to play the GUI Game.
	2. Collect enough collectables to get a score greater than 0.
	3. Play until the player dies.
	4. Click the play again button.
	5. Collect a number of collectables so that your score is less than your previous one.
	6. Play until the player dies.
	7. Check that "High Score" indicates previous score and "Score" indicates latest score.

Test Condition: The player collects a health collectable.
Expected Result: The Health Collectable should both increase the player's score and the player's health.
Procedure: 1. Select 2 to play the GUI Game.
	2. Take some damage by colliding with enemy projectiles and enemy ships.
	3. Shoot enemies until a health collectable (red +) is dropped.
	4. Collect the health collectable.
	5. Check that the score counter in the top left is increased by one and the player's (green) health bar below their avatar is slightly increased. 

Test Condition: The player collects a health collectable.
Expected Result: The Health Collectable should both increase the player's score and the player's health.
Procedure: 1. Select 2 to play the GUI Game.
	2. Take some damage by colliding with enemy projectiles and enemy ships.
	3. Shoot enemies until a health collectable (red +) is dropped.
	4. Collect the health collectable.
	5. Check that the score counter in the top left is increased by one and the player's (green) health bar below their avatar is slightly increased. 

Test Condition: The player collects a health collectable.
Expected Result: The Health Collectable should both increase the player's score and the player's health.
Procedure: 1. Select 2 to play the GUI Game.
	2. Take some damage by colliding with enemy projectiles and enemy ships.
	3. Shoot enemies until a health collectable (red +) is dropped.
	4. Collect the health collectable.
	5. Check that the score counter in the top left is increased by one and the player's (green) health bar below their avatar is slightly increased. 

Test Condition: The player collects a health collectable.
Expected Result: The Health Collectable should both increase the player's score and the player's health.
Procedure: 1. Select 2 to play the GUI Game.
	2. Take some damage by colliding with enemy projectiles and enemy ships.
	3. Shoot enemies until a health collectable (red +) is dropped.
	4. Collect the health collectable.
	5. Check that the score counter in the top left is increased by one and the player's (green) health bar below their avatar is slightly increased. 

Test Condition: The player collects a money collectable.
Expected Result: The Money Collectable should increase the player's score.
Procedure: 1. Select 2 to play the GUI Game.
	2. Shoot enemies until a money collectable (yellow $) is dropped.
	3. Collect the money collectable.
	4. Check that the score counter in the top left is increased by one. 

Test Condition: The player collects an Increased Fire Rate collectable.
Expected Result: The Increased Fire Rate Collectable should increase the player's fire rate for 5 seconds and increase the player's score.
Procedure: 1. Select 2 to play the GUI Game.
	2. Shoot enemies until an Increased Fire Rate collectable (orange ^) is dropped.
	3. Collect the Increased Fire Rate collectable.
	4. Check that the score counter in the top left is increased by one.
	5. Once the collectable is collected, count five seconds. The increased fire rate will last only for that duration.  

Test Condition: The player dies and plays the game again.
Expected Result: The game should be reset to level 1 and the current score should be reset to 0.
Procedure: 1. Select 2 to play the GUI Game.
	2. Play until the player dies.
	3. Press the "Play Again" button on the game over screen.
	4. Check that the score counter in the top left has been reset to 0 and that the level counter in the top right has been reset to 1 (the background should also be black again).

Authors

Group 3, CPSC 233

