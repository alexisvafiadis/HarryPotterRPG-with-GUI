package alexis.isep.harrypotter.Core.Characters.Enemies;

import alexis.isep.harrypotter.Core.Characters.Boss;
import alexis.isep.harrypotter.Core.Game.Game;

public class DoloresUmbridge extends Boss {
    int distractionLevel;

    public DoloresUmbridge(Game game) {
        super(game, 1, 0, 1,null,'D',1,1);
    }

    @Override
    public void spawn() {
        super.spawn();
        distractionLevel = 3;
    }

    public void act() {
        if (canDoSomething()) {
            distractionLevel -= 1;
        }
        else {
            if (distractionLevel < 7) {
                distractionLevel += 1;
            }
        }
        roundTalk();
    }

    public String getName() {
        return "Dolores Umbridge";
    }

    public void roundTalk() {
        switch(distractionLevel) {
            case 0:
                speak("Students, please continue with your exams while I go check on the situation. Don't move.");
                break;
            case 1:
                speak("This noise is highly distracting. I should probably check what is happening.");
                break;
            case 2:
                speak("What even is happening outside? That sound is weird, but I don't know if I should leave you all without any surveillance...");
                break;
            case 3:
                speak("Do you hear this noise coming from outside? It's a bit distracting.");
                break;
            case 4:
                speak("If you don't stop using these ridiculous spells immediately, there will be consequences! Stop this!");
                break;
            case 5:
                speak("What even is this?? I'll have you all expelled if this continues! Who is causing all this trouble?");
                break;
            case 6:
                speak("Potter, I demand that you stop what you are doing right now! This is completely unacceptable.");
                break;
            case 7:
                speak("You've crossed a line, Potter. There will be no more leniency. I'll make you pay for this.");
                break;
        }
    }

    public boolean isDistracted() {
        return (distractionLevel != 0);
    }
}
