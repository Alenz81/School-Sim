public class SchoolSim {
  public void runSim() {
    //any system.out.prints are for testing purposes only and they have been turned into comments
    Intro i = new Intro();
    i.startIntro();
    Day1 d = new Day1();
    //d.startDay1();
    AGame a = new AGame();
    //a.startAGame();
    Day2 d2 = new Day2();
    //d2.startDay2();
    Day3 d3 = new Day3();
    //d3.startDay3();
    //d3.end();
    RPS r = new RPS();
    //r.startRPS();
    //r.startGameThread();
    Day35 d35 = new Day35();
    //d35.startDay35();
    //d35.endDay();
    CFlip c = new CFlip();
    //c.startCFlip();
    Day4 d4 = new Day4();
    //d4.startDay4();
    EGame e = new EGame();
    //e.startEGame();
    Boss b = new Boss(200);
    //b.startBoss();
    FGame f = new FGame(200);
    //f.startFGame();
  }
}