package ArrayListMyWorldCupProgram;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//For Rankings Based on 14 August 2014
public class RewrittenWorldCupWithArrayList {

    public static String[] tier1Teams = {"Germany", "Argentina", "Netherlands", "Colombia", "Belgium"};
    public static String[] tier2Teams = {"Uruguay", "Brazil", "Spain", "Switzerland", "France", "Portugal", "Chile", "Greece", "Italy", "Costa Rica"};
    public static String[] tier3Teams = {"Croatia", "Mexico", "USA", "Bosnia and Herzegovina", "England", "Ecuador", "Ukraine", "Russia", "Algeria", "Ivory Coast"};
    public static String[] tier4Teams = {"Denmark", "Romania", "Scotland", "Venezuela", "Sweden"};
    public static String[] tierOverTeams = {"South Korea", "Japan"};

    public static ArrayList<String> teamTier1List = new ArrayList<String>(5);
    public int arrayPos = 0;
    public static ArrayList<String> teamTier2List = new ArrayList<String>(10);
    public int array2Pos = 0;
    public static ArrayList<String> teamTier3List = new ArrayList<String>(10);
    public int array3Pos = 0;
    public static ArrayList<String> teamTier4List = new ArrayList<String>(5);
    public int array4Pos = 0;
    public static ArrayList<String> teamOverList = new ArrayList<String>(2);

    public static ArrayList<String> dadTeams = new ArrayList<String>(6);
    public static ArrayList<String> mumTeams = new ArrayList<String>(6);
    public static ArrayList<String> alTeams = new ArrayList<String>(6);
    public static ArrayList<String> geoTeams = new ArrayList<String>(6);
    public static ArrayList<String> ozTeams = new ArrayList<String>(6);

    FileWriter fstream = null;

    public static void main(String[] args)
    {
        RewrittenWorldCupWithArrayList worldCupShuffle = new RewrittenWorldCupWithArrayList();

        worldCupShuffle.setupFilePrint();


        worldCupShuffle.setupArrayList(teamTier1List, tier1Teams);
        worldCupShuffle.setupArrayList(teamTier2List, tier2Teams);
        worldCupShuffle.setupArrayList(teamTier3List, tier3Teams);
        worldCupShuffle.setupArrayList(teamTier4List, tier4Teams);
        worldCupShuffle.setupArrayList(teamOverList, tierOverTeams);

        worldCupShuffle.printAllToFile();

        worldCupShuffle.shuffleTeamList();

        worldCupShuffle.setupFilePrintMultiple("TeamsShuffled.txt");

        worldCupShuffle.printAllToFile();

        worldCupShuffle.assignTeams(dadTeams);
        worldCupShuffle.assignTeams(mumTeams);
        worldCupShuffle.assignTeams(alTeams);
        worldCupShuffle.assignTeams(geoTeams);
        worldCupShuffle.assignTeams(ozTeams);

        worldCupShuffle.determineBonusTeams();

        worldCupShuffle.setupFilePrintMultiple("DadsTeams.txt");
        worldCupShuffle.printPersonsTeamsToFile(dadTeams);
        worldCupShuffle.setupFilePrintMultiple("MumsTeams.txt");
        worldCupShuffle.printPersonsTeamsToFile(mumTeams);
        worldCupShuffle.setupFilePrintMultiple("AlexsTeams.txt");
        worldCupShuffle.printPersonsTeamsToFile(alTeams);
        worldCupShuffle.setupFilePrintMultiple("GeorgesTeams.txt");
        worldCupShuffle.printPersonsTeamsToFile(geoTeams);
        worldCupShuffle.setupFilePrintMultiple("OllysTeams.txt");
        worldCupShuffle.printPersonsTeamsToFile(ozTeams);

    }


    public void setupArrayList(ArrayList<String> a, String[] b)
    {
        for (int i = 0; i < b.length; i++)
        {
            a.add(i, b[i]);
        }
    }

    public void shuffleTeamList()
    {
        System.out.println("\nShuffling Teams...\n");
        Collections.shuffle(teamTier1List);
        Collections.shuffle(teamTier2List);
        Collections.shuffle(teamTier3List);
        Collections.shuffle(teamTier4List);
    }


