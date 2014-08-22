package ArrayListMyWorldCupProgram;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Squall on 19/08/2014.
 */
public class WorldCupShuffle2 {

    public static ArrayList<String> allTeams = new ArrayList<String>(Arrays.asList("Germany", "Argentina", "Netherlands", "Colombia", "Belgium",
    "Uruguay", "Brazil", "Spain", "Switzerland", "France", "Portugal", "Chile", "Greece", "Italy", "Costa Rica",
    "Croatia", "Mexico", "USA", "Bosnia and Herzegovina", "England", "Ecuador", "Ukraine", "Russia", "Algeria", "Ivory Coast",
    "Denmark", "Romania", "Scotland", "Venezuela", "Sweden", "South Korea", "Japan"));

    public static int totalNumberOfTeams = allTeams.size();

    public static ArrayList<String> teamTier1List = new ArrayList<String>(0);
    public static ArrayList<String> teamTier2List = new ArrayList<String>(0);
    public static ArrayList<String> teamTier3List = new ArrayList<String>(0);
    public static ArrayList<String> teamTier4List = new ArrayList<String>(0);
    public static ArrayList<String> teamRemainder = new ArrayList<String>(0);

    public static ArrayList<String> recombinedList = new ArrayList<String>(0);

    public static ArrayList<String> playerNames = new ArrayList<String>(0);

    public static int numberOfPlayers = 0;

    FileWriter fstream = null;

    int tier1 = 0;
    int tier2 = 0;
    int tier3 = 0;
    int tier4 = 0;
    int tier4Remaining = 0;
    int finishedIndex = 0;

    public static void main(String[] args)
    {
        WorldCupShuffle2 worldCupShuffle = new WorldCupShuffle2();

        worldCupShuffle.setUpPlayers();

        worldCupShuffle.smartTierSetup();

        worldCupShuffle.shuffleTeamList();

        worldCupShuffle.recombineList();

        worldCupShuffle.setupFilePrint("TeamsShuffled.txt");

        worldCupShuffle.printAllToFile();

        worldCupShuffle.printAllToScreen();

        worldCupShuffle.printPlayerTeamsToFile();

        System.out.println("You can now see your teams in the files indicated");
        System.out.println("If you have any remainders they can now be assigned:");

        worldCupShuffle.assignAndPrintRemainders();

        System.out.println("All Done!");

    }

    /**
     * Starts by taking an integer numberOfPlayers for the amount of players
     * Then Takes user input to obtain names for numberOfPlayers players.
     * These names are stored as Strings in an ArrayList called playerNames.
     */
    public void setUpPlayers()
    {
        Scanner keyboard = new Scanner(System.in);
        boolean done = false;

        while (!done)
        {
            try
            {
                System.out.println("How many members would you like to Generate teams for?");
                System.out.println("Note There is a Maximum of Ten Players!");
                numberOfPlayers = keyboard.nextInt();
                if (numberOfPlayers > 0 && numberOfPlayers <= 10)
                {
                    done = true;
                }
                if (numberOfPlayers > 10)
                {
                    System.out.println("Error, Too many Players Entered.");
                }
            }
            catch (InputMismatchException e)
            {
                keyboard.nextLine();
                System.out.println("You must enter a valid Integer between 1 and 10");
            }
        }
        System.out.println("You entered " + numberOfPlayers);

        keyboard.reset();

        for (int i = 0; i < numberOfPlayers; i++)
        {
            System.out.println("Family Member " + (i + 1) + " Enter your Name.");
            playerNames.add(i, keyboard.next());
        }
    }

