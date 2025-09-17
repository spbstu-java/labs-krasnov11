package ru.spbstu.edu.krasnov2.lab1;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var map = new HashMap<String, HeroMove>();

        map.put("walk", new WalkHeroMove());
        map.put("run", new RunHeroMove());
        map.put("fly", new FlyHeroMove());

        var hero = new Hero("superman", 0, 0, map.get("walk"));

        Scanner scanner = new Scanner(System.in);

        var gameProcess = true;
        while (gameProcess) {

            System.out.print("\n > ");

            var cmd = scanner.next();

            switch (cmd){
                case "move":
                    if (!scanner.hasNextInt())
                    {
                        System.out.printf("Bad move command argument: '%s'%n", scanner.next());
                        break;
                    }

                    var x = scanner.nextInt();

                    if (!scanner.hasNextInt())
                    {
                        System.out.printf("Bad move command argument: '%s'%n", scanner.next());
                        break;
                    }

                    var y = scanner.nextInt();
                    hero.move(x, y);
                    break;

                case "set":

                    var name = scanner.next();
                    if (map.containsKey(name))
                    {
                        hero.setMoveStrategy(map.get(name));
                        System.out.printf("Set move strategy: '%s'%n", name);
                    }
                    else
                    {
                        System.out.printf("Move strategy '%s' is not recognized%n", name);
                    }

                    break;

                case "quit":
                case "exit":
                    gameProcess = false;
                    break;

                default:
                    System.out.printf("Unknown command: '%s'%n", cmd);
                    break;
            }
        }

        scanner.close();
    }
}
