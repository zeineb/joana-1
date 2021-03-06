/**
 * This file is part of the Joana IFC project. It is developed at the
 * Programming Paradigms Group of the Karlsruhe Institute of Technology.
 *
 * For further details on licensing please read the information at
 * http://joana.ipd.kit.edu or contact the authors.
 */
package conc.cliser.kk;

/** KnockKnockConcurrentTCPServer.java
 *  Auto-generated by Cliser, Mon Jun 19 14:06:55 EDT 2000,
 *   except for the body of the method:
 *     KnockKnockThread.interactWithClient().
 *
 *  See http://cs.calvin.edu/~cliser/ for more details.
 */

import java.util.Random;

import conc.cliser.kk.cliser.ConcurrentTCPServer;
import conc.cliser.kk.cliser.ServiceThread;

class KnockKnockThread extends ServiceThread {
    public KnockKnockThread() {
        super();
    }

    private static String message = "Knock knock";
    
    public void interactWithClient() {
        int randomIndex = myGenerator.nextInt(FIRST_PART.length);
        this.send(message);
        String reply1 = this.receive();

        if (reply1.equalsIgnoreCase("Who's there?")) {
            this.send(FIRST_PART[randomIndex]);
            String reply2 = this.receive();

            if (reply2.equalsIgnoreCase(FIRST_PART[randomIndex] + " who?"))
                this.send(LAST_PART[randomIndex]);
            else
                this.send("***'" + FIRST_PART[randomIndex] + " who?' expected");

        } else
            this.send("'Who's there?' expected");
    }

    private static Random myGenerator = new Random();

    private final static String [] FIRST_PART = {
        "Hatch",
        "Boo",
        "Cash",
        "Cows-go",
        "Doris",
        "Dwayne",
        "Max",
        "Norma Lee",
        "Old lady",
        "Omelette",
        "Police",
        "Stopwatch",
        "Wendy",
        "Who",
        "Wild Tom of the One-Eyed Left-Handed Monkey Boys",
        "Winner",
        "Ya"
    };

    private final static String [] LAST_PART = {
        "Gesundheit!",
        "Don't cry, it's only a joke.",
        "No thanks, I prefer peanuts.",
        "No, cows go 'moo', owls go 'who'!",
        "Doris locked, that's why I had to knock!",
        "Dwayne the bathtub, I'm dwowning!",
        "Max no difference -- open ze door.",
        "Norma lee, I don't knock on strangers' doors, but would you like to buy some encyclopedias?",
        "I didn't know you could yodel!",
        "Omelette smarter than I look!",
        "Police open the door -- it's cold out here!",
        "Stopwatch your doin' and open this door!",
        "Wendy wind blows, de cradle will rock!",
        "Is there an owl in there?",
        "Well how many 'Wild Tom of the One-Eyed Left-Handed Monkey Boys' do you know?",
        "Winner you gonna get this door fixed?",
        "What's all the excitement in there?  Let me in!"
    };
}

class KnockKnockConcurrentTCPServer extends ConcurrentTCPServer {
    public KnockKnockConcurrentTCPServer(int localPort) {
        super("KnockKnock", localPort, new KnockKnockThread());
    }

    public static void main(String [] args) {
        KnockKnockConcurrentTCPServer myServer = null;

        if (args.length == 0)
            myServer = new KnockKnockConcurrentTCPServer(1234);
        else if (args.length == 1)
            myServer = new KnockKnockConcurrentTCPServer( Integer.parseInt(args[0]) );
        else {
            System.err.println("\nUsage: java KnockKnockConcurrentTCPServer [<port>]\n");
            System.exit(1);
        }

        myServer.run();
    }
}

