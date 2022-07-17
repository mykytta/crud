package com.mykyta.crud;

import com.mykyta.crud.view.SkillsView;

import java.util.Scanner;

public class CrudRunner {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        firstDo();
    }

    private static void firstDo(){
        int c = printFirst();
        choiceChecker(c);
        userWantsToContinue();

    }
    private static int printFirst(){
        System.out.println("You can choose the option: \n" +
                "Press 1 to see all skills \n" +
                "Press 2 to get skill by id \n" +
                "Press 3 to create your own skill \n" +
                "Press 4 to update skill \n" +
                "Press 5 to delete skill by ID" );
        int c = scanner.nextInt();
        return c;
    }

    private static void choiceChecker(int c){
        SkillsView skillsView = new SkillsView();

         if( c <= 0 || c >  5)
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
        }
    }
    private static void userWantsToContinue() {
        System.out.println("Do you want to edit something else? [Y/n]");
        String userWantsToContinue = scanner.next();

        if (userWantsToContinue.toUpperCase().equals("Y")) {
            firstDo();
        } else {
            System.out.println("See you");
        }
    }
}