    /**
     * Note: Probably the most room for improvement, ideally more generic numbers for tiers to allow A, more players
     * and B. More teams.
     *
     * Precondition IF Statement 1: numberOfTeams / numberOfPlayers has no remainder, and numberOfTeams/numberOfPlayers
     * is greater than or equal to 4. Filters out player numbers greater than 8 which are divisible by numberOfTeams but cannot
     * have teams in 4 groups divided evenly by that amount of players. For example 16. (Note with the current format
     * 16 is impossible anyway as players are limited to 10, but programmed it like this for future upgrades.
     * Note: This statement deals with the event of numberOfPlayers = 1, 2, 4, 8.
     * Within this statement tier1, tier2, tier3 and tier4 are all assigned the value 8. As the value provides an
     * even and divisible number of teams for all of these.
     *
     * Precondition IF Statement 2: numberOfTeams / numberOfPlayers has a remainder. Condition only met for numbers which
     * cannot divide by numberOfTeams, e.g for numberOfTeams = 32: 3, 5, 6, 7, 9
     * Within this statement tier1, tier2, tier3 and tier4 are first assigned values that make tier1, tier2 and tier3
     * easily usable for the nubmerOfPlayers. And tier4 is given the remainder.
     * The ArrayLists for these teams are then built using the assigned tier values.
     *
     * Result: Teams are taken from the allTeams ArrayList and added to arrayLists with the designated tier values.
     */
    public void smartTierSetup()
    {
        int nCat = 4;
        int j = 0;
        if (totalNumberOfTeams % numberOfPlayers == 0 && totalNumberOfTeams / numberOfPlayers >= 4)
        {
            for (int i = 0; i < totalNumberOfTeams/nCat; i++)
            {
                teamTier1List.add(i, allTeams.get(j));
                j++;
            }
            for (int i = 0; i < totalNumberOfTeams/nCat; i++)
            {
                teamTier2List.add(i, allTeams.get(j));
                j++;
            }
            for (int i = 0; i < totalNumberOfTeams/nCat; i++)
            {
                teamTier3List.add(i, allTeams.get(j));
                j++;
            }
            for (int i = 0; i < totalNumberOfTeams/nCat; i++)
            {
                teamTier4List.add(i, allTeams.get(j));
                j++;
            }
        }
        if (totalNumberOfTeams % numberOfPlayers != 0)
        {

            if (numberOfPlayers == 3)
            {
                tier1 = 9;
                tier2 = 9;
                tier3 = 9;
                tier4 = 5;
            }
            if (numberOfPlayers == 5)
            {
                tier1 = 10;
                tier2 = 10;
                tier3 = 10;
                tier4 = 2;
            }
            if (numberOfPlayers == 6)
            {
                tier1 = 6;
                tier2 = 12;
                tier3 = 12;
                tier4 = 2;
            }
            if (numberOfPlayers == 7)
            {
                tier1 = 7;
                tier2 = 7;
                tier3 = 7;
                tier4 = 11;
            }
            if (numberOfPlayers == 9)
            {
                tier1 = 9;
                tier2 = 9;
                tier3 = 9;
                tier4 = 5;
            }
            if (numberOfPlayers == 10)
            {
                tier1 = 10;
                tier2 = 10;
                tier3 = 10;
                tier4 = 2;
            }
            for (int i = 0; i < tier1; i++)
            {
                teamTier1List.add(i, allTeams.get(j));
                j++;
            }
            for (int i = 0; i < tier2; i++)
            {
                teamTier2List.add(i, allTeams.get(j));
                j++;
            }
            for (int i = 0; i < tier3; i++)
            {
                teamTier3List.add(i, allTeams.get(j));
                j++;
            }
            for (int i = 0; i < tier4; i++)
            {
                teamTier4List.add(i, allTeams.get(j));
                j++;
            }
        }
    }

    /**
     * Simply shuffles all the lists created by smartTierSetup. Note done this way to maintain a 4 'tiered' divide of team quality
     * so that one player does not get all the top teams.
     */
    public void shuffleTeamList()
    {
        System.out.println("\nShuffling Teams...\n");
        Collections.shuffle(teamTier1List);
        Collections.shuffle(teamTier2List);
        Collections.shuffle(teamTier3List);
        Collections.shuffle(teamTier4List);
    }

    /**
     * Recombines the shuffled tierLists into one full list again
     */
    public void recombineList()
    {
        int i;
        int j = 0;
        for (i = 0; i < teamTier1List.size(); i++)
        {
            recombinedList.add(i, teamTier1List.get(i));
            j++;
        }
        for (i = 0; i < teamTier2List.size(); i++)
        {
            recombinedList.add(j, teamTier2List.get(i));
            j++;
        }
        for (i = 0; i < teamTier3List.size(); i++)
        {
            recombinedList.add(j, teamTier3List.get(i));
            j++;
        }
        for (i = 0; i < teamTier4List.size(); i++)
        {
            recombinedList.add(j, teamTier4List.get(i));
            j++;
        }
    }

    /**
     * Sets up a FileWriter that creates the directory (if it doesnt exist)
     * C:/Users/WorldCupTeamsApp and names
     * files in that directory based on the name param.
     * @param name Takes a string argument which acts as the file name.
     */
    public void setupFilePrint(String name)
    {
        try
        {
            File theDir = new File(System.getProperty("user.home")+File.separator+"WorldCupTeamsApp");
            if (!theDir.exists())
            {
                theDir.mkdir();
            }
            System.out.println("Generating File " + name + ":");
            name = theDir+File.separator+name;
            System.out.print("File Created At ");
            System.out.println(name);
            fstream = new FileWriter(name);
        }
        catch (IOException e)
        {
            System.out.println("Error Opening File!");
            System.exit(0);
        }
    }

