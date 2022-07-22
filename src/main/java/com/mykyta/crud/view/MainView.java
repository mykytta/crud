package com.mykyta.crud.view;

import com.mykyta.crud.model.Specialty;

import java.util.Scanner;

public class MainView {

    private final Scanner scanner = new Scanner(System.in);

    public void mainMenu() {
        int c = printFirst();
        choiceChecker(c);
        userWantsToContinue();

    }

    private int printFirst() {
        System.out.println("You can choose the option: \n" +
                "Press 1 to see all skills \n" +
                "Press 2 to get skill by id \n" +
                "Press 3 to create your own skill \n" +
                "Press 4 to update skill \n" +
                "Press 5 to delete skill by ID \n" +
                "Press 6 to see all specialties \n" +
                "Press 7 to get specialty by id \n" +
                "Press 8 to create your own specialty \n" +
                "Press 9 to update specialty \n" +
                "Press 10 to delete specialty by ID \n" +
                "Press 11 to see all developers \n" +
                "Press 12 to get developer by id \n" +
                "Press 13 to create your own developer \n" +
                "Press 14 to update developer \n" +
                "Press 15 to delete developer by ID");
        int c = scanner.nextInt();
        return c;
    }

    private void choiceChecker(int c) {
        SkillsView skillsView = new SkillsView();
        SpecialtiesView specialtiesView = new SpecialtiesView();
        DevelopersView developersView = new DevelopersView();

        if (c <= 0 || c > 15)
            System.out.println("Please enter the right number");

        switch (c) {
            case 1:
                skillsView.getAllSkills();
                break;
            case 2:
                skillsView.getSkillById();
                break;
            case 3:
                skillsView.createSkill();
                break;
            case 4:
                skillsView.updateSkill();
                break;
            case 5:
                skillsView.deleteSkillById();
                break;
            case 6:
                specialtiesView.getAllSpecialties();
                break;
            case 7:
                specialtiesView.getSpecialtyById();
                break;
            case 8:
                specialtiesView.createSpecialty();
                break;
            case 9:
                specialtiesView.updateSpecialty();
                break;
            case 10:
                specialtiesView.deleteSpecialtyById();
                break;
            case 11:
                developersView.getAllDevelopers();
                break;
            case 12:
                developersView.getDeveloperById();
                break;
            case 13:
                developersView.createDeveloper();
                break;
            case 14:
                developersView.updateDeveloper();
                break;
            case 15:
                developersView.deleteDeveloperById();
                break;
        }
    }

    private void userWantsToContinue() {
        System.out.println("Do you want to edit something else? [Y/n]");
        String userWantsToContinue = scanner.next();

        if (userWantsToContinue.toUpperCase().equals("Y")) {
            mainMenu();
        } else {
            System.out.println("See you");
        }
    }
}