    public void setupFilePrint()
    {
        try
        {
            File theDir = new File(System.getProperty("user.home")+File.separator+"WorldCupTeamsApp");
            if (!theDir.exists())
            {
                theDir.mkdir();
            }
            String name = theDir+File.separator+"Teams.txt";

            System.out.println("Generating File Teams.txt:");
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

    public void setupFilePrintMultiple(String name)
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

    public void assignTeams(ArrayList<String> a)
    {
        a.add(0, teamTier1List.get(arrayPos));
        arrayPos++;
        a.add(1, teamTier2List.get(array2Pos));
        array2Pos++;
        a.add(2, teamTier2List.get(array2Pos));
        array2Pos++;
        a.add(3, teamTier3List.get(array3Pos));
        array3Pos++;
        a.add(4, teamTier3List.get(array3Pos));
        array3Pos++;
        a.add(5, teamTier4List.get(array4Pos));
        array4Pos++;
    }

    public void printPersonsTeamsToFile(ArrayList<String> a)
    {
        int i = 0;
        try
        {
            for (i = 0; i < a.size(); i++)
            {
                fstream.write(a.get(i));
                fstream.write("\r\n");
            }
            fstream.write("\n");
            fstream.close();
        }
        catch(IOException e)
        {
            System.out.println("Error copying to File!");
            System.exit(0);
        }
    }

    public void determineBonusTeams()
    {
        Integer[] randomTeam = {1, 2, 3, 4, 5};
        int lastA = 0;
        for (int i = 0; i < 2; i++)
        {
            Collections.shuffle(Arrays.asList(randomTeam));
            int a = randomTeam[0];
            if (lastA == a)
            {
               System.out.println("Oops same team detected, Reshuffling...");
               a = randomTeam[1];
            }
            if (a == 5)
            {
                System.out.println("Bonus Team For Dad!");
                assignBonusTeams(dadTeams, i);
                lastA = 5;
            }
            if (a == 4)
            {
                System.out.println("Bonus Team For Mum!");
                assignBonusTeams(mumTeams, i);
                lastA = 4;
            }
            if (a == 3)
            {
                System.out.println("Bonus Team For Al!");
                assignBonusTeams(alTeams, i);
                lastA = 3;
            }
            if (a == 2)
            {
                System.out.println("Bonus Team For George!");
                assignBonusTeams(geoTeams, i);
                lastA = 2;
            }
            if (a == 1)
            {
                System.out.println("Bonus Team For Olly!");
                assignBonusTeams(ozTeams, i);
                lastA = 1;
            }
        }
    }

    public void assignBonusTeams(ArrayList<String> a, int i)
    {
        a.add(6, teamOverList.get(i));
    }

    public void printAllToFile() {
        try {
            fstream.write("Top 5 Teams:");
            for (int i = 0; i < teamTier1List.size(); i++) {
                fstream.write("\n");
                fstream.write(teamTier1List.get(i));
            }
            fstream.write("\n\n");
            fstream.write("Rank 6-15 Teams:");
            for (int j = 0; j < teamTier2List.size(); j++) {
                fstream.write("\n");
                fstream.write(teamTier2List.get(j));
            }
            fstream.write("\n\n");
            fstream.write("Rank 16-25 Teams:");
            for (int k = 0; k < teamTier3List.size(); k++) {
                fstream.write("\n");
                fstream.write(teamTier3List.get(k));
            }
            fstream.write("\n\n");
            fstream.write("Rank 26-30 Teams:");
            for (int l = 0; l < teamTier4List.size(); l++) {
                fstream.write("\n");
                fstream.write(teamTier4List.get(l));
            }
            fstream.write("\n\n");
            fstream.write("Lowest Rank Extra Teams:");
            for (int m = 0; m < teamOverList.size(); m++) {
                fstream.write("\n");
                fstream.write(teamOverList.get(m));
            }
            fstream.write("\n");
            fstream.close();
            }
            catch(IOException e)
            {
                System.out.println("Error copying to File!");
                System.exit(0);
            }

    }

}
