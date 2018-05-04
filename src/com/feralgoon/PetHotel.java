package com.feralgoon;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class PetHotel
{
    private Map<Integer, String> pets;
    private String[] splitChoice;

    public void run()
    {
        Scanner input = new Scanner(System.in);
        String choice;

        printHeader();

        do
        {
            printOptions();
            choice = input.nextLine();
            handleSelection(choice);

        } while (!choice.equalsIgnoreCase("EXIT"));

        System.out.println("Exiting program...");
        System.out.println("Goodbye!");
    }

    private void moveUp()
    {
        String tempPetName = pets.get(109);

        for(int i = 109; i > 100; i--)
        {
            pets.put(i, pets.get(i-1));
        }
        pets.put(100,tempPetName);
        System.out.println("Moved all pets up one room.");
    }

    private void moveDown()
    {
        String tempPetName = pets.get(100);

        for(int i = 100; i < 109; i++)
        {
            pets.put(i, pets.get(i+1));
        }
        pets.put(109,tempPetName);
        System.out.println("Moved all pets down one room.");
    }

    private void swap()
    {
        int roomOne         = Integer.parseInt(splitChoice[1]);
        int roomTwo         = Integer.parseInt(splitChoice[2]);
        String petOneName   = pets.get(roomOne);
        String petTwoName   = pets.get(roomTwo);

        if (petOneName == null)
        {
            System.out.println("Room " + roomOne + " is empty. Use move function instead.");
        }
        else if (petTwoName == null)
        {
            System.out.println("Room " + roomTwo + " is empty. Use move function instead.");
        }
        else
        {
            pets.put(roomOne,petTwoName);
            pets.put(roomTwo,petOneName);
            System.out.println("Swapped " + petOneName + " and " + petTwoName + ".");
        }
    }

    private void closeForSeason()
    {
        pets.clear();
        System.out.println("All pets checked out. Closed for season.");
        System.out.println();
    }

    private void occupancy()
    {
        System.out.println("Current Occupancy:");
        System.out.println();
        System.out.println("Room number\t\tPet Name");
        System.out.println("------------------------");
        for(int i = 100; i < 110; i++)
        {
            if (pets.get(i) != null)
            {
                System.out.print(i + "\t\t\t\t" + pets.get(i) + "\n");
            }
            else
            {
                System.out.println(i);
            }
        }
    }

    private void move()
    {
        int roomFrom = Integer.parseInt(splitChoice[1]);
        int roomTo   = Integer.parseInt(splitChoice[2]);

        if (pets.get(roomTo) != null)
        {
            System.out.println("Room already occupied. Cannot move " + pets.get(roomFrom) + " to room " + roomTo + ".");
        }
        else
        {
            pets.put(roomTo,pets.get(roomFrom));
            pets.remove(roomFrom);
            System.out.println("Moved " + pets.get(roomTo) + " to room " + roomTo + ".");
        }
    }

    private void checkOut()
    {
        int roomNumber = Integer.parseInt(splitChoice[1]);
        String petName = pets.remove(roomNumber);

        if (petName != null)
        {
            System.out.println("Goodbye " + petName + "!");
            System.out.println();
        }
        else
        {
            System.out.println("Room already empty!");
            System.out.println();
        }
    }

    private void checkIn()
    {
        int roomNumber;
        String petName;

        petName = splitChoice[1];
        roomNumber = Integer.parseInt(splitChoice[2]);

        if (pets.get(roomNumber) != null)
        {
            System.out.println("Room currently occupied. Please choose another room.");
            return;
        }
        if (roomNumber < 100 || roomNumber > 109)
        {
            System.out.println("Room " + roomNumber + " does not exist. Please select a valid room.");
            return;
        }

        pets.put(roomNumber,petName);
        System.out.println("Checked " + petName + " in to room " + roomNumber + ".");
    }

    private void handleSelection(String choice)
    {
        splitChoice = choice.split(" ");

        try
        {
            if (splitChoice[0].equalsIgnoreCase("CheckIn"))
            {
                checkIn();
            }
            else if (splitChoice[0].equalsIgnoreCase("CheckOut"))
            {
                checkOut();
            }
            else if (splitChoice[0].equalsIgnoreCase("Move"))
            {
                move();
            }
            else if (splitChoice[0].equalsIgnoreCase("Swap"))
            {
                swap();
            }
            else if (splitChoice[0].equalsIgnoreCase("MoveUp"))
            {
                moveUp();
            }
            else if (splitChoice[0].equalsIgnoreCase("MoveDown"))
            {
                moveDown();
            }
            else if (splitChoice[0].equalsIgnoreCase("Occupancy"))
            {
                occupancy();
            }
            else if (splitChoice[0].equalsIgnoreCase("CloseForSeason"))
            {
                closeForSeason();
            }
            else if (splitChoice[0].equalsIgnoreCase("Exit"))
            {
                return;
            }
            else
            {
                throw new IllegalArgumentException();
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e)
        {
            System.out.println("Invalid entry.");
            System.out.println();
        }
    }

    private void printOptions()
    {
        System.out.println();
        System.out.println("Please select from the following: ");
        System.out.println();
        System.out.println("CheckIn <petName> <roomNumber>");
        System.out.println("CheckOut <roomNumber>");
        System.out.println("Move <fromRoomNumber> <toRoomNumber>");
        System.out.println("Swap <firstRoomNumber> <secondRoomNumber>");
        System.out.println("MoveUp");
        System.out.println("MoveDown");
        System.out.println("Occupancy");
        System.out.println("CloseForSeason");
        System.out.println("Exit");
        System.out.println();
    }

    private void printHeader()
    {
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println("| Welcome to the Pet Hotel! |");
        System.out.println("-----------------------------");
        System.out.println();
    }

    private PetHotel()
    {
        pets = new TreeMap<>();
    }

    public static void main(String[] args)
    {
        PetHotel petHotel = new PetHotel();

        petHotel.run();
    }
}