    /**
     * Prints player teams to files
     *
     * While loop loops through numberOfPlayers, for each player runs the following:
     * for loops count number of teams to assign from {@link #recombinedList} (based on values set by smartTierSetup())
     * Note the three for loops handle tier1, tier2 and tier3 teams only.
     * Two if statements following print tier 4 to file, for tier4 == numberOfPlayers simply prints as normal
     * For tier4 &gt; numberOfPlayers (which obviously must leave a remainder) prints only up to playerNumber
     *
     * Finally after looping no remainder, and any remainders for the tier4 &gt; numberOfPlayers cases the remaining
     * teams are placed into an ArrayList to be used by {@link #assignAndPrintRemainders()}
     */
    public void printPlayerTeamsToFile()
    {
        int i = 0;
        int j = 0;
        int k = teamTier1List.size();
        int l = teamTier1List.size() + teamTier2List.size();
        int m = teamTier1List.size() + teamTier2List.size() + teamTier3List.size();
        //Sets remainder for the tier4 > numberOfPlayers cases.
        tier4Remaining = tier4 % numberOfPlayers;
        finishedIndex = m;

        try
        {
            while (i < numberOfPlayers)
            {
                setupFilePrint(playerNames.get(i) + "Teams.txt");
                fstream.write(playerNames.get(i) + " Teams:\r\n");
                for (int z = 0; z < (teamTier1List.size() / numberOfPlayers); z++)
                {
                    fstream.write(recombinedList.get(j));
                    fstream.write("\r\n");
                    j++;
                }
                for (int z = 0; z < (teamTier2List.size() / numberOfPlayers); z++)
                {
                    fstream.write(recombinedList.get(k));
                    fstream.write("\r\n");
                    k++;
                }
                for (int z = 0; z < (teamTier3List.size() / numberOfPlayers); z++)
                {
                    fstream.write(recombinedList.get(l));
                    fstream.write("\r\n");
                    l++;
                }
                if (tier4 > numberOfPlayers)
                {
                    System.out.println(tier4Remaining + " teams must be Remain");
                    for (int z = 0; z < (teamTier4List.size() - tier4Remaining) / numberOfPlayers; z++)
                    {
                        fstream.write(recombinedList.get(m));
                        fstream.write("\r\n");
                        m++;
                    }
                    //Sets the final index set (only needed by tier4 > numberOfPlayers as its the only case that leaves
                    //a variable remainder
                    finishedIndex = m;
                }
                //finishedIndex not needed for this case.
                if ((tier4 % numberOfPlayers) == 0)
                {
                    for (int z = 0; z < teamTier4List.size() / numberOfPlayers; z++)
                    {
                        fstream.write(recombinedList.get(m));
                        fstream.write("\r\n");
                        m++;
                    }
                }
                fstream.close();
                i++;
            }
            if (tier4Remaining > 0)
            {
                System.out.println(tier4 + " teams Remain");
                for (int z = 0; z < tier4Remaining; z++)
                {
                    teamRemainder.add(z, recombinedList.get(m));
                    m++;
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error Writing to File!");
            System.exit(0);
        }
    }

    /**
     * Distributes the remaining teams based on user selection.
     * Using data within the {@link #teamRemainder} arrayList
     * As determined within the {@link #printPlayerTeamsToFile()} method.
      */
    public void assignAndPrintRemainders()
    {
        if (tier4Remaining > 0)
        {
            Scanner keyboard = new Scanner(System.in);

            int i = 0;
            int errorI = 0;
            boolean done = false;
            setupFilePrint("RemainingTeamAssignments.txt");

            System.out.println("You can see Your teams in the directories displayed!");
            System.out.println("There are a few teams remaining which can be assigned how you wish");
            System.out.println("For example to the person/s with the weakest Teams");
            while (!done)
            {
                try
                {
                    for (i = errorI; i < tier4Remaining; i++)
                    {
                        System.out.println("Please enter which player to give Reamining Team " + (i+1) + " To.");
                        int playerFor = keyboard.nextInt();
                        fstream.write(recombinedList.get(finishedIndex) + " is a bonus team for " + (playerNames.get(playerFor - 1)));
                        fstream.write("\r\n");
                        errorI++;
                        finishedIndex++;
                    }
                    fstream.close();
                    done = true;
                }
                catch(IOException e)
                {
                    System.out.println("File could not be Created.");
                    System.exit(0);
                }
                catch(InputMismatchException e)
                {
                    errorI = i;
                    keyboard.nextLine();
                    System.out.println("You must enter a valid Integer between 1 and the number of Players you Entered");
                }
            }
        }
    }

    public void printAllToScreen()
    {
        for (int i = 0; i < recombinedList.size(); i++)
        {
            System.out.println(recombinedList.get(i));
        }
    }

    public void printAllToFile()
    {
        try
        {
            fstream.write("Shuffled Teams");
            for (int i = 0; i < recombinedList.size(); i++)
            {
                fstream.write("\r\n");
                fstream.write(recombinedList.get(i));
            }
            fstream.close();
        }
        catch (IOException e)
        {
            System.out.println("Error Copying to File");
            System.exit(0);
        }
    }
}
